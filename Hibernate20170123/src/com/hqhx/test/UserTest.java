package com.hqhx.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hqhx.model.User;

public class UserTest {

	
private SessionFactory sf=null;
	
	@Before
	public void before(){
		Configuration cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
	}
	
	
	@Test
	public void listUser(){
		Session session=sf.openSession();
		User user=(User) session.get(User.class, 1);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getDetail().getEmail());
	}
	
	@After
	public void after(){
		sf.close();
	}
}
