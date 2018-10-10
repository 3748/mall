package cn.itcast.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

/**
 * 连接管理器(类似于数据库连接池)
 *
 * @author gp6
 * @date 2018-08-20
 */
public class HttpConnectManager {

    public static void main(String[] args) throws Exception {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        // 设置最大连接数
        cm.setMaxTotal(200);

        // 设置每个主机(域名)地址的并发数
        cm.setDefaultMaxPerRoute(20);

        // 执行两次,两次的httpClient对象不是一个
        doGet(cm);
        doGet(cm);
    }

    private static void doGet(HttpClientConnectionManager cm) throws Exception {
        // 通过连接管理器获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");

        // 执行请求
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容长度：" + content.length());
            }
        }

        // 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
        // httpClient.close();
    }
}
