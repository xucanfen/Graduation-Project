package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.UserMapper;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

//根据用户名和密码查询用户
	@Override
	public User queryUserByLogin_nameAndPassword(User user) {
		// TODO Auto-generated method stub
		return userMapper.queryUserByLogin_nameAndPassword(user);
	}

//查询所有的用户
	@Override
	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		return userMapper.queryAllUser();
	}

//增加用户
	@Override
	public int addUserByUser(User u) {
		// TODO Auto-generated method stub
		return userMapper.addUserByUser(u);
	}

//删除用户
	@Override
	public int deleteUserByUser_id(int userId) {
		// TODO Auto-generated method stub
		return userMapper.deleteUserByUser_id(userId);
	}

//根据id查询用户信息
	@Override
	public User queryUserByUser_id(int userId) {
		// TODO Auto-generated method stub
		return userMapper.queryUserByUser_id(userId);
	}

//修改用户信息
	@Override
	public int updateUserById(User u) {
		// TODO Auto-generated method stub
		return userMapper.updateUserById(u);
	}

	@Override
	public int queryUserIdByLoginName(String login_name) {
		// TODO Auto-generated method stub
		return userMapper.queryUserIdByLoginName(login_name);
	}

	@Override
	public int deleteUserByLoginName(String login_name) {
		// TODO Auto-generated method stub
		return userMapper.deleteUserByLoginName(login_name);
	}

	@Override
	public List<User> selectUserByUserId(int user_id) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByUserId(user_id);
	}

	@Override
	public List<User> selectUserByUserName(String user_name) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByUserName(user_name);
	}

	@Override
	public int updateUserNameById(User u) {
		// TODO Auto-generated method stub
		return userMapper.updateUserNameById(u);
	}

	 
}
