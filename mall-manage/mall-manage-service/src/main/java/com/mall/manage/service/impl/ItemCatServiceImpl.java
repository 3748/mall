package com.mall.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.StringEnum;
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
import com.mall.common.vo.ItemCatData;
import com.mall.common.vo.ItemCatVo;

/**
 * 商品类目
 *
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCatServiceImpl.class);

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * Spring4增加了对泛型注入的支持，可以直接在Service中写
     *
     * @Autowired
     * private Mapper<T> mapper;
     */

    @Override
    public List<ItemCat> selectItemCatListByParentId(long parentId) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        return itemCatMapper.select(itemCat);
    }

    @Override
    public ItemCatVo selectAllItemCatListToTree() {
        // 先从缓存中查询,命中即返回
        try {
            String cacheData = redisUtil.get(StringEnum.MALL_MANAGE_ITEM_CAT_ALL.getValue());

            // 查询到结果
            if (StringUtils.isNotEmpty(cacheData)) {
                // 此处将cacheData进行反序列化,因为存入的时候序列化存入
                return OBJECTMAPPER.readValue(cacheData, ItemCatVo.class);
            }
        } catch (Exception e) {
            LOGGER.error("获取缓存失败,原因:{}" + e.getMessage());
        }

        // 缓存未能命中
        List<ItemCat> cats = itemCatMapper.select(null);
        ItemCatVo result = new ItemCatVo();

        // 转为map存储，key为父节点ID，value为数据集合
        Map<Long, List<ItemCat>> itemCatMap = new HashMap<>(16);
        for (ItemCat itemCat : cats) {
            if (!itemCatMap.containsKey(itemCat.getParentId())) {
                itemCatMap.put(itemCat.getParentId(), new ArrayList<>());
            }
            itemCatMap.get(itemCat.getParentId()).add(itemCat);
        }

        // 封装一级对象
        List<ItemCat> itemCatList1 = itemCatMap.get(0L);
        for (ItemCat itemCat : itemCatList1) {
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/" + itemCat.getId() + ".html");
            itemCatData.setName("<a href='" + itemCatData.getUrl() + "'>" + itemCat.getName() + "</a>");
            result.getItemCats().add(itemCatData);
            if (0 == itemCat.getIsParent()) {
                continue;
            }

            // 封装二级对象
            List<ItemCat> itemCatList2 = itemCatMap.get(itemCat.getId());
            List<ItemCatData> itemCatData2 = new ArrayList<>();
            itemCatData.setItems(itemCatData2);
            for (ItemCat itemCat2 : itemCatList2) {
                ItemCatData id2 = new ItemCatData();
                id2.setName(itemCat2.getName());
                id2.setUrl("/products/" + itemCat2.getId() + ".html");
                itemCatData2.add(id2);
                if (1 == itemCat2.getIsParent()) {
                    // 封装三级对象
                    List<ItemCat> itemCatList3 = itemCatMap.get(itemCat2.getId());
                    List<String> itemCatData3 = new ArrayList<>();
                    id2.setItems(itemCatData3);
                    for (ItemCat itemCat3 : itemCatList3) {
                        itemCatData3.add("/products/" + itemCat3.getId() + ".html|" + itemCat3.getName());
                    }
                }
            }
        }

        // 将查询结果集写入缓存,此处将result进行序列化
        try {
            redisUtil.set(StringEnum.MALL_MANAGE_ITEM_CAT_ALL.getValue(), OBJECTMAPPER.writeValueAsString(result), NumberEnum.ITEM_CAT_EXPIRE_TIME.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
