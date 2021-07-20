package water.bean.req;

import java.util.HashMap;
import java.util.Map;

public class MySubmissionDetailReq extends Req{

    private final String id;

    public MySubmissionDetailReq(String id){
        this.id = id;
    }

    @Override
    public String getOperationName() {
        return "mySubmissionDetail";
    }

    @Override
    public Map<String, Object> getVariables() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    @Override
    public String getQuery() {
        return "query mySubmissionDetail($id: ID!) {\n  submissionDetail(submissionId: $id) {\n    id\n    code\n    runtime\n    memory\n    rawMemory\n    statusDisplay\n    timestamp\n    lang\n    passedTestCaseCnt\n    totalTestCaseCnt\n    sourceUrl\n    question {\n      titleSlug\n      title\n      translatedTitle\n      questionId\n      __typename\n    }\n    ... on GeneralSubmissionNode {\n      outputDetail {\n        codeOutput\n        expectedOutput\n        input\n        compileError\n        runtimeError\n        lastTestcase\n        __typename\n      }\n      __typename\n    }\n    submissionComment {\n      comment\n      flagType\n      __typename\n    }\n    __typename\n  }\n}\n";
    }
}
