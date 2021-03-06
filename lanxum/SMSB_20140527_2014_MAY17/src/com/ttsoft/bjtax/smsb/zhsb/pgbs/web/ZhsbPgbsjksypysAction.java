/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import com.ttsoft.bjtax.sfgl.common.action.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块_评估补税</p>
 * <p>Description: 一票一税缴款书维护。</p>
 * @author zzb  20090327
 * @version 1.1
 */

public class ZhsbPgbsjksypysAction
    extends SfglAction
{
    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报缴款</font>");
        //评估补税
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "纳税评估税款");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsbypys-000.htm");
    }

    /**
     * 查询一票一税缴款书
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        ZhsbPgbsjksypysActionForm form = (ZhsbPgbsjksypysActionForm) actionForm;
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);

        //评估补税
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.pgbs.processor.ZhsbPgbsjksypysProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        try
        {
        	//评估补税
            form = (ZhsbPgbsjksypysActionForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书信息失败!");
        }
    }

    /**
     * 更新一票一税的缴款书维护
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doUpdate (ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	//评估补税
        ZhsbPgbsjksypysActionForm form = (ZhsbPgbsjksypysActionForm) actionForm;
        String columns[] = form.getColumns();
        form.setDataList(this.getValuesToList(httpServletRequest, columns));
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.pgbs.processor.ZhsbPgbsjksypysProcessor");
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        vo.setUserData(ud);
        vo.setData(form);
        try
        {
        	//评估补税
            form = (ZhsbPgbsjksypysActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书信息失败!");
        }
        if (form.getForward().equals("Back"))
        {
        	//评估补税
        	ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
            forwardForm.setActionType("Query");
            forwardForm.setJsjdm(form.getJsjdm());
            forwardForm.setNsrmc(form.getNsrmc());
            forwardForm.setPresbbh(form.getPresbbh());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setSjly(form.getSjly());
            httpServletRequest.setAttribute("zhsbjkswhActionForm", forwardForm);
            return actionMapping.findForward("Update");
        }

        //评估补税
        ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
        forwardForm.setActionType("Query");
        forwardForm.setJsjdm(form.getJsjdm());
        forwardForm.setNsrmc(form.getNsrmc());
        httpServletRequest.setAttribute("zhsbjkswhActionForm", forwardForm);
        return actionMapping.findForward("Update");

    }

    /**
     * 更新一票一税的缴款书维护
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doBack (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	//评估补税
        ZhsbPgbsjksypysActionForm form = (ZhsbPgbsjksypysActionForm) actionForm;
        if (form.getForward().equals("Back"))
        {
        	//评估补税
            ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
            forwardForm.setActionType("Query");
            forwardForm.setJsjdm(form.getJsjdm());
            forwardForm.setNsrmc(form.getNsrmc());
            //设置申报编号标志
            if (form.getPresbbh() == null || !form.getPresbbh().equals("1"))
            {
                forwardForm.setSbbh("");
                form.setSbbh("");
            }
            forwardForm.setPresbbh(form.getPresbbh());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setSjly(form.getSjly());
            //评估补税
            httpServletRequest.setAttribute("zhsbPgbsjkswhActionForm", forwardForm);
            return actionMapping.findForward("Back");
        }

        //评估补税
        ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
        forwardForm.setActionType("Query");
        forwardForm.setJsjdm(form.getJsjdm());
        forwardForm.setNsrmc(form.getNsrmc());
        //评估补税
        httpServletRequest.setAttribute("zhsbPgbsjkswhActionForm", forwardForm);
        return actionMapping.findForward("Back");

    }

}
