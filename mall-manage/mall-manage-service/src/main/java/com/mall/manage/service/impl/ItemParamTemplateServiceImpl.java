package com.mall.manage.service.impl;

import com.mall.common.bean.ItemParamTemplate;
import com.mall.common.utils.DateTimeUtil;
import com.mall.manage.mapper.ItemParamTemplateMapper;
import com.mall.manage.service.ItemParamTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gp6
 * @date 2018-09-27
 */
@Service
public class ItemParamTemplateServiceImpl implements ItemParamTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemParamTemplateServiceImpl.class);

    @Autowired
    private ItemParamTemplateMapper itemParamTemplateMapper;

    @Override
    public ItemParamTemplate selectItemParamTemplateByItemId(Long catId) {
        ItemParamTemplate itemParamTemplate = new ItemParamTemplate();
        itemParamTemplate.setCatId(catId);
        itemParamTemplate = itemParamTemplateMapper.selectOne(itemParamTemplate);
        return itemParamTemplate;
    }

    @Override
    public void insertItemParamTemplate(ItemParamTemplate itemParamTemplate) {
        try {
            itemParamTemplate.setCreateTime(DateTimeUtil.CURRENTTIME);
            itemParamTemplate.setUpdatedTime(itemParamTemplate.getCreateTime());
            itemParamTemplateMapper.insert(itemParamTemplate);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
