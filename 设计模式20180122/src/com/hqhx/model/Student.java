package com.hqhx.model;

//饿汉模式
public class Student {

	private static final Student s=new Student();
	
	//构造私有化
	private Student(){
		
	}
	
	public static Student newInstance(){
		return s;
	}
	
}
