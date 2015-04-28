package com.ttsoft.bjtax.smsb.gdzys.sbzs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统--耕地占用税征收管理</p>
 * <p>Description:出具滞纳金缴款书Action </p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysCjznjjksAction extends SmsbAction{
	 /**
	   * 公共的前置处理方法，每次进入页面都会被调用<BR>
	   * 设置页面显示时使用的导航信息和标题
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   */
	  protected void initialRequest(ActionMapping mapping,
	                                ActionForm form,
	                                HttpServletRequest request,
	                                HttpServletResponse response)

	  {
	    super.initialRequest(mapping, form, request, response);
	    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
	                         "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
	                        // "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
	                         "<font color=\"#7C9AAB\">耕地占用征收</font>&gt;"+
	                         "<font color=\"#7C9AAB\">逾期未缴纳出具缴款书</font>");
	    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                         "逾期未缴纳出具缴款书");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "help/smsb/lszs/jkscx-000.htm");
	  }
	  
	  /**
	   * 初始化
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @exception BaseException
	   */
	  public ActionForward doShow(ActionMapping mapping,
	                              ActionForm form,
	                              HttpServletRequest request,
	                              HttpServletResponse response) throws
	      BaseException
	  {
		  GdzysCjznjjksForm pf = (GdzysCjznjjksForm) form;
	    try
	    {
	      System.out.println("##############GdzysCjznjjksAction.doShow");
	     //设置查询结果提示标识未0
		  pf.setCxJgTsFlag("0");
	      //获得当前的userData对象
	      UserData ud = this.getUserData(request);
	    
	      //保存返回值--------Shi Yanfeng 20031029-------
	      request.setAttribute(mapping.getAttribute(), pf);
	      return mapping.findForward("Show");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	      throw ExceptionUtil.getBaseException(ex);
	    }
	  }
	  
	  
	  


	 /**
	   * 功能：查询可出具的滞纳金缴款书信息！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	  public ActionForward doQuery(ActionMapping mapping,
	                               ActionForm form,
	                               HttpServletRequest request,
	                               HttpServletResponse response) throws
	      BaseException
	  {
		  System.out.println("##############GdzysCjznjjksAction.doQuery");
	    //获得当前的userData对象
	    UserData ud = this.getUserData(request);

	    GdzysCjznjjksForm pf = (GdzysCjznjjksForm) form;
	    try
	    {
	      //把预装载的信息获得并填写
	      VOPackage vo = new VOPackage();
	      vo.setAction(GdzysCodeConstant.SMSB_QUERYACTION);
	      vo.setData(pf);
	      vo.setUserData(ud);
	      vo.setProcessor(GdzysCodeConstant.GDZYS_SBZS_CJZNJJKS_PROCESSOR);

	      //调用processor
	      pf = (GdzysCjznjjksForm) ZhsbProxy.getInstance().process(vo);
	      if(pf.getDataList().size()==0){
	    	//设置查询结果提示标识未1
	  	    pf.setCxJgTsFlag("1");
	      }else{
	       //设置查询结果提示标识未0
		    pf.setCxJgTsFlag("0");
	      }
	      request.setAttribute(mapping.getAttribute(), pf);
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex);
	    }
	    return mapping.findForward("Query");
	  }
	  
	/**
	   * 功能：查询可出具的滞纳金缴款书信息！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
public ActionForward doCkJks(ActionMapping mapping,
                             ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws
    BaseException
{
	  System.out.println("##############GdzysCjjksAction.doCkJks");
  //获得当前的userData对象
  UserData ud = this.getUserData(request);

  GdzysCjznjjksForm pf = (GdzysCjznjjksForm) form;
  try
  {
    //把预装载的信息获得并填写
    VOPackage vo = new VOPackage();
    vo.setAction(GdzysCodeConstant.SMSB_GDZYS_CKJKSACTION);
    vo.setData(pf);
    vo.setUserData(ud);
    vo.setProcessor(GdzysCodeConstant.GDZYS_SBZS_CJZNJJKS_PROCESSOR);

    //调用processor
    pf = (GdzysCjznjjksForm) ZhsbProxy.getInstance().process(vo);
    List dataList=pf.getDataList();
    Map map=(Map)dataList.get(0);
    
    System.out.println("#########jkpzh="+(String)map.get("jkpzh"));
    System.out.println("#########jsjdm="+(String)map.get("jsjdm"));
    
    JksPrintForm jkspf = new JksPrintForm();
    //不显示保存按钮
    jkspf.setSaveBtnVisible(false);
    //限缴日期不可编辑
    jkspf.setXjrqEdit(false);
    //显示备注
    jkspf.setBzVisible(true);
    
    jkspf.setHeadJkpzh((String)map.get("jkpzh"));
    jkspf.setHeadJsjdm((String)map.get("jsjdm"));
    jkspf.setHeadSjly(GdzysCodeConstant.SMSB_SJLY_GDZYS); //数据来源
    jkspf.setActionType("Show");
    request.setAttribute("jksPrintForm", jkspf);
    return mapping.findForward("Print");
  }
  catch (Exception ex)
  {
    throw ExceptionUtil.getBaseException(ex);
  }
}

//获取税务所数据
public ActionForward doGetJkpzh(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	System.out.println("###########ddddd######doGetJkpzh");
	GdzysCjznjjksForm pf = (GdzysCjznjjksForm) form;
	VOPackage vo = new VOPackage();
	vo.setData(pf);
            vo.setUserData(this.getUserData(request));
	vo.setProcessor(GdzysCodeConstant.GDZYS_SBZS_CJZNJJKS_PROCESSOR);

	vo.setAction(GdzysCodeConstant.SMSB_GDZYS_GETJKPZHCTION);
	pf = (GdzysCjznjjksForm) ZhsbProxy.getInstance().process(vo);
	response.setContentType("text/xml;charset=GB2312");
	response.setHeader("Cache-Control", "no-cache");
	PrintWriter responseOut = null;
	try {
		responseOut = response.getWriter();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String jkpzh="0";
	if(pf.getDataList().size()>0){
		Map jkpzhMap=(HashMap)pf.getDataList().get(0);
		jkpzh=(String)jkpzhMap.get("jkpzh");
	}
	responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
	responseOut.write("<re>");
	responseOut.write("<jkpzh>" + jkpzh + "</jkpzh>");
	responseOut.write("</re>");
	// responseOut.flush();
	responseOut.close();

	return null;
}




}
