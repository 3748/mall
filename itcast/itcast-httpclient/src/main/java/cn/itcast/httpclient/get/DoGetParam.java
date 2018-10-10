package cn.itcast.httpclient.get;

import cn.itcast.httpclient.CommonUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

/**
 * 带有参数的GET请求
 *
 * @author gp6
 * @date 2018-08-20
 */
public class DoGetParam {

    public static void main(String[] args) throws Exception {

        // 创建Httpclient对象
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        // 定义请求的参数
        URI uri = new URIBuilder("http://manage.mall.com/rest/item/cat").setParameter("id", "2").build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);

        CommonUtil.handleGetResponse(closeableHttpClient,httpGet);
    }
}
