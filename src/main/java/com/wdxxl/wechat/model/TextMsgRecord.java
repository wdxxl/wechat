package com.wdxxl.wechat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The persistent class for the DERBYLOOKUP database table.
 * @XmlRootElement for generate XML results
 */
@XmlRootElement
@Entity
@Table(name = "WC_TEXT_MSG_RECORD")
public class TextMsgRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "sequence",
					parameters = {@Parameter(name = "sequence",value="seq_textMsgRecord")})
	@GeneratedValue(generator = "idGenerator")
	private Long id;
	
	@Column(length=128,name="TO_USER_NAME")
	private String toUserName;
	
	@Column(length=128,name="FROM_USER_NAME")
	private String fromUserName;
	
	@Column(length=20,name="MSG_Type")
	private String msgType;
	
	@Column(length=512,name="Content")
	private String content;
	
	@Column(length=100,name="MSG_ID")
	private String msgId;
	
	@Column(name="create_time")
	private Date createTime;
	
	public TextMsgRecord() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
