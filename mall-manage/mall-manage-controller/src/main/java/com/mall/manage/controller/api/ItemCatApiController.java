package com.mall.manage.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.manage.service.ItemCatService;
import com.mall.manage.vo.ItemCatResult;

/**
 * 商品类目接口(供商城前台调用)
 * 
 * @author gp6
 * @date 2018-08-16
 */
@Controller
@RequestMapping({ "api/item/cat" })
public class ItemCatApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemCatApiController.class);

	@Autowired
	private ItemCatService itemCatService;

	//private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 接口单独支持jsonp
	 * 首页左侧商品类目
	 * 
	 * @return
	 */
	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> queryAllToTree(@RequestParam(value = "callback", required = false) String callback) {
		try {
			ItemCatResult itemCatResult = itemCatService.queryAllToTree();

			if (null == itemCatResult) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			String result = MAPPER.writeValueAsString(itemCatResult);

			// 无需跨域支持
			if (StringUtils.isEmpty(callback)) {
				return ResponseEntity.ok(result);
			} else {
				return ResponseEntity.ok(callback + "(" + result + ")");
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}*/

	/**
	 * 首页左侧商品类目
	 * 项目统一支持jsonp后
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ItemCatResult> queryAllToTree() {
		try {
			ItemCatResult itemCatResult = itemCatService.queryAllToTree();

			if (null == itemCatResult) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			//String result = MAPPER.writeValueAsString(itemCatResult);

			return ResponseEntity.ok(itemCatResult);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
