package com.xiaoy.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * 页面类
 * 
 * @author XiaoY
 * @date: 2015年5月16日 下午11:14:36
 */
public class Page
{
	private Integer id;
	private String title = "未命名";
	private String description;

	// 简历从Page到Survey之间多对一关联关系
	private Survey survey;

	// 简历从Page到Question之间一对多关联关系
	private Set<Question> questions = new HashSet<>();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Survey getSurvey()
	{
		return survey;
	}

	public void setSurvey(Survey survey)
	{
		this.survey = survey;
	}

	public Set<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(Set<Question> questions)
	{
		this.questions = questions;
	}
}
