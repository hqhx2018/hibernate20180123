package com.hqhx.model;

import java.util.Set;

public class Module {

	private Integer mid;
	private String mname;
	private String murl;
	private Set<Role> roles;//拥有该模块的所有集合
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Module(Integer mid, String mname, String murl) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.murl = murl;
	}
	
}
