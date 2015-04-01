package com.wdxxl.wechat.service;

import java.util.List;

import com.wdxxl.wechat.model.Token;

public interface ITokenService {
	void insertToken(Token token);
	List<Token> retrieveTokenList();
}
