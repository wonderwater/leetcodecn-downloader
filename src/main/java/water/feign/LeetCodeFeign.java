package water.feign;

import feign.Headers;
import feign.RequestLine;
import water.bean.req.Req;
import water.bean.resp.Rsp;

@Headers({
        "accept: */*",
        "accept-encoding: gzip",
        "accept-language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
        "content-type: application/json",
        "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"Microsoft Edge\";v=\"91\", \"Chromium\";v=\"91\"",
        "sec-ch-ua-mobile: ?0",
        "sec-fetch-dest: empty",
        "sec-fetch-mode: cors",
        "sec-fetch-site: none",
        "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36 Edg/91.0.864.70",
})
public interface LeetCodeFeign {

    @RequestLine("POST /graphql")
    Rsp call(Req req);

}
