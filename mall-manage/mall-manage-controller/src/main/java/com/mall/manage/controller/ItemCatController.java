package com.mall.manage.controller;

import com.mall.common.bean.ItemCat;
import com.mall.manage.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品类目
 *
 * @author gp6
 * @date 2018-07-07
 */
@Controller
@RequestMapping(value = "item/cat")
public class ItemCatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCatController.class);

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 根据父id获取所有的子商品类目
     *
     * @param parentId 商品类目父id
     * @return ResponseEntity<List<ItemCat>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCat>> selectItemCatListByParentId(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        try {
            List<ItemCat> list = itemCatService.selectItemCatListByParentId(parentId);
            if (null == list || list.isEmpty()) {
                // 资源不存在
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            LOGGER.error("根据父id获取所有的子商品类目:" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
