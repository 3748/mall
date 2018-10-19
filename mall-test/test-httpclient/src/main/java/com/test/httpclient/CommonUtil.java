package com.test.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

/**
 * HttpClient通用工具类
 *
 * @author gp6
 * @date 2018-10-08
 */
public class CommonUtil {
    /**
     * 处理Get返回值
     *
     * @param closeableHttpClient CloseableHttpClient
     * @param httpGet             HttpGet
     * @throws Exception 异常信息
     */
    public static void handleGetResponse(CloseableHttpClient closeableHttpClient, HttpGet httpGet) throws Exception {
        CloseableHttpResponse response = null;
        try {
            // 执行请求,相当于按下回车键
            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                // 获取相应内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容：" + content);
            }
        } finally {
            // 释放资源,相当于关闭浏览器
            if (response != null) {
                response.close();
            }
            closeableHttpClient.close();
        }
    }

    /**
     * 处理Post返回值
     *
     * @param closeableHttpClient CloseableHttpClient
     * @param httpPost            HttpPost
     * @throws Exception 异常信息
     */
    public static void handlePostResponse(CloseableHttpClient closeableHttpClient, HttpPost httpPost) throws Exception {
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = closeableHttpClient.execute(httpPost);
            // 判断返回状态是否为301
            if (response.getStatusLine().getStatusCode() == HttpStatus.MOVED_PERMANENTLY.value()) {
                // 获取相应内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容" + content);
            }
        } finally {
            // 释放资源,相当于关闭浏览器
            if (response != null) {
                response.close();
            }
            closeableHttpClient.close();
        }
    }
}
