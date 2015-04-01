package com.wdxxl.wechat.service;

import java.util.List;

import com.wdxxl.wechat.model.ChatUser;

public interface IChatUserService {
	void insertChatUser(ChatUser chatUser);
	List<ChatUser> retrieveChatUserList();
}
