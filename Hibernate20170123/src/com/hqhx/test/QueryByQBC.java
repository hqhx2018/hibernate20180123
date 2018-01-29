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
	
	//1.��ѯ���ж���
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
	//2.����������ѯ
	//�Ƽ�ʹ��session.get()����session.load()
	@Test
	public void findEmpById(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria("com.hqhx.model.Emp");
		//����һ����������
		Criterion criterion=Restrictions.eq("empno", 1002);
		//�������
		c.add(criterion);
		List<Emp> emps=c.list();
		if(emps.size()>0){
			System.out.println(emps.get(0));
		}
		session.close();
	}
	//3.�����������Բ�ѯ
	@Test
	public void findEmpByPropertys(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		Criterion t1=Restrictions.eq("dname", "ss");
		Criterion t2=Restrictions.gt("deptno", 50);
		LogicalExpression l=Restrictions.and(t1, t2);
		//�������
		c.add(l);
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	//4.�ۺϺ�����ѯ
	@Test
	public void function(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		CountProjection t1=Projections.count("deptno");
		//����ͶӰ��ѯ
		c.setProjection(t1);
		Long count=(Long) c.uniqueResult();
		System.out.println(count);
		session.close();
	}
	
	//5.�����ѯ
	//��ѯ������Ա������
	@Test
	public void groupBy(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Emp.class);
		Projection p1=Projections.groupProperty("dept.deptno");
		Projection p2=Projections.count("empno");
		Projection p4=Projections.count("dept.deptno");
		Projection p3=Projections.projectionList().add(p1).add(p2).add(p4);
		
		//����ͶӰ
		c.setProjection(p3);
		List<Object[]> aa=c.list();
		for (Object[] objects : aa) {
			System.out.println(objects[0]+" "+objects[1]);
		}
		session.close();
	}
	//6.����
	@Test
	public void orderBy(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		//���ղ��ű�Ž�������
		c.addOrder(Order.desc("deptno"));
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	//7.��ҳ
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
	
	
	//8.ģ��ƥ���ѯ
	@Test
	public void like(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		//������������
		//Criterion t1=Restrictions.like("dname", "%s%");
		Criterion t1=Restrictions.like("dname", "s",MatchMode.START);
		//�������
		c.add(t1);
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	//9.������Ӳ�ѯ
	@Test
	public void duobiao(){
		Session session=sf.openSession();
		Criteria c=session.createCriteria(Dept.class);
		List<Dept> depts=c.list();
		for (Dept dept : depts) {
			System.out.println(dept);
			//��ѯ������Ա��
			Set<Emp> emps=dept.getEmps();
			for (Emp emp : emps) {
				System.out.println(emp);
			}
		}
		session.close();
	}
	
	//10.ͶӰ��ѯ
	/**
	 * ����ѯ�����ֶ�ʱ
	 * List<Emp> emps;
	 * 
	 * ����ѯ����ֶ�ʱ��
	 * List<Object[]>
	 * 
	 * ����ѯһ���ֶ�ʱ��
	 * List<Object>
	 */
	//ͶӰ��ѯ����ѯ����ֶ�List<Object[]>
	@Test
	public void testListEmpPropertys(){
		Session session=sf.openSession();
		//����Criteria����
		Criteria c=session.createCriteria(Emp.class);
		//
		//����һ��Projections����
		Projection p1=Projections.property("empno");
		Projection p2=Projections.property("ename");
		Projection p3=Projections.property("sex");
		Projection p4=Projections.property("salary");
		
		Projection p=Projections.projectionList()
		.add(p1)
		.add(p2)
		.add(p3)
		.add(p4);
		//����ͶӰ
		c.setProjection(p);
		List<Object[]> datas= c.list();
		for (Object[] objects : datas) {
			System.out.println(objects[0]+" "+objects[1]+" "+objects[2]+" "+objects[3]);
		}
		session.close();
	}
	//ͶӰ��ѯ����ѯһ���ֶ�List<Object>
	@Test
	public void testListEmpProperty(){
		Session session=sf.openSession();
		session.close();
	}
	
	
	
	//�������Ӳ�ѯ
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
