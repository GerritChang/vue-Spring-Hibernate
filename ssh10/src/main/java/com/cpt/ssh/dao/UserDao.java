package com.cpt.ssh.dao;

import java.util.List;

import com.cpt.ssh.entity.User;

public interface UserDao {
	// 添加用户
	public int addUser(User user);

	// 删除用户
	public void deleteUser(Integer userId);

	// 修改用户信息
	public void editUser(User user);

	// 查询所有用户
	public List<User> findAllUser();

	// 根据ID查询用户
	public User findUserById(Integer userId);

	// 根据用户名查询用户
	public User findUserByUserName(String username);

	// 根据用户名模糊查询用户信息
	public List<User> findUserByUsernameKey(String usernameKey);

	// 根据真实姓名模糊查询用户信息
	public List<User> findUserByRealnameKey(String realnameKey);
}
