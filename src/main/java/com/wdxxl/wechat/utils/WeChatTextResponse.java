package com.wdxxl.wechat.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name="xml")
@XmlType(propOrder = { "toUserName", "fromUserName", "createTime", "msgType","content"}) 
public class WeChatTextResponse{
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "ToUserName",required = true) 
	private String toUserName;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "FromUserName",required = true) 
	private String fromUserName;
	
	@XmlElement(name = "CreateTime",required = true) 
	private long createTime;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "MsgType",required = true) 
	private String msgType;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "Content",required = true)  
	private String content;
	
	public WeChatTextResponse() {
		super();
	}

	public String getResponseXML(){
		return "<xml>" +
				"<ToUserName><![CDATA["+ fromUserName +"]]></ToUserName>" +
				"<FromUserName><![CDATA["+ toUserName +"]]></FromUserName>" +
				"<CreateTime>"+ createTime +"</CreateTime>" +
				"<MsgType><![CDATA["+ msgType +"]]></MsgType>" +
				"<Content><![CDATA["+ content +"]]></Content>" +
				"</xml>";  
	}

	
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
