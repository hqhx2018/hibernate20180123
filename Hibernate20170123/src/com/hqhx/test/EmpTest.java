package com.hqhx.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hqhx.model.Dept;
import com.hqhx.model.Emp;

public class EmpTest {

	private SessionFactory sf=null;
	
	
	@Before
	public void before(){
		Configuration cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
	}
	
	
	@Test
	public void testListEmp(){
		Session session=sf.openSession();
		Query q=session.createQuery("from Emp");
		List<Emp> emps=q.list();
		for (Emp emp : emps) {
			System.out.println(emp.getEname());
			System.out.println(emp.getDept().getDeptno());
			System.out.println(emp.getDept().getDname());
		}
		session.close();
	}
	
	
	@After
	public void after(){
		sf.close();
	}
}
