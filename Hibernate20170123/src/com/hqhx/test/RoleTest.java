package com.hqhx.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hqhx.model.Module;
import com.hqhx.model.Role;

public class RoleTest {

private SessionFactory sf=null;
	
	@Before
	public void before(){
		Configuration cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
	}
	
	@Test
	public void testFindModule(){
		Session session=sf.openSession();
		Role r=(Role) session.get(Role.class, 1);
		System.out.println(r);
		//��ȡ��role��ɫ�µ�Ȩ��ģ��
		Set<Module> ms=r.getModules();
		for (Module module : ms) {
			System.out.println(module.getMname());
		}
		
		
		//�����ݿ��л�ȡһ���µ�ģ��
		Module m=(Module) session.get(Module.class, 4);
		ms.add(m);
		r.setModules(ms);
		session.update(r);
		session.beginTransaction().commit();
		session.close();
	}
	
	@After
	public void after(){
		sf.close();
	}
}
