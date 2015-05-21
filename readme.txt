一、使用：implements SessionAware

//接收session的map
	private Map<String, Object> sessionMap;
	
	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.sessionMap = arg0;
	}

	sessionMap.put("user", user);

二、两种后台校验方式：
	方法一：只是在某个方法前校验：
		public String doLogin()
		{
			return "success";
		}
		
		**
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
	
	方法二：某个方法不校验
	添加注解：@SkipValidation注解是不校验的意思

三、修改基础架：

1.BasicService层：
	public abstract class BasicServiceImpl<T> implements BasicService<T>
	{
	
		protected BasicDao<T> basicDao;
	
		public abstract void setBasicDao(BasicDao<T> basicDao);
	。。。
	}
	
2.具体Service层：
	@Service("surveyService")
	public class SurveyServiceImpl extends BasicServiceImpl<Survey> implements SurveyService
	{
		private SurveyDao surveyDao;
	
		@Override
		@Resource(name="surveyDao")
		public void setBasicDao(BasicDao<Survey> basicDao)
		{
			super.basicDao = basicDao;
			this.surveyDao= (SurveyDao) basicDao;
		}
		
		@Resource(name="pageDao")
		private PageDao pageDao;
	。。。
	}


