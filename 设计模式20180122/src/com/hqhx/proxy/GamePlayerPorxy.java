package com.hqhx.proxy;

import java.util.Date;

public class GamePlayerPorxy implements IGamePlayer{

	private IGamePlayer gamePlayer;
	
	public GamePlayerPorxy(IGamePlayer gamePlayer) {
		this.gamePlayer=gamePlayer;
	}
	
	
	
	@Override
	public void login(String username, String password) {
		System.out.println("---------------"+new Date());
		gamePlayer.login(username, password);
		System.out.println("---------------"+new Date());
	}

	@Override
	public void killBoss() {
		
		gamePlayer.killBoss();
		
	}

	@Override
	public void upgrade() {
		gamePlayer.upgrade();
	}

}
