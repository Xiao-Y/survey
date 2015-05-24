package com.xiaoy.entities;

import java.util.Date;

public class Answer
{
	private Integer id;
	private String answerIds;// 选项的索引.
	private String otherAnswer;
	private String uuid;// 批次.
	private Date answerTime;

	// private Integer questionId;// 问题的id（关联字段）
	// private Integer surveyId;// 关联字段(冗余)

	private Survey survey;

	private Question question;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getAnswerIds()
	{
		return answerIds;
	}

	public void setAnswerIds(String answerIds)
	{
		this.answerIds = answerIds;
	}

	public String getOtherAnswer()
	{
		return otherAnswer;
	}

	public void setOtherAnswer(String otherAnswer)
	{
		this.otherAnswer = otherAnswer;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public Date getAnswerTime()
	{
		return answerTime;
	}

	public void setAnswerTime(Date answerTime)
	{
		this.answerTime = answerTime;
	}

	public Survey getSurvey()
	{
		return survey;
	}

	public void setSurvey(Survey survey)
	{
		this.survey = survey;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}
}
