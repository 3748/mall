package com.test.solrj.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.test.solrj.pojo.Foo;

/**
 * Solrj服务
 *
 * @author gp6
 * @date 2018-10-23
 */
class SolrjService {

    /**
     * 定义http的solr服务
     */
    private HttpSolrServer httpSolrServer;

    public SolrjService(HttpSolrServer httpSolrServer) {
        this.httpSolrServer = httpSolrServer;
    }

    /**
     * 新增数据到solr服务
     *
     * @param foo f
     * @throws Exception 异常
     */
    public void add(Foo foo) throws Exception {
        // 添加数据到solr服务器
        this.httpSolrServer.addBean(foo);

        // 提交
        this.httpSolrServer.commit();
    }

    public void delete(List<String> ids) throws Exception {
        this.httpSolrServer.deleteById(ids);
        this.httpSolrServer.commit(); //提交
    }

    public List<Foo> search(String keywords, Integer page, Integer rows) throws Exception {
        //构造搜索条件
        SolrQuery solrQuery = new SolrQuery();

        //搜索关键词
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
        List<Foo> fooList = queryResponse.getBeans(Foo.class);
        if (isHighlighting) {
            // 将高亮的标题数据写回到数据对象中
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                for (Foo foo : fooList) {
                    if (!highlighting.getKey().equals(foo.getId())) {
                        continue;
                    }
                    foo.setTitle(StringUtils.join(highlighting.getValue().get("title"), ""));
                    break;
                }
            }
        }

        return fooList;
    }

}
