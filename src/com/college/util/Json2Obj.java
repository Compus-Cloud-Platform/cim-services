package com.college.util;


import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

public class Json2Obj {
	
	private static final Logger log = Logger4j.getLogger(Json2Obj.class);
	
	public static Object getObj(String jsonString, Class<?> obj){
		
		try {
			
			Class<?> cls = Class.forName(obj.getName());
			
			Object tempObject = cls.newInstance();
			
			@SuppressWarnings("unchecked")
			Map<String, String> data = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
			
			Iterator<String> keyIterator = data.keySet().iterator();
			while(keyIterator.hasNext()){
				String fName = keyIterator.next();
				Object tmpObj = data.get(fName);
				
				Field field = null;
				try {
					field = cls.getDeclaredField(fName);
				} catch (Exception e) {
					/* if not find filed skip */
					continue;
				}
				
				String type = field.getType().toString();
				field.setAccessible(true);
				
				if("int".equals(type) || "class java.lang.Integer".equals(type)){
					field.set(tempObject, Integer.parseInt(tmpObj.toString()));
				}else if("float".equals(type) || "class java.lang.Float".equals(type)){
					field.set(tempObject, Float.parseFloat(tmpObj.toString()));
				}else if("double".equals(type) || "class java.lang.Double".equals(type)){
					field.set(tempObject, Double.parseDouble(tmpObj.toString()));
				}else if("long".equals(type) || "class java.lang.Long".equals(type)){
					field.set(tempObject, Long.parseLong(tmpObj.toString()));
				}else if("class java.util.Date".equals(type)){
					SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					Date date = format.parse(tmpObj.toString());
					field.set(tempObject, date);
				}else{
					field.set(tempObject, tmpObj.toString());
				}
			}
			
			return tempObject;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} 
		
		return null;
	}
	
	public static void repalceDiffObjMem(Object obj1, Object obj2, Class<?> obj){
	
		try {
			Field[] f = obj.getDeclaredFields();
			for(Field field : f){
				field.setAccessible(true);
				Object objtemp = field.get(obj1);
				if(null != objtemp){
					field.set(obj2, objtemp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		testobj a1,a2;
		a1 = new testobj();
		a2 = new testobj();
		a1.setI(4);
		a1.setName("alex");
		a2.setI(2);
		Json2Obj.repalceDiffObjMem(a1,a2,testobj.class);
		
		System.out.print(a2.getI() + a2.getName());
	}

}

class testobj{
	public testobj(){}
	private Integer i;
	private String name;
	public Integer getI() {
		return i;
	}
	public void setI(Integer i) {
		this.i = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
