package com.assignment.survey.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "option")
public class Option {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "question_id")
    private Long questionId;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "order_num")
    private Integer order;
    
    @Transient
    private String checked = "";

    public Option() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    
    public Long getQuestionId() {
        return questionId;
    }
    
    public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
}
