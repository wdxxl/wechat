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
		Session session = sessionFactory.openSession();
		session.persist(chatUser);
		session.flush();
		session.close();//session need to be closed, returned to connection pool.
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ChatUser> retrieveChatUserList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ChatUser");
		List<ChatUser> chatUserList =query.list();
		session.close();//session need to be closed, returned to connection pool.
		return chatUserList;
	}
	
}
