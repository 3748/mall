package com.mall.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mall.manage.vo.ItemVo;
import com.mall.web.service.ItemService;

/**
 * 商品
 * 
 * @author gp6
 * @date 2018-08-21
 */
@Controller
@RequestMapping({ "item" })
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	/**
	 * 调用mall.com/item/2.html, 会出现406错误,由于浏览器把数据当成html解析导致
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "test/{id}", method = RequestMethod.GET)
	public ResponseEntity<ItemVo> getItemInfoById1(@PathVariable("id") Long id) {
		ItemVo itemVo = itemService.getItemInfoById(id);

		try {
			if (null == itemVo) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemVo);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	/**
	 * 获取商品详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView getItemInfoById(@PathVariable("id") Long id) {
		ItemVo itemVo = itemService.getItemInfoById(id);

		ModelAndView modelAndView = new ModelAndView("item");
		modelAndView.addObject("itemVo", itemVo);
		return modelAndView;
	}

}
