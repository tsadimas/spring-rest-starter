package gr.hua.dit.ds.springdemo.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.springdemo.entity.User;

@Repository
public class UserDaoImpl implements UserDAO {

	// inject the session factory
		@Autowired
		private SessionFactory sessionFactory;

		@Override
		@Transactional
		public User findUserByUsername(String username) {
			return sessionFactory.getCurrentSession().get(User.class, username);

		}

		@Override
		@Transactional
		public User save(User user) {
			sessionFactory.getCurrentSession().save(user);
			return sessionFactory.getCurrentSession().get(User.class, user.getUsername());

		}
}
