package com.assignment.survey.service;

import com.assignment.survey.bean.User;

public interface UserService {
	User get(Long id);
	
	User getByNameEmail(String name, String email);
	
	Long save(User entity);
	
	void delete(Long id);
}
