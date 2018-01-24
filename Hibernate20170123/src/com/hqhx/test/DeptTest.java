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
	
	//session的常用方法，session.get() session.load()
	/**session.get() session.load()
	 *相同点
	 * 两个方法都是根据主标识符查询一个实体对象，
	 * 参数：1.要查询的对象的Class对象，2.主标识符
	 * 
	 * 不同：
	 * session.get()
	 * 1.立即加载
	 * 2.如果查询的实体不存在则返回一个null
	 * 3.查询到返回对象
	 * 
	 * session.load()
	 * 1.延迟加载或者懒加载
	 * 2.如果查询的实体不存在则抛出一个ObjectNotFoundException异常
	 * 3.调用session.load()返回一个代理对象
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
	
	
	//把一个对象从session中移除
	@Test
	public void testEvict(){
		Session session=sf.openSession(); 
		Dept dept=(Dept) session.get(Dept.class, 22);
		System.out.println(dept);
		
		//从session中移除dept对象
		session.evict(dept);
		boolean b=session.contains(dept);
		System.out.println("dept是否在session中"+b);
		Dept dept1=(Dept) session.get(Dept.class, 22);
		System.out.println(dept1);
		session.close();
	}
	
	//清除session
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
		
		
		
		//清除session
//		session.clear();
//		Dept dept=(Dept) session.get(Dept.class, 22);
//		System.out.println(dept);
		session.close();
	}
	
	//合并
	@Test
	public void testMerge(){
		Session session=sf.openSession();
		Transaction ts=session.beginTransaction();
		Dept dept=(Dept) session.get(Dept.class,89);
		System.out.println(dept);
		//22	运营部	陕西
		Dept d=new Dept(89,"好","ss");
		//22,"你好","sss"
		session.merge(d);
		ts.commit();
		Dept dept3=(Dept) session.get(Dept.class,89);
		System.out.print(dept3.getDeptno()+"\t");
		System.out.print(dept3.getDname()+"\t");
		System.out.print(dept3.getLoc()+"\t");
		session.close();
	}
	
	
	//动态更新
	@Test
	public void testUpdate(){
		Session session=sf.openSession();
		Dept d=(Dept) session.get(Dept.class,89);		
		d.setDname("你aaa");
		d.setLoc("a");
		session.update(d);
		session.beginTransaction().commit();
	}
	
	
	@After
	public void after(){
		sf.close();
	}
}
