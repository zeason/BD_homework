package com.assignment.survey.bean.service;

import com.assignment.survey.bean.User;

public interface UserService {
	User get(Long id);
	
	User getByNameEmail(String name, String email);
	
	Long save(User entity);
	
	void delete(Long id);
}
