package com.mall.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.KeywordEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.utils.RedisUtil;
import com.mall.common.bean.ItemCat;
import com.mall.manage.mapper.ItemCatMapper;
import com.mall.manage.service.ItemCatService;
import com.mall.common.response.ItemCatData;
import com.mall.common.response.ItemCatResponse;

/**
 * 商品类目
 *
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCatServiceImpl.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * Spring4增加了对泛型注入的支持，可以直接在Service中写
     *
     * @Autowired private Mapper<T> mapper;
     */

    @Override
    public List<ItemCat> selectItemCatListByParentId(long parentId) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        return itemCatMapper.select(itemCat);
    }

    @Override
    public ItemCatResponse selectAllItemCatListToTree() {
        // 先从缓存中查询,命中即返回
        try {
            String cacheData = redisUtil.get(KeywordEnum.MALL_MANAGE_ITEM_CAT_ALL.getValue());

            // 查询到结果
            if (StringUtils.isNotEmpty(cacheData)) {
                // 此处将cacheData进行反序列化,因为存入的时候序列化存入
                return OBJECT_MAPPER.readValue(cacheData, ItemCatResponse.class);
            }
        } catch (Exception e) {
            LOGGER.error("商品类目获取缓存失败,原因:" + e.getMessage());
        }

        // 缓存未能命中
        List<ItemCat> cats = itemCatMapper.select(null);
        ItemCatResponse result = new ItemCatResponse();

        // 转为map存储，key为父节点ID，value为数据集合
        Map<Long, List<ItemCat>> itemCatMap = new HashMap<>(NumberEnum.MAP_INIT_SIZE.getValue());
        for (ItemCat itemCat : cats) {
            if (!itemCatMap.containsKey(itemCat.getParentId())) {
                itemCatMap.put(itemCat.getParentId(), new ArrayList<>());
            }
            itemCatMap.get(itemCat.getParentId()).add(itemCat);
        }

        // 封装一级对象
        List<ItemCat> itemCatListOne = itemCatMap.get(0L);
        for (ItemCat itemCat : itemCatListOne) {
            ItemCatData itemCatDataOne = new ItemCatData();
            itemCatDataOne.setUrl("/products/" + itemCat.getId() + ".html");
            itemCatDataOne.setName("<a href='" + itemCatDataOne.getUrl() + "'>" + itemCat.getName() + "</a>");
            result.getItemCats().add(itemCatDataOne);
            if (NumberEnum.ZERO.getValue() == itemCat.getIsParent()) {
                continue;
            }

            // 封装二级对象
            List<ItemCat> itemCatListTwo = itemCatMap.get(itemCat.getId());
            List<ItemCatData> itemCatDataListTwo = new ArrayList<>();
            itemCatDataOne.setItems(itemCatDataListTwo);
            for (ItemCat itemCatTwo : itemCatListTwo) {
                ItemCatData itemCatDataTwo = new ItemCatData();
                itemCatDataTwo.setName(itemCatTwo.getName());
                itemCatDataTwo.setUrl("/products/" + itemCatTwo.getId() + ".html");
                itemCatDataListTwo.add(itemCatDataTwo);
                if (NumberEnum.ONE.getValue() == itemCatTwo.getIsParent()) {
                    // 封装三级对象
                    List<ItemCat> itemCatListThree = itemCatMap.get(itemCatTwo.getId());
                    List<String> itemCatDataThree = new ArrayList<>();
                    itemCatDataTwo.setItems(itemCatDataThree);
                    for (ItemCat itemCatThree : itemCatListThree) {
                        itemCatDataThree.add("/products/" + itemCatThree.getId() + ".html|" + itemCatThree.getName());
                    }
                }
            }
        }

        // 将查询结果集写入缓存,此处将result进行序列化
        try {
            redisUtil.set(KeywordEnum.MALL_MANAGE_ITEM_CAT_ALL.getValue(), OBJECT_MAPPER.writeValueAsString(result), NumberEnum.ITEM_CAT_EXPIRE_TIME.getValue());
        } catch (Exception e) {
            LOGGER.error("商品类目写入缓存失败,原因:" + e.getMessage());
        }

        return result;
    }

}
