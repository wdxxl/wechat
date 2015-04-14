package com.wdxxl.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.model.ChatUser;
import com.wdxxl.wechat.service.IChatUserService;


@Controller
public class ChatUserController {
	Logger logger = Logger.getLogger(ChatUserController.class);
	
	@Autowired
	private IChatUserService chatUserService;
	
	@RequestMapping(method = RequestMethod.GET, value="/retrieveChatUserList")
	public @ResponseBody List<ChatUser> retrieveChatUserList(){
		List<ChatUser> chatUserList = new ArrayList<ChatUser>();
		try {
			chatUserList = chatUserService.retrieveChatUserList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatUserList;
	}
}
