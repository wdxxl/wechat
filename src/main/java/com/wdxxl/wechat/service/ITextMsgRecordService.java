package com.wdxxl.wechat.service;

import java.util.List;

import com.wdxxl.wechat.model.TextMsgRecord;

public interface ITextMsgRecordService {
	void insertTextMsgRecord(TextMsgRecord textMsgRecord);
	List<TextMsgRecord> retrieveTextMsgRecordList();
}
