package com.mall.manage.service.impl;

import com.github.abel533.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.bean.ItemDesc;
import com.mall.manage.mapper.ItemDescMapper;
import com.mall.manage.service.ItemDescService;

/**
 * @author gp6
 * @date 2018/8/30
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public int saveItemDesc(ItemDesc itemDesc) {
        return itemDescMapper.insert(itemDesc);
    }

    @Override
    public int updateItemDesc(ItemDesc itemDesc) {
        //指定Where条件
        Example example = new Example(ItemDesc.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemDesc.getItemId());

        //修改itemDesc中不为null的字段
        return itemDescMapper.updateByExampleSelective(itemDesc,example);
    }
}
