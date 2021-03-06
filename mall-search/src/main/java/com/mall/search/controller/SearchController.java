package com.mall.search.controller;

import com.mall.common.enums.NumberEnum;
import com.mall.common.response.SearchResponse;
import com.mall.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品搜索功能
 *
 * @author gp6
 * @date 2018-10-24
 */
@Controller
public class SearchController {
    private static final Integer ROWS = 32;

    @Autowired
    private ItemSearchService itemSearchService;

    @RequestMapping(value = {"search"}, method = RequestMethod.GET)
    public ResponseEntity<Map> search(@RequestParam("q") String keyWords, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Map<String, Object> map = new HashMap<>(NumberEnum.EIGHT.getValue());

        // 解决Get请求中文乱码问题
        keyWords = new String(keyWords.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        // 搜索关键字
        map.put("query", keyWords);

        SearchResponse searchResponse;
        try {
            searchResponse = itemSearchService.search(keyWords, page, ROWS);
        } catch (Exception e) {
            searchResponse = new SearchResponse(0L, null);
        }

        // 搜索结果集
        map.put("itemList", searchResponse.getList());

        // 当前页数
        map.put("page", page);

        // 总条数
        int total = searchResponse.getTotal().intValue();

        // 总页数
        int pages = total % ROWS == 0 ? total / ROWS : total / ROWS + 1;

        map.put("pages", pages);
        return ResponseEntity.ok(map);
    }
}
