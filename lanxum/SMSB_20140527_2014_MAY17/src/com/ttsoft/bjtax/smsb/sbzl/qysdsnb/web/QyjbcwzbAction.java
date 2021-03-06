/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 企业基本财务指标</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QyjbcwzbAction
    extends SmsbAction
{
    /**
     * 操作员数据
     */
    private UserData userData = null;

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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业基本财务指标情况表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "企业基本财务指标情况表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/qyjbcwzb/qyjbcwzb-000.htm");
    }

    /**
     * 初始化页面数据
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
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

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;
        userData = this.getUserData(request);
        sbForm.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //调用processor
            sbForm = (QyjbcwzbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sbForm);
            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 查询已申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;
        userData = this.getUserData(request);
        sbForm.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //调用processor
            sbForm = (QyjbcwzbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sbForm);
        }
        catch (Exception ex)
        {
            sbForm.reset(mapping, request);
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Query");
    }

    /**
     * 保存已申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
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
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;
        sbForm.setDataList(SfRequestUtil.getValuesToList(request,
            sbForm.getColumns()));
        userData = this.getUserData(request);
        sbForm.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //调用processor
            SbzlProxy.getInstance().process(vo);
            sbForm.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 删除已申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
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
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //调用processor
            SbzlProxy.getInstance().process(vo);
            sbForm.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
            return mapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}