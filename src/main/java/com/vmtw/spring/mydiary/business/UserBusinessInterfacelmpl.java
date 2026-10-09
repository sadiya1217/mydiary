package com.vmtw.spring.mydiary.business;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmtw.spring.mydiary.dao.UserDaoInterface;
import com.vmtw.spring.mydiary.entities.User;

@Component
public class UserBusinessInterfacelmpl implements UserBusinessInterface {
	
	@Autowired
	private UserDaoInterface userDaoInterface;
	
	


	public UserDaoInterface getUserDaoInterface() {
		return userDaoInterface;
	}

	public void setUserDaoInterface(UserDaoInterface userDaoInterface) {
		this.userDaoInterface = userDaoInterface;
	}
	
	@Transactional
	public void save(User user) {
		userDaoInterface.save(user);

	}

	@Override
	public void update(User user) {
		userDaoInterface.update(user);
		

	}
	
	public void delete(User user) {
		userDaoInterface.delete(user);

	}

	@Override
	public User findById(int id) {
		return userDaoInterface.findById(id);
	}

	@Override
	public List<User> findAll() {
	
		return userDaoInterface.findAll();
	}

	@Override
	public User findByUsername(String username) {
	
		return userDaoInterface.findByUsername(username);
	}

}
