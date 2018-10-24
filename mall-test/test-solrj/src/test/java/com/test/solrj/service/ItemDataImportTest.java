package com.test.solrj.service;

import com.test.solrj.pojo.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Solr简单使用----商品数据导入
 *
 * @author gp6
 * @date 2018-10-23
 */
public class ItemDataImportTest {

    /**
     * 定义http的solr服务
     */
    private HttpSolrServer httpSolrServer;

    @Before
    public void setUp() {
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
    public void testData() {
        Item item = new Item();
        item.setId(1L);
        item.setTitle("标题");
        item.setPrice(5000L);
        item.setImage("http://image.mall.com/jd/65e2007d41dc4e3cb308833a1a910f8d.jpg");
        item.setSellPoint("卖点");
        item.setStatus(1);
        item.setItemCatId(2);
        item.setUpdateTime(20180930152359L);

        try {
            // 添加数据到Solr服务器
            this.httpSolrServer.addBean(item);

            // 提交
            this.httpSolrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() throws Exception {
        Item item = new Item();
        item.setItemCatId(1);
        item.setId(999L);
        item.setImage("image");
        item.setPrice(100L);
        item.setSellPoint("很好啊，赶紧来买吧. 豪啊");
        item.setStatus(1);
        item.setTitle("飞利浦 老人手机 (X2560) 深情蓝 移动联通2G手机 双卡双待");
        this.httpSolrServer.addBean(item);
        this.httpSolrServer.commit();
    }

    @Test
    public void testDelete() throws Exception {
        this.httpSolrServer.deleteById("999");
        this.httpSolrServer.commit();
    }

    @Test
    public void testQuery() throws Exception {
        int page = 2;
        int rows = 1;
        String keywords = "手机";
        // 构造搜索条件
        SolrQuery solrQuery = new SolrQuery();
        // 搜索关键词
        solrQuery.setQuery("title:" + keywords);
        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
        solrQuery.setRows(rows);

        //是否需要高亮
        boolean isHighlighting = !StringUtils.equals("*", keywords) && StringUtils.isNotEmpty(keywords);

        if (isHighlighting) {
            // 设置高亮
            // 开启高亮组件
            solrQuery.setHighlight(true);

            // 高亮字段
            solrQuery.addHighlightField("title");

            // 标记，高亮关键字前缀
            solrQuery.setHighlightSimplePre("<em>");

            // 后缀
            solrQuery.setHighlightSimplePost("</em>");
        }

        // 执行查询
        QueryResponse queryResponse = this.httpSolrServer.query(solrQuery);
        List<Item> items = queryResponse.getBeans(Item.class);
        if (isHighlighting) {
            // 将高亮的标题数据写回到数据对象中
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                for (Item item : items) {
                    if (!highlighting.getKey().equals(item.getId().toString())) {
                        continue;
                    }
                    item.setTitle(StringUtils.join(highlighting.getValue().get("title"), ""));
                    break;
                }
            }
        }

        for (Item item : items) {
            System.out.println(item);
        }
    }
}
