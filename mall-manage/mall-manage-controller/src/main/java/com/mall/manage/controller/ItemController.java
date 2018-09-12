package com.mall.manage.controller;

import com.mall.common.model.ItemModel;
import com.mall.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 商品管理
 *
 * @author gp6
 * @date 2018-07-09
 */
@Controller
@RequestMapping({"item"})
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 新增商品
     *
     * @param itemModel 前台传入商品参数
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(@RequestBody ItemModel itemModel) {

        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增商品， item = {}", itemModel);
            }

            // 模拟400情况
            if (StringUtils.isEmpty(itemModel.getTitle())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Boolean bool = itemService.saveItem(itemModel);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增商品失败， itemVo = {}", itemModel);
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增商品成功， itemVo = {}", itemModel);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增商品出错! itemVo = " + itemModel, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 更新商品
     *
     * @param itemModel 前台传入商品参数
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(@RequestBody ItemModel itemModel) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("编辑商品， itemModel = {}", itemModel);
            }
            if (StringUtils.isEmpty(itemModel.getTitle())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Boolean bool = itemService.updateItem(itemModel);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("编辑商品失败， itemModel = {}", itemModel);
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("编辑商品成功， itemId = {}", itemModel.getId());
            }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("编辑商品出错! itemModel = " + itemModel, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
