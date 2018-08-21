package com.mall.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mall.web.service.IndexService;

/**
 * 商城首页
 * @author gp6
 * @date 2018-08-21
 */
@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;

	/**
	 * 获取首页内容
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");

		// 大广告位数据
		String indexAD1 = indexService.queryIndexAD1();
		modelAndView.addObject("indexAD1", indexAD1);

		String indexAD2 = indexService.queryIndexAD2();
		modelAndView.addObject("indexAD2", indexAD2);
		return modelAndView;
	}
}
