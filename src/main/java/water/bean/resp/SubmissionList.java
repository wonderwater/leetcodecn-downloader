package water.bean.resp;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubmissionList{

	@JsonProperty("submissions")
	private List<SubmissionsItem> submissions;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("lastKey")
	private String lastKey;

	@JsonProperty("hasNext")
	private Boolean hasNext;
}