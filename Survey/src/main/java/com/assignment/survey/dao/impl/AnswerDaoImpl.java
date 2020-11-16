package com.assignment.survey.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.Answer;
import com.assignment.survey.dao.AnswerDao;


@Repository("answerDao")
@Transactional
public class AnswerDaoImpl implements AnswerDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Answer get(Long id) {
        return (Answer) currentSession().get(Answer.class,id);
    }

    @Override
    public Long save(Answer entity) {
        return (Long) currentSession().save(entity);
    }

    @Override
    public void update(Answer entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        currentSession().delete(currentSession().get(Answer.class, id));

    }

	/**
	 *Get answer for question by userId and questionId 
	 */
	@Override
	public Answer getAnswer(Long userId, Long questionId) {
		Criteria criteria = currentSession().createCriteria(Answer.class);
		return (Answer) criteria.add(Restrictions.eq("userId", userId)).add(Restrictions.eq("questionId", questionId))
		                             .uniqueResult();
	}
}
