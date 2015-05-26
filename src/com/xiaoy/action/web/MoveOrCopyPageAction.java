package com.xiaoy.action.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Page;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;
import com.xiaoy.service.SurveyService;
import com.xiaoy.struts2.UserAware;

/**
 * 移动或复制页面
 * 
 * @author XiaoY
 * @date: 2015年5月26日 下午2:10:26
 */
@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BasicAction<Page> implements UserAware
{
	private static final long serialVersionUID = 8163735129883578232L;

	// 原页面id
	private Integer srcPid;

	// 目标页面id
	private Integer targPid;

	// 位置:0-之前 1-之后
	private int pos;

	// 目标调查id
	private Integer sid;

	private List<Survey> mySurveys;

	private User user;

	@Override
	public void setUser(User user)
	{
		this.user = user;
	}

	@Resource
	private SurveyService surveyService;

	public String toSelectTargetPage()
	{
		this.mySurveys = surveyService.findMySurveys(this.user);
		return "moveOrCopyPageListPage";
	}

	/**
	 * 进行页面移动/复制
	 */
	public String doMoveOrCopyPage()
	{
		surveyService.moveOrCopyPage(srcPid, targPid, pos);
		return "designSurveyAction";
	}

	/**************** getter and sertter ********************************/
	public Integer getSrcPid()
	{
		return srcPid;
	}

	public void setSrcPid(Integer srcPid)
	{
		this.srcPid = srcPid;
	}

	public List<Survey> getMySurveys()
	{
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys)
	{
		this.mySurveys = mySurveys;
	}

	public Integer getTargPid()
	{
		return targPid;
	}

	public void setTargPid(Integer targPid)
	{
		this.targPid = targPid;
	}

	public int getPos()
	{
		return pos;
	}

	public void setPos(int pos)
	{
		this.pos = pos;
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
