package com.wdxxl.wechat.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

/**
 * 自定义菜单接口
 * http://mp.weixin.qq.com/wiki/13/43de8269be54a0a6f64413e4dfa94f39.html
 * 
 * @author wdxxl
 * @date 2015-4-21 13:46:07
 * 
 */
public class MenuUtil {
	private static final String MENU_CREATE_URI_POST = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";	//自定义菜单创建接口
	private static final String MENU_GET_URI_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";			//自定义菜单查询接口
	private static final String MENU_DELETE_URI_GET = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";	//自定义菜单删除接口
	
	/**
	 * 自定义菜单创建接口
	 * 自定义菜单能够帮助公众号丰富界面，让用户更好更快地理解公众号的功能
	 * @param accessToken
	 * @param reqJson (菜单的json结果)
	 * @return
	 */
	public static String createMenu(String accessToken, String reqJson){
		String result = null;
		try {
			result = HttpClientUtils.Post(MENU_CREATE_URI_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 自定义菜单查询接口
	 * 使用接口创建自定义菜单后，开发者还可使用接口查询自定义菜单的结构。
	 * @param accessToken
	 * @return
	 */
	public static String getMenu(String accessToken){
		String result = null;
        
		try {
			result = HttpClientUtils.Get(MENU_GET_URI_GET+accessToken);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 自定义菜单删除接口
	 * 使用接口创建自定义菜单后，开发者还可使用接口删除当前使用的自定义菜单。
	 * @param accessToken
	 * @return
	 */
	public static String deleteMenu(String accessToken){
		String result = null;
        
		try {
			result = HttpClientUtils.Get(MENU_DELETE_URI_GET+accessToken);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
