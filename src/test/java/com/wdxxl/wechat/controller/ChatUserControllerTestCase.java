package com.wdxxl.wechat.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.wdxxl.wechat.util.SpringTransactionalTestCase;

@ContextConfiguration(locations = { "/spring-mvc-servlet.xml" })
public class ChatUserControllerTestCase extends SpringTransactionalTestCase {

//	private final MockHttpServletRequest request = new MockHttpServletRequest();
//	private final MockHttpServletResponse response = new MockHttpServletResponse();
	
	@Autowired
	private ChatUserController chatUserController;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Ignore
	@Test
	public void testGetChatUser() {
		System.out.println(chatUserController.retrieveChatUserList());
	}
}
