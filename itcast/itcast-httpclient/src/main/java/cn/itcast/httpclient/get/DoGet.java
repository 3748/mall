package cn.itcast.httpclient.get;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

/**
 * GET请求
 * 
 * @author gp6
 * @date 2018-08-20
 */
public class DoGet {

	public static void main(String[] args) throws Exception {

		// 创建Httpclient对象,相当于打开浏览器
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建http GET请求,相当于在地址栏输入url地址
		HttpGet httpGet = new HttpGet("http://manage.mall.com/rest/item/cat?id=2");

		CloseableHttpResponse response = null;
		try {
			// 执行请求,相当于按下回车键
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
				// 获取相应内容
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println("内容：" + content);
			}
		} finally {
			// 释放资源,相当于关闭浏览器
			if (response != null) {
				response.close();
			}
			httpclient.close();
		}

	}

}
