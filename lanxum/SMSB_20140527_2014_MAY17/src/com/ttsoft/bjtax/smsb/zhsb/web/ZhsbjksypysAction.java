/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.web;

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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 一票一税缴款书维护。</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbjksypysAction
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
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "综合申报");
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
        ZhsbjksypysActionForm form = (ZhsbjksypysActionForm) actionForm;
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbjksypysProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        try
        {
            form = (ZhsbjksypysActionForm) ZhsbProxy.getInstance().process(vo);
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
        ZhsbjksypysActionForm form = (ZhsbjksypysActionForm) actionForm;
        String columns[] = form.getColumns();
        form.setDataList(this.getValuesToList(httpServletRequest, columns));
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbjksypysProcessor");
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        vo.setUserData(ud);
        vo.setData(form);
        try
        {
            form = (ZhsbjksypysActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书信息失败!");
        }
        if (form.getForward().equals("Back"))
        {
            ZhsbjkswhActionForm forwardForm = new ZhsbjkswhActionForm();
            forwardForm.setActionType("Query");
            forwardForm.setJsjdm(form.getJsjdm());
            forwardForm.setNsrmc(form.getNsrmc());
            forwardForm.setPresbbh(form.getPresbbh());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setSjly(form.getSjly());
            httpServletRequest.setAttribute("zhsbjkswhActionForm", forwardForm);
            return actionMapping.findForward("Update");
        }
        ZhsbjkswhActionForm forwardForm = new ZhsbjkswhActionForm();
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
        ZhsbjksypysActionForm form = (ZhsbjksypysActionForm) actionForm;
        if (form.getForward().equals("Back"))
        {
            ZhsbjkswhActionForm forwardForm = new ZhsbjkswhActionForm();
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
            httpServletRequest.setAttribute("zhsbjkswhActionForm", forwardForm);
            return actionMapping.findForward("Back");
        }
        ZhsbjkswhActionForm forwardForm = new ZhsbjkswhActionForm();
        forwardForm.setActionType("Query");
        forwardForm.setJsjdm(form.getJsjdm());
        forwardForm.setNsrmc(form.getNsrmc());
        httpServletRequest.setAttribute("zhsbjkswhActionForm", forwardForm);
        return actionMapping.findForward("Back");

    }

}
