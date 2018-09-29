package com.mall.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mall.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gp6
 * @date 2018-08-21
 */
@Service
public class IndexService {
    @Autowired
    private HttpClientUtil httpClientUtil;

    @Value("${MANAGE_MALL_URL}")
    private String manageMallUrl;

    @Value("${INDEX_AD1_URL}")
    private String indexAd1Url;

    @Value("${INDEX_AD2_URL}")
    private String indexAd2Url;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String queryIndexAD1() {
        try {
            String url = this.manageMallUrl + this.indexAd1Url;
            String jsonData = httpClientUtil.doGet(url);

            if (StringUtils.isEmpty(jsonData)) {
                return null;
            }

            // 解析json,生成前端需要的json数据
            JsonNode jsonNode = MAPPER.readTree(jsonData);

            ArrayNode rows = (ArrayNode) jsonNode.get("list");

            List<Map<String, Object>> result = new ArrayList<>();
            for (JsonNode row : rows) {
                // 这样获取的值,是有顺序的
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("srcB", row.get("pic").asText());
                map.put("height", 240);
                map.put("alt", row.get("title").asText());
                map.put("width", 670);
                map.put("src", row.get("pic").asText());
                map.put("widthB", 550);
                map.put("href", row.get("url").asText());
                map.put("heightB", 240);
                result.add(map);
            }

            // 转为json字符串
            return MAPPER.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String queryIndexAD2() {
        try {
            String url = this.manageMallUrl + this.indexAd2Url;
            String jsonData = httpClientUtil.doGet(url);
            if (StringUtils.isEmpty(jsonData)) {
                return null;
            }

            JsonNode jsonNode = MAPPER.readTree(jsonData);
            ArrayNode rows = (ArrayNode) jsonNode.get("list");
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            for (JsonNode row : rows) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("width", 310);
                map.put("height", 70);
                map.put("src", row.get("pic").asText());
                map.put("href", row.get("url").asText());
                map.put("alt", row.get("title").asText());
                map.put("widthB", 210);
                map.put("heightB", 70);
                map.put("srcB", row.get("pic").asText());
                result.add(map);
            }

            return MAPPER.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
