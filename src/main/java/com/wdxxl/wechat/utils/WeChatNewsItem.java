package com.wdxxl.wechat.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlType(propOrder = { "title", "description", "picUrl", "url"})
public class WeChatNewsItem {
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "Title",required = true) 
	private String title;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "Description",required = true) 
	private String description;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "PicUrl",required = true) 
	private String picUrl;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "Url",required = true) 
	private String url;
	
	public WeChatNewsItem() {
		super();
	}
	
	public WeChatNewsItem(String title, String description, String picUrl,
			String url) {
		super();
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}

	public String getNewsItemsXML(){
		return "<item>" +
				"<Title><![CDATA["+ title +"]]></Title>" +
				"<Description><![CDATA["+ description +"]]></Description>" +
				"<PicUrl><![CDATA["+ picUrl +"]]></PicUrl>" +
				"<Url><![CDATA["+ url +"]]></Url>" +
				"</item>";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
