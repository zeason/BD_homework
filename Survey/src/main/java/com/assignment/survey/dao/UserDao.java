package com.assignment.survey.dao;

import com.assignment.survey.bean.User;

public interface UserDao extends GenericDao<User,Long>{
	public User getByNameEmail(String name, String email);
}
