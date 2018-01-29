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
	
	//1.��ѯ���ж���
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
	//2.����������ѯ
	//�Ƽ�ʹ��session.get()����session.load()
	@Test
	public void findEmpById(){
		Session session=sf.openSession();
		//Query query=session.createQuery("from Emp where empno=?");
		//���ò���
		//query.setParameter(0, 1002);
		
		Query query=session.createQuery("from Emp where empno=:eno");
		//���ò���
		query.setParameter("eno", 1002);
		
		List<Emp> emps=query.list();
		if(emps.size()>0){
			System.out.println(emps.get(0));
		}
		session.close();
	}
	//3.�����������Բ�ѯ
	@Test
	public void findEmpByPropertys(){
		Session session=sf.openSession();
		
		
		Query query=session.createQuery("from Emp where ename=? and salary>?");
		//���ò���
		query.setParameter(0, "����");
		query.setParameter(1, 4000.0);
		
		List<Emp> emps=query.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
		session.close();
	}
	
	//4.�ۺϺ�����ѯ
	@Test
	public void function(){
		Session session=sf.openSession();
		Query query=session.createQuery("select count(empno) from Emp");
		//�����ѯ��������Ψһ��ֵ������Ҫʹ��Query�ӿڵ�uniqueResult()��ȡ
		Long count=(Long) query.uniqueResult();
		System.out.println(count);
		session.close();
	}
	
	//5.�����ѯ
	//��ѯ������Ա������
	@Test
	public void groupBy(){
		Session session=sf.openSession();
		Query query=session.createQuery("select count(empno),dept.dname from Emp group by dept.dname having dept.dname=?");
		query.setParameter(0, "��Ӫ��");	
		List<Object[]> emps=query.list();
		for (Object[] objects : emps) {
			System.out.println(objects[0]+" "+objects[1]);
		}
		session.close();
	}
	//6.����
	@Test
	public void orderBy(){
		Session session=sf.openSession();
		Query query=session.createQuery("from Emp order by empno desc");
		//���ò���
		//query.setParameter(0, "%��%");
		
		List<Emp> emps=query.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
		session.close();
	}
	//7.��ҳ
	@Test
	public void ListDeptByPager(){
		Session session=sf.openSession();
		Query query=session.createQuery("from Dept");
		//������������
		//ƫ����
		query.setFirstResult(0);
		//ÿҳ��ʾ������
		query.setMaxResults(3);
		List<Dept> depts=query.list();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
		session.close();
	}
	
	
	//8.ģ��ƥ���ѯ
	@Test
	public void like(){
		Session session=sf.openSession();
		Query query=session.createQuery("from Emp where ename like ?");
		//���ò���
		query.setParameter(0, "%��%");
		
		List<Emp> emps=query.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
		session.close();
	}
	//9.������Ӳ�ѯ
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
	//ͶӰ��ѯ����ѯһ���ֶ�List<Object>
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
