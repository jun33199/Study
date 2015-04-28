/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.srmxb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.syax.creports.Constants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;


/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author zhangyj
 * @version 1.1
 */

public class SrmxbAction2009 extends 
   SmsbAction 
   {
	/**
     * 操作员数据
     */

    private UserData userData = null;
    
    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm QysdsnbForm
     * @param httpServletRequest HttpServletRequest
     * @param response HttpServletResponse
     */

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税年度纳税申报表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "企业所得税年度纳税申报表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }
    
    /**
     * 初始化页面数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
    	SrmxbForm2009 srmxbForm = (SrmxbForm2009)form;
        userData = this.getUserData(request);
        this.getBaseForm(request, srmxbForm); //加入纳税人基本数据
        VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION); //设置操作类型
		vo.setUserData(userData);
		vo.setData(srmxbForm); //设置操作数据
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_SRMXB_PROCESSOR2009); //设置PROCESSOR
		try
		{
			srmxbForm =(SrmxbForm2009)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), srmxbForm); //把查询结果放入REQUEST
		}catch(Exception ex)
		{
			throw ExceptionUtil.getBaseException(ex);
		}

        return mapping.findForward("Show");
        
    }  
    
    /**
     * 保存页面数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    
    public ActionForward doSave (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
    	
		SrmxbForm2009 srmxbForm = (SrmxbForm2009)form;
     userData = this.getUserData(request);
	  String[] sb_columns = srmxbForm.getSb_columns();
	  srmxbForm.setSrmxb(SfRequestUtil.getValuesToList(request, sb_columns)); //设置页面数据
	  this.getBaseForm(request, srmxbForm); //加入纳税人基本信息
	  request.setAttribute(mapping.getAttribute(), srmxbForm);
	  
	  VOPackage vo = new VOPackage();
	  vo.setAction(CodeConstant.SMSB_SAVEACTION); //设置操作类型
	  vo.setData(srmxbForm); //设置操作数据
	  vo.setUserData(userData); 
	  vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_SRMXB_PROCESSOR2009); //设置PROCESSOR
	  try
	  {
		  srmxbForm = (SrmxbForm2009)SbzlProxy.getInstance().process(vo);
		  request.setAttribute(mapping.getAttribute(), srmxbForm);
	  }catch(Exception ex)
	  {
//		系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
	  }
	  request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！"); //返回保存结果
	  return mapping.findForward("Show");
	  
	}   
    
    /**
     * 删除页面数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doDelete (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
    	
		SrmxbForm2009 srmxbForm = (SrmxbForm2009)form;
        userData = this.getUserData(request);
        this.getBaseForm(request, srmxbForm); //加入纳税人基本信息
        
   	  VOPackage vo = new VOPackage();
   	  vo.setAction(CodeConstant.SMSB_DELETEACTION); //设置操作类型
   	  vo.setData(srmxbForm); //设置操作数据
   	  vo.setUserData(userData);
   	  vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_SRMXB_PROCESSOR2009); //设置PROCESSOR
   	  try
   	  {
   		srmxbForm= (SrmxbForm2009)SbzlProxy.getInstance().process(vo);
   		  request.setAttribute(mapping.getAttribute(), srmxbForm);
   	  }catch(Exception ex)
   	  {
//   		系统捕捉异常，根据异常类型抛出
   			throw ExceptionUtil.getBaseException(ex);
   	  }
   	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！"); //返回删除结果
   	  return mapping.findForward("Show");
	
	}   
    
    /**
     * 审核页面数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doCheck (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
    	
		SrmxbForm2009 srmxbForm = (SrmxbForm2009)form;
        userData = this.getUserData(request);
   	  String[] sb_columns = srmxbForm.getSb_columns();
   	  srmxbForm.setSrmxb(SfRequestUtil.getValuesToList(request, sb_columns)); //设置页面数据
   	  this.getBaseForm(request, srmxbForm); //加入纳税人基本信息
   	  request.setAttribute(mapping.getAttribute(), srmxbForm);
   	  
   	  VOPackage vo = new VOPackage();
   	  vo.setAction(CodeConstant.SMSB_CHECKACTION); //设置操作类型
   	  vo.setData(srmxbForm); //设置操作数据
   	  vo.setUserData(userData);
   	  vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.srmxb.processor.SrmxbProcessor2009"); //设置PROCESSOR
   	  try
   	  {
   		srmxbForm = (SrmxbForm2009)SbzlProxy.getInstance().process(vo);
   		request.setAttribute(mapping.getAttribute(), srmxbForm);
		
		if(srmxbForm.getCheckList()==null){
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
		}else if(srmxbForm.getCheckList().size()==0){
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
		}else if(srmxbForm.getCheckList().size()>0){
			request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsUtil2009.getCheckResults(srmxbForm.getCheckList()));
		}
		return mapping.findForward("Show");
   	  }catch(Exception ex)
   	  {
//   		系统捕捉异常，根据异常类型抛出
   			throw ExceptionUtil.getBaseException(ex);
   	  }
	}   
    /**
     * 退出页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doExit (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
	
	return mapping.findForward("Return");
	} 
    /**
	 * 获取纳税人基本信息
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request,SrmxbForm2009 form)
	{
		
		userData = this.getUserData(request);
		
		QysdsNewForm baseForm =(QysdsNewForm)request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
		}
		
		if(baseForm!=null)
		{
			form.setJsjdm(baseForm.getJsjdm());
			form.setSbrq(baseForm.getSbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			form.setSkssksrq(baseForm.getSkssksrq());
			form.setSkssjsrq(baseForm.getSkssjsrq());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setCkzd(baseForm.getCkzd());
			form.setZsfs(baseForm.getZsfs());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setGzglxs(baseForm.getGzglxs());
			form.setJmlx(baseForm.getJmlx());
			form.setNextTableURL(QysdsUtil2009.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_2009_1_1));
			form.setPreviousTableURL(QysdsUtil2009.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_2009_1_1));
		}
	}
    

}