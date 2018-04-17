package com.cpt.ssh.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cpt.ssh.entity.User;
import com.cpt.ssh.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	// 查询所有用户
	@RequestMapping("/user/getUserList")
	@ResponseBody
	public List<User> getUserList() {
		List<User> list = userService.findAllUser();
		System.out.println(list.toString());
		return list;
	}

	// 添加用户
	@RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(User user) {
		if (user != null) {
			int resnum = userService.addUser(user);
			if (resnum == -1) {
				return "用户名已经存在";
			} else if (resnum == 0) {
				return "用户添加失败";
			} else {
				return "用户添加成功";
			}
		} else {
			return "数据格式不正确";
		}
	}

	// 删除用户
	@RequestMapping(value = "/user/delUser/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteUser(@PathVariable String userId) throws IOException {
		int i = Integer.parseInt(userId);
		User user = userService.findUserById(i);
		if (user != null) {
			boolean deleteUserSuccessFlag = userService.deleteUser(i);
			if(deleteUserSuccessFlag) {
				return "用户删除成功";
			}else {
				return "用户删除失败";
			}
		}else {
			return "找不到用户";
		}
	}

	// 修改用户
	@RequestMapping(value="/user/updateUser",method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(User user) {
		boolean updateFlag = userService.editUser(user);
		if(updateFlag) {
			return "修改成功";
		}else {
			return "修改失败";
		}
	}

	// 根据用户名模糊查询用户信息
	@RequestMapping(value = "/user/findUserByUsernameKey/{usernameKey}", method =  RequestMethod.GET )
	@ResponseBody
	public List<User> findUserByUsernameKey(@PathVariable String usernameKey) {
		List<User> list = userService.findUserByUsernameKey(usernameKey);
		return list;
	}

	// 根据用户真实姓名查询用户信息
	@RequestMapping(value = "/user/findUserByRealnameKey/{realnameKey}", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findUserByRealnameKey(@PathVariable String realnameKey) {
		List<User> list = userService.findUserByRealnameKey(realnameKey);
		return list;
	}

}
