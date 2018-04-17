package com.cpt.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cpt.ssh.dao.UserDao;
import com.cpt.ssh.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	// 添加用户
	@Override
	public int addUser(User user) {
		Session session = sessionFactory.openSession();
		int res = (int) session.save(user);
		session.close();
		return res;
		// HibernateUtil.add(user);
	}

	// 删除用户
	@Override
	public void deleteUser(Integer userId) {
		Session session = sessionFactory.openSession();
		User user = new User();
		user = findUserById(userId);
		session.delete(user);
		session.flush();
		session.close();
	}

	// 修改用户
	@Override
	public void editUser(User user) {
		Session session = sessionFactory.openSession();
		session.update(user);
		session.flush();
		session.close();
	}

	// 根据id查询用户信息
	@Override
	public User findUserById(Integer userId) {
		Session session = sessionFactory.openSession();
		String hql = "from User as user where user.userId= :userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		User user = new User();
		user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	// 根据用户名模糊查询用户信息
	@Override
	public List<User> findUserByUsernameKey(String usernameKey) {
		Session session = sessionFactory.openSession();
		String hql = "from User as user where username like :username";
		Query query = session.createQuery(hql);
		query.setString("username", "%"+usernameKey+"%");
		List<User> list = query.list();
		session.close();
		return list;
	}

	// 根据用户名查询用户信息
	@Override
	public User findUserByUserName(String username) {
		Session session = sessionFactory.openSession();
		String hql = "from User as user where user.username= :username";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		User user = null;
		user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	// 根据用户真实姓名模糊查询用户信息
	@Override
	public List<User> findUserByRealnameKey(String realnameKey) {
		Session session = sessionFactory.openSession();
		String hql = "from User as user where realname like :realname";
		Query query = session.createQuery(hql);
		query.setString("realname", "%"+realnameKey+"%");
		List<User> list = query.list();
		session.close();
		return list;
	}
	@Override
	public List<User> findAllUser() {
		Session session = sessionFactory.openSession();
		String hql = "from User as user";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		session.close();
		return list;
	}

}
