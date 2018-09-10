package com.mall.web.service;

import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.StringEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.utils.HttpClientUtil;
import com.mall.common.utils.RedisUtil;
import com.mall.common.vo.ItemVo;

/**
 * @author gp6
 * @date 2018-08-21
 */
@Service
public class ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    @Value("${MANAGE_MALL_URL}")
    private String manageMallUrl;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 因为缓存原因,当后台更改商品时,前台获取的商品不是最新(用MQ解决该问题)
     *
     * @param id 商品id
     * @return ItemVo
     */
    public ItemVo getItemInfoById(Long id) {

        // 从缓存中命中
        String key = StringEnum.WEB_ITEM_DETAIL.getValue() + id;
        try {
            String cacheData = redisUtil.get(key);
            if (StringUtils.isNotEmpty(cacheData)) {
                return OBJECTMAPPER.readValue(cacheData, ItemVo.class);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

        String jsonData;
        try {
            String url = this.manageMallUrl + "/rest/api/item/2";
            jsonData = httpClientUtil.doGet(url);
            if (StringUtils.isEmpty(jsonData)) {
                return null;
            }

            // 将数据写入到缓存中
            redisUtil.set(key, jsonData, NumberEnum.ITEM_DETAIL_EXPIRE_TIME.ordinal());
            return OBJECTMAPPER.readValue(jsonData, ItemVo.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
