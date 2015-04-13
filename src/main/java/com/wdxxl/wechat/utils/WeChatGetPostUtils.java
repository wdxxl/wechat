package com.wdxxl.wechat.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeChatGetPostUtils {
	private String accessToken = null;

	private WeChatGetPostUtils() {

	}

	private static WeChatGetPostUtils single = null;

	public static WeChatGetPostUtils getInstance() {
		if (single == null) {
			single = new WeChatGetPostUtils();
		}
		return single;
	}

	public String retrieveAccessToken() throws IOException {
		String appid = "wx42ce82205f5320c2";
		String secret = "dd8637104006a94213324401b4c3b725";
		accessToken = this.retrieveAccessToken(appid, secret);
		return accessToken;
	}

	public String retrieveAccessToken(String appid, String secret)
			throws IOException {
		String turl = String
				.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
						appid, secret);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(turl);
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = httpResponse.getEntity();
				String entityString = EntityUtils.toString(entity);
				JSONObject fromObject = JSON.parseObject(entityString);
				accessToken = fromObject.get("access_token").toString();//出错的话 java.lang.NullPointerException
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpResponse != null) {
				httpResponse.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}
}
