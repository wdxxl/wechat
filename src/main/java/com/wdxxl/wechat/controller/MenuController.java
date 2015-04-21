package com.wdxxl.wechat.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.http.MenuUtil;
import com.wdxxl.wechat.service.ITokenService;

/**
 * 自定义菜单管理
 * 
 * @author wdxxl
 *
 */
@Controller
public class MenuController {
	Logger logger = Logger.getLogger(MenuController.class);
	
	@Autowired
	private ITokenService tokenService;
	
	@RequestMapping(method = RequestMethod.PUT, value="/createMenu")
	public @ResponseBody String createMenu(HttpServletRequest request){
		 StringBuffer jb = new StringBuffer();
		 String line = null;
		 try {
			 BufferedReader reader = request.getReader();
			 while ((line = reader.readLine()) != null)
				 jb.append(line);
		 } catch (Exception e) { 
			  e.printStackTrace();
		 }
		String result = MenuUtil.createMenu(tokenService.getCurrentAccessToken(),jb.toString());
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getMenu")
	public @ResponseBody String getMenu(){
		String result = MenuUtil.getMenu(tokenService.getCurrentAccessToken());
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/deleteMenu")
	public @ResponseBody String deleteMenu(){
		String result = MenuUtil.deleteMenu(tokenService.getCurrentAccessToken());
		return result;
	}
	
}
