package com.mall.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.utils.HttpClientUtil;
import com.mall.manage.vo.ItemVo;

/**
 * @author gp6
 * @date 2018-08-21
 */
@Service
public class ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

	@Value("${MANAGE_MALL_URL}")
	private String manageMallUrl;

	@Autowired
	private HttpClientUtil httpClientUtil;

	public ItemVo getItemInfoById(Long id) {
		try {
			String url = this.manageMallUrl + "/rest/api/item/2";
			String jsonData = httpClientUtil.doGet(url);
			return OBJECTMAPPER.readValue(jsonData, ItemVo.class);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
