package com.xiaoy.action.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.User;
import com.xiaoy.service.UserService;

@Controller
@Scope("prototype")
public class LoginAction extends BasicAction<User>
{
	private static final long serialVersionUID = -8733425298891227372L;

	//private User model = super.getModel();

	@Resource
	private UserService userService;

	private String inputPage;

	public String toLoginPage()
	{
		return "loginPage";
	}

	public String doLogin()
	{
		return "success";
	}

	public void prepareDoLogin()
	{
		this.inputPage = "/index.jsp";
	}

	/**
	 * 验证登陆信息<br/>
	 * 这种命名规则的方法只会在doLogin的方法上起作用
	 */
	public void validateDoLogin()
	{
		User user = userService.validLogin(super.model);
		if (user == null)
		{
			addActionError("用户名或密码错误");
			sessionMap.put("user", null);
		} else
		{
			sessionMap.put("user", user);
		}
	}

	/*************** getter and setter ******************************/
	public String getInputPage()
	{
		return inputPage;
	}

	public void setInputPage(String inputPage)
	{
		this.inputPage = inputPage;
	}
}
