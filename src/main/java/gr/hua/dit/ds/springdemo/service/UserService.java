package gr.hua.dit.ds.springdemo.service;

import java.util.List;

import gr.hua.dit.ds.springdemo.entity.User;

public interface UserService {

	
    User save(User user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
