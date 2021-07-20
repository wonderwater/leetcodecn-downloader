package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubmissionsItem{

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("runtime")
	private String runtime;

	@JsonProperty("id")
	private String id;

	@JsonProperty("lang")
	private String lang;

	@JsonProperty("url")
	private String url;

	@JsonProperty("timestamp")
	private String timestamp;

}