package com.hqhx.model;

public class User {

	private Integer uid;
	private String username;
	private String password;
	private Detail detail;//该用户的详细信息
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer uid, String username, String password) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
	}

	public User(Integer uid, String username, String password, Detail detail) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.detail = detail;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
