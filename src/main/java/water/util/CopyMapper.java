package water.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import water.bean.resp.Question;
import water.bean.resp.SubmissionDetail;
import water.bean.resp.SubmissionsItem;
import water.dao.QuestionDto;
import water.dao.SubmissionDto;
import water.dao.SubmissionDetailDto;

@Mapper
public interface CopyMapper {

    CopyMapper MAPPER = Mappers.getMapper( CopyMapper.class );

    @Mapping(target = "id", source = "id")
    SubmissionDto map(SubmissionsItem submission);

    @Mappings({
            @Mapping(target = "questionId", source = "question.questionId"),
            @Mapping(target = "title", source = "question.title"),
            @Mapping(target = "titleSlug", source = "question.titleSlug"),
            @Mapping(target = "translatedTitle", source = "question.translatedTitle"),
            @Mapping(target = "submissionComment", expression = "java(java.util.Optional.ofNullable(submission.getSubmissionComment()).map(x -> x.getFlagType() + x.getComment()).orElse(\"\"))")
    })
    SubmissionDetailDto map(SubmissionDetail submission);


    @Mappings({
            @Mapping(target = "titleSlug", source = "titleSlug"),
            @Mapping(target = "codeSnippets", expression = "java(question.getCodeSnippets().stream().map(water.bean.resp.CodeSnippetsItem::getLangSlug).collect(java.util.stream.Collectors.joining(\",\")))"),
            @Mapping(target = "hints", expression = "java(question.getHints().stream().collect(java.util.stream.Collectors.joining(\"\\n\")))"),
            @Mapping(target = "mysqlSchemas", expression = "java(question.getMysqlSchemas().stream().collect(java.util.stream.Collectors.joining()))"),
            @Mapping(target = "topicTags", expression = "java(question.getTopicTags().stream().map(water.bean.resp.TopicTagsItem::getSlug).collect(java.util.stream.Collectors.joining(\",\")))"),
            @Mapping(target = "book", expression = "java(java.util.Optional.ofNullable(question.getBook()).map(water.bean.resp.Book::getBookName).orElse(\"\"))"),
            @Mapping(target = "solution", expression = "java(java.util.Optional.ofNullable(question.getSolution()).map(water.bean.resp.Solution::getId).orElse(\"\"))"),
            @Mapping(target = "contributors", expression = "java(question.getContributors().stream().map(water.bean.resp.Contributor::getUsername).collect(java.util.stream.Collectors.joining(\",\")))")
    })
    QuestionDto map(Question question);
}
