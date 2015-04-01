package com.wdxxl.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wdxxl.wechat.dao.ITokenDao;
import com.wdxxl.wechat.model.Token;
import com.wdxxl.wechat.service.ITokenService;

@Repository("tokenService")
public class TokenServiceImpl implements ITokenService {
	@Autowired
	private ITokenDao tokenDao;

	@Override
	public void insertToken(Token token) {
		tokenDao.insertToken(token);
	}

	@Override
	public List<Token> retrieveTokenList() {
		return tokenDao.retrieveTokenList();
	}
	
}
