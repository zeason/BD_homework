package com.assignment.survey.dao;

import java.io.Serializable;

public interface GenericDao <T, PK extends Serializable>{
    T get(PK id);  

    PK save(T entity);  

    void update(T entity);  

    void delete(PK id);  
}
