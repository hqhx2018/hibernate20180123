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

public class DeptTest {

	private SessionFactory sf=null;
	
	
	@Before
	public void before(){
		Configuration cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
	}
	
	//session�ĳ��÷�����session.get() session.load()
	/**session.get() session.load()
	 *��ͬ��
	 * �����������Ǹ�������ʶ����ѯһ��ʵ�����
	 * ������1.Ҫ��ѯ�Ķ����Class����2.����ʶ��
	 * 
	 * ��ͬ��
	 * session.get()
	 * 1.��������
	 * 2.�����ѯ��ʵ�岻�����򷵻�һ��null
	 * 3.��ѯ�����ض���
	 * 
	 * session.load()
	 * 1.�ӳټ��ػ���������
	 * 2.�����ѯ��ʵ�岻�������׳�һ��ObjectNotFoundException�쳣
	 * 3.����session.load()����һ���������
	 * 
	 * 
	 */
	@Test
	public void testSessionGet(){
		Session session=sf.openSession();
		Dept dept=(Dept) session.get(Dept.class, 56);
		System.out.println(dept);
		session.close();
	}
	
	
	@Test
	public void testSessionLoad(){
		Session session=sf.openSession();
		Dept dept=(Dept) session.load(Dept.class, 22);
		
		System.out.println(dept.getDeptno());
		System.out.println(dept.getDname());
		System.out.println(dept.getLoc());
		session.close();
	}
	
	
	//��һ�������session���Ƴ�
	@Test
	public void testEvict(){
		Session session=sf.openSession(); 
		Dept dept=(Dept) session.get(Dept.class, 22);
		System.out.println(dept);
		
		//��session���Ƴ�dept����
		session.evict(dept);
		boolean b=session.contains(dept);
		System.out.println("dept�Ƿ���session��"+b);
		Dept dept1=(Dept) session.get(Dept.class, 22);
		System.out.println(dept1);
		session.close();
	}
	
	//���session
	@Test
	public void testClear(){
		Session session=sf.openSession(); 
		Query q=session.createQuery("from Dept");
		//List<Dept> depts=q.list();
		
//		for (Dept dept : depts) {
//			System.out.print(dept.getDeptno()+"\t");
//			System.out.print(dept.getDname()+"\t");
//			System.out.print(dept.getLoc()+"\t");
//			System.out.println();
//		}
		
		Iterator it=q.iterate();
		while(it.hasNext()){
			Dept dept=(Dept) it.next();
			System.out.print(dept.getDeptno()+"\t");
			System.out.print(dept.getDname()+"\t");
			System.out.print(dept.getLoc()+"\t");
			System.out.println();
		}
		
		
		
		//���session
//		session.clear();
//		Dept dept=(Dept) session.get(Dept.class, 22);
//		System.out.println(dept);
		session.close();
	}
	
	//�ϲ�
	@Test
	public void testMerge(){
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		Dept dept=(Dept) session.get(Dept.class,89);
		System.out.println(dept);
		//22	��Ӫ��	����
		Dept d=new Dept(89,"��","ss");
		//22,"���","sss"
		session.merge(d);
		ts.commit();
		Dept dept3=(Dept) session.get(Dept.class,89);
		System.out.print(dept3.getDeptno()+"\t");
		System.out.print(dept3.getDname()+"\t");
		System.out.print(dept3.getLoc()+"\t");
		session.close();
	}
	
	
	//��̬����
	@Test
	public void testUpdate(){
		Session session=sf.openSession();
		Dept d=(Dept) session.get(Dept.class,89);		
		d.setDname("��aaa");
		d.setLoc("a");
		session.update(d);
		session.beginTransaction().commit();
	}
	
	
	@After
	public void after(){
		sf.close();
	}
}
