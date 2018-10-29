package com.mall.web.service;

import com.mall.common.bean.Item;
import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.KeywordEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.utils.HttpClientUtil;
import com.mall.common.utils.RedisUtil;

/**
 * @author gp6
 * @date 2018-08-21
 */
@Service
public class ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PropertiesService propertiesService;

    /**
     * 因为缓存原因,当后台更改商品时,前台获取的商品不是最新(用MQ解决该问题)
     *
     * @param id 商品id
     * @return ItemResponse
     */
    public Item selectItemById(Long id) {

        // 从缓存中命中
        String key = KeywordEnum.MALL_WEB_ITEM_DETAIL.getValue() + id;
        try {
            String cacheData = redisUtil.get(key);
            if (StringUtils.isNotEmpty(cacheData)) {
                return OBJECT_MAPPER.readValue(cacheData, Item.class);
            }
        } catch (Exception e) {
            LOGGER.error("获取商品详情失败,原因:" + e.getMessage());
        }

        String jsonData;
        try {
            String url = propertiesService.manageUrl + propertiesService.manageItemDetail;
            jsonData = httpClientUtil.doGet(url);
            if (StringUtils.isEmpty(jsonData)) {
                return null;
            }

            // 将数据写入到缓存中
            redisUtil.set(key, jsonData, NumberEnum.ITEM_DETAIL_EXPIRE_TIME.getValue());
            return OBJECT_MAPPER.readValue(jsonData, Item.class);
        } catch (Exception e) {
            LOGGER.error("商品写入缓存失败,商品id:{},原因:{}", id, e.getMessage());
        }
        return null;
    }
}
