package com.xiaoy.action.web;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.action.form.UserForm;
import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.User;
import com.xiaoy.service.UserService;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LoginAction extends BasicAction<UserForm> implements SessionAware
{
	private UserForm model = super.getModel();

	// 接收session的map
	private Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.sessionMap = arg0;
	}

	@Resource
	private UserService userService;

	public String toLoginPage()
	{
		return "loginPage";
	}

	public String doLogin()
	{
		return "success";
	}

	/**
	 * 验证登陆信息<br/>
	 * 这种命名规则的方法只会在doLogin的方法上起作用
	 */
	public void validateDoLogin()
	{
		User user = userService.validLogin(model);
		if (user == null)
		{
			addActionError("用户名或密码错误");
			sessionMap.put("user",null);
		} else
		{
			sessionMap.put("user", user);
		}
	}
}