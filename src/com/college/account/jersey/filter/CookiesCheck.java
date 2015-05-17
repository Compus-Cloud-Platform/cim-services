package com.college.account.jersey.filter;

import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

public class CookiesCheck {
	
	public static  Hashtable<Integer, String> hashtable = new Hashtable<Integer, String>();
	
	private CookiesCheck(){}
	
	private static Timer timer = new Timer();
	
	private static CookiesCheck cookiescheck = null;
	
	static {
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				CookiesCheck.getInstance().check();
			}
		}, 10000, 30 * 1000);
	}
	
	public static CookiesCheck getInstance(){
		
		if(null == cookiescheck){
			cookiescheck = new CookiesCheck();
		}
		
		return cookiescheck;
	}
	
	public void check(){
		return ;
	}
	
}
