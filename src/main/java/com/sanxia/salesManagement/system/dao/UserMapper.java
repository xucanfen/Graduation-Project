package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
//根据登录名查询用户信息
	User queryUserByLogin_nameAndPassword(User user);

	
//查询所有用户
	List<User> queryAllUser();

//增加所有用户
	int addUserByUser(User u);

//删除用户信息
	int deleteUserByUser_id(int userId);

//根据id查询用户信息
	User queryUserByUser_id(int userId);

//修改用户信息
	int updateUserById(User u);

	int queryUserIdByLoginName(String login_name);

	int deleteUserByLoginName(String login_name);

	int queryUserIdByLogin_name(String login_name);

	List<User> selectUserByUserId(int user_id);

	List<User> selectUserByUserName(String user_name);

	int updateUserNameById(User u);
}