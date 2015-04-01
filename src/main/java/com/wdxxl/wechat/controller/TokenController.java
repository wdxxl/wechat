package com.wdxxl.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.model.Token;
import com.wdxxl.wechat.service.ITokenService;


@Controller
public class TokenController 
{

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
}
