package com.hqhx.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CountProjection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hqhx.model.Dept;
import com.hqhx.model.Emp;

public class QueryByQBC {

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
		Criteria c=session.createCriteria(Emp.class);
		List<Emp> emps=c.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
		session.close();
	}
	//2.根据主键查询
	//推荐使用session.get()或者session.load()
	@Test
	public void findEmpById(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria("com.hqhx.model.Emp");
		//构建一个条件对象
		Criterion criterion=Restrictions.eq("empno", 1002);
		//添加条件
		c.add(criterion);
		List<Emp> emps=c.list();
		if(emps.size()>0){
			System.out.println(emps.get(0));
		}
		session.close();
	}
	//3.根据其它属性查询
	@Test
	public void findEmpByPropertys(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		Criterion t1=Restrictions.eq("dname", "ss");
		Criterion t2=Restrictions.gt("deptno", 50);
		LogicalExpression l=Restrictions.and(t1, t2);
		//添加条件
		c.add(l);
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	//4.聚合函数查询
	@Test
	public void function(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		CountProjection t1=Projections.count("deptno");
		//设置投影查询
		c.setProjection(t1);
		Long count=(Long) c.uniqueResult();
		System.out.println(count);
		session.close();
	}
	
	//5.分组查询
	//查询各部门员工个数
	@Test
	public void groupBy(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Emp.class);
		Projection p1=Projections.groupProperty("dept.deptno");
		Projection p2=Projections.count("empno");
		Projection p4=Projections.count("dept.deptno");
		Projection p3=Projections.projectionList().add(p1).add(p2).add(p4);
		
		//设置投影
		c.setProjection(p3);
		List<Object[]> aa=c.list();
		for (Object[] objects : aa) {
			System.out.println(objects[0]+" "+objects[1]);
		}
		session.close();
	}
	//6.排序
	@Test
	public void orderBy(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		//按照部门编号降序排序
		c.addOrder(Order.desc("deptno"));
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	//7.分页
	@Test
	public void ListDeptByPager(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		c.setFirstResult(0);
		c.setMaxResults(5);
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	
	//8.模糊匹配查询
	@Test
	public void like(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		//创建条件对象
		//Criterion t1=Restrictions.like("dname", "%s%");
		Criterion t1=Restrictions.like("dname", "s",MatchMode.START);
		//添加条件
		c.add(t1);
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	//9.多表连接查询
	@Test
	public void duobiao(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
			//查询部门下员工
			Set<Emp> emps=dept.getEmps();
			for (Emp emp : emps) {
				System.out.println(emp);
			}
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
		//创建Criteria对象
		Criteria c=session.createCriteria(Emp.class);
		//
		//创建一个Projections对象
		Projection p1=Projections.property("empno");
		Projection p2=Projections.property("ename");
		Projection p3=Projections.property("sex");
		Projection p4=Projections.property("salary");
		
		Projection p=Projections.projectionList()
		.add(p1)
		.add(p2)
		.add(p3)
		.add(p4);
		//设置投影
		c.setProjection(p);
		List<Object[]> datas= c.list();
		for (Object[] objects : datas) {
			System.out.println(objects[0]+" "+objects[1]+" "+objects[2]+" "+objects[3]);
		}
		session.close();
	}
	//投影查询，查询一个字段List<Object>
	@Test
	public void testListEmpProperty(){
		Session session=sf.openSession();
		session.close();
	}
	
	
	
	//按照例子查询
	@Test
	public void QueryByExample(){
		Session session=sf.openSession();
		Criteria criteria = session.createCriteria(Dept.class);
		Dept d=new Dept();
		d.setDname("ss");
		d.setLoc("ss");
		criteria.add(Example.create(d));
		//criteria.add(Restrictions.sqlRestriction("loc='ss'"));
		List<Dept> depts=criteria.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	
	
	
	@After
	public void after(){
		sf.close();
	}
}
