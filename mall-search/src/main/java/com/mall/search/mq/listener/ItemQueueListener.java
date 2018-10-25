package com.mall.search.mq.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.Item;
import com.mall.common.enums.KeywordEnum;
import com.mall.search.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品队列监听
 *
 * @author gp6
 * @date 2018-10-25
 */
public class ItemQueueListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemQueueListener.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private HttpSolrServer httpSolrServer;

    @Autowired
    private ItemService itemService;

    public void execute(String msg) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(msg);
            Long itemId = jsonNode.get("itemId").asLong();
            String type = jsonNode.get("type").asText();
            if ((StringUtils.equals(type, KeywordEnum.MQ_TYPE_INSERT.getValue())) || (StringUtils.equals(type, KeywordEnum.MQ_TYPE_UPDATE.getValue()))) {
                Item item = itemService.selectItemById(itemId);

                // 将新增商品加入Solr服务器
                httpSolrServer.addBean(item);
                httpSolrServer.commit();
            } else if (StringUtils.equals(type, KeywordEnum.MQ_TYPE_DELETE.getValue())) {
                // 将商品从Solr服务器中删除
                httpSolrServer.deleteById(String.valueOf(itemId));
                httpSolrServer.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
}
