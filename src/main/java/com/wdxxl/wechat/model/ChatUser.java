package com.wdxxl.wechat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * The persistent class for the DERBYLOOKUP database table.
 * @XmlRootElement for generate XML results
 */
@XmlRootElement
@Entity
@Table(name = "WC_CHAT_USER")
public class ChatUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "sequence",
					parameters = {@Parameter(name = "sequence",value="seq_chatuser")})
	@GeneratedValue(generator = "idGenerator")
	private Long id;
	
	@Column(length=128,name="open_id")
	private String openid;
	
	@Column(length=128,name="name")
	private String name;
	
	@Column(length=20,name="phone")
	private String phone;
	
	@Column(length=20,name="group_Id")
	private String groupid;
	
	@Column(nullable=true)
	@Type(type="yes_no")
	private Boolean confirmed;

	public ChatUser() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public Boolean getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}


}
