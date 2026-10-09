package com.vmtw.spring.mydiary.dao;

import java.util.List;

import com.vmtw.spring.mydiary.entities.User;

public interface UserDaoInterface {
	
	public void save(User user);
	public void update(User uer);
	public void delete(User user);
	public User findById(int id);
	public List<User> findAll();
	
	public User findByUsername(String username);
}