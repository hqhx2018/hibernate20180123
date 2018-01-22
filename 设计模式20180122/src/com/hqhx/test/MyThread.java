package com.hqhx.test;

import com.hqhx.model.Student;
import com.hqhx.model.Student2;

public class MyThread extends Thread{
	
	@Override
	public void run() {
		Student2 s=Student2.newInstance();
		System.out.println(s);
	}

}
