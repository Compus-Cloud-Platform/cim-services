package com.college.account.service;

import java.util.List;

public interface Service<T> {

	public Integer create(T t);
	
	public T update(T t);
	
	public  void delete(Class cls , Integer id);
	
	public T searchByid(Integer id, String tableName);
	
	public List searchAll(String tableName);

}
