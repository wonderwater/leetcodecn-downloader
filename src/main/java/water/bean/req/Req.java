package water.bean.req;

import lombok.Data;

import java.util.Map;

@Data
public class Req {
    protected String operationName;
    protected Map<String, Object> variables;
    protected String query;
}
