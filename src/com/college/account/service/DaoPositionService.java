package com.college.account.service;

import com.college.account.bean.Position;
import com.college.util.Cause;

public class DaoPositionService extends  DaoService<Position>{

	private int POSITIONIDNOTFIND = 4001;
	private String tablename = "Position";
	
	public String search(Integer id){
		
		Position position = searchByid(id, tablename);
		
		if(null == position){
			
			return Cause.getFailcode(POSITIONIDNOTFIND, "id", "position id not find");
		}
		
		return Cause.getSuccess(id);
	}
}
