package com.cydiguo.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cydiguo.ssm.dao.IUserDao;
import com.cydiguo.ssm.entity.User;
import com.cydiguo.ssm.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
	@Transactional
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return this.userDao.insertSelective(user);
	}
	public List<User> getListUser() {
		// TODO Auto-generated method stub
		return this.userDao.getListUser();
	}
	@Transactional
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return this.userDao.updateByPrimaryKeySelective(record);
	}
	@Transactional
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return  this.userDao.deleteByPrimaryKey(id);
	}

}