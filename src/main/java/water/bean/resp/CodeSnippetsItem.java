package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CodeSnippetsItem{

	@JsonProperty("code")
	private String code;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("langSlug")
	private String langSlug;

	@JsonProperty("lang")
	private String lang;

}