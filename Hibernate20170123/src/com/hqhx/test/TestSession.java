package com.hqhx.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hqhx.model.Dept;
import com.hqhx.model.Emp;

public class TestSession {

private SessionFactory sf=null;
	
	@Before
	public void before(){
		Configuration cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
	}
	
	@Test
	public void testSession() {
		Session session1=sf.getCurrentSession();
		session1.beginTransaction().commit();
		Session session2=sf.getCurrentSession();
		System.out.println(session1==session2);
	}
	
	
	
	@Test
	public void testListDept(){
		Session session=sf.openSession();
		//一级缓存和二级缓存中都有Dept对象
		Dept dept=(Dept) session.get(Dept.class, 10);
		System.out.println(dept.getDeptno());
		System.out.println(dept.getDname());
		System.out.println(dept.getLoc());
		System.out.println(dept.getEmps());
		
		Dept dept1=(Dept) session.get(Dept.class, 10);
		System.out.println(dept1.getDeptno());
		System.out.println(dept1.getDname());
		System.out.println(dept1.getLoc());
		System.out.println(dept1.getEmps());
		session.close();
		
		Session s1=sf.openSession();
		Dept dept2=(Dept) s1.get(Dept.class, 10);
		System.out.println(dept2.getDeptno());
		System.out.println(dept2.getDname());
		System.out.println(dept2.getLoc());
		//System.out.println(dept2.getEmps());
		Set<Emp> emps=dept2.getEmps();
		for (Emp emp : emps) {
			System.out.println(emp.getEmpno());
		}
	}

	@After
	public void after(){
		sf.close();
	}
}
