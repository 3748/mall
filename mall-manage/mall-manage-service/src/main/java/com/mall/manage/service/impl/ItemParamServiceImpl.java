package com.mall.manage.service.impl;

import com.github.abel533.entity.Example;
import com.mall.common.bean.ItemDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.utils.DateTimeUtil;
import com.mall.common.bean.ItemParam;
import com.mall.manage.mapper.ItemParamMapper;
import com.mall.manage.service.ItemParamService;

import javax.xml.crypto.Data;

/**
 * 商品规格参数
 *
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemParamServiceImpl.class);

    @Autowired
    private ItemParamMapper itemParamMapper;

    @Override
    public int saveItemParam(ItemParam itemParam) {
        int count = 0;

        try {
            itemParam.setCreateTime(DateTimeUtil.getCurrentTime());
            itemParam.setUpdateTime(itemParam.getCreateTime());
            count = itemParamMapper.insert(itemParam);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return count;
    }

    @Override
    public int updateItemParam(ItemParam itemParam) {
        int count = 0;

        try {
            //指定Where条件
            Example example = new Example(ItemParam.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("itemId",itemParam.getItemId());
            itemParam.setUpdateTime(DateTimeUtil.CURRENTTIME);

            //updateByExampleSelective(修改实体类中不为null的字段)
            count = itemParamMapper.updateByExampleSelective(itemParam,example);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return count;
    }
}
