package com.hqhx.model;

import java.util.Date;

public class Emp {

	private Integer empno;
	private String ename;
	private String sex;
	private String job;
	private Integer mgr;
	private Double salary;
	private Dept dept;//员工所在的部门
	private Date hiredate;//入职日期
	
	public Emp(Integer empno, String ename, String sex, String job,
			Integer mgr, Double salary, Date hiredate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sex = sex;
		this.job = job;
		this.mgr = mgr;
		this.salary = salary;
		this.hiredate = hiredate;
	}
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(Integer empno, String ename, String sex, String job,
			Integer mgr, Double salary, Dept dept, Date hiredate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sex = sex;
		this.job = job;
		this.mgr = mgr;
		this.salary = salary;
		this.dept = dept;
		this.hiredate = hiredate;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getMgr() {
		return mgr;
	}
	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	/*@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", sex=" + sex
				+ ", job=" + job + ", mgr=" + mgr + ", salary=" + salary
				+ ", dept=" + dept + ", hiredate=" + hiredate + "]";
	}*/
	
	
	
	
}
