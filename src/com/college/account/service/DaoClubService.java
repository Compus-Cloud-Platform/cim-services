package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Club;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoClubService extends  DaoService<Club>{

	private int CLUBIDNOTFIND = 7001;
	private int CLUBNAMENULL = 7002;
	private int CLUBNAMEEXIST = 7003;
	
	private String tablename = "Club";
	
	public boolean selIsExist(Integer id){		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	private boolean selNameUniq(String name){
		
		return (null == searchByFeild(tablename, "name", name))?true:false;
	}
	
	public String save(String jsonString){

		Club club = (Club)Json2Obj.getObj(jsonString, Club.class);
		
		if(null == club.getName()){
			return Cause.getFailcode(CLUBNAMENULL, "name", "name can not null");
		}
		
		if(!selNameUniq(club.getName())){
			return Cause.getFailcode(CLUBNAMEEXIST, "name", "name is exist");
		}

		club.setCreateTime(new Date());
		
		Integer id = create(club);
		
	    return Cause.getSuccess(id);
	}
	
	
	public String sel(Integer id){
		
		Club club = searchByid(id, tablename);
		
		if(null == club){
			
			return Cause.getFailcode(CLUBIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(club, Club.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Club club = null;
		Club clubfind = null;
		
		club = (Club)Json2Obj.getObj(jsonString, Club.class);
		
		
		
		clubfind = searchByid(id, tablename);
		
		if(null == clubfind){
			return Cause.getFailcode(CLUBIDNOTFIND, "id", "org id not find");
		}
		
		if(null != club.getName() && !selNameUniq(club.getName())){
			
			return Cause.getFailcode(CLUBNAMEEXIST, "name", "name is exist");
		}
		
		Json2Obj.repalceDiffObjMem(club, clubfind, Club.class);
		
		update(clubfind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Club club = null;
		
		club = searchByid(id, tablename);
		
		if(null == club){
			return Cause.getFailcode(CLUBIDNOTFIND, "id", "org id not find");
		}
		
		delete(Club.class, id);
		
		return Cause.getSuccess(id);
	}
}
