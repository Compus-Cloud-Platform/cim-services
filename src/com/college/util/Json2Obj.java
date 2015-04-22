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
				
				Field field = cls.getDeclaredField(fName);
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

}
