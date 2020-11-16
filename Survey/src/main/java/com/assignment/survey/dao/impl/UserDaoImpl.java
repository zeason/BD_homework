package com.assignment.survey.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.User;
import com.assignment.survey.dao.UserDao;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public User get(Long id) {
        return (User) currentSession().get(User.class,id);
    }

    @Override
    public Long save(User entity) {
        return (Long) currentSession().save(entity);
    }

    @Override
    public void update(User entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        currentSession().delete(currentSession().get(User.class, id));

    }

	/**
	 *Get user by its name and email
	 */
	@Override
	public User getByNameEmail(String name, String email) {
		Criteria criteria = currentSession().createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("name", name)).add(Restrictions.eq("email", email))
		                             .uniqueResult();
		return user;
	}



}
