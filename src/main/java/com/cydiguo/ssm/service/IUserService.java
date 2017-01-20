package com.cydiguo.ssm.service;

import java.util.List;

import com.cydiguo.ssm.entity.User;

public interface IUserService {
	public User getUserById(int userId);
	public int saveUser(User user);
	public List<User> getListUser();
	public int updateByPrimaryKey(User record);
	public int deleteByPrimaryKey(Integer id);
}
