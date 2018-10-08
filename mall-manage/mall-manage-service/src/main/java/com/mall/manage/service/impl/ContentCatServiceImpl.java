package com.mall.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.abel533.entity.Example;
import com.mall.common.enums.NumberEnum;
import com.mall.common.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.bean.ContentCat;
import com.mall.manage.mapper.ContentCatMapper;
import com.mall.manage.service.ContentCatService;

/**
 * @author gp6
 * @date 2018年8月20日
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    private ContentCatMapper contentCatMapper;

    @Override
    public List<ContentCat> selectContentCatByParentId(Long parentId) {
        ContentCat contentCat = new ContentCat();
        contentCat.setParentId(parentId);
        return contentCatMapper.select(contentCat);
    }

    @Override
    public void insertContentCat(ContentCat contentCat) {
        // 新增的内容类目不可能是父类目
        contentCat.setIsParent(NumberEnum.CONCAT_CAT_IS_NOT_PARENT.getValue());
        contentCat.setSortOrder(1);
        contentCat.setStatus(NumberEnum.CONCAT_CAT_STATUS_NORMAL.getValue());
        contentCat.setCreateTime(DateTimeUtil.CURRENTTIME);
        contentCat.setUpdateTime(contentCat.getCreateTime());
        contentCatMapper.insert(contentCat);

        /*
            判断新增类目的父类目是否为父类目(新增类目A,A默认非父类目,在A下新增类目B,此时要将A类目设置为父类目)

            使用selectByPrimaryKey,在id字段上要加上@Id注解,否则可能会出现类型转换异常
         */
        ContentCat parentContentCat = contentCatMapper.selectByPrimaryKey(contentCat.getParentId());
        if (1 != parentContentCat.getIsParent()) {
            parentContentCat.setUpdateTime(contentCat.getCreateTime());
            parentContentCat.setIsParent(NumberEnum.CONCAT_CAT_IS_PARENT.getValue());
            contentCatMapper.updateByPrimaryKeySelective(parentContentCat);
        }
    }

    @Override
    public void deleteContentCat(Long id) {
        List<Object> idsList = new ArrayList<>();
        ContentCat contentCat = contentCatMapper.selectByPrimaryKey(id);

        findAllSubContentCat(idsList, id);

        // 删除类目以及其子类目
        Example example = new Example(ContentCat.class);
        example.createCriteria().andIn("id",idsList);
        contentCatMapper.deleteByExample(example);

        // 判断当前类目的父类目是否有其他子类目
        List<ContentCat> list = selectContentCatByParentId(contentCat.getParentId());

        // 没有其他子类目,将父类目的isParent改为0
        if(null == list || list.size() ==0 ){
            ContentCat parentContentCat = new ContentCat();
            parentContentCat.setId(contentCat.getParentId());
            parentContentCat.setIsParent(NumberEnum.CONCAT_CAT_IS_NOT_PARENT.getValue());
            contentCatMapper.updateByPrimaryKeySelective(parentContentCat);
        }
    }

    /**
     * 递归查找某类目下的所有子类目
     *
     * @param idsList  类目以及其子类目的id集合
     * @param parentId 类目id
     */
    private void findAllSubContentCat(List<Object> idsList, Long parentId) {
        List<ContentCat> list = selectContentCatByParentId(parentId);

        idsList.add(parentId);
        for (ContentCat subContentCat : list) {
            idsList.add(subContentCat.getId());
            if (subContentCat.getIsParent() == NumberEnum.CONCAT_CAT_IS_PARENT.getValue()) {
                findAllSubContentCat(idsList, subContentCat.getId());
            }
        }
    }
}
