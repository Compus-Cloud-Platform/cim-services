package com.college.account.service;

import java.util.List;

import com.college.account.bean.Users;
import com.college.account.bean.UsersExt;
import com.college.dao.basic.AbstractServiceDao;

public class DaoUsersServiceExt extends AbstractServiceDao implements UsersServiceExt{

	@Override
	public Integer createUsersServiceExt(UsersExt usersext) {
		// TODO Auto-generated method stub
		 return (Integer) this.getDao().create(usersext);
	}

	@Override
	public UsersExt updateUsersServiceExt(UsersExt usersext) {
		// TODO Auto-generated method stub
		return (UsersExt)this.getDao().update(usersext);
	}

	@Override
	public UsersExt search(String loginId) {
		// TODO Auto-generated method stub
		
		List<UsersExt> usersextList = (List)getDao().query("getUserExtByLoginId", new String[]{loginId});
		if(usersextList.size() > 0)
			return usersextList.get(0);
		else
			return null;
		
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
