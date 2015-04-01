package com.wdxxl.wechat.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wdxxl.wechat.dao.ITokenDao;
import com.wdxxl.wechat.model.Token;

@Repository("tokenDao")
public class TokenDaoImpl implements ITokenDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertToken(Token token) {
		Session session = sessionFactory.openSession();
		session.persist(token);
		session.flush();
		session.close();//session need to be closed, returned to connection pool.
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Token> retrieveTokenList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Token");
		List<Token> tokenList = query.list();
		session.close();//session need to be closed, returned to connection pool.
		return tokenList;
	}
	
}
