package com.test.crawler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.crawler.bean.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 京东爬虫
 *
 * @author gp6
 * @date 2018-10-18
 */
class JdCrawler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String BASE_URL = "https://list.jd.com/list.html?cat=737,794,798&page={page}";

    void start() throws IOException {
        // 京东入口地址
        String startUrl = StringUtils.replace(BASE_URL, "{page}", "1");

        // 获取页面HTML源码,并进行解析
        String html = doGet(startUrl);
        Document document = Jsoup.parse(html);

        // 在页面源码中找到相应的div容器id,第一页和最后一页的页码(得到结果: 1/281 < >)
        String pageText = document.select("#J_topPage").text();

        // 提取结果中数字提取
        String[] pageNums = pageText.split("\\D+");

        // 得到总页数
        int totalPage = Integer.parseInt(pageNums[1]);

        for (int i = 1; i < totalPage; i++) {
            String url = StringUtils.replace(BASE_URL, "{page}", String.valueOf(i));

            // 获取页面HTML源码,并进行解析
            String content = doGet(url);
            Document documentContent = Jsoup.parse(content);

            // 商品列表
            Elements itemLis = documentContent.select("#plist li.gl-item");

            Map<Long, Item> itemMap = new HashMap<>(16);
            for (Element itemLi : itemLis) {
                Element div = itemLi.child(0);

                // 获取商品id
                Long itemId = Long.valueOf(div.attr("data-sku"));

                // 获取商品图片
                String imgUrl = itemLi.select(".p-img img").attr("src");

                // 获取商品标题
                String title = itemLi.select(".p-name").text();

                Item item = new Item();
                item.setId(itemId);
                item.setImage(imgUrl);
                item.setTitle(title);
                itemMap.put(itemId, item);
            }

            List<String> itemIdList = new ArrayList<>();
            for (Long itemId : itemMap.keySet()) {
                itemIdList.add(String.valueOf(itemId));
            }

            // 京东获取商品价格接口
            String priceUrl = "https://p.3.cn/prices/mgets?skuIds=" + StringUtils.join(itemIdList, ",");

            // [{"op":"5498.00","m":"5499.00","id":"J_7476007","p":"4599.00"},{"op":"3999.00","m":"4399.00","id":"J_6050379","p":"2899.00"},{"op":"3799.00","m":"4599.00","id":"J_5728954","p":"2299.00"}]
            String priceJsonData = doGet(priceUrl);

            ArrayNode arrayNode = (ArrayNode) OBJECT_MAPPER.readTree(priceJsonData);
            for (JsonNode jsonNode : arrayNode) {
                // 获取商品id
                Long id = Long.valueOf(StringUtils.substringAfter(jsonNode.get("id").asText(), "_"));

                // 将价格写入实体类
                itemMap.get(id).setPrice(jsonNode.get("p").asLong());
            }
        }
    }

    /**
     * 指定GET请求，返回:null,请求失败，String数据，请求成功
     *
     * @param url 请求地址
     * @return String
     * @throws ClientProtocolException 异常信息
     * @throws IOException             异常信息
     */
    private String doGet(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            closeableHttpClient.close();
        }
        return null;
    }
}
