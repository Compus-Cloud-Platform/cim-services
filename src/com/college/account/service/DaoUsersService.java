package com.college.account.service;

import java.util.List;

import com.college.account.bean.Users;
import com.college.dao.basic.AbstractServiceDao;

public class DaoUsersService extends AbstractServiceDao implements UsersService{

	@Override
	public Integer createUsers(Users users) {
		// TODO Auto-generated method stub
	    return (Integer) this.getDao().create(users);
	}

	@Override
	public Users search(String loginId) {
		// TODO Auto-generated method stub
		
		List<Users> usersList = (List)getDao().query("getUserByLoginId", new String[]{loginId});
		if(usersList.size() > 0)
			return usersList.get(0);
		else
			return null;
	}

	@Override
	public Users updateUsers(Users users) {
		// TODO Auto-generated method stub
		return (Users)this.getDao().update(users);
	}

	@Override
	public void deleteUsers(Integer id) {
		// TODO Auto-generated method stub
		this.getDao().delete(Users.class, id);
	}

	@Override
	public Users searchByid(Integer id) {
		// TODO Auto-generated method stub
		List<Users> usersList = (List)getDao().query("getUserById", new Integer[]{id});
		if(usersList.size() > 0)
			return usersList.get(0);
		else
			return null;
	}

}
