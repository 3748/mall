package com.mall.web.mq.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品队列监听
 *
 * @author gp6
 * @date 2018-09-13
 */
public class ItemQueueListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemQueueListener.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @param msg MQ从交换机中接收到的消息
     */
    public void execute(String msg) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(msg);
            Long itemId = jsonNode.get("itemId").asLong();

            redisUtil.del(KeywordEnum.MALL_WEB_ITEM_DETAIL.getValue() + itemId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
