package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutputDetail{

	@JsonProperty("runtimeError")
	private String runtimeError;

	@JsonProperty("input")
	private String input;

	@JsonProperty("__typename")
	private String typename;

	@JsonProperty("lastTestcase")
	private String lastTestcase;

	@JsonProperty("expectedOutput")
	private String expectedOutput;

	@JsonProperty("compileError")
	private String compileError;

	@JsonProperty("codeOutput")
	private String codeOutput;
}