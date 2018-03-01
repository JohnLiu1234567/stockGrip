package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.BackUser;



public interface BackUserDao {

	public List<BackUser> showUser(@Param("name")String name,@Param("state")String state,@Param("page")Integer page, @Param("size")Integer size);
	public BackUser findUser(@Param("id")String id,@Param("name")String name,@Param("password")String password);
	public int addUser(@Param("backUser")BackUser backUser);
	public int updateUser(@Param("backUser")BackUser backUser);
	public int deleteUser(@Param("id")String id);
}
