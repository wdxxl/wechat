package com.wdxxl.wechat.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.http.MessageUtil;
import com.wdxxl.wechat.service.ITokenService;

/**
 * 高级群发接口
 * 
 * @author wdxxl
 *
 */
@Controller
public class MessageController {
	Logger logger = Logger.getLogger(MessageController.class);
	
	@Autowired
	private ITokenService tokenService;
	
	@RequestMapping(method = RequestMethod.PUT, value="/sendAllMessage")
	public @ResponseBody String sendAllMessage(HttpServletRequest request){
		 StringBuffer jb = new StringBuffer();
		 String line = null;
		 try {
			 BufferedReader reader = request.getReader();
			 while ((line = reader.readLine()) != null)
				 jb.append(line);
		 } catch (Exception e) { 
			  e.printStackTrace();
		 }
		String result = MessageUtil.sendAllMessage(tokenService.getCurrentAccessToken(),jb.toString());
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/previewMessage")
	public @ResponseBody String previewMessage(HttpServletRequest request){
		 StringBuffer jb = new StringBuffer();
		 String line = null;
		 try {
			 BufferedReader reader = request.getReader();
			 while ((line = reader.readLine()) != null)
				 jb.append(line);
		 } catch (Exception e) { 
			  e.printStackTrace();
		 }
		String result = MessageUtil.previewMessage(tokenService.getCurrentAccessToken(),jb.toString());
		return result;
	}
	
}
