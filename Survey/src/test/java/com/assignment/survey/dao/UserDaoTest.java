package com.assignment.survey.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.User;
import com.assignment.survey.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml", "classpath:META-INF/spring-mvc.xml"})
@Transactional
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void getByNameandEmail() {
	    User userInfo = userDao.getByNameEmail("test", "test@test.com");
	    System.out.println(userInfo);
	}
	
	@Test
	public void get() {
	    User userInfo = userDao.get(1L);
	    System.out.println(userInfo);
	}
	
	@Test
	public void save() {
		User user = new User();
		user.setEmail("test@test.com");
		user.setName("test");
	    Long id = userDao.save(user);
	    assert(id != null);
	}
	
	@Test
	public void update() {
	    User userInfo = userDao.getByNameEmail("chen", "test@test.com");
	    assert(userInfo != null);
	    userInfo.setName("admin");
	    userDao.update(userInfo);
	    userInfo = userDao.get(userInfo.getId());
	    assertEquals("admin", userInfo.getName());
	}
	
	@Test
	public void delete() {
		if (null != userDao.get(1L))
			userDao.delete(1L);
	}

}
