package com.assignment.survey.dao;

import java.util.List;

import com.assignment.survey.bean.Survey;

public interface SurveyDao extends GenericDao<Survey,Long>{

	List<Survey> getByCreator(Long id);

	List<Survey> getAnswered(Long id);

	boolean isAnswered(Long id);
}
