package com.hqhx.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GamePlayIH implements InvocationHandler{

	//被代理者
	Class cls=null;
	//被代理的实例
	Object obj=null;
	
	public GamePlayIH(Object _obj) {
		// TODO Auto-generated constructor stub
		this.obj=_obj;
	}
	
	//调用被代理的方法
	@Override
	public Object invoke(Object arg0, Method method, Object[] arg2)
			throws Throwable {
		Object o=method.invoke(this.obj, arg2);
		if(method.getName().equals("login")){
			System.out.println("您的账号在XXX地登录");
		}
		return o;
	}

}
