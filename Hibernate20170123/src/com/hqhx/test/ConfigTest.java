package com.hqhx.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hqhx.model.Dept;

public class ConfigTest {

	//添加
	@Test
	public void configTest(){
		//读取配置文件
		Configuration cfg=new Configuration().configure();
		//建立sessionFactory对象
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//获取一个session
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		//创建一个Dept对象
		Dept dept=new Dept(22,"运营部","陕西");
		session.save(dept);
		ts.commit();
		//释放资源
		session.close();
		sf.close();
	}
	
	
	//删除
	@Test
	public void deleteTest(){
		//读取配置文件
		Configuration cfg=new Configuration().configure();
		//建立sessionFactory对象
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//获取一个session
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		//创建一个Dept对象
		Dept dept=new Dept();
		dept.setDeptno(21);
		session.delete(dept);
		ts.commit();
		//释放资源
		session.close();
		sf.close();
	}

	
	//修改部门
	@Test
	public void updateTest(){
		//读取配置文件
		Configuration cfg=new Configuration().configure();
		//建立sessionFactory对象
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//获取一个session
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		//创建一个Dept对象
		Dept dept=new Dept(99,"研发部","上海");
		session.update(dept);
		ts.commit();
		//释放资源
		session.close();
		sf.close();
	}
	
	//查询所有部门
	@Test
	public void listTest(){
		//读取配置文件
		Configuration cfg=new Configuration().configure();
		//建立sessionFactory对象
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//获取一个session
		Session session=sf.openSession();
		Query query=session.createQuery("from Dept");
		List<Dept> depts=query.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		//释放资源
		session.close();
		sf.close();
	}
	
}
