package com.mall.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.manage.bean.ItemCat;
import com.mall.manage.service.ItemCatService;

/**
 * @describe 商品类目
 * @author gp6
 * @date 2018-07-07
 */
@Controller
@RequestMapping(value = "item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 根据父id获取所有的子商品类目
	 * @param parentId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> getListByParentId(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		try {
			List<ItemCat> list = itemCatService.getListByParentId(parentId);
			if (null == list || list.isEmpty()) {
				// 资源不存在
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
