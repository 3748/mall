package com.mall.web.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.utils.HttpClientUtil;
import com.mall.common.utils.RedisUtil;
import com.mall.manage.vo.ItemVo;

/**
 * @author gp6
 * @date 2018-08-21
 */
@Service
public class ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	/**
	 * 定义key的规则:项目名_模块名_业务名
	 */
	private static final String REDIS_KEY = "MALL_ITEM_DETAIL";
	
	private static final Integer REDIS_TIME = 60 * 60 * 24;

	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

	@Value("${MANAGE_MALL_URL}")
	private String manageMallUrl;

	@Autowired
	private HttpClientUtil httpClientUtil;

	@Autowired
	private RedisUtil redisUtil;

	public ItemVo getItemInfoById(Long id) {

		// 从缓存中命中
		String key = REDIS_KEY + id;
		try {
			String cacheData = redisUtil.get(key);
			if (StringUtils.isNotEmpty(cacheData)) {
				return OBJECTMAPPER.readValue(cacheData, ItemVo.class);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		String jsonData;
		try {
			String url = this.manageMallUrl + "/rest/api/item/2";
			jsonData = httpClientUtil.doGet(url);
			if (StringUtils.isEmpty(jsonData)) {
				return null;
			}

			// 将数据写入到缓存中
			redisUtil.set(key, jsonData, REDIS_TIME);
			return OBJECTMAPPER.readValue(jsonData, ItemVo.class);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
