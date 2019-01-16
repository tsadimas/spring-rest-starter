package gr.hua.dit.ds.springdemo.dao;

import gr.hua.dit.ds.springdemo.entity.User;

public interface UserDAO  {
	 User findUserByUsername(String username);
	 User save(User user);
}