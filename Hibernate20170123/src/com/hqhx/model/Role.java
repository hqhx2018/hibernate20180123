package com.hqhx.model;

import java.util.Set;

public class Role {

	private Integer rid;
	private String rname;
	private Set<Module> modules;//该角色拥有的所有模块
	
	public Role(Integer rid, String rname) {
		super();
		this.rid = rid;
		this.rname = rname;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	
	
	
}
