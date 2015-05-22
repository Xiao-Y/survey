package com.xiaoy.action.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;
import com.xiaoy.service.SurveyService;
import com.xiaoy.struts2.UserAware;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SurveyAction extends BasicAction<Survey> implements UserAware
{
	/**
	 * 接收用户对象
	 */
	private User user;

	@Override
	public void setUser(User user)
	{
		this.user = user;
	}

	@Resource
	private SurveyService surveyService;

	private List<Survey> mySurveyList = null;

	// Survey的主键
	private Integer sid;

	/**
	 * 查询我的调查列表
	 * 
	 * @return
	 */
	public String mySurveysListPage()
	{
		mySurveyList = surveyService.findMySurveys(user);
		return "mySurveysListPage";
	}

	/**
	 * 新建调查页面
	 * 
	 * @return
	 */
	public String newSurvey()
	{
		Survey survey = surveyService.newSurvey(user);
		BeanUtils.copyProperties(survey, model);

		return "designSurvey";
	}

//	/**
//	 * 预处理拦截器：该方法只在designSurvey方法之前调用
//	 */
//	public void prepareDesignSurvey()
//	{
//		this.model = surveyService.getEntity(sid);
//	}

	/**
	 * 设计设想
	 * 
	 * @return
	 */
	public String designSurvey()
	{
		this.model = surveyService.getEntity(sid);
		return "designSurvey";
	}

	/************************ getter and setter ***************************/

	public void setMySurveyList(List<Survey> mySurveyList)
	{
		this.mySurveyList = mySurveyList;
	}

	public List<Survey> getMySurveyList()
	{
		return mySurveyList;
	}

	public Integer getSid()
	{
		return sid;
	}

	public void setSid(Integer sid)
	{
		this.sid = sid;
	}
}
