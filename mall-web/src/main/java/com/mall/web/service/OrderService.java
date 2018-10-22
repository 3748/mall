package com.mall.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.User;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.http.client.HttpResult;
import com.mall.common.request.OrderRequest;
import com.mall.common.utils.HttpClientUtil;
import com.mall.web.threadlocal.UserThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author gp6
 * @date 2018-10-18
 */
@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private HttpClientUtil httpClientUtil;

    public String insertOrderRequest(OrderRequest orderRequest) {
        String url = propertiesService.mallOrderUrl + "/order/insert";

        // 从本地线程中获取user对象
        User user = UserThreadLocal.get();
        orderRequest.setUserId(user.getId());
        orderRequest.setBuyerNick(user.getUserName());
        try {
            String json = OBJECT_MAPPER.writeValueAsString(orderRequest);
            HttpResult httpResult = httpClientUtil.doPostJson(url, json);
            if (httpResult.getCode() == HttpStatus.OK.value()) {
                String data = httpResult.getData();
                JsonNode jsonNode = OBJECT_MAPPER.readTree(data);
                if (jsonNode.get(KeywordEnum.STSTUS.getValue()).asInt() == HttpStatus.OK.value()) {
                    return jsonNode.get("data").asText();
                }
            }
        } catch (Exception e) {
            LOGGER.error("mall-web调用mall-order中订单新增接口失败,原因:", e.getMessage());
        }
        return null;
    }
}
