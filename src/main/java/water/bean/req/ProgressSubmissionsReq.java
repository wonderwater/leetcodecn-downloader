package water.bean.req;

import java.util.HashMap;
import java.util.Map;

public class ProgressSubmissionsReq extends Req{

    private final int offset;
    private final int limit;

    public ProgressSubmissionsReq(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public String getOperationName() {
        return "progressSubmissions";
    }

    @Override
    public Map<String, Object> getVariables() {
        Map<String, Object> map = new HashMap<>();
        map.put("offset", offset);
        map.put("limit", limit);
        return map;
    }

    @Override
    public String getQuery() {
        return "query progressSubmissions($offset: Int, $limit: Int, $lastKey: String, $questionSlug: String) {\n  submissionList(offset: $offset, limit: $limit, lastKey: $lastKey, questionSlug: $questionSlug) {\n    lastKey\n    hasNext\n    submissions {\n      id\n      timestamp\n      url\n      lang\n      runtime\n      __typename\n    }\n    __typename\n  }\n}\n";
    }
}
