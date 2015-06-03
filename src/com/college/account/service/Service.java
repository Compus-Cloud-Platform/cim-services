package com.college.account.service;

import java.util.List;

public interface Service<T> {

	public Integer create(T t);
	
	public T update(T t);
	
	public  void delete(Class<?> cls , Integer id);
	
	public T searchByid(Integer id, String tableName);
	
	public List<Object> searchAll(String tableName);
	public List<Object> searchAll(String tableName, Integer from, Integer size);
	
	public T searchByFeild(String tableName, String feild, Object feildValue);
	
	public List<Object> searchByFeildList(String tableName, String feild, Object feildValue);
	public List<Object> searchByFeildList(String tableName, String feild, Object feildValue, Integer from, Integer size);
	
	public T searchByMutiFeild(String tableName, String feild, Object[] feildValue);
	

	public Integer searchCount(String tableName);

	public Integer searchCount(String tableName, String feild, Object feildValue);

}
