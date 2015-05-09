package com.college.account.service;

import java.util.List;

import com.college.dao.basic.AbstractServiceDao;

public abstract class DaoService<T> extends AbstractServiceDao implements Service<T>{

	
	@Override
	public Integer create(T t) {
		return (Integer) this.getDao().create(t);
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return (T) this.getDao().update(t);
	}

	@Override
	public void delete(Class cls, Integer id) {
		// TODO Auto-generated method stub
		this.getDao().delete(cls, id);
	}

	@Override
	public T searchByid(Integer id, String tableName) {
		String name = "get" + tableName + "ById";
		List<T> list = (List)getDao().query(name, new Integer[]{id});
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}
	
	@Override
	public List searchAll(String tableName){
		String name = "getAll" + tableName;
		return getDao().query(name);
	}


}
