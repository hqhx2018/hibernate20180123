package com.hqhx.model.onetoone;

import java.util.Date;
import java.util.Set;

public class Emp {

	private Integer empno;
	private String ename;
	private String sex;
	private String job;
	private Emp mgr;//表示当前员工的上司
	private Set<Emp> emps;//表示当前员工的下属
	private Double salary;
	private Dept dept;//员工所在的部门
	private Date hiredate;//入职日期
	
	
	
	public Emp(Integer empno, String ename, String sex, String job,
			Double salary, Date hiredate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sex = sex;
		this.job = job;
		this.salary = salary;
		this.hiredate = hiredate;
	}
	public Set<Emp> getEmps() {
		return emps;
	}
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	public void setMgr(Emp mgr) {
		this.mgr = mgr;
	}
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Emp getMgr() {
		return mgr;
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
