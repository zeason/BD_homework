package com.assignment.survey.dao;

import java.util.List;

import com.assignment.survey.bean.Question;

public interface QuestionDao extends GenericDao<Question,Long>{
	List<Question> getBySurveyId(Long id);
}
