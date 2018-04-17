package com.cpt.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt.ssh.dao.UserDao;
import com.cpt.ssh.entity.User;
import com.cpt.ssh.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public int addUser(User user) {
		User resUser = userDao.findUserByUserName(user.getUsername());
		if(resUser != null) {
			return -1;
		}else {
			int resnum = userDao.addUser(user);
			if(resnum>0){
				return 1;
			}
			return 0;
		}
		
	}
	@Override
	public boolean deleteUser(Integer userId) {
		userDao.deleteUser(userId);
		User user = userDao.findUserById(userId);
		if(user==null) {
			return true;
		}
		return false;
	}
	@Override
	public boolean editUser(User user) {
		userDao.editUser(user);
		User resUser = userDao.findUserById(user.getUserId());
		if(user.getUsername().equals(resUser.getUsername())&&
				user.getRealname().equals(resUser.getRealname())&&
				user.getAge().equals(resUser.getAge())&&
				user.getAddress().equals(resUser.getAddress())
				) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public User findUserById(Integer userId) {
		return userDao.findUserById(userId);
	}
	@Override
	public List<User> findUserByUsernameKey(String usernameKey) {
		return userDao.findUserByUsernameKey(usernameKey);
	}
	@Override
	public User findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}
	@Override
	public List<User> findUserByRealnameKey(String realnameKey) {
		return userDao.findUserByRealnameKey(realnameKey);
	}
	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

}
