package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.web;


import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;

import javax.servlet.http.HttpServletResponse;

import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.exception.BaseException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.framework.util.VOPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import com.ttsoft.common.model.UserData;
import java.util.HashMap;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.QysdsNbConstant2014;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.QysdsNbUtil2014;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.bo.CzzsfzjgNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.bo.ZfjgfzjgNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.CzzssdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.ZfjgqysdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;

/**
 * 
 * Description : 总分机构分支机构年报2014版（中华人民共和国企业所得税汇总纳税分支机构分配表）action类
 * @author zhangj
 * @version 2014-9-17 上午11:55:31
 */
public class ZfjgfzjgNbAction extends DcBaseAction
{

	public ZfjgfzjgNbAction()
    {
    }

    protected String beforePerform(HttpServletRequest request, HttpServletResponse response)
    {
        // 检查权限
        if(!SbzlAccess.getAuthority(SbzlAccess.CZZSQYNB2012, request)) {
        	
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    /**
     * 初始化企业所得税申报数据
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doQuery(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doQuery!!!");
        // 登记基本数据
        SWDJJBSJ djJbsj = null;
        // 构造VOPackage
        VOPackage vo = new VOPackage();
        Map pDataMap = new HashMap();
        UserData ud = (UserData)request.getSession(false).getAttribute("UserData");
        if(ud == null) {
            System.out.println("session is null");
        }
        String jsjdm = ud.yhid;

        Timestamp curDate = new Timestamp(System.currentTimeMillis());

        ZfjgfzjgNbBO qysdsnbbo = null;
        ZfjgqysdsNbVO qysdsnbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2014 qysdsUtil = new QysdsNbUtil2014();

        try {
            // 取得登记基本数据
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
            pDataMap.put(QysdsNbConstant2014.STRING_KEY_NDLX, QysdsNbConstant2014.STRING_KEY_NDLX_VALUE);

            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(QysdsNbConstant2014.ZFJGQYSDSNB_2014_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // 调用后台查询数据
            qysdsnbbo = (ZfjgfzjgNbBO)ShenbaoProxy.getInstance().process(vo);

            //qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            CzzssdsNbVO czzsjbvo = (CzzssdsNbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSNB_2014");
            //HashMap nsfs_zfjg = (HashMap)request.getSession(false).getAttribute("REQ_KEY_NSFS_ZFJG_OLD");
            qysdsnbvo = qysdsUtil.zfjgxxGetDataFromAconvert2XMLVO(qysdsnbbo, djJbsj, czzsjbvo, "1");
            
            //System.out.println("zfjg: nsfs_old========="+nsfs_zfjg.get("nsfs_old"));
            //System.out.println("zfjg: zfjg_old========="+nsfs_zfjg.get("zfjg_old"));
            
            String tmpxml = qysdsnbvo.toXML();
            System.out.println("Query xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            request.setAttribute("maxrows", ((HashMap) qysdsnbbo.getSbsj()).get(QysdsNbConstant2014.ZFJGSDSNB_2014_MAX_ROW));

            // 转化从A表获取的数据
            request.setAttribute("DataFromA", formatDataFromA(request));

            // 转向企业所得税亏损申报页面
            return CAConstants.QUERY;
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsnbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    private String formatDataFromA(HttpServletRequest request){
    	CzzssdsNbVO cvo = (CzzssdsNbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSNB_2014");
    	StringBuffer sb = new StringBuffer();
    	sb.append("nsfs:"+cvo.getSbsj().getNsfs()+";")
    	.append("zfjg:"+cvo.getSbsj().getZfjg()+";")
    	.append("ynsdse:"+cvo.getSbsj().getYnsdse()+";")
    	.append("zjgyftsdse:"+cvo.getSbsj().getZjgyftsdse()+";")
    	.append("czjzfpsdse:"+cvo.getSbsj().getCzjzfpsdse()+";")
    	.append("fzjgyftsdse:"+cvo.getSbsj().getFzjgyftsdse()+";")
    	.append("fpbl:"+cvo.getSbsj().getFpbl()+";")
    	.append("fpsdse:"+cvo.getSbsj().getFpsdse()+";")
    	.append("nsrsbh:"+cvo.getNsrxx().getNsrsbh()+";")
    	.append("nsrmc:"+cvo.getNsrxx().getNsrmc());
    	return sb.toString();
    }

    /**
     * 保存企业所得税申报分配表数据
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doSave(HttpServletRequest request,HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doSave!!!");
        if(!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }
        
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("save xml:::\n" + xmldata);
        UserData ud = (UserData) this.getUserData(request);
        System.out.println("ud.getCaflag() = " + ud.getCaflag());
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Map retmap = null;

        ZfjgfzjgNbBO qysdsnbbo = null;
        ZfjgqysdsNbVO qysdsNbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2014 qysdsUtil = new QysdsNbUtil2014();

        try
        {
        	this.doSaveSbsjZb(request,response,qysdsNbvo);
            if(ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch(ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            try
            {
                XMLParseHelper.parseXMLString(qysdsNbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
            }
            catch(ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            
            qysdsnbbo = qysdsUtil.Zfjgconvert2BO(qysdsNbvo);
            dzyj.setYwdm(qysdsNbvo.getYwlx());
            dzyj.setYwczlx(qysdsNbvo.getYwczlx());
            System.out.println("qmyj_ywdm = " + dzyj.getYwdm());
            System.out.println("qmyj_Ywczlx = " + dzyj.getYwczlx());

//            // 取得Form
            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
//
            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsnbbo);
            pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsNbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
            pData.put(QysdsNbConstant2014.STRING_KEY_NDLX, QysdsNbConstant2014.STRING_KEY_NDLX_VALUE);

            // 构造VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(QysdsNbConstant2014.ZFJGQYSDSNB_2014_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
            vo.setUserData(ud);
            // 调用后台Procxy,设置回执下载页面的后续操作。
            retmap = (Map) ShenbaoProxy.getInstance().process(vo);
            if(ud.getCaflag()) {
                dzyj = (DzyjsjVO)retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                ArrayList hzlist = new ArrayList();
                hzlist.add(Long.toString(dzyj.getLsh()));
                // request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, hzlist);
            }
            else {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            }
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "企业所得税分支机构年度纳税申报表、分配表" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"企业所得税分支机构年度纳税申报表、分配表" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_CZZSSDS_QYJB2012);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业所得税分支机构年度纳税申报表、分配表", qysdsNbvo.getSbxx().getSbrq(), "成功!");
//			return CAConstants.SUCCESSSB;
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsNbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsNbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsNbvo.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税分支机构年度纳税申报表、分配表", qysdsNbvo.getSbxx().getSbrq(), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
	/**
	 * 保存申报主表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public void doSaveSbsjZb(HttpServletRequest request,HttpServletResponse response,ZfjgqysdsNbVO qysdsNbvo) throws BaseException 
	{
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		Map retmap = null;

		CzzssdsNbVO qynbvo = (CzzssdsNbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSNB_2014");
		QysdsNbUtil2014 qysdsUtil = new QysdsNbUtil2014();

		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			

			CzzsfzjgNbBO qysdsndbo = qysdsUtil.Czzsconvert2VO(qynbvo);
			dzyj.setYwdm(qynbvo.getYwlx());
			dzyj.setYwczlx(qynbvo.getYwczlx());
			
			// 取得Form
			// 取得登记基本数据
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsndbo);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant2014.TABLE_ID_CZZSSDSNB_2014);
			pData.put(QysdsNbConstant2014.STRING_KEY_NDLX, QysdsNbConstant2014.STRING_KEY_NDLX_VALUE);
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qynbvo);
			pData.put("fpb_fzjgftse", qysdsNbvo.getZjgxx().getFzjgftse());
			//查询A类表中纳税方法与总分机构
			HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
			if(("".equals(qysdsndbo.getNsfs_fsjg().get("nsfs"))) || qysdsndbo.getNsfs_fsjg().get("nsfs")==null){
				qysdsndbo.getNsfs_fsjg().put("nsfs", "0");
			}
			if(("".equals(qysdsndbo.getNsfs_fsjg().get("zfjg"))) || qysdsndbo.getNsfs_fsjg().get("zfjg")==null){
				qysdsndbo.getNsfs_fsjg().put("zfjg", "0");
			}
			if(qysdsndbo.getNsfs_fsjg().get("nsfs").equals("2")){
				qysdsndbo.getNsfs_fsjg().put("zfjg", "0");
			}
			nsfs_zfjg.put("nsfs_old", qysdsndbo.getNsfs_fsjg().get("nsfs"));
			nsfs_zfjg.put("zfjg_old", qysdsndbo.getNsfs_fsjg().get("zfjg"));
			

			// 构造VoPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(QysdsNbConstant2014.CZZSQYSDSNB_PROCESSOR_2014);
			vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
			vo.setUserData(ud);
			// 调用后台Procxy
			// 设置回执下载页面的后续操作。
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);
			String nsfsOld = (String)nsfs_zfjg.get("nsfs_old");
			String zfjgOld = (String)nsfs_zfjg.get("zfjg_old");
			
			if(qysdsndbo.getNsfs().equals("1")){
				if(!zfjgOld.equals(qysdsndbo.getZfjg())){
					qysdsUtil.deleteCascadeZfjgData(pData);
				}
				qysdsUtil.saveCascadeZfjgData(pData);
			}else{
				qysdsUtil.deleteCascadeZfjgData(pData);
			}

			if (ud.getCaflag()) {
				dzyj = (DzyjsjVO) retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				ArrayList hzlist = new ArrayList();
				hzlist.add(Long.toString(dzyj.getLsh()));
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,hzlist);
			} else {
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			}
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业所得税分支机构年度纳税申报表、分配表", qynbvo.getSbxx().getSbrq(), "成功!");
		} catch (Exception ex) {
			// /3.9.设置空xml，转向失败页面
			ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业所得税分支机构年度纳税申报表、分配表", qynbvo.getSbxx().getSbrq(), "失败!");
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
    /**
     * 删除企业所得税申报数据
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        // 检查token
        if(!isTokenValid(request))
        {
            return CAConstants.INVALIDTOKEN;
        }

        // 得到xml
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Timestamp sbrq = new Timestamp(System.currentTimeMillis());

        ZfjgfzjgNbBO qysdsNbbo = null;
        ZfjgqysdsNbVO qysdsNbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2014 qysdsUtil = new QysdsNbUtil2014();

        try {
            if(ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch(ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            System.out.println("delete xml:" + xmldata);

            try {
                XMLParseHelper.parseXMLString(qysdsNbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false, true);
            }
            catch(ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            dzyj.setYwczlx(qysdsNbvo.getYwczlx());
            dzyj.setYwdm(qysdsNbvo.getYwlx());

            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
            // 更新form中的数据
            qysdsNbbo = qysdsUtil.Zfjgconvert2BO(qysdsNbvo);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsNbbo);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsNbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
            pData.put(QysdsNbConstant2014.STRING_KEY_NDLX, QysdsNbConstant2014.STRING_KEY_NDLX_VALUE);
            // 构造VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(QysdsNbConstant2014.ZFJGQYSDSNB_2014_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
            vo.setUserData(ud);
            // 调用后台Proxy
            ShenbaoProxy.getInstance().process(vo);

            request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税分支机构年度纳税申报表、分配表" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业所得税分支机构年度纳税申报表、分配表", qysdsNbvo.getSbxx().getSbrq(), "成功!");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"企业所得税分支机构年度纳税申报表、分配表" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsNbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsNbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsNbvo.getSchemaVersion());
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "删除企业所得税汇总纳税分支机构分配表",(new SimpleDateFormat("yyyyMMdd")).format(sbrq), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * 返回操作
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     * @throws BaseException
     */
    public String doReturn(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException
    {
        return "Return";
    }

    /**
     * 返回查帐征收页面
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     * @throws BaseException
     */
    public String doJump(HttpServletRequest request,
            HttpServletResponse response) throws BaseException {
        System.out.println("i am jumping...");

        // 转向企业所得税查帐征收申报页面
        return "Jump";
    }
    
    /**
     * 初始化企业所得税申报数据  
     * 无申报记录
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doShow!!!****************************");
        // 登记基本数据
        SWDJJBSJ djJbsj = null;
        // 构造VOPackage
        VOPackage vo = new VOPackage();
        Map pDataMap = new HashMap();
        UserData ud = (UserData)request.getSession(false).getAttribute("UserData");
        if(ud == null) {
            System.out.println("session is null");
            
        }
        String jsjdm = ud.yhid;

        Timestamp curDate = new Timestamp(System.currentTimeMillis());

        ZfjgfzjgNbBO qysdsnbbo = null;
        ZfjgqysdsNbVO qysdsnbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2014 qysdsUtil = new QysdsNbUtil2014();

        try {
            // 取得登记基本数据
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

//            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
//            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
//            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
//            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant.TABLE_ID_ZFJGSDSNB_2012);
//            pDataMap.put(QysdsNbConstant.STRING_KEY_NDLX, QysdsNbConstant.STRING_KEY_NDLX_VALUE);
//
//            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
//            vo.setProcessor(QysdsNbConstant2014.ZFJGQYSDSNB_2014_PROCESSOR);
//            vo.setData(pDataMap);
//            vo.setUserData(ud);
//            // 调用后台查询数据
//            qysdsnbbo = (ZfjgqysdsNbBO)ShenbaoProxy.getInstance().process(vo);

           
            CzzssdsNbVO czzsjbvo = (CzzssdsNbVO)request.getSession(false).getAttribute(QysdsNbConstant2014.REQ_KEY_QYSBSNB_2014);
            //HashMap nsfs_zfjg = (HashMap)request.getSession(false).getAttribute("REQ_KEY_NSFS_ZFJG_OLD");
            qysdsnbvo = qysdsUtil.CzzssdsNbVOGetDataFromAconvert2XMLVO(djJbsj, czzsjbvo);
            
            //System.out.println("zfjg: nsfs_old========="+nsfs_zfjg.get("nsfs_old"));
            //System.out.println("zfjg: zfjg_old========="+nsfs_zfjg.get("zfjg_old"));
            
            String tmpxml = qysdsnbvo.toXML();
            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            request.setAttribute("maxrows", String.valueOf(QysdsNbConstant2014.ZFJGSDSNB_2014_DEFAULT_MX_ROW_NUMBER));

            // 转化从A表获取的数据
            request.setAttribute("DataFromA", formatDataFromA(request));

            // 转向企业所得税亏损申报页面
            return CAConstants.QUERY;
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsnbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}
