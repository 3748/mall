package com.mall.manage.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.manage.bean.Item;
import com.mall.manage.service.ItemService;

/**
 * 商品接口(供商城前台调用)
 * 
 * @author gp6
 * @date 2018-08-16
 */
@Controller
@RequestMapping({ "api/item" })
public class ApiItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiItemController.class);

	@Autowired
	private ItemService itemService;

	/**
	 * 根据id查询商品信息
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Item> getItemInfoById(@PathVariable("id") Long id) {
		Item item = itemService.getItemInfoById(id);

		try {
			if (null == item) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(item);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
