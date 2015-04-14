package com.wdxxl.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.model.TextMsgRecord;
import com.wdxxl.wechat.service.ITextMsgRecordService;

@Controller
public class TextMsgRecordController {
	Logger logger = Logger.getLogger(TextMsgRecordController.class);
	
	@Autowired
	private ITextMsgRecordService textMsgRecordService;
	
	@RequestMapping(method = RequestMethod.GET, value="/retrieveTextMsgRecordList")
	public @ResponseBody List<TextMsgRecord> retrieveTextMsgRecordList(){
		List<TextMsgRecord> textMsgRecordList = new ArrayList<TextMsgRecord>();
		try {
			textMsgRecordList = textMsgRecordService.retrieveTextMsgRecordList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textMsgRecordList;
	}
}
