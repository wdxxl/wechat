package com.wdxxl.wechat.httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wdxxl.wechat.service.ITokenService;
import com.wdxxl.wechat.util.SpringTransactionalTestCase;


@ContextConfiguration(locations = { "/spring-mvc-servlet.xml" })
public class WCGetPostDevTest extends SpringTransactionalTestCase {
	@Autowired
	private ITokenService tokenService;
	
	@Ignore
	@Test
	public void testPost() throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("touser", "oqqZyuLXjSamIiF7mLbWtQHfADIs");
		requestMap.put("msgtype", "text");
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("content", "帅噶尔1-111");
		requestMap.put("text", content);
		String requestJson = JSON.toJSONString(requestMap);

		String currentToken = tokenService.getCurrentAccessToken();

		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+ currentToken);

		StringEntity params = new StringEntity(requestJson,"UTF-8");
		
		httpPost.addHeader("content-type", "application/json;charset=UTF-8");
		httpPost.addHeader("Accept", "application/json");
		httpPost.setEntity(params);

		CloseableHttpResponse response = httpclient.execute(httpPost);
		int httpCode = response.getStatusLine().getStatusCode();
		
		StringBuilder result = new StringBuilder();
        if (response != null && httpCode == HttpURLConnection.HTTP_OK) {//200
        	 HttpEntity entity = response.getEntity();
        	 InputStream inputStream = entity.getContent();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader);
             String s;
             while (((s = reader.readLine()) != null)) {
                 result.append(s);
             }
             reader.close();//关闭输入流
             JSON jsonObject = (JSON)JSONObject.parse(result.toString());
             
             System.out.println(jsonObject.toJSONString()); 
             //{"errmsg":"ok","errcode":0}
             //{"errmsg":"response out of time limit or subscription is canceled","errcode":45015} //ToUser不存在 或者 未建立2天的连接
             //{"errmsg":"invalid credential, access_token is invalid or not latest","errcode":40001} //AccessToken 出错
             
             //{"errmsg":"empty content","errcode":44004}
             //{"errmsg":"invalid message type","errcode":40008}
             //{"errmsg":"invalid openid","errcode":40003}
        }
        response.close();
        httpclient.close();
	}

	@Ignore
	@Test
	public void testGet() throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String currentToken = tokenService.getCurrentAccessToken();

		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/groups/get?access_token="+ currentToken);
		
		httpGet.addHeader("content-type", "application/json;charset=UTF-8");
		httpGet.addHeader("Accept", "application/json");

		CloseableHttpResponse response = httpclient.execute(httpGet);
		int httpCode = response.getStatusLine().getStatusCode();
		
		StringBuilder result = new StringBuilder();
        if (response != null && httpCode == HttpURLConnection.HTTP_OK) {//200
        	 HttpEntity entity = response.getEntity();
        	 InputStream inputStream = entity.getContent();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader);
             String s;
             while (((s = reader.readLine()) != null)) {
                 result.append(s);
             }
             reader.close();//关闭输入流
             JSON jsonObject = (JSON)JSONObject.parse(result.toString());
             
             System.out.println(jsonObject.toJSONString()); 
        }
        response.close();
        httpclient.close();
	}

	@Ignore
	@SuppressWarnings("deprecation")
	@Test
	public void testPostFile() throws IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//String currentToken = tokenService.getCurrentAccessToken();
		//HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+ currentToken+"&type=image");
		HttpPost httpPost = new HttpPost("http://localhost:8080/wechat/uploadMedia?type=image");
		FileBody bin = new FileBody(new File("C:\\Users\\wangkexue\\Desktop\\ScreenClip.jpg"));  
        MultipartEntity mpEntity = new MultipartEntity(); // 文件传输  
        mpEntity.addPart("media", bin);  
        httpPost.setEntity(mpEntity);  
		
		CloseableHttpResponse response = httpclient.execute(httpPost);
		int httpCode = response.getStatusLine().getStatusCode();
		
		StringBuilder result = new StringBuilder();
        if (response != null && httpCode == HttpURLConnection.HTTP_OK) {//200
        	 HttpEntity entity = response.getEntity();
        	 InputStream inputStream = entity.getContent();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader);
             String s;
             while (((s = reader.readLine()) != null)) {
                 result.append(s);
             }
             reader.close();//关闭输入流
             JSON jsonObject = (JSON)JSONObject.parse(result.toString());
             
             System.out.println(jsonObject.toJSONString()); 
        }
        response.close();
        httpclient.close();
	}
}
