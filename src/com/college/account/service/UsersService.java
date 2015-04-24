package com.college.account.service;

import com.college.account.bean.Users;

public interface UsersService {

	/**
	 * 
	 * @param users
	 * @return
	 */
	public Integer createUsers(Users users);
	
	/**
	 * 
	 * @param loginId
	 * @return
	 */
	public Users search(String loginId);
	
}
