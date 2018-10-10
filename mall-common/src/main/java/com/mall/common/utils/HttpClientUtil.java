package com.mall.common.utils;

import com.mall.common.http.client.HttpResult;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 *
 * @author gp6
 * @date 2018-08-21
 */
@Service
public class HttpClientUtil implements BeanFactoryAware {

    /**
     * @Service是一个单例, 这种方法注入产生的closeableHttpClient也是一个单例, 而closeableHttpClient不能是单例, 所以使用BeanFactory
     * 不能使用 @Autowired private CloseableHttpClient closeableHttpClient进行注入
     */
    private BeanFactory beanFactory;

    /**
     * 如果Spring中有就注入,没有就忽略
     */
    @Autowired(required = false)
    private RequestConfig requestConfig;

    private CloseableHttpClient getHttpClient() {
        // 通过Bean工厂获取bean，保证HttpClient对象是多例
        return this.beanFactory.getBean(CloseableHttpClient.class);
    }

    /**
     * Spring在初始化时,会调用该方法
     *
     * 讲述了如何在单例对象中使用多例对象
     * @param beanFactory Bean工厂
     * @throws BeansException 异常
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // Spring初始化时,获取bean工厂,将bean工厂赋值给当前类中定义的beanFactory属性
        this.beanFactory = beanFactory;
    }

    /**
     * 指定GET请求，返回:null,请求失败，String数据，请求成功
     *
     * @param url 请求地址
     * @return String
     * @throws ClientProtocolException 异常信息
     * @throws IOException             异常信息
     */
    public String doGet(String url) throws ClientProtocolException, IOException {
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        // 执行请求
        CloseableHttpResponse response = getHttpClient().execute(httpGet);
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }

        return null;
    }

    /**
     * 带有参数的GET请求，返回:null,请求失败，String数据，请求成功
     *
     * @param url    请求地址
     * @param params 参数
     * @return String
     * @throws ClientProtocolException 异常信息
     * @throws IOException             异常信息
     * @throws URISyntaxException      异常信息
     */
    public String doGet(String url, Map<String, String> params) throws ClientProtocolException, IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.setParameter(entry.getKey(), entry.getValue());
        }
        return this.doGet(builder.build().toString());
    }

    /**
     * 带有参数的POST请求
     *
     * @param url    请求地址
     * @param params 参数
     * @return String
     * @throws ParseException 异常信息
     * @throws IOException    异常信息
     */
    private HttpResult doPost(String url, Map<String, String> params) throws ParseException, IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        if (null != params) {

            // 设置post参数
            List<NameValuePair> parameters = new ArrayList<>(0);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);

            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }

        // 执行请求
        CloseableHttpResponse response = getHttpClient().execute(httpPost);

        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

    /**
     * 没有参数的POST请求
     *
     * @param url 请求地址
     * @return HttpResult
     * @throws ParseException 异常信息
     * @throws IOException    异常信息
     */
    public HttpResult doPost(String url) throws ParseException, IOException {
        return this.doPost(url, null);
    }

}
