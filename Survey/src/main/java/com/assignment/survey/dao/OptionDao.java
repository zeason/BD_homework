package com.assignment.survey.dao;

import java.util.List;

import com.assignment.survey.bean.Option;

public interface OptionDao extends GenericDao<Option,Long>{
	List<Option> getByQuestionId(Long id);
}
