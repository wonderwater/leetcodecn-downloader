package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Book{

	@JsonProperty("pressImgUrl")
	private String pressImgUrl;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("id")
	private String id;

	@JsonProperty("source")
	private String source;

	@JsonProperty("shortDescription")
	private String shortDescription;

	@JsonProperty("pressName")
	private String pressName;

	@JsonProperty("fullDescription")
	private String fullDescription;

	@JsonProperty("productUrl")
	private String productUrl;

	@JsonProperty("bookName")
	private String bookName;

	@JsonProperty("bookImgUrl")
	private String bookImgUrl;
}