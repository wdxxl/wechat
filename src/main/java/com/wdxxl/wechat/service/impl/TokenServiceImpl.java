package com.wdxxl.wechat.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wdxxl.wechat.dao.ITokenDao;
import com.wdxxl.wechat.model.Token;
import com.wdxxl.wechat.service.ITokenService;
import com.wdxxl.wechat.utils.WeChatGetPostUtils;

@Repository("tokenService")
@Transactional
public class TokenServiceImpl implements ITokenService {
	Logger logger = Logger.getLogger(TextMsgRecordServiceImpl.class);
	
	@Autowired
	private ITokenDao tokenDao;


	@Override
	public void retrieveNewAccessToken() {
		try {
			logger.debug("Retrieve the New Access Token by Spring Schedule Job!");
			String accessTokenStr = WeChatGetPostUtils.getInstance().retrieveAccessToken();
			Token token = new Token();
			token.setAccessToken(accessTokenStr);
			token.setCreateTime(new Date());
			tokenDao.insertToken(token);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertToken(Token token) {
		logger.debug("Manually Retrieve the New Access Token!");
		tokenDao.insertToken(token);
	}

	@Override
	public List<Token> retrieveTokenList() {
		return tokenDao.retrieveTokenList();
	}
	
}
