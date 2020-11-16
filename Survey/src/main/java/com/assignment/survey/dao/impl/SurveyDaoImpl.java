package com.assignment.survey.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.Answer;
import com.assignment.survey.bean.Survey;
import com.assignment.survey.dao.SurveyDao;;


@Repository("surveyDao")
@Transactional
public class SurveyDaoImpl implements SurveyDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Survey get(Long id) {
        return (Survey) currentSession().get(Survey.class,id);
    }

    @Override
    public Long save(Survey entity) {
        return (Long) currentSession().save(entity);
    }

    @Override
    public void update(Survey entity) {
    	currentSession().update(entity);
    }

    @Override
    public void delete(Long id) {
        currentSession().delete(currentSession().get(Survey.class, id));

    }

	/**
	 *Get surveys created by particular user
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> getByCreator(Long id) {
		Criteria criteria = currentSession().createCriteria(Survey.class);
		return criteria.add(Restrictions.eq("creator", id)).list();
	}

	/**
	 *Get surveys that have been answered
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> getAnswered(Long id) {
		String hql = "select distinct s.id, s.title, s.creator from survey s inner join question q on s.id = q.survey_id inner join answer a on a.question_id = q.id where a.user_id = :user";
		Query q = currentSession().createSQLQuery(hql).addEntity(Survey.class);
		q.setParameter("user", id);
		return q.list();
	}

	/**
	 *Check if survey has been answered
	 */
	@Override
	public boolean isAnswered(Long id) {
		String hql = "select a from answer a inner join question q on a.question_id = q.id inner join survey s on s.id = q.survey_id where s.id = :id";
		Query q = currentSession().createSQLQuery(hql).addEntity(Answer.class);
		q.setParameter("id", id);
		return q.list().size() > 0;
	}



}
