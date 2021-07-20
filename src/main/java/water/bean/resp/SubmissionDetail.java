package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubmissionDetail{

	@JsonProperty("code")
	private String code;

	@JsonProperty("memory")
	private String memory;

	@JsonProperty("passedTestCaseCnt")
	private Integer passedTestCaseCnt;

	@JsonProperty("totalTestCaseCnt")
	private Integer totalTestCaseCnt;

	@JsonProperty("question")
	private Question question;

	@JsonProperty("statusDisplay")
	private String statusDisplay;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("runtime")
	private String runtime;

	@JsonProperty("submissionComment")
	private SubmissionComment submissionComment;

	@JsonProperty("sourceUrl")
	private String sourceUrl;

	@JsonProperty("rawMemory")
	private String rawMemory;

	@JsonProperty("id")
	private String id;

	@JsonProperty("lang")
	private String lang;

	@JsonProperty("outputDetail")
	private OutputDetail outputDetail;

	@JsonProperty("timestamp")
	private Integer timestamp;
}