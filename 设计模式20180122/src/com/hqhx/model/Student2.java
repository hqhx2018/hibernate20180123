package com.hqhx.model;

//懒汉模式
public class Student2 {

	private static Student2 s;
	
	//构造私有化
	private Student2(){
		
	}
	
	public static synchronized Student2 newInstance(){
		if(s==null){
			s=new Student2();
		}
		return s;
	}
	
}
