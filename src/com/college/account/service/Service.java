package com.college.account.service;

import java.util.List;

public interface Service<T> {

	public Integer create(T t);
	
	public T update(T t);
	
	public  void delete(Class cls , Integer id);
	
	public T searchByid(Integer id, String tableName);
	
	public List<Object> searchAll(String tableName);
	
	public T searchByFeild(String tableName, String feild, String feildValue);

}
