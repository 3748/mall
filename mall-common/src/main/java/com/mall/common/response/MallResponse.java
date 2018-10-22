package com.mall.common.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 商城自定义响应结构
 *
 * @author gp6
 * @date 2018-10-15
 */
public class MallResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(MallResponse.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    private static MallResponse build(Integer status, String msg, Object data) {
        return new MallResponse(status, msg, data);
    }

    public static MallResponse ok(Object data) {
        return new MallResponse(data);
    }

    public static MallResponse ok() {
        return new MallResponse(null);
    }

    public MallResponse() {

    }

    public static MallResponse build(Integer status, String msg) {
        return new MallResponse(status, msg, null);
    }

    private MallResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private MallResponse(Object data) {
        this.status = HttpStatus.OK.value();
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == HttpStatus.OK.value();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为MallResponse对象
     *
     * @param jsonData json数据
     * @param clazz    MallResponse中的object类型
     * @return MallResponse
     */
    public static MallResponse formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return OBJECT_MAPPER.readValue(jsonData, MallResponse.class);
            }
            JsonNode jsonNode = OBJECT_MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = OBJECT_MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = OBJECT_MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            LOGGER.error("将json结果集转化为MallResponse对象", e.getMessage());
        }
        return null;
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static MallResponse format(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, MallResponse.class);
        } catch (Exception e) {
            LOGGER.error("没有object对象的转化", e.getMessage());
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return MallResponse 商城自定义响应结构
     */
    public static MallResponse formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = OBJECT_MAPPER.readValue(data.traverse(), OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            LOGGER.error("集合转化失败", e.getMessage());
        }
        return null;
    }

}
