package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contributor{

	@JsonProperty("profileUrl")
	private String profileUrl;

	@JsonProperty("avatarUrl")
	private String avatarUrl;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("username")
	private String username;
}