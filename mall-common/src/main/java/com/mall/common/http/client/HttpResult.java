package com.mall.common.http.client;

/**
 * HttpClient请求返回结果
 * 
 * @author gp6
 * @date 2018-08-21
 */
public class HttpResult {
	private Integer code;

	private String data;

	public HttpResult() {

	}

	public HttpResult(Integer code, String data) {
		this.code = code;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
