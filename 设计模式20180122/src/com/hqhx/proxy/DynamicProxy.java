package com.hqhx.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy {

	public static Object getPorxy(ClassLoader classLoader,Class[] clss,InvocationHandler hander){
		BeforeAdvice b=new BeforeAdvice();
		b.exec();
		return Proxy.newProxyInstance(classLoader, clss, hander);
	}
}
