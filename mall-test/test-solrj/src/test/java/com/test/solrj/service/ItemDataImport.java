package com.test.solrj.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.solrj.pojo.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ItemDataImport {
    private HttpSolrServer httpSolrServer;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        // 有#代表页面,无#代表服务
        String url = "http://solr.mall.com/mall";
        HttpSolrServer httpSolrServer = new HttpSolrServer(url);
        // 设置相应解析器
        httpSolrServer.setParser(new XMLResponseParser());
        // 设置充实次数,推荐为1
        httpSolrServer.setMaxRetries(1);
        // 建立连接的最长时间
        httpSolrServer.setConnectionTimeout(500);
        this.httpSolrServer = httpSolrServer;
    }

    @Test
    public void testImport() {
        // 查询商品数据，1、从数据库查询，2、从后台系统中查询（推荐）
        String url = "http://manage.mall.com/rest/item?pageNum={page}&pageSize=100";
        int page = 1;
        int pageSize = 0;
        try {
            do {
                String jsonData = null;
                jsonData = doGet(StringUtils.replace(url, "{page}", "" + page));
                JsonNode jsonNode = MAPPER.readTree(jsonData);
                ArrayNode list = (ArrayNode) jsonNode.get("list");
                List<Item> items = MAPPER.readValue(list.toString(), MAPPER.getTypeFactory()
                        .constructCollectionType(List.class, Item.class));
                pageSize = items.size();
                this.httpSolrServer.addBeans(items);
                this.httpSolrServer.commit();
                page++;
            } while (pageSize == 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String doGet(String url) throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
        return null;
    }
}