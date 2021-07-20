package water.helper;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import water.bean.req.MySubmissionDetailReq;
import water.bean.req.ProgressSubmissionsReq;
import water.bean.req.QuestionDataReq;
import water.bean.resp.Question;
import water.bean.resp.SubmissionDetail;
import water.bean.resp.SubmissionList;
import water.feign.LeetCodeFeign;

public class FeignHelper {

    public final static String URL = System.getProperty("leetcode", "https://leetcode-cn.com");

    private static final LeetCodeFeign leetCodeFeign = Feign.builder()
        .encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder())
        .requestInterceptor(template -> template.header("cookie", System.getenv("cookie")))
        .logLevel(Logger.Level.FULL)
        .logger(new Slf4jLogger())
        .target(LeetCodeFeign.class, URL);

    public static Question call(QuestionDataReq question){
        return leetCodeFeign.call(question).getData().getQuestion();
    }
    public static SubmissionDetail call(MySubmissionDetailReq question){
        return leetCodeFeign.call(question).getData().getSubmissionDetail();
    }
    public static SubmissionList call(ProgressSubmissionsReq question){
        return leetCodeFeign.call(question).getData().getSubmissionList();
    }
}
