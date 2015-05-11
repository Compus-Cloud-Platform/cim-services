package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Position;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoPositionService extends  DaoService<Position>{

	private int POSITIONIDNOTFIND = 4001;
	private int POSITIONNAMENULL = 4002;
	private int POSITIONNAMEEXIST = 4003;
	
	private String tablename = "Position";
	
	public boolean selIsExist(Integer id){		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	private boolean selNameUniq(String name){
		
		return (null == searchByFeild(tablename, "name", name))?true:false;
	}
	
	public String save(String jsonString){

		Position position = (Position)Json2Obj.getObj(jsonString, Position.class);
		
		if(null == position.getName()){
			return Cause.getFailcode(POSITIONNAMENULL, "name", "name can not null");
		}
		
		if(!selNameUniq(position.getName())){
			return Cause.getFailcode(POSITIONNAMEEXIST, "name", "name is exist");
		}

		position.setCreateTime(new Date());
		
		Integer id = create(position);
		
	    return Cause.getSuccess(id);
	}
	
	
	public String sel(Integer id){
		
		Position position = searchByid(id, tablename);
		
		if(null == position){
			
			return Cause.getFailcode(POSITIONIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(position, Position.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Position position = null;
		Position positionfind = null;
		
		position = (Position)Json2Obj.getObj(jsonString, Position.class);
		
		
		
		positionfind = searchByid(id, tablename);
		
		if(null == positionfind){
			return Cause.getFailcode(POSITIONIDNOTFIND, "id", "org id not find");
		}
		
		if(null != position.getName() && !selNameUniq(position.getName())){
			
			return Cause.getFailcode(POSITIONNAMEEXIST, "name", "name is exist");
		}
		
		Json2Obj.updateObject(position, positionfind);
		
		update(positionfind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Position position = null;
		
		position = searchByid(id, tablename);
		
		if(null == position){
			return Cause.getFailcode(POSITIONIDNOTFIND, "id", "org id not find");
		}
		
		delete(Position.class, id);
		
		return Cause.getSuccess(id);
	}
}
