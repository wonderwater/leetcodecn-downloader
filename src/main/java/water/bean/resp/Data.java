package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Data{

	@JsonProperty("question")
	private Question question;

	@JsonProperty("submissionDetail")
	private SubmissionDetail submissionDetail;

	@JsonProperty("submissionList")
	private SubmissionList submissionList;

}