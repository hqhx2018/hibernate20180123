package com.hqhx.proxy;

public class GamePlayer implements IGamePlayer{

	private String name;
	
	public GamePlayer(String name){
		this.name=name;
	}
	
	@Override
	public void login(String username, String password) {
		System.out.println(this.name+"µÇÂ¼"+username+" "+password);
	}

	@Override
	public void killBoss() {
		// TODO Auto-generated method stub
		System.out.println(this.name+"´ò¹Ö");
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		System.out.println(this.name+"Éý¼¶");
	}

}
