package com.hqhx.model;

public class Detail {

	private Integer did;
	private String trueName;
	private String sex;
	private String email;
	private User user;//用户的登录信息
	
	public Detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Detail(Integer did, String trueName, String sex, String email) {
		super();
		this.did = did;
		this.trueName = trueName;
		this.sex = sex;
		this.email = email;
	}
	public Detail(Integer did, String trueName, String sex, String email,
			User user) {
		super();
		this.did = did;
		this.trueName = trueName;
		this.sex = sex;
		this.email = email;
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
