package com.wdxxl.wechat.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;


/**
 * 高级群发接口
 * http://mp.weixin.qq.com/wiki/15/5380a4e6f02f2ffdc7981a8ed7a40753.html
 * 
 * @author wdxxl
 * @date 2015-4-21 16:37:05
 * 
 */
public class MessageUtil {
	private static final String MESSAGE_MASS_SENDALL_POST = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";	//根据分组进行群发
	private static final String MESSAGE_MASS_DELETE_POST = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=";	//删除群发
	private static final String MESSAGE_MASS_PREVIEW_POST = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=";	//预览接口
	
	
	/**
	 * 预览接口
	 * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版。
	 * @param accessToken
	 * @param reqJson (內容的json结果)
	 * @return
	 */
	public static String previewMessage(String accessToken, String reqJson){
		String result = null;
		try {
			result = HttpClientUtils.Post(MESSAGE_MASS_PREVIEW_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
