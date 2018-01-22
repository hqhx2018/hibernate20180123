package com.hqhx.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;

import com.hqhx.proxy.DynamicProxy;
import com.hqhx.proxy.GamePlayIH;
import com.hqhx.proxy.GamePlayer;
import com.hqhx.proxy.GamePlayerPorxy;
import com.hqhx.proxy.IGamePlayer;

public class ProxyTest {

	public static void main(String[] args) {
//		//创建一个代练者
//		IGamePlayer porxy=new GamePlayerPorxy("李四");
//		porxy.login("admin", "123456");
//		porxy.killBoss();
//		porxy.upgrade();
		
		//创建一个玩家
		IGamePlayer gamePlary=new GamePlayer("张三");
		InvocationHandler i=new GamePlayIH(gamePlary);
		System.out.println(new Date());
		//获取类加载器
		ClassLoader classLoader=gamePlary.getClass().getClassLoader();
		IGamePlayer proxy=(IGamePlayer) DynamicProxy.getPorxy(classLoader, new Class[]{IGamePlayer.class}, i);
		proxy.login("admin", "123456");
		proxy.killBoss();
		proxy.upgrade();
	}
}
