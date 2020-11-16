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

import com.assignment.survey.bean.Question;
import com.assignment.survey.dao.QuestionDao;;


@Repository("questionDao")
@Transactional
public class QuestionDaoImpl implements QuestionDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Question get(Long id) {
        return (Question) currentSession().get(Question.class,id);
    }

    @Override
    public Long save(Question entity) {
        return (Long) currentSession().save(entity);
    }

    @Override
    public void update(Question entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        currentSession().delete(currentSession().get(Question.class, id));

    }

	/**
	 *Get all questions for specific survey
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getBySurveyId(Long id) {
		Criteria criteria = currentSession().createCriteria(Question.class).addOrder(Order.asc("order"));
		return criteria.add(Restrictions.eq("surveyId", id)).list();
	}



}
