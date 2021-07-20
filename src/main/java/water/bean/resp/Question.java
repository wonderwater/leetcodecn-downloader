package water.bean.resp;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Question{

	@JsonProperty("sampleTestCase")
	private String sampleTestCase;

	@JsonProperty("enableRunCode")
	private Boolean enableRunCode;

	@JsonProperty("questionId")
	private String questionId;

	@JsonProperty("codeSnippets")
	private List<CodeSnippetsItem> codeSnippets;

	@JsonProperty("translatedTitle")
	private String translatedTitle;

	@JsonProperty("book")
	private Book book;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("title")
	private String title;

	@JsonProperty("content")
	private String content;

	@JsonProperty("topicTags")
	private List<TopicTagsItem> topicTags;

	@JsonProperty("judgerAvailable")
	private Boolean judgerAvailable;

	@JsonProperty("titleSlug")
	private String titleSlug;

	@JsonProperty("metaData")
	private String metaData;

	@JsonProperty("isSubscribed")
	private Boolean isSubscribed;

	@JsonProperty("questionFrontendId")
	private String questionFrontendId;

	@JsonProperty("ugcQuestionId")
	private String ugcQuestionId;

	@JsonProperty("solution")
	private Solution solution;

	@JsonProperty("similarQuestions")
	private String similarQuestions;

	@JsonProperty("stats")
	private String stats;

	@JsonProperty("exampleTestcases")
	private String exampleTestcases;

	@JsonProperty("envInfo")
	private String envInfo;

	@JsonProperty("judgeType")
	private String judgeType;

	@JsonProperty("mysqlSchemas")
	private List<String> mysqlSchemas;

	@JsonProperty("likes")
	private Integer likes;

	@JsonProperty("langToValidPlayground")
	private String langToValidPlayground;

	@JsonProperty("boundTopicId")
	private Integer boundTopicId;

	@JsonProperty("dailyRecordStatus")
	private String dailyRecordStatus;

	@JsonProperty("hints")
	private List<String> hints;

	@JsonProperty("categoryTitle")
	private String categoryTitle;

	@JsonProperty("isLiked")
	private String isLiked;

	@JsonProperty("isPaidOnly")
	private Boolean isPaidOnly;

	@JsonProperty("dislikes")
	private Integer dislikes;

	@JsonProperty("isDailyQuestion")
	private Boolean isDailyQuestion;

	@JsonProperty("difficulty")
	private String difficulty;

	@JsonProperty("style")
	private String style;

	@JsonProperty("contributors")
	private List<Contributor> contributors;

	@JsonProperty("translatedContent")
	private String translatedContent;

	@JsonProperty("editorType")
	private String editorType;

	@JsonProperty("companyTagStats")
	private String companyTagStats;

	@JsonProperty("status")
	private String status;
}