package com.wdxxl.wechat.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wdxxl.wechat.dao.ITextMsgRecordDao;
import com.wdxxl.wechat.model.TextMsgRecord;
import com.wdxxl.wechat.service.ITextMsgRecordService;

@Repository("textMsgRecordService")
@Transactional
public class TextMsgRecordServiceImpl implements ITextMsgRecordService {
	Logger logger = Logger.getLogger(TextMsgRecordServiceImpl.class);
	
	@Autowired
	private ITextMsgRecordDao textMsgRecordDao;

	@Override
	public void insertTextMsgRecord(TextMsgRecord textMsgRecord) {
		textMsgRecordDao.insertTextMsgRecord(textMsgRecord);		
	}

	@Override
	public List<TextMsgRecord> retrieveTextMsgRecordList() {
		return textMsgRecordDao.retrieveTextMsgRecordList();
	}
}
