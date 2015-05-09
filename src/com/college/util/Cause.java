package com.college.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cause {
	
	static public String getFailcode(int errorCode,
							  String failField,
							  String description){
		
		Map map = new HashMap();
		Map errMap = new HashMap();
		
		errMap.put("code", errorCode);
		errMap.put("field", failField);
		errMap.put("description", description);
		
		map.put("ack", "failure");
		map.put("error", errMap);
		
		return JacksonUtils.getJsonString(map);
	}
	
	static public String getSuccess(Integer id){
		Map map = new HashMap();
		Map dataMap = new HashMap();
		
		map.put("ack", "success");
		
		if(null != id){
			dataMap.put("id", id);
			map.put("data", dataMap);
		}
		
		return JacksonUtils.getJsonString(map);
	}
	
	static public String getData(List list){
		Map map = new HashMap();

		map.put("ack", "success");
		map.put("data", list);
		map.put("size", list.size());
		
		return JacksonUtils.getJsonString(map);
	}

}
