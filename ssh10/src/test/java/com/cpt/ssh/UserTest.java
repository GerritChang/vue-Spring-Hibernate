package com.cpt.ssh;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cpt.ssh.entity.User;
import com.cpt.ssh.service.UserService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class UserTest {
	@Autowired
	private UserService userService;

	@Test
	public void test() {
		User user = new User();
		user.setUserId(1);
		user.setUsername("123456");
		user.setRealname("456789");
//		int ínt = 0;
		user.setAge(20);
		user.setAddress("123");
		int b = userService.addUser(user);
		System.out.println(b);
	}
	@Test
	public void test1() {
		userService.deleteUser(1);
	}
	@Test
	public void testfindUserByName() {
		String username = "123456";
		User user = new User();
		user.setUsername(username);
		User resUser = userService.findUserByUserName(username);
		System.out.println(resUser.toString());
	}
	@Test
	public void testFindUserById() {
		User user = userService.findUserById(2);
		System.out.println(user.toString());
	}
	@Test
	public void testFindUserByUsernameKey() {
		String key = "2";
		List<User> userList = userService.findUserByUsernameKey(key);
		for(User user:userList) {
			System.out.println(user.toString());
		}
	}
	@Test
	public void test2() {
		List<User> list = userService.findAllUser();
		System.out.println(list);
	}
}
