package com.mall.web.controller;

import com.mall.common.enums.NumberEnum;
import com.mall.web.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 商城首页
 *
 * @author gp6
 * @date 2018-08-21
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 获取首页内容
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> map = new HashMap<>(NumberEnum.FOUR.getValue());
        
        // 大广告位数据
        String indexBigAD = indexService.selectBigIndexAD();
        map.put("indexBigAD", indexBigAD);

        // 小广告位数据
        String indexSmallAD = indexService.selectSmallIndexAD();
        map.put("indexSmallAD", indexSmallAD);
        return ResponseEntity.ok(map);
    }
}
