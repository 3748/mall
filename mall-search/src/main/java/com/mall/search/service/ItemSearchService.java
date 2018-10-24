package com.mall.search.service;

import com.mall.common.bean.Item;
import com.mall.common.response.SearchResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 商品搜索
 *
 * @author gp6
 * @date 2018-10-24
 */
@Service
public class ItemSearchService {
    @Autowired
    private HttpSolrServer httpSolrServer;

    public SearchResponse search(String keyWords, Integer page, Integer rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        // 搜索关键字和搜索条件
        solrQuery.setQuery("title:" + keyWords + " AND status:1");

        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为2就可以
        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
        solrQuery.setRows(rows);

        boolean isHighlighting = (!StringUtils.equals("*", keyWords)) && (StringUtils.isNotEmpty(keyWords));

        // 设置高亮显示
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

        QueryResponse queryResponse = httpSolrServer.query(solrQuery);
        List<Item> items = queryResponse.getBeans(Item.class);
        Iterator i$;
        if (isHighlighting) {
            Map map = queryResponse.getHighlighting();
            for (i$ = map.entrySet().iterator(); i$.hasNext(); ) {
                Map.Entry highlighting = (Map.Entry) i$.next();
                for (Item item : items) {
                    if ((highlighting.getKey()).equals(item.getId().toString())) {
                        item.setTitle(StringUtils.join((Iterable) ((Map) highlighting.getValue()).get("title"), ""));
                    }
                }
            }
        }

        return new SearchResponse(queryResponse.getResults().getNumFound(), items);
    }
}
