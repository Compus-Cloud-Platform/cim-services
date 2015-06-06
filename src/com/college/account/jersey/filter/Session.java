package com.college.account.jersey.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Session {
	
	private static Timer timer = new Timer();
	
	private static ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
	
	public static final long INTERTIME = 20 * 60 * 1000; // 20 min
	
	static {
		
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				Session.check();
			}
		}, Session.INTERTIME/2, Session.INTERTIME/2);
	}
	
	private static Map<String, inner> map = new HashMap<String, inner>();
	
	private static void check(){

		Date date = new Date();
		
		Long timestamp = date.getTime();
		
		Map<String, inner> tempmap = new HashMap<String, inner>(); 
		
		Set<String> set = map.keySet();
		
		for (String s:set) {
			
			inner temp = map.get(s);
			
			if((timestamp - temp.getTimestamp()) <= INTERTIME){
				tempmap.put(s, temp);
			}
		}
		
		map = tempmap;
		
		return;
	}
	
	private Session(){}
	
	
	public static void setAttribute(String key, Object value){
		Date date = new Date();
		map.put(key, new inner(date.getTime(), value));
		
		
	}
	
	public static Object getAttribute(String key){
		inner temp = map.get(key);
		if(null == temp) return null;
		Date date = new Date();
		temp.setTimestamp(date.getTime());
		
		Map<?, ?> map = (Map<?, ?>)temp.getValue();
		Integer id = (Integer)map.get("loginId");
		tl.set(id);
		
		return temp.getValue();
	}
	
	public static Integer getOperId(){
		return tl.get();
	}

}

class inner{
	
	private Object value;
	private Long timestamp;
	
	public inner(Long timestamp, Object value){
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}