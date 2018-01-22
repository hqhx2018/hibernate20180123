package com.hqhx.test;

import com.hqhx.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		MyThread m1=new MyThread();
		MyThread m2=new MyThread();
		MyThread m3=new MyThread();
		MyThread m4=new MyThread();
		MyThread m5=new MyThread();
		MyThread m6=new MyThread();
		MyThread m7=new MyThread();
		MyThread m8=new MyThread();
		m1.start();
		m2.start();
		m3.start();
		m4.start();
		m5.start();
		m6.start();
		m7.start();
		m8.start();
	}
}
