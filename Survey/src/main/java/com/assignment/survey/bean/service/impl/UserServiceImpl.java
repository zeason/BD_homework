package com.assignment.survey.bean.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.survey.bean.User;
import com.assignment.survey.bean.service.UserService;
import com.assignment.survey.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired  
    private UserDao userDao;
	
	@Override
	public User get(Long id) {
		return userDao.get(id);
	}

	@Override
	public User getByNameEmail(String name, String email) {
		return userDao.getByNameEmail(name, email);
	}

	@Override
	public Long save(User entity) {
		return userDao.save(entity);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);

	}

}
