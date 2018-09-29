package com.mall.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.mall.common.bean.Content;
import com.mall.manage.service.ContentService;

/**
 * 首页内容(首页上所有商品,广告等都可看做首页内容)
 *
 * @author gp6
 * @date 2018年8月20日
 */
@Controller
@RequestMapping({"content"})
public class ContentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    /**
     * @param contentCatId 首页内容类目id
     * @param pageNum      页码
     * @param pageSize     每页显示条数
     * @return ResponseEntity<PageInfo<Content>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageInfo<Content>> selectContentListByCatId(@RequestParam("contentCatId") Long contentCatId,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            PageInfo<Content> pageInfo = contentService.selectContentListByCatId(contentCatId, pageNum, pageSize);
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
