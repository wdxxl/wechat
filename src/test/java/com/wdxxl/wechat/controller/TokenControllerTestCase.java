package com.wdxxl.wechat.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.wdxxl.wechat.util.SpringTransactionalTestCase;

@ContextConfiguration(locations = { "/spring-mvc-servlet.xml" })
public class TokenControllerTestCase extends SpringTransactionalTestCase {

	
	@Autowired
	private TokenController tokenController;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Ignore
	@Test
	public void testGetChatUser() {
		System.out.println(tokenController.getCurrentAccessToken());
	}
}
