package com.wdxxl.wechat.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * HttpClient Get/Post Method utils 
 * @author wdxxl
 *
 */
public class HttpClientUtils {
	
	public static String Post(String url,String reqJson) throws ClientProtocolException, IOException{
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity params = new StringEntity(reqJson,"UTF-8");
		httpPost.addHeader("content-type", "application/json;charset=UTF-8");
		httpPost.addHeader("Accept", "application/json");
		httpPost.setEntity(params);

		CloseableHttpResponse response = httpclient.execute(httpPost);
		int httpCode = response.getStatusLine().getStatusCode();
		
		StringBuilder tempBuffer = new StringBuilder();
        if (response != null && httpCode == HttpURLConnection.HTTP_OK) {//200
        	 HttpEntity entity = response.getEntity();
        	 InputStream inputStream = entity.getContent();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader);
             String s;
             while (((s = reader.readLine()) != null)) {
            	 tempBuffer.append(s);
             }
             reader.close();//关闭输入流
             JSON jsonObject = (JSON)JSONObject.parse(tempBuffer.toString());
             
             result = jsonObject.toJSONString(); 
        }
        response.close();
        httpclient.close();
		
        return result;
	}
	
	public static String Get(String url) throws ClientProtocolException, IOException{
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		httpGet.addHeader("content-type", "application/json;charset=UTF-8");
		httpGet.addHeader("Accept", "application/json");

		CloseableHttpResponse response = httpclient.execute(httpGet);
		int httpCode = response.getStatusLine().getStatusCode();
		
		StringBuilder tempBuffer = new StringBuilder();
        if (response != null && httpCode == HttpURLConnection.HTTP_OK) {//200
        	 HttpEntity entity = response.getEntity();
        	 InputStream inputStream = entity.getContent();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader);
             String s;
             while (((s = reader.readLine()) != null)) {
            	 tempBuffer.append(s);
             }
             reader.close();//关闭输入流
             JSON jsonObject = (JSON)JSONObject.parse(tempBuffer.toString());
             
             result = jsonObject.toJSONString(); 
        }
        response.close();
        httpclient.close();
        
        return result;
	}

	@SuppressWarnings("deprecation")
	public static String PostFile(String url,File file) throws ClientProtocolException, IOException{
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);

		FileBody bin = new FileBody(file);  
        MultipartEntity mpEntity = new MultipartEntity(); // 文件传输  
        mpEntity.addPart("media", bin);  
        httpPost.setEntity(mpEntity);  
		
		CloseableHttpResponse response = httpclient.execute(httpPost);
		int httpCode = response.getStatusLine().getStatusCode();
		
		StringBuilder tempBuffer = new StringBuilder();
        if (response != null && httpCode == HttpURLConnection.HTTP_OK) {//200
        	 HttpEntity entity = response.getEntity();
        	 InputStream inputStream = entity.getContent();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader);
             String s;
             while (((s = reader.readLine()) != null)) {
            	 tempBuffer.append(s);
             }
             reader.close();//关闭输入流
             JSON jsonObject = (JSON)JSONObject.parse(tempBuffer.toString());
             
             result = jsonObject.toJSONString(); 
        }
        response.close();
        httpclient.close();
        
        return result;
	}
}
