package com.xiaoy.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xiaoy.action.web.LoginAction;
import com.xiaoy.action.web.RegAction;
import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.User;
import com.xiaoy.struts2.UserAware;

/**
 * 登陆拦截器
 * 
 * @author XiaoY
 * @date: 2015年5月22日 下午10:12:10
 */
public class LoginInterceptor implements Interceptor
{
	private static final long serialVersionUID = 7224652305921331279L;

	@Override
	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		BasicAction basicAction = (BasicAction) actionInvocation.getAction();
		if (basicAction instanceof LoginAction || basicAction instanceof RegAction)
		{
			// 放行
			return actionInvocation.invoke();
		} else
		{
			Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
			User user = (User) session.get("user");

			if (user != null)
			{
				//注入用户对象
				if(basicAction instanceof UserAware)
				{
					((UserAware)basicAction).setUser(user);
				}
				// 放行
				return actionInvocation.invoke();
			} else
			{
				return "login";
			}
		}
	}
	
	@Override
	public void destroy(){}

	@Override
	public void init(){}

}
