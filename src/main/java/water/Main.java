package water;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.session.SqlSession;
import water.bean.req.MySubmissionDetailReq;
import water.bean.req.ProgressSubmissionsReq;
import water.bean.req.QuestionDataReq;
import water.bean.resp.Question;
import water.bean.resp.SubmissionDetail;
import water.bean.resp.SubmissionList;
import water.dao.QuestionDto;
import water.dao.SubmissionDetailDto;
import water.dao.SubmissionDto;
import water.helper.FeignHelper;
import water.helper.SqliteHelper;
import water.mapper.InitMapper;
import water.mapper.QuestionMapper;
import water.mapper.SubmissionDetailMapper;
import water.mapper.SubmissionMapper;
import water.util.CopyMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {

    static String HELP = "## Usage\n\n " +
            "```bash\n" +
            "export cookie=\"...\" ## cookie from logon leetcode-cn\n" +
            "java -jar leetcodecn-downloader-1.0-SNAPSHOT-jar-with-dependencies.jar\n" +
            "```\n" +
            "Generate db file(sqlite)，and README.md file.\n" +
            "\n\n\n";

    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
    }

    public static void init() {
        try(SqlSession sqlSession = SqliteHelper.getInstance().openSession()) {

            InitMapper mapper = sqlSession.getMapper(InitMapper.class);
            mapper.createQuestion();
            mapper.createSubmission();
            mapper.createSubmissionDetail();

            sqlSession.commit();
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        downloadSubmssions();
        downloadSubmssionDetail();
        downloadQuestion();
        genMkSummary();
    }

    public static void genMkSummary() throws IOException {

        List<QuestionDto> questionDtos;
        Map<String, List<SubmissionDetailDto>> map;
        try(SqlSession sqlSession = SqliteHelper.getInstance().openSession()) {
            QuestionMapper questionMapper = sqlSession.getMapper(QuestionMapper.class);
            SubmissionDetailMapper submissionDetailMapper = sqlSession.getMapper(SubmissionDetailMapper.class);

            questionDtos = questionMapper.selectList(new QueryWrapper<>());
            map = submissionDetailMapper.selectList(new QueryWrapper<SubmissionDetailDto>().lambda()
                    .eq(SubmissionDetailDto::getStatusDisplay, "Accepted")
                    .orderByDesc(SubmissionDetailDto::getTimestamp))
                    .stream().collect(Collectors.groupingBy(SubmissionDetailDto::getTitleSlug));
        }

        Map<Boolean, List<QuestionDto>> qs = questionDtos.stream().collect(Collectors.groupingBy(x -> x.getQuestionFrontendId().matches("\\d+")));
        questionDtos = qs.get(true);
        questionDtos.sort(Comparator.comparingInt(x -> Integer.parseInt(x.getQuestionFrontendId())));
        qs.get(false).sort(Comparator.comparing(QuestionDto::getQuestionFrontendId));
        questionDtos.addAll(qs.get(false));

        List<List<String>> prints = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        prints.add(Arrays.asList("#", "title", "solution", "topic", "difficulty"));
        for (QuestionDto q : questionDtos) {
            List<SubmissionDetailDto> list = map.getOrDefault(q.getTitleSlug(), Collections.emptyList());
            Map<String, SubmissionDetailDto> dtoMap = list.stream().collect(Collectors.toMap(SubmissionDetailDto::getLang, Function.identity(), (x, y) -> x));
            dtoMap.values().forEach(x -> builder.append(String.format("### %s %s%n%n> submit time: %s%n%n```%s%n%s%n```%n%n",
                    q.getTitleSlug().replaceAll("-", " "), x.getLang(),new Date(Long.parseLong(x.getTimestamp()) * 1000).toString(),
                    x.getLang(), x.getCode())));
            List<String> langs = dtoMap.keySet().stream().map(x -> String.format("[%s](#%s)", x, q.getTitleSlug() + "-" + x)).collect(Collectors.toList());
            prints.add(Arrays.asList(q.getQuestionFrontendId(), String.format("[%s](%s/problems/%s)", q.getTitle(), FeignHelper.URL, q.getTitleSlug()),
                    String.join(",", langs), q.getTopicTags(), q.getDifficulty()));
        }


        // fill markdown file
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("README.md"))) {
            List<String> title = prints.get(0);
            bufferedWriter.write(HELP);
            bufferedWriter.newLine();
            bufferedWriter.write("## My Leetcode-cn Summary");
            bufferedWriter.newLine();
            bufferedWriter.write(String.join("|", title));
            bufferedWriter.newLine();
            bufferedWriter.write(title.stream().map(x -> "--").collect(Collectors.joining("|")));
            for (int i = 1; i < prints.size(); i++) {
                bufferedWriter.newLine();
                bufferedWriter.write(String.join("|", prints.get(i)));
            }
            bufferedWriter.newLine();
            bufferedWriter.write(builder.toString());
            bufferedWriter.flush();
        }
    }

    public static void downloadQuestion() throws InterruptedException {

        try(SqlSession sqlSession = SqliteHelper.getInstance().openSession()) {
            QuestionMapper questionMapper = sqlSession.getMapper(QuestionMapper.class);
            SubmissionDetailMapper submissionDetailMapper = sqlSession.getMapper(SubmissionDetailMapper.class);
            List<String> list = submissionDetailMapper.selectList(new QueryWrapper<>()).stream().map(SubmissionDetailDto::getTitleSlug).collect(Collectors.toList());
            for (String s : list) {
                if (questionMapper.selectById(s) == null) {
                    Question question;
                    while((question = FeignHelper.call(new QuestionDataReq(s))) == null){
                        // leetcode anti-crawler, just wait
                        TimeUnit.SECONDS.sleep(5);
                    }
                    questionMapper.insert(CopyMapper.MAPPER.map(question));
                    sqlSession.commit();
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200) + 250);
                }
            }
        }
    }


    public static void downloadSubmssionDetail() throws InterruptedException {

        try(SqlSession sqlSession = SqliteHelper.getInstance().openSession()) {
            SubmissionMapper submissionMapper = sqlSession.getMapper(SubmissionMapper.class);
            SubmissionDetailMapper detailMapper = sqlSession.getMapper(SubmissionDetailMapper.class);
            List<SubmissionDto> submissionDtos = submissionMapper.selectList(new QueryWrapper<SubmissionDto>().lambda().eq(SubmissionDto::getFetch, 0));
            for (SubmissionDto submissionDto : submissionDtos) {
                if (detailMapper.selectById(submissionDto.getId()) == null) {
                    SubmissionDetail submissionDetail;
                    while((submissionDetail = FeignHelper.call(new MySubmissionDetailReq(submissionDto.getId()))) == null){
                        // leetcode anti-crawler, just wait
                        TimeUnit.SECONDS.sleep(5);
                    }
                    detailMapper.insert(CopyMapper.MAPPER.map(submissionDetail));
                }
                submissionDto.setFetch(1);
                submissionMapper.updateById(submissionDto);
                sqlSession.commit();
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200) + 250);
            }
        }
    }

    public static void downloadSubmssions(){

        int start = 0;
        int limit = 40;
        try(SqlSession sqlSession = SqliteHelper.getInstance().openSession()) {
            SubmissionMapper mapper = sqlSession.getMapper(SubmissionMapper.class);
            CopyMapper cm = CopyMapper.MAPPER;
            List<String> ids = mapper.selectList(new QueryWrapper<SubmissionDto>().lambda().select(SubmissionDto::getId)).stream().map(SubmissionDto::getId).collect(Collectors.toList());
            while (true) {
                SubmissionList list = FeignHelper.call(new ProgressSubmissionsReq(start, limit));
                list.getSubmissions().stream().map(cm::map).filter(x -> !ids.contains(x.getId())).forEach(mapper::insert);
                sqlSession.commit();
                if (!list.getHasNext()) {
                    break;
                }
                start += limit;
            }
        }
    }
}
