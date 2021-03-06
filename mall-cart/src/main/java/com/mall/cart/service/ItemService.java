package com.mall.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.Item;
import com.mall.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品
 *
 * @author gp6
 * @date 2018-10-26
 */
@Service
public class ItemService {

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private PropertiesService propertiesService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    Item selectItemById(Long itemId) {
        try {
            String url = propertiesService.manageUrl + propertiesService.manageItemUrl + itemId;
            String jsonData = httpClientUtil.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData, Item.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
