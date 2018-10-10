package cn.itcast.httpclient.post;

import cn.itcast.httpclient.CommonUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

/**
 * POST请求
 *
 * @author gp6
 * @date 2018-08-20
 */
public class DoPost {

    public static void main(String[] args) throws Exception {

        // 创建Httpclient对象
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        // 创建http POST请求
        HttpPost httpPost = new HttpPost("http://www.oschina.net/");

        CommonUtil.handlePostResponse(closeableHttpClient, httpPost);
    }

}
