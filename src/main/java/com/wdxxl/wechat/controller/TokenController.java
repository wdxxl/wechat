package com.wdxxl.wechat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.model.Token;
import com.wdxxl.wechat.service.ITokenService;
import com.wdxxl.wechat.utils.WeChatGetPostUtils;


@Controller
public class TokenController {
	Logger logger = Logger.getLogger(TokenController.class);
	
	@Autowired
	private ITokenService tokenService;
	
	@RequestMapping(method = RequestMethod.GET, value="/retrieveTokenList")
	public @ResponseBody List<Token> retrieveTokenList(){
		List<Token> tokenList = new ArrayList<Token>();
		try {
			tokenList = tokenService.retrieveTokenList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenList;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/manuallyRefreshToken")
	public @ResponseBody String manuallyRefreshToken(
				@RequestParam(value="appid", required = true) String appid,
				@RequestParam(value="secret", required = true) String secret){
		String accessTokenStr = null;
		try {
			accessTokenStr = WeChatGetPostUtils.getInstance().retrieveAccessToken(appid,secret);
			Token token = new Token();
			token.setAccessToken(accessTokenStr);
			token.setCreateTime(new Date());
			tokenService.insertToken(token);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return accessTokenStr;
				
	}
	
}
