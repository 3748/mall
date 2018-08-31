package com.mall.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.manage.bean.ContentCat;
import com.mall.manage.service.ContentCatService;

/**
 * 内容分类
 *
 * @author gp6
 * @date 2018-07-09
 */
@RequestMapping({"content/cat"})
@Controller
public class ContentCatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentCatController.class);

    @Autowired
    private ContentCatService contentCatService;

    /**
     * 根据父节点id查询分类列表
     *
     * @param parentId 商品类目父id
     * @return ResponseEntity<List < ContentCat>>
     */

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContentCat>> queryListByParentId(
            @RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        try {
            List<ContentCat> list = contentCatService.queryListByParentId(parentId);
            if ((null == list) || (list.isEmpty())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
