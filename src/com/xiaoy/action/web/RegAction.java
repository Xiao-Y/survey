package com.xiaoy.action.web;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.User;
import com.xiaoy.service.UserService;
import com.xiaoy.util.DataHelp;
import com.xiaoy.util.ValiDateUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RegAction extends BasicAction<User>
{
	private User model = super.getModel();

	private String confirmPassword;

	@Resource
	private UserService userService;

	/**
	 * 到达注册页面 这个@SkipValidation注解是不校验的意思
	 * 
	 * @return
	 */
	@SkipValidation
	public String toRegPage()
	{
		return "regPage";
	}

	/**
	 * 进行用户注册
	 * 
	 * @return
	 */
	public String doReg()
	{
		User user = new User();

		BeanUtils.copyProperties(model, user, new String[] { "regDate" });
		user.setPassword(DataHelp.md5(model.getPassword()));

		userService.saveEntity(user);
		return SUCCESS;
	}

	/**
	 * 校验
	 */
	@Override
	public void validate()
	{
		String email = model.getEmail();
		if (ValiDateUtil.isValid(email))
		{
			addFieldError("email", "email不能为空");
		}

		if (ValiDateUtil.isValid(model.getPassword()))
		{
			addFieldError("password", "密码不能为空");
		}

		if (ValiDateUtil.isValid(model.getNickName()))
		{
			addFieldError("nickName", "昵称不能为空");
		}

		if (hasErrors())
		{
			return;
		}

		if (!model.getPassword().equals(confirmPassword))
		{
			addFieldError("password", "两次密码不一置");
		}

		if (!userService.isValidEmailRepeat(model.getEmail()))
		{
			addFieldError("email", "邮箱被占用");
		}
	}

	/*************************** getter and setter ***********************************/
	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}
}
