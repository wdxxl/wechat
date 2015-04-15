package com.wdxxl.wechat.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wdxxl.wechat.dao.ITextMsgRecordDao;
import com.wdxxl.wechat.model.TextMsgRecord;

@Repository("textMsgRecordDao")
public class TextMsgRecordDaoImpl implements ITextMsgRecordDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertTextMsgRecord(TextMsgRecord textMsgRecord) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(textMsgRecord);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TextMsgRecord> retrieveTextMsgRecordList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TextMsgRecord");
		List<TextMsgRecord> textMsgRecordList = query.list();
		return textMsgRecordList;
	}
	
}
