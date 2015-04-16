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
		Session session = sessionFactory.getCurrentSession();
		session.persist(token);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Token> retrieveTokenList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Token");
		List<Token> tokenList = query.list();
		return tokenList;
	}

	@Override
	public String getCurrentAccessToken() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select accessToken from Token token order by token.createTime desc");
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<String> tokenList = query.list();
		if(tokenList!=null&&tokenList.size()>=1){
			return tokenList.get(0);
		}else{
			return null;
		}
	}
	
}
