package com.test.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 配置HttpClient的参数
 *
 * @author gp6
 * @date 2018-08-20
 */
public class RequestConfigDemo {

    public static void main(String[] args) throws Exception {

        // 创建Httpclient对象
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");

        // 构建请求配置信息
        RequestConfig config = RequestConfig.custom().
                // 创建连接的最长时间
                setConnectTimeout(1000)
                // 从连接池中获取到连接的最长时间
                .setConnectionRequestTimeout(500)
                // 数据传输的最长时间
                .setSocketTimeout(10 * 1000)
                // 提交请求前测试连接是否可用
                .setStaleConnectionCheckEnabled(true).build();

        // 设置请求配置信息
        httpGet.setConfig(config);

        CommonUtil.handleGetResponse(closeableHttpClient, httpGet);
    }
}
