package com.mall.manage.controller;

import com.mall.common.bean.ContentCat;
import com.mall.manage.service.ContentCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内容分类
 *
 * @author gp6
 * @date 2018-07-09
 */
@Controller
@RequestMapping({"content/cat"})
public class ContentCatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentCatController.class);

    @Autowired
    private ContentCatService contentCatService;

    /**
     * 根据父内容类目id查询内容分类列表
     *
     * @param parentId 父内容类目id
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContentCat>> selectContentCatByParentId(@RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        try {
            List<ContentCat> list = contentCatService.selectContentCatByParentId(parentId);
            if ((null == list) || (list.isEmpty())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            LOGGER.error("根据父内容类目id查询内容分类列表失败,原因:" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 新增内容类目
     *
     * @param contentCat 内容类目信息
     * @return 将新增类目返回供展示
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContentCat> insertContentCat(@RequestBody ContentCat contentCat) {
        try {
            contentCatService.insertContentCat(contentCat);
            return ResponseEntity.status(HttpStatus.CREATED).body(contentCat);
        } catch (Exception e) {
            LOGGER.error("新增内容类目失败,contentCat={},原因:", contentCat, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 删除内容类目
     *
     * @param id 内容类目id
     * @return ResponseEntity
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContentCat(@PathVariable("id") Long id) {
        try {
            contentCatService.deleteContentCat(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("删除内容类目失败,id={},原因:", id, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
