package water.bean.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
@lombok.Data
public class Rsp{

	@JsonProperty("data")
	private Data data;

}