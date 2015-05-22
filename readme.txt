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

四、拦截器的使用：
1、自定义拦截器：

	（1）定义类实现Interceptor接口：
		public class LoginInterceptor implements Interceptor
	
	（2）实现方法：
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
					// 放行
					return actionInvocation.invoke();
				} else
				{
					return "login";
				}
			}
		}
		
	（3）在struts.xml文件中注册：
		<!-- 自定义拦截器栈 -->
		<interceptors>
			<!-- 注册登陆拦截器 -->
			<interceptor name="loginInterceptor"
				class="com.xiaoy.interceptor.LoginInterceptor" />
			<!-- 定义拦截器桡 -->
			<interceptor-stack name="surveyStack">
				<interceptor-ref name="loginInterceptor" />
				<interceptor-ref name="defaultStack" />
			</interceptor-stack>
		</interceptors>

		<!-- 设置默认拦截器栈 -->
		<default-interceptor-ref name="surveyStack" />
		
		<!-- 定义全局结果 -->
		<global-results>
			<result name="login">/index.jsp</result>
		</global-results>

五、hibernater懒加载问题
解决方式：
	1、修改hibernate的配置文件lazy为false。不建议使用，性能很差。
	2、配置Spring的openSessionInViewFilter。可以起来一劳永逸的效果。不建议使用。性能不佳。
		在web.xml文件中配置：
		<!-- 页面渲染时打开Session，该过滤器必须在Struts2之前 -->
		<filter>
			<filter-name>openSessionInViewFilter</filter-name>
			<filter-class>org.springframework.orm.hibernate4.support.OpenSessionInViewFilter</filter-class>
		</filter>
	
		<filter-mapping>
			<filter-name>openSessionInViewFilter</filter-name>
			<url-pattern>/*</url-pattern>
		</filter-mapping>
	3、强行在Service层中初始化：
		在获取父类的同时，任意的遍历一次所有的子类。
		
六、模型赋值问题：
解决方式：
	1、手动压入栈中。耦合度高，不建议使用。
	2、手动将新模型中的数据set到旧模型中。性能不高，建议使用。
	3、使用paramsPrepareParamsStack + prepare的方式。
	
		1）在struts.xml文件中
		
		<!-- 自定义拦截器栈 -->
		<interceptors>
			<!-- 注册登陆拦截器 -->
			<interceptor name="loginInterceptor"
				class="com.xiaoy.interceptor.LoginInterceptor" />
			<!-- 定义拦截器桡 -->
			<interceptor-stack name="surveyStack">
				<interceptor-ref name="loginInterceptor" />
				<interceptor-ref name="paramsPrepareParamsStack" />
			</interceptor-stack>
		</interceptors>

		<!-- 设置默认拦截器栈 -->
		<default-interceptor-ref name="surveyStack" />
		
		2）在action中添加接口Preparable，实现public void prepare() throws Exception
		
		/**
		 * 预处理拦截器：该方法只在designSurvey方法之前调用
		 */
		public void prepareDesignSurvey()
		{
			this.model = surveyService.getEntity(sid);
		}
	
		/**
		 * 设计设想
		 * 
		 * @return
		 */
		public String designSurvey()
		{
			return "designSurvey";
		}
	
	4、修改ModelDrivenInterceptor中的refreshModelBeforeResult为true
		1）在struts.xml文件中
		
		<!-- 自定义拦截器栈 -->
		<interceptors>
			<!-- 注册登陆拦截器 -->
			<interceptor name="loginInterceptor"
				class="com.xiaoy.interceptor.LoginInterceptor" />
			<!-- 定义拦截器桡 -->
			<interceptor-stack name="surveyStack">
				<interceptor-ref name="loginInterceptor" />
				<interceptor-ref name="defaultStack">
					<!-- 修改ModelDrivenInterceptor中的refreshModelBeforeResult为true，表示刷新模型 -->
					<param name="modelDriven.refreshModelBeforeResult">true</param>
				</interceptor-ref>
			</interceptor-stack>
		</interceptors>
		
		2）在action中不用在实现Preparable
		
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
		















