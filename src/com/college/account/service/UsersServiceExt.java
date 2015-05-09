package com.college.account.service;

import com.college.account.bean.UsersExt;

public interface UsersServiceExt {

	/**
	 * 
	 * @param usersext
	 * @return
	 */
	public Integer createUsersServiceExt(UsersExt usersext);
	
	/**
	 * 
	 * @param usersext
	 * @return
	 */
	public UsersExt updateUsersServiceExt(UsersExt usersext);
	
	/**
	 * 
	 * @param loginId
	 * @return
	 */
	public UsersExt search(String loginId);
	
	
	public void delete(Integer id);
}
