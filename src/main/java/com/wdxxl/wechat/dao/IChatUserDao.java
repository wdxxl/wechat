package com.wdxxl.wechat.dao;

import java.util.List;

import com.wdxxl.wechat.model.ChatUser;

public interface IChatUserDao {
	void insertChatUser(ChatUser chatUser);
	List<ChatUser> retrieveChatUserList();
}
