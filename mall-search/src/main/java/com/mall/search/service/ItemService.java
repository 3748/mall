package com.mall.search.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.Item;
import com.mall.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author gp6
 * @date 2018-10-25
 */
@Service
public class ItemService {

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Value("${MALL_MANAGE_URL}")
    private String mallManageUrl;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Item selectItemById(Long itemId) {
        try {
            String url = mallManageUrl + "/rest/api/item/" + itemId;
            String jsonData = httpClientUtil.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return OBJECT_MAPPER.readValue(jsonData, Item.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
