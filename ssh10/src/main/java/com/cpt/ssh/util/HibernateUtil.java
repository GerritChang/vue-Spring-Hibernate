package com.cpt.ssh.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateUtil {
	@Autowired
	private static SessionFactory sessionFactory;

	public HibernateUtil() {
	}

	// static{
	// Configuration cfg = new Configuration();
	// cfg.configure();
	// sessionFactory= cfg.buildSessionFactory();
	// }
	// public static SessionFactory getSessionFactory() {
	// return sessionFactory;
	// }
	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void add(Object entity) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void update(Object entity) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void delete(Object entity) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static Object get(Class<?> clazz, Serializable id) {
		Session s = null;
		try {
			s = sessionFactory.openSession();
			Object obj = s.get(clazz, id);
			return obj;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
