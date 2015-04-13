package com.wdxxl.wechat.service;

import java.util.List;

import com.wdxxl.wechat.model.Token;

public interface ITokenService {
	/**
	 * Job Schedule very 90mins to refresh access token.
	 */
	void retrieveNewAccessToken();
	/**
	 * Manually refresh access token
	 * @param token
	 */
	void insertToken(Token token);
	List<Token> retrieveTokenList();
}
