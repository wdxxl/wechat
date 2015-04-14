package com.wdxxl.wechat.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wdxxl.wechat.dao.IChatUserDao;
import com.wdxxl.wechat.model.ChatUser;
import com.wdxxl.wechat.service.IChatUserService;

@Service("chatUserService")
@Transactional
public class ChatUserServiceImpl implements IChatUserService {
	Logger logger = Logger.getLogger(ChatUserServiceImpl.class);
	
	@Autowired
	private IChatUserDao chatUserDao;
	
	@Override
	public void insertChatUser(ChatUser chatUser) {
		chatUserDao.insertChatUser(chatUser);
	}

	@Override
	public List<ChatUser> retrieveChatUserList() {
		return chatUserDao.retrieveChatUserList();
	}
	
}
