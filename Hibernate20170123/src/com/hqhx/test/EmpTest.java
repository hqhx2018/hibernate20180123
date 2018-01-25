package com.hqhx.test;

import java.util.Date;
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
	
	
	@Test
	public void testAddEmp(){
		Session session=sf.openSession();
		//����Ա��
		Emp e=new Emp(1002,"����","Ů","����",5000.0,new Date());
		Dept dept=new Dept(12,"��ѧ��","����");
		Emp m=(Emp) session.get(Emp.class, 1001);
		e.setMgr(m);
		
		//session.save(dept);
		//����Ա������һ�����Ĳ���
		e.setDept(dept);
		session.save(e);
		session.beginTransaction().commit();
		session.close();
	}
	
	
	@Test
	public void testUpdateEmp(){
		Session session=sf.openSession();
		Emp e=new Emp(2000,"����","Ů","����",5000.0,new Date());
		Dept dept=new Dept();
		dept.setDeptno(27);
		e.setDept(dept);
		session.update(e);
		session.beginTransaction().commit();
		session.close();
	}

	
	
	@Test
	public void testdeptEmp(){
		Session session=sf.openSession();
		Emp e=new Emp(2000,"����","Ů","����",5000.0,new Date());
		Dept dept=new Dept();
		dept.setDeptno(27);
		e.setDept(dept);
		session.delete(e);
		session.beginTransaction().commit();
		session.close();
	}
	
	@After
	public void after(){
		sf.close();
	}
}
