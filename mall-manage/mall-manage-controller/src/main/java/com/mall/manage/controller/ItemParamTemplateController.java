package com.mall.manage.controller;

import com.mall.common.bean.ItemParamTemplate;
import com.mall.manage.service.ItemParamTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品规格参数模板
 *
 * @author gp6
 * @date 2018-09-27
 */
@Controller
@RequestMapping({"item/param/template"})
public class ItemParamTemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemParamTemplateController.class);

    @Autowired
    private ItemParamTemplateService itemParamTemplateService;

    /**
     * 根据商品类目ID查询商品规格参数模板
     *
     * @param catId 商品类目ID
     * @return ResponseEntity<ItemParamTemplate>
     */
    @RequestMapping(value = {"{catId}"}, method = RequestMethod.GET)
    public ResponseEntity<ItemParamTemplate> selectItemParamTemplateByItemId(@PathVariable("catId") Long catId) {
        ItemParamTemplate itemParamTemplate = itemParamTemplateService.selectItemParamTemplateByItemId(catId);
        try {
            if (null == itemParamTemplate) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemParamTemplate);
        } catch (Exception e) {
            LOGGER.error("根据商品类目ID查询商品规格参数模板失败,原因:" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 新增商品规格参数模板
     *
     * @param catId     商品类目ID
     * @param paramData 商品规格参数
     * @return ResponseEntity<Void>
     */
    @RequestMapping(value = {"{catId}"}, method = RequestMethod.POST)
    public ResponseEntity<Void> insertItemParamTemplate(@PathVariable("catId") Long catId,
                                                        @RequestParam("paramData") String paramData) {
        /*
            @RequestParam 底层是通过request.getParameter方式获得参数的，也就是说，@RequestParam 和 request.getParameter是同一回事。
            因为使用request.getParameter()方式获取参数，可以处理get方式中queryString的值，也可以处理post方式中 body data的值，所以，@RequestParam可以处理get 方式中queryString的值，也可以处理post方式中 body data的值。

            @RequestParam用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST。
         */
        try {
            ItemParamTemplate itemParamTemplate = new ItemParamTemplate();
            itemParamTemplate.setCatId(catId);
            itemParamTemplate.setParamData(paramData);
            itemParamTemplateService.insertItemParamTemplate(itemParamTemplate);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增商品规格参数模板失败,catId={},paramData={}", catId, paramData);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
