package com.wdxxl.wechat.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wdxxl.wechat.dao.IChatUserDao;
import com.wdxxl.wechat.model.ChatUser;

@Repository("chatUserDao")
public class ChatUserDaoImpl implements IChatUserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertChatUser(ChatUser chatUser) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(chatUser);
		session.flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ChatUser> retrieveChatUserList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ChatUser");
		List<ChatUser> chatUserList =query.list();
		return chatUserList;
	}
	
}
