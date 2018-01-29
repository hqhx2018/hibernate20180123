package com.hqhx.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hqhx.model.Dept;
import com.hqhx.model.Emp;

public class QueryByHQL {

	private SessionFactory sf=null;
	
	@Before
	public void before(){
		Configuration cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
	}
	
	//1.查询所有对象
	@Test
	public void testListEmp(){
		Session session=sf.openSession();
		Query q=session.createQuery("from Emp");
		List<Emp> emps=q.list();
		for (Emp emp : emps) {
			System.out.println(emp.getEmpno());
			System.out.println(emp.getEname());
			System.out.println(emp.getSex());
			System.out.println(emp.getJob());
			System.out.println(emp.getHiredate());
			System.out.println(emp.getSalary());
			System.out.println(emp.getMgr());
			System.out.println(emp.getDept());
		}
		session.close();
	}
	//2.根据主键查询
	//推荐使用session.get()或者session.load()
	@Test
	public void findEmpById(){
		Session session=sf.openSession();
		//Query query=session.createQuery("from Emp where empno=?");
		//设置参数
		//query.setParameter(0, 1002);
		
		Query query=session.createQuery("from Emp where empno=:eno");
		//设置参数
		query.setParameter("eno", 1002);
		
		List<Emp> emps=query.list();
		if(emps.size()>0){
			System.out.println(emps.get(0));
		}
		session.close();
	}
	//3.根据其它属性查询
	@Test
	public void findEmpByPropertys(){
		Session session=sf.openSession();
		
		
		Query query=session.createQuery("from Emp where ename=? and salary>?");
		//设置参数
		query.setParameter(0, "周兰");
		query.setParameter(1, 4000.0);
		
		List<Emp> emps=query.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
		session.close();
	}
	
	//4.聚合函数查询
	@Test
	public void function(){
		Session session=sf.openSession();
		Query query=session.createQuery("select count(empno) from Emp");
		//如果查询的数据是唯一的值，则需要使用Query接口的uniqueResult()获取
		Long count=(Long) query.uniqueResult();
		System.out.println(count);
		session.close();
	}
	
	//5.分组查询
	//查询各部门员工个数
	@Test
	public void groupBy(){
		Session session=sf.openSession();
		Query query=session.createQuery("select count(empno),dept.dname from Emp group by dept.dname having dept.dname=?");
		query.setParameter(0, "运营部");	
		List<Object[]> emps=query.list();
		for (Object[] objects : emps) {
			System.out.println(objects[0]+" "+objects[1]);
		}
		session.close();
	}
	//6.排序
	@Test
	public void orderBy(){
		Session session=sf.openSession();
		Query query=session.createQuery("from Emp order by empno desc");
		//设置参数
		//query.setParameter(0, "%周%");
		
		List<Emp> emps=query.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
		session.close();
	}
	//7.分页
	@Test
	public void ListDeptByPager(){
		Session session=sf.openSession();
		Query query=session.createQuery("from Dept");
		//设置两个参数
		//偏移量
		query.setFirstResult(0);
		//每页显示数据量
		query.setMaxResults(3);
		List<Dept> depts=query.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	
	//8.模糊匹配查询
	@Test
	public void like(){
		Session session=sf.openSession();
		Query query=session.createQuery("from Emp where ename like ?");
		//设置参数
		query.setParameter(0, "%周%");
		
		List<Emp> emps=query.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
		session.close();
	}
	//9.多表连接查询
	@Test
	public void duobiao(){
		Session session=sf.openSession();
		Query query=session.createQuery("select dept from Emp");
		List<Dept> depts=query.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	//10.投影查询
	/**
	 * 当查询所有字段时
	 * List<Emp> emps;
	 * 
	 * 当查询多个字段时：
	 * List<Object[]>
	 * 
	 * 当查询一个字段时：
	 * List<Object>
	 */
	//投影查询，查询多个字段List<Object[]>
	@Test
	public void testListEmpPropertys(){
		Session session=sf.openSession();
		Query q=session.createQuery("select empno,ename,sex,mgr.empno,salary from Emp");
		List<Object[]> emps=q.list();
		for (Object[] objects : emps) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
			System.out.println(objects[2]);
			System.out.println(objects[3]);
			System.out.println(objects[4]);
		}

	}
	//投影查询，查询一个字段List<Object>
	@Test
	public void testListEmpProperty(){
		Session session=sf.openSession();
		Query q=session.createQuery("select ename from Emp");
		List<Object> enames=q.list();
		for (Object object : enames) {
			System.out.println(object);
		}

	}
	
	@After
	public void after(){
		sf.close();
	}
}
