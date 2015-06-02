package com.xiaoy.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaoy.entities.Survey;

public class TestService
{

	private static SurveyService surveyService;
	
	@BeforeClass
	public static void initSurveyService()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-hibernate-c3p0.xml");
		surveyService = (SurveyService) ac.getBean("surveyService");
	}
	
	@Test
	public void getSurvey()
	{
		Survey su = surveyService.getEntity(4);
		System.out.println(su.getMaxOrderno());
		System.out.println(su.getMinOrderno());
	}
}
