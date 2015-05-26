package com.xiaoy.action.web;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;
import com.xiaoy.service.SurveyService;
import com.xiaoy.struts2.UserAware;
import com.xiaoy.util.ValiDateUtil;

@Controller
@Scope("prototype")
public class SurveyAction extends BasicAction<Survey> implements UserAware
{
	private static final long serialVersionUID = -7000719030490941650L;

	/**
	 * 接收用户对象
	 */
	private User user;

	@Override
	public void setUser(User user)
	{
		this.user = user;
	}

	@Resource
	private SurveyService surveyService;

	private List<Survey> mySurveyList = null;

	// Survey的主键
	private Integer sid;

	// 上传文件
	private File logoPhoto;
	private String logoPhotoFileName;
	
	//动态指定错误页面
	private String inputPage;

	/**
	 * 查询我的调查列表
	 * 
	 * @return
	 */
	public String mySurveysListPage()
	{
		mySurveyList = surveyService.findMySurveys(user);
		return "mySurveysListPage";
	}

	/**
	 * 新建调查页面
	 * 
	 * @return
	 */
	public String newSurvey()
	{
		this.model = surveyService.newSurvey(user);
		return "designSurvey";
	}

	// /**
	// * 预处理拦截器：该方法只在designSurvey方法之前调用
	// */
	// public void prepareDesignSurvey()
	// {
	// this.model = surveyService.getEntity(sid);
	// }

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

	/**
	 * 进入编辑页面
	 * 
	 * @return
	 */
	public String toEditSurvey()
	{
		this.model = surveyService.getEntity(sid);
		return "editSurveyPage";
	}

	/**
	 * 保存调查信息
	 * 
	 * @return
	 */
	public String updateSurvey()
	{
		this.sid = model.getId();
		model.setUser(this.user);
		surveyService.updateEntity(model);
		return "designSurveyAction";
	}
	
	/**
	 * 预处理：当出现错误时，返回的是input，Strus.xml中使用OGNL找到inputPage
	 */
	public void prepareUpdateSurvey()
	{
		this.inputPage = "/editSurvey.jsp";
	}

	/**
	 * 删除调查
	 * 
	 * @return
	 */
	public String deleteSurvey()
	{
		surveyService.delecteEntity(surveyService.getEntity(sid));
		return "mySurveysListPageAction";
	}

	/**
	 * 清除答案
	 * 
	 * @return
	 */
	public String clearAnswer()
	{
		surveyService.clearAnswer(sid);
		return "mySurveysListPageAction";
	}

	/**
	 * 切换调查打开状态
	 * 
	 * @return
	 */
	public String toggleStatus()
	{
		surveyService.toggleStatus(sid);
		return "mySurveysListPageAction";
	}

	/**
	 * 到达添加logo页面
	 * 
	 * @return
	 */
	public String toAddLogoPage()
	{
		return "addLogoPage";
	}

	/**
	 * 实现logo上传
	 * 
	 */
	public String doAddLogo()
	{
		if (!ValiDateUtil.isValid(this.logoPhotoFileName))
		{
			// 1、文件上传

			// ① upload文件夹的真实路径
			String dir = ServletActionContext.getServletContext().getRealPath("/upload");
			// ②文件扩展名 .jpg .png等
			String ext = this.logoPhotoFileName.substring(this.logoPhotoFileName.lastIndexOf("."));
			// ③文件重新命名
			Long l = System.nanoTime();
			String newFileName = l + ext;
			// ④文件另存为
			File newFile = new File(dir, newFileName);
			logoPhoto.renameTo(newFile);

			// 2、更新文件路径
			surveyService.updateLogoPhoto(sid, "/upload/" + newFileName);
		}
		return "designSurveyAction";
	}
	
	public void prepareDoAddLogo()
	{
		this.inputPage = "/addLogo.jsp";
	}
	
	/**
	 * 图片是否存在
	 * @return
	 */
	public Boolean photoExists()
	{
		String path = model.getLogoPhotoPath();
		if(!ValiDateUtil.isValid(path))
		{
			String dir = ServletActionContext.getServletContext().getRealPath(path);
			File file = new File(dir);
			return file.exists();
		}
		return false;
	}

	/************************ getter and setter ***************************/

	public void setMySurveyList(List<Survey> mySurveyList)
	{
		this.mySurveyList = mySurveyList;
	}

	public List<Survey> getMySurveyList()
	{
		return mySurveyList;
	}

	public Integer getSid()
	{
		return sid;
	}

	public void setSid(Integer sid)
	{
		this.sid = sid;
	}

	public File getLogoPhoto()
	{
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto)
	{
		this.logoPhoto = logoPhoto;
	}

	public String getLogoPhotoFileName()
	{
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName)
	{
		this.logoPhotoFileName = logoPhotoFileName;
	}

	public String getInputPage()
	{
		return inputPage;
	}

	public void setInputPage(String inputPage)
	{
		this.inputPage = inputPage;
	}
}
