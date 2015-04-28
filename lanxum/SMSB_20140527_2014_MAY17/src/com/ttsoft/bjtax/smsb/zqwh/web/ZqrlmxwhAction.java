/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ�������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ�������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����������ϸ��¼ά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlmxwhAction
    extends SmsbAction
{

    /**
     * ������ǰ�ô���������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����</font>&gt;<font color=\"#6F8EA2\">�걨����</font>&gt;��������ά��&gt;</td>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "��������ά��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/zqwh/zqwh-005.htm");

    }

    /**
     * ��ʾ����
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        try
        {
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ��ѯ����
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ǰ��ActionForm
        ZqrlmxwhForm sf = (ZqrlmxwhForm) actionForm;
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(sf);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_MXWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            sf = (ZqrlmxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), sf);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        //��ת
        return actionMapping.findForward("Query");
    }

    /**
     * ɾ������
     * @param mapping The ActionMapping used to select this instance
     * @param from The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doDelete (ActionMapping mapping, ActionForm from,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        ZqrlmxcxForm sf = (ZqrlmxcxForm) from;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setProcessor(CodeConstant.ZQWH_MXWH_PROCESSOR);
        vo.setData(sf);
        try
        {
            sf = (ZqrlmxcxForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), sf);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");

            return mapping.findForward("Delete");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���洦��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        String forwardFlag = "Save";
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        //��ǰ��ActionForm
        ZqrlmxwhForm pf = (ZqrlmxwhForm) form;
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_MXWH_PROCESSOR);
        //�����������Data����ActionForm
        vo.setData(pf);

        UserData userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));

        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            pf = (ZqrlmxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), pf);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
            //����ɹ�,��ת

            //����ɹ���򿪲�ѯҳ��
            ZqrlmxcxForm cxpf = new ZqrlmxcxForm();
            cxpf.setActionType("Query");
            cxpf.setHeadDjzclx(pf.getQueryDjzclx());
            cxpf.setHeadMonth(pf.getQueryMonth());
            cxpf.setHeadNd(pf.getQueryNd());
            cxpf.setHeadSz(pf.getQuerySz());
            cxpf.setHeadZqlx(pf.getQueryZqlx());
            request.setAttribute("zqrlmxcxForm", cxpf);
            forwardFlag = "Back";

        }
        catch (Exception ex)
        {
            //��ӡ������Ϣ
            ex.printStackTrace();
            forwardFlag = "Save";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * ��������������ѯҳ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doBack (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ZqrlmxwhForm pf1 = (ZqrlmxwhForm) form;
        ZqrlmxcxForm pf = new ZqrlmxcxForm();
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            pf.setActionType("Query");
            pf.setHeadDjzclx(pf1.getQueryDjzclx());
            pf.setHeadMonth(pf1.getQueryMonth());
            pf.setHeadNd(pf1.getQueryNd());
            pf.setHeadSz(pf1.getQuerySz());
            pf.setHeadZqlx(pf1.getQueryZqlx());
            request.setAttribute("zqrlmxcxForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Back");
    }

}