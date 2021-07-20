package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Solution{

	@JsonProperty("canSeeDetail")
	private Boolean canSeeDetail;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("id")
	private String id;
}