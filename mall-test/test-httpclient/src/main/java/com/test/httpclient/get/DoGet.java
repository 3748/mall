package com.test.httpclient.get;

import com.test.httpclient.CommonUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * GET请求
 *
 * @author gp6
 * @date 2018-08-20
 */
public class DoGet {

    public static void main(String[] args) throws Exception {

        // 创建HttpClient对象,相当于打开浏览器
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        // 创建http GET请求,相当于在地址栏输入url地址
        HttpGet httpGet = new HttpGet("http://manage.mall.com/rest/item/cat?id=2");

        CommonUtil.handleGetResponse(closeableHttpClient, httpGet);
    }
}
