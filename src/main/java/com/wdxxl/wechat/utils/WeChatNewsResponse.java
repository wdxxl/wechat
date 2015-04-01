package com.wdxxl.wechat.utils;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name="xml")
@XmlType() 
public class WeChatNewsResponse {
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
	
	@XmlElement(name = "ArticleCount",required = true) 
	private Integer articleCount;
	
	@XmlElementWrapper(name = "Articles")  
    @XmlElement(name = "item")  
	private List<WeChatNewsItem> weChatNewsItems;
	
	public WeChatNewsResponse() {
		super();
	}
	
	public String getResponseXML(){
		return "<xml>" +
				"<ToUserName><![CDATA["+ fromUserName +"]]></ToUserName>" +
				"<FromUserName><![CDATA["+ toUserName +"]]></FromUserName>" +
				"<CreateTime>"+ createTime +"</CreateTime>" +
				"<MsgType><![CDATA[news]]></MsgType>" +
				getNewsItemsXML()+
				"</xml>";  
	}
	
	private String getNewsItemsXML(){
		String responseItemsXML = "<ArticleCount>"+ weChatNewsItems.size() +"</ArticleCount>";
		responseItemsXML += "<Articles>";
		for(int i=0;i<weChatNewsItems.size();i++){
			responseItemsXML +=	weChatNewsItems.get(i).getNewsItemsXML();
		}
		responseItemsXML += "</Articles>";
		return responseItemsXML;
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

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public List<WeChatNewsItem> getWeChatNewsItems() {
		return weChatNewsItems;
	}

	public void setWeChatNewsItems(List<WeChatNewsItem> weChatNewsItems) {
		this.weChatNewsItems = weChatNewsItems;
	}
	
}
