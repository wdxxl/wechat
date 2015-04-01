package com.wdxxl.wechat.dao;

import java.util.List;

import com.wdxxl.wechat.model.TextMsgRecord;

public interface ITextMsgRecordDao {
	void insertTextMsgRecord(TextMsgRecord textMsgRecord);
	List<TextMsgRecord> retrieveTextMsgRecordList();
}
