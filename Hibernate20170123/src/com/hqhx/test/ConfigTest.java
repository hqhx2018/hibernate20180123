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

	//���
	@Test
	public void configTest(){
		//��ȡ�����ļ�
		Configuration cfg=new Configuration().configure();
		//����sessionFactory����
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//��ȡһ��session
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		//����һ��Dept����
		Dept dept=new Dept(10,"��Ӫ��","����");//˲ʱ̬
		session.save(dept);//�־û�״̬
		ts.commit();//��session�е�����ͬ�������ݿ���
		
		//select deptno,dname,loc from dept where deptno=11
		Dept d=(Dept) session.get(Dept.class, 11);
		System.out.println(d);
		//�ͷ���Դ
		session.close();
		//dept d�Դ����ѹ�״̬
		sf.close();
	}
	
	
	//ɾ��
	@Test
	public void deleteTest(){
		//��ȡ�����ļ�
		Configuration cfg=new Configuration().configure();
		//����sessionFactory����
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//��ȡһ��session
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		//����һ��Dept����
		Dept dept=new Dept();
		dept.setDeptno(21);
		session.delete(dept);
		ts.commit();
		//�ͷ���Դ
		session.close();
		sf.close();
	}

	
	//�޸Ĳ���
	@Test
	public void updateTest(){
		//��ȡ�����ļ�
		Configuration cfg=new Configuration().configure();
		//����sessionFactory����
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//��ȡһ��session
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		//����һ��Dept����
		Dept dept=new Dept(99,"�з���","�Ϻ�");
		session.update(dept);
		ts.commit();
		//�ͷ���Դ
		session.close();
		sf.close();
	}
	
	//��ѯ���в���
	@Test
	public void listTest(){
		//��ȡ�����ļ�
		Configuration cfg=new Configuration().configure();
		//����sessionFactory����
		SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		//��ȡһ��session
		Session session=sf.openSession();
		Query query=session.createQuery("from Dept");
		List<Dept> depts=query.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		//�ͷ���Դ
		session.close();
		sf.close();
	}
	
}
