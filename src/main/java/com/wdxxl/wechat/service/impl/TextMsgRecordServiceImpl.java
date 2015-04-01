package com.wdxxl.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wdxxl.wechat.dao.ITextMsgRecordDao;
import com.wdxxl.wechat.model.TextMsgRecord;
import com.wdxxl.wechat.service.ITextMsgRecordService;

@Repository("textMsgRecordService")
public class TextMsgRecordServiceImpl implements ITextMsgRecordService {
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
