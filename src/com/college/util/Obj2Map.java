package com.college.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Obj2Map {
	private static String[] typeJava = new String[]{"class java.lang.Integer",
									    "class java.lang.Float",
									    "class java.lang.Long",
									    "class java.lang.Double",
									    "class java.util.Date",
									    "class java.lang.String"};
	
	public  static Map<String, Object>  toMap(Object temp, Class<?> obj){

		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			Field[] f = obj.getDeclaredFields();
			for(Field field : f){
				field.setAccessible(true);
				
				Object objtemp = field.get(temp);
				
				/* filter */
				String type = field.getType().toString();
			
				if(isExistType(type)){
					map.put(field.getName(), objtemp);
				}
				
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public  static Map<String, Object>  toMapRecursive(Object temp, Class<?> obj){

		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			Field[] f = obj.getDeclaredFields();
			for(Field field : f){
				field.setAccessible(true);
				
				Object objtemp = field.get(temp);
				
				/* filter */
				String type = field.getType().toString();
			
				if(isExistType(type)){
					map.put(field.getName(), objtemp);
				}else{
					map.put(field.getName(), toMapRecursive(objtemp, field.getType()));
				}
				
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static boolean isExistType(String type){
		
		for(String s:typeJava){
			if(s.equals(type)){
				return true;
			}
		}
		return false;
	}
	
}
