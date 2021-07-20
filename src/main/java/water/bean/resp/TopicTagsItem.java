package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TopicTagsItem{

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("name")
	private String name;

	@JsonProperty("translatedName")
	private String translatedName;

	@JsonProperty("slug")
	private String slug;

}