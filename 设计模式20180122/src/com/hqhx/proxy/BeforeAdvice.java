package com.hqhx.proxy;

public class BeforeAdvice implements Iadvice{

	@Override
	public void exec() {
		System.out.println("我是前置通知，我被执行了");
	}

}
