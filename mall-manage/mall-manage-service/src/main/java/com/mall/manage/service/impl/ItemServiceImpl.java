package com.mall.manage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.Item;
import com.mall.common.bean.ItemDesc;
import com.mall.common.bean.ItemParam;
import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.StringEnum;
import com.mall.common.model.ItemModel;
import com.mall.common.utils.DateTimeUtil;
import com.mall.common.utils.RedisUtil;
import com.mall.common.vo.ItemVo;
import com.mall.manage.mapper.ItemMapper;
import com.mall.manage.service.ItemDescService;
import com.mall.manage.service.ItemParamService;
import com.mall.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品规格参数
 *
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemParamService itemParamService;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Boolean saveItem(ItemModel itemModel) {
        boolean flag = true;
        itemModel.setCreateTime(DateTimeUtil.getCurrentTime());
        itemModel.setUpdateTime(itemModel.getCreateTime());

        // 保存商品基本信息
        Item item = new Item();
        BeanUtils.copyProperties(itemModel, item);
        item.setStatus(NumberEnum.ITEM_STATUS_NORMAL.getValue());
        int countItem = itemMapper.insert(item);

        // 保存商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        BeanUtils.copyProperties(itemModel, itemDesc);
        int countItemDesc = itemDescService.saveItemDesc(itemDesc);

        // 保存商品规格参数
        ItemParam itemParam = new ItemParam();
        itemParam.setItemId(item.getId());
        itemParam.setParamData(itemModel.getItemParams().toString());
        int countItemParam = itemParamService.saveItemParam(itemParam);

        if (1 != countItem || 1 != countItemDesc || 1 != countItemParam) {
            flag = false;
        }
        return flag;
    }

    @Override
    public ItemVo getItemInfoById(Long id) {

        // 从缓存中命中
        String key = StringEnum.MALL_MANAGE_ITEM_DETAIL.getValue()+ id;
        try {
            String cacheData = redisUtil.get(key);
            if (StringUtils.isNotEmpty(cacheData)) {
                return OBJECTMAPPER.readValue(cacheData, ItemVo.class);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        ItemVo itemVo = itemMapper.getItemInfoById(id);
        try {
            if (null == itemVo) {
                return null;
            }

            // 将数据写入到缓存中
            redisUtil.set(key, OBJECTMAPPER.writeValueAsString(itemVo), NumberEnum.ITEM_DETAIL_EXPIRE_TIME.getValue());
            return itemVo;
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
