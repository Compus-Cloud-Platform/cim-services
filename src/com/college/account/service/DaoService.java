package com.college.account.service;

import java.util.List;

import com.college.dao.basic.AbstractServiceDao;

public abstract class DaoService<T> extends AbstractServiceDao implements Service<T>{

	
	@Override
	public Integer create(T t) {
		return (Integer) this.getDao().create(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return (T) this.getDao().update(t);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public void delete(Class cls, Integer id) {
		// TODO Auto-generated method stub
		this.getDao().delete(cls, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T searchByid(Integer id, String tableName) {
		String name = "get" + tableName + "ById";
		List<T> list = (List<T>)getDao().query(name, new Integer[]{id});
		
		if(null != list){
			if(list.size() > 0 )return list.get(0);
		}
		return null;
	}

	@Override
	public List<Object> searchAll(String tableName){
		String name = "getAll" + tableName;
		return getDao().query(name);
	}
	
	public List<Object> searchAll(String tableName,Integer from, Integer size){
		String name = "getAll" + tableName;
		return getDao().query(name, from, size);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T searchByFeild(String tableName, String feild, Object feildValue) {
		if(null == feildValue) return null;
		if("".equals(feildValue)) return null;
		String query = "get" + tableName + feild;
		List<T> list = (List<T>)getDao().query(query, new Object[]{feildValue});
		if(null != list){
			if(list.size() > 0 )return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Object> searchByFeildList(String tableName, String feild, Object feildValue) {

		String query = "get" + tableName + feild;
		List<Object> list = (List<Object>)getDao().query(query, new Object[]{feildValue});

		return list;

	}
	
	@Override
	public List<Object> searchByFeildList(String tableName, String feild, Object feildValue, Integer from, Integer size){
		String query = "get" + tableName + feild;
		List<Object> list = (List<Object>)getDao().query(query, new Object[]{feildValue}, from, size);
		return list;
	}
	
	@Override
	public List<Object> searchByFeildList(String tableName, String feild, Object[] feildValue, Integer from, Integer size){
		String query = "get" + tableName + feild;
		List<Object> list = (List<Object>)getDao().query(query, feildValue, from, size);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T searchByMutiFeild(String tableName, String feild, Object[] feildValue) {

		String query = "get" + tableName + feild;
		List<T> list = (List<T>)getDao().query(query, feildValue);
		if(null != list){
			if(list.size() > 0 )return list.get(0);
		}
		return null;
	}
	
	@Override
	public Integer searchCount(String tableName){
		String name = "getCount" + tableName;
		return getDao().querycount(name);
	}
	
	@Override
	public Integer searchCount(String tableName, String feild, Object feildValue){
		String query = "getCount" + tableName + feild;
		return getDao().querycount(query,  new Object[]{feildValue});
	}
	
	@Override
	public Integer searchCount(String tableName, String feild, Object[] feildValue){
		String query = "getCount" + tableName + feild;
		return getDao().querycount(query, feildValue);
	}


}
