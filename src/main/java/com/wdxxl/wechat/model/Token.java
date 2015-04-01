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
@Table(name = "WC_TOKEN")
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "sequence",
					parameters = {@Parameter(name = "sequence",value="seq_token")})
	@GeneratedValue(generator = "idGenerator")
	private Long id;
	
	@Column(length=512,name="access_token")
	private String accessToken;
	
	@Column(name="create_time")
	private Date createTime;
	
	public Token() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
