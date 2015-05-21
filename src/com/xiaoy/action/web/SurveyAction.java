package com.xiaoy.action.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.action.form.SurveyForm;
import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;
import com.xiaoy.service.SurveyService;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SurveyAction extends BasicAction<SurveyForm>
{
	private SurveyForm model = super.getModel();
	
	@Resource
	private SurveyService surveyService;
	
	@SuppressWarnings("unused")
	private List<Survey> mySurveyList;

	public void setMySurveyList(List<Survey> mySurveyList)
	{
		this.mySurveyList = mySurveyList;
	}

	/**
	 * 查询我的调查列表
	 * @return
	 */
	public String mySurveysListPage()
	{
		User user = (User) sessionMap.get("user");
		this.mySurveyList = surveyService.findMySurveys(user);
		return "mySurveysListPage";
	}
	
	/**
	 * 新建调查页面
	 * @return
	 */
	public String newSurvey()
	{
		User user = (User) sessionMap.get("user");
		Survey survey = surveyService.newSurvey(user);
		BeanUtils.copyProperties(survey, model);
		
		return "designSurvey";
	}
}
