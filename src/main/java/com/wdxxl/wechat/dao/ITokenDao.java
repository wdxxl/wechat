package com.wdxxl.wechat.dao;

import java.util.List;

import com.wdxxl.wechat.model.Token;

public interface ITokenDao {
	void insertToken(Token token);
	List<Token> retrieveTokenList();
	String getCurrentAccessToken();
}
