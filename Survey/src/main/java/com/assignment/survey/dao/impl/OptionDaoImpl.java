package com.assignment.survey.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.Option;
import com.assignment.survey.dao.OptionDao;;


@Repository("optionDao")
@Transactional
public class OptionDaoImpl implements OptionDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Option get(Long id) {
        return (Option) currentSession().get(Option.class,id);
    }

    @Override
    public Long save(Option entity) {
        return (Long) currentSession().save(entity);
    }

    @Override
    public void update(Option entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        currentSession().delete(currentSession().get(Option.class, id));

    }

	/**
	 *Get all options for specific question
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Option> getByQuestionId(Long id) {
		Criteria criteria = currentSession().createCriteria(Option.class).addOrder(Order.asc("order"));
		return criteria.add(Restrictions.eq("questionId", id)).list();
	}



}
