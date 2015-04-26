package com.college.account.service;

import java.util.List;

import com.college.account.bean.Position;
import com.college.dao.basic.AbstractServiceDao;

public class DaoPositionService extends AbstractServiceDao implements PositionService{

	@Override
	public Integer create(Position position) {
		// TODO Auto-generated method stub
		return (Integer) this.getDao().create(position);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getDao().delete(Position.class, id);
	}

	@Override
	public Position update(Position position) {
		// TODO Auto-generated method stub
		return (Position)this.getDao().update(position);
	}

	@Override
	public Position searchById(Integer id) {
		// TODO Auto-generated method stub
		if(null ==id) return null;
		List<Position> posiList = (List)getDao().query("getPositionById", new Integer[]{id});
		if(posiList.size() > 0)
			return posiList.get(0);
		else
			return null;
	}

}
