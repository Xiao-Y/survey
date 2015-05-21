<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="divOuterFrame">
	<div class="divInnerFrame">欢迎使用SurveyDoor调查系统!</div>
</div>
<div class="divWhiteLine"></div>
<div class="divNavigatorOuterFrame" style="height: 30px">
	<div class="divNavigatorInnerFrame" style="height: 30px">
		<s:a action="LoginAction_toLoginPage" namespace="/">[首页]</s:a>&nbsp;
		<s:a action="SurveyAction_newSurvey" namespace="/">[新建调查]</s:a>&nbsp;
		<s:a action="SurveyAction_mySurveysListPage.action" namespace="/">[我的调查]</s:a>&nbsp;
		<s:a namespace="/" action="EngageSurveyAction_findAllAvailableSurveys">[参与调查]</s:a>&nbsp;
		<s:a action="RegAction_toRegPage" namespace="/">[用户注册]</s:a>&nbsp;
		[用户授权管理]&nbsp;
		[角色管理]&nbsp;
		<s:a namespace="/" action="/RightAction_findAllRights">[权限管理]</s:a>&nbsp;
		[日志管理]&nbsp;
	</div>
</div>
<div class="divWhiteLine"></div>