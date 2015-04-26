package com.college.account.service;

import com.college.account.bean.Position;

public interface PositionService {
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public Integer create(Position position);
	
	/**
	 * 
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public Position update(Position position);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Position searchById(Integer id);

}
