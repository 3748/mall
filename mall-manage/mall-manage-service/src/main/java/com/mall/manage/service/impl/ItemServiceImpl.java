package com.mall.manage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.bean.Item;
import com.mall.common.bean.ItemDesc;
import com.mall.common.bean.ItemParam;
import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.request.ItemRequest;
import com.mall.common.utils.DateTimeUtil;
import com.mall.common.utils.RedisUtil;
import com.mall.common.response.ItemResponse;
import com.mall.manage.mapper.ItemMapper;
import com.mall.manage.service.ItemDescService;
import com.mall.manage.service.ItemParamService;
import com.mall.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品规格参数
 *
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemParamService itemParamService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Boolean insertItem(ItemRequest itemRequest) {
        boolean flag = true;
        itemRequest.setCreateTime(DateTimeUtil.CURRENTTIME);
        itemRequest.setUpdateTime(itemRequest.getCreateTime());

        // 保存商品基本信息
        Item item = new Item();
        BeanUtils.copyProperties(itemRequest, item);
        item.setStatus(NumberEnum.ITEM_STATUS_NORMAL.getValue());
        int countItem = itemMapper.insert(item);

        // 保存商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        BeanUtils.copyProperties(itemRequest, itemDesc);
        int countItemDesc = itemDescService.saveItemDesc(itemDesc);

        // 保存商品规格参数
        ItemParam itemParam = new ItemParam();
        itemParam.setItemId(item.getId());
        itemParam.setParamData(itemRequest.getItemParams().toString());
        int countItemParam = itemParamService.saveItemParam(itemParam);

        if (NumberEnum.ONE.getValue() != countItem || NumberEnum.ONE.getValue() != countItemDesc || NumberEnum.ONE.getValue() != countItemParam) {
            flag = false;
        }

        // 向MQ发送消息
        sendMsg(itemRequest.getId(), KeywordEnum.MQ_TYPE_INSERT.getValue());
        return flag;
    }

    @Override
    public ItemResponse selectItemById(Long id) {

        // 从缓存中命中
        String key = KeywordEnum.MALL_MANAGE_ITEM_DETAIL.getValue() + id;
        try {
            String cacheData = redisUtil.get(key);
            if (StringUtils.isNotEmpty(cacheData)) {
                return OBJECT_MAPPER.readValue(cacheData, ItemResponse.class);
            }
        } catch (Exception e) {
            LOGGER.error("从缓存中获取商品详情失败" + e.getMessage());
        }

        ItemResponse itemResponse = itemMapper.getItemInfoById(id);
        try {
            if (null == itemResponse) {
                return null;
            }

            // 将数据写入到缓存中
            redisUtil.set(key, OBJECT_MAPPER.writeValueAsString(itemResponse), NumberEnum.ITEM_DETAIL_EXPIRE_TIME.getValue());
            return itemResponse;
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateItem(ItemRequest itemRequest) {
        boolean flag = true;
        itemRequest.setUpdateTime(DateTimeUtil.CURRENTTIME);

        // 更新商品基本信息
        Item item = new Item();
        BeanUtils.copyProperties(itemRequest, item);

        // 将不能被修改的字段设置为null,防止前台传入参数,值被修改
        item.setCreateTime(null);
        item.setStatus(null);

        // updateByPrimaryKeySelective(只更新model中不为空的字段)   updateByPrimaryKey(将为空的字段在数据库中置为NULL)
        int countItem = itemMapper.updateByPrimaryKeySelective(item);

        // 更新商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemRequest.getId());
        BeanUtils.copyProperties(itemRequest, itemDesc);
        itemDesc.setCreateTime(null);
        int countItemDesc = itemDescService.updateItemDesc(itemDesc);

        // 更新商品规格参数
        ItemParam itemParam = new ItemParam();
        itemParam.setItemId(itemRequest.getId());
        itemParam.setParamData(itemRequest.getItemParams().toString());
        itemParam.setUpdateTime(itemRequest.getUpdateTime());
        itemDesc.setCreateTime(null);
        int countItemParam = itemParamService.updateItemParam(itemParam);

        if (NumberEnum.ONE.getValue() != countItem || NumberEnum.ONE.getValue() != countItemDesc || NumberEnum.ONE.getValue() != countItemParam) {
            flag = false;
        }

        // 向MQ发送消息
        sendMsg(itemRequest.getId(), KeywordEnum.MQ_TYPE_UPDATE.getValue());
        return flag;
    }

    @Override
    public PageInfo<Item> selectItemList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Item.class);
        example.setOrderByClause("ID DESC");
        List<Item> items = itemMapper.selectByExample(example);
        return new PageInfo<>(items);
    }

    /**
     * MQ发送消息到交换机
     *
     * @param itemId 商品ID
     * @param type   类型
     */
    private void sendMsg(Long itemId, String type) {
        try {
            // 封装MQ传递的参数
            Map<String, Object> data = new HashMap<>(NumberEnum.MAP_INIT_SIZE.getValue());
            data.put("itemId", itemId);
            data.put("type", type);
            data.put("date", System.currentTimeMillis());

            // 发送MQ,通知其他系统更新缓存中的商品信息(尽量少的传递信息)
            rabbitTemplate.convertAndSend("item." + type, OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            LOGGER.error("MQ消息发送失败", e.getMessage());
        }
    }
}
