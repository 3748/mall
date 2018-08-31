package com.mall.manage.service.impl;

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
}
