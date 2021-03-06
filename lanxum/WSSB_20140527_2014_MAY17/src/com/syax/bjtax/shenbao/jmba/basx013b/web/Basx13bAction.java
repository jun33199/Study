package com.syax.bjtax.shenbao.jmba.basx013b.web;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jcdlscqy12.web.JcdlscqyAction;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba013bVo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba07Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba08Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;

import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;

import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.syax.bjtax.shenbao.model.dm.JMBASXDM;
import com.syax.bjtax.shenbao.model.dm.JSZRLX;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;

/**
 *
 * <p>
 * Title: 北京地税核心征管系统－－上门申报
 * </p>
 *
 * <p>
 * Description: （七）符合条件的技术转让所得
 * </p>
 *
 * <p>
 * Copyright: 四一安信
 * </p>
 *
 * <p>
 * Company: 四一安信
 * </p>
 *
 * @author wangcl
 * @version 1.1
 */
public class Basx13bAction extends DcBaseAction {
	public Basx13bAction() {

	}






	 private JmbaZbVO completeDm(JmbaZbVO vo,Map dmMap){
		   	vo.setJmbasxmc(((JMBASXDM)dmMap.get(vo.getJmbasxdm())).getJMBASXMC());
		   	return vo;
		   }
	 private JmbaVO convertToXmlVO(JmbaZbVO zb,UserData ud,SWDJJBSJ jbsj) {
			//1 最上层VO
		      JmbaVO vo = new JmbaVO();
		      //2 纳税人VO 1.set 2
		      NsrxxVO nsrxx = new NsrxxVO();
		      nsrxx.setJsjdm(jbsj.getJsjdm());
		      nsrxx.setNsrmc(jbsj.getNsrmc());
		      nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		      //1.set 2
		      vo.setNsrxx(nsrxx);
		      List zbl=new ArrayList();
		      zbl.add(zb);
		      vo.setJmsbajl(zbl);

		  	vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);
		  	vo.setYwlx(VOConstants.ZLBM_QYSDSJMBA_BA13 );
		     vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		      vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		      return vo;
		  }
	 public String doEditZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
		{
		        System.out.println("into save action");
		    if (!isTokenValid(request))
		    {
		        return CAConstants.INVALIDTOKEN;
		    }
			  // 取得userdata
		    UserData userdata = (UserData) this.getUserData(request);
	      DzyjsjVO dzyj =  new DzyjsjVO();
	      JmbaVO vo = new JmbaVO();

	      // 验证电子元件签名
	      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
	      DzyjHelper dh = new DzyjHelper();
	      if (userdata.getCaflag())
	      {
	          try
	          {
	              dzyj = dh.verifyAndSign(request, userdata.getCert(), userdata.getSsdwdm());
	          }
	          catch (ApplicationException e)
	          {
	              throw ExceptionUtil.getBaseException(e);
	          }
	      }
	      try
	      {
	          XMLParseHelper.parseXMLString(vo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
	      }
	      catch (ApplicationException e)
	      {
	          throw ExceptionUtil.getBaseException(e);
	      }

	      dzyj.setYwczlx(vo.getYwczlx());
	      dzyj.setYwdm(vo.getYwlx());

	      HashMap hm = new HashMap();
	      hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
	      hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

		    Debug.out("------------------------------------"+xmldata);
		    // 生成VOPackage
		    VOPackage voPackage = new VOPackage();
		    // 设定vopackage参数
		    voPackage.setProcessor(ProcessorNames.FHTJJSZRSD13B_PROCESSOR);
		    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
		    voPackage.setUserData(userdata);
		    voPackage.setData(hm);

		    // 调用后台操作，取得返回值
		     ShenbaoProxy.getInstance().process(voPackage);
	      request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
	      //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
	            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案保存",
	                    (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

		            return  CAConstants.EDITZB;
		}
	// 初始化页面,展示的是多条记录。
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if("VIEW".equals(request.getSession().getAttribute("XSLLX13b"))){
			request.getSession().setAttribute("XSLLX13b", null);
		}
		// 获取代码表的值。代码表的值的获取要修改

		 List codeTable1 = CodeTableUtil.getCodeTableList(request,
                 CodeTable.JMBA_GXJSLY_LIST);

		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.FHTJJSZRSD13B_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的session中获取
		JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
	      voPackage.setData(bo);
	      JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
	      zbvo=completeDm(zbvo,CodeTableUtil.getCodeTableMap(request, CodeTable.JMBA_BASX_MAP));
		// 拼装jmbaVO
	     JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
	     if (zbvo.getQysdsjmba()==null || zbvo.getQysdsjmba().size()==0){
		      	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		      }
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession(false).setAttribute("codeTable", codeTable1);
		if(vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)){

		 request.setAttribute("JSJRLX13b", ((Jmba013bVo) (zbvo.getQysdsjmba().get(0))).getGxjslydm());
		}
        String querystate = "0";
            try {
                JmbaZbVO vo1 = (JmbaZbVO)vo.getJmsbajl().get(0);
                Jmba013bVo mxvo = (Jmba013bVo)vo1.getQysdsjmba().get(0);
                SWDJJBSJ jbsj1 = null;
                if(mxvo.getBtzqyssd().equals("1")){
                    querystate = "0";
                }else if(mxvo.getBtzqyjsjdm()!=null &&!"".equals(mxvo.getBtzqyjsjdm())){
                    jbsj1 = FriendHelper.getDjJbsj(mxvo.getBtzqyjsjdm());
                    if(jbsj1 != null) {
                        querystate = "1";
                    }

                }else{
                    querystate = "-1";
                }

            }
            catch(Exception ex) {
                ex.printStackTrace();

            }
            request.getSession(false).setAttribute("querystate", querystate);


				return CAConstants.SHOW;

		// 转向到显示页面

		// return null;
	}

	// 获取数据。
	public String doSwdjzhHqwnsj(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		 List codeTable1 = CodeTableUtil.getCodeTableList(request,
                 CodeTable.JMBA_GXJSLY_LIST);
			UserData userdata = (UserData) this.getUserData(request);

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		System.out.println("jmba xmldata" + xmldata);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO jmbavo = new JmbaVO();

		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e) {
					throw ExceptionUtil.getBaseException(e);
				}
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++");
			try {
				// XMLParseHelper.parseXMLString(wsksbvo, xmldata,
				// XMLParseHelper.XERCES_PARSER, true,true);
				// wsksbvo = new WsksbVO();
				XMLParseHelper.parseXMLString(jmbavo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++");

			dzyj.setYwczlx(jmbavo.getYwczlx());
			dzyj.setYwdm(jmbavo.getYwlx());
			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			voPackage.setUserData(ud);
			HashMap hm = new HashMap();
			// hm.put(ZhsbMapConstant.SBSJ, jmbavo);
			// hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			hm.put("type", "Save");
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.FHTJJSZRSD13B_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY1);

			 JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
			 Debug.out(zbvo.toXML());
		      zbvo=completeDm(zbvo,CodeTableUtil.getCodeTableMap(request, CodeTable.JMBA_BASX_MAP));
			// 拼装jmbaVO
		     JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		     String strXml = vo.toXML();
		     Debug.out(strXml);
		     if (zbvo.getQysdsjmba()==null || zbvo.getQysdsjmba().size()==0){
			      	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			      }
			System.out
					.println("执行操作之后要+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"符合条件的技术转让所得减免备案保存成功！");
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"符合条件的技术转让所得减免备案保存",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
			request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
					.getXsltVersion());
			request.getSession(false).setAttribute("codeTable", codeTable1);
			if(vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)){

			 request.setAttribute("JSJRLX13b", ((Jmba013bVo) (zbvo.getQysdsjmba().get(0))).getGxjslydm());
			}
            String querystate = "0";
            try {
                JmbaZbVO vo1 = (JmbaZbVO)vo.getJmsbajl().get(0);
                Jmba013bVo mxvo = (Jmba013bVo)vo1.getQysdsjmba().get(0);
                SWDJJBSJ jbsj1 = FriendHelper.getDjJbsj(mxvo.getBtzqyjsjdm());
                if(jbsj1!=null){
                    querystate = "1";
                }
            }
            catch(Exception ex) {
                ex.printStackTrace();

            }
            request.getSession(false).setAttribute("querystate", querystate);
			return CAConstants.SHOW;
		} catch (Exception e) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					jmbavo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					jmbavo.getSchemaVersion());
			System.out.println("减免备案异常信息: ===== " + e.getMessage());

			throw ExceptionUtil.getBaseException(e);
		}
	}

	// 获取数据。
	public String doJsjdmHqwnsj(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		 List codeTable1 = CodeTableUtil.getCodeTableList(request,
                 CodeTable.JMBA_GXJSLY_LIST);
			UserData userdata = (UserData) this.getUserData(request);

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		System.out.println("jmba xmldata" + xmldata);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO jmbavo = new JmbaVO();

		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e) {
					throw ExceptionUtil.getBaseException(e);
				}
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++");
			try {
				// XMLParseHelper.parseXMLString(wsksbvo, xmldata,
				// XMLParseHelper.XERCES_PARSER, true,true);
				// wsksbvo = new WsksbVO();
				XMLParseHelper.parseXMLString(jmbavo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++");

			dzyj.setYwczlx(jmbavo.getYwczlx());
			dzyj.setYwdm(jmbavo.getYwlx());
			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			voPackage.setUserData(ud);
			HashMap hm = new HashMap();
			// hm.put(ZhsbMapConstant.SBSJ, jmbavo);
			// hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			hm.put("type", "Save");
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.FHTJJSZRSD13B_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);

			 JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
			 Debug.out(zbvo.toXML());
		      zbvo=completeDm(zbvo,CodeTableUtil.getCodeTableMap(request, CodeTable.JMBA_BASX_MAP));
			// 拼装jmbaVO
		     JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		     String strXml = vo.toXML();
		     Debug.out(strXml);
		     if (zbvo.getQysdsjmba()==null || zbvo.getQysdsjmba().size()==0){
			      	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			      }

			System.out
					.println("执行操作之后要+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"符合条件的技术转让所得减免备案保存成功！");
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"符合条件的技术转让所得减免备案保存",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
			request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
					.getXsltVersion());
			request.getSession(false).setAttribute("codeTable", codeTable1);
			if(vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)){

			 request.setAttribute("JSJRLX13b", ((Jmba013bVo) (zbvo.getQysdsjmba().get(0))).getGxjslydm());
			}
            String querystate = "0";
            try {
                JmbaZbVO vo1 = (JmbaZbVO)vo.getJmsbajl().get(0);
                Jmba013bVo mxvo = (Jmba013bVo)vo1.getQysdsjmba().get(0);
                SWDJJBSJ jbsj1 = FriendHelper.getDjJbsj(mxvo.getBtzqyjsjdm());
                if(jbsj1!=null){
                    querystate = "1";
                }
            }
            catch(Exception ex) {
                ex.printStackTrace();

            }
            request.getSession(false).setAttribute("querystate", querystate);
			return CAConstants.SHOW;
		} catch (Exception e) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					jmbavo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					jmbavo.getSchemaVersion());
			System.out.println("减免备案异常信息: ===== " + e.getMessage());

			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 录入备案申请 初始请求处理
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doAdd(HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// 获取代码表的数据
		List codeTable = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JSZRLX_LIST);
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.FHTJJSZRSD07_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		// test if
		if (BASQWSH == null)
			BASQWSH = "062008000002";
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("jsjdm", jbsj.getJsjdm());
		map.put("type", "ADD");

		voPackage.setData(map);

		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);

		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH, "ADD");
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession(false).setAttribute("codeTable", codeTable);
		request.getSession(false).setAttribute("buss", "Save");
		// 转向到显示页面
		return CAConstants.ADDSHOW;
	}

	/**
	 * 编辑备案申请 初始请求处理
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doView(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		// 获取代码表的值。代码表的值的获取要修改

		 List codeTable1 = CodeTableUtil.getCodeTableList(request,
                 CodeTable.JMBA_GXJSLY_LIST);

		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.FHTJJSZRSD13B_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW1);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的session中获取
		JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
	      voPackage.setData(bo);
	      JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
	      zbvo=completeDm(zbvo,CodeTableUtil.getCodeTableMap(request, CodeTable.JMBA_BASX_MAP));
		// 拼装jmbaVO
	     JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
	     if (zbvo.getQysdsjmba()==null || zbvo.getQysdsjmba().size()==0){
		      	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		      }
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);

		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession(false).setAttribute("codeTable", codeTable1);
		request.getSession().setAttribute("XSLLX13b", "VIEW");


		 request.setAttribute("JSJRLX13b", ((Jmba013bVo) (zbvo.getQysdsjmba().get(0))).getGxjslydm());

				return CAConstants.SHOW;

	}

	/**
	 * 删除备案申请 初始请求处理
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doDel(HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		String selIndex = request.getParameter("selIndex");
		System.out.println("selIndex = " + selIndex);

		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.FHTJJSZRSD07_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_DELETE);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		// test if
		if (BASQWSH == null)
			BASQWSH = "062008000002";
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("selIndex", selIndex);
		map.put("type", "DEL");

		voPackage.setData(map);

		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);

		// 转向到显示页面
		return doShow(request, response);
	}

	private JmbaVO convertToXmlVO(Map map, UserData ud, SWDJJBSJ jbsj,
			String BASQWSH, String type) {
		// 1 最上层VO
		JmbaVO vo = new JmbaVO();
		// 2 纳税人VO 1.set 2
		NsrxxVO nsrxx = new NsrxxVO();
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(jbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		// 1.set 2
		vo.setNsrxx(nsrxx);
		// 3 主表VO 1-n 备案文书号 1.list.add 3s
		JmbaZbVO zbvo = (JmbaZbVO) map.get("JmbaZbVO");
		// db query result,set zbvo data
		zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM07);
		zbvo.setBasqwsh(BASQWSH);
		// SF_JL_QYSDSJMSBAJLZLQD
		// List zlqdVOs = (List) map.get("JmbaZlqdVO");
		// vo07.setJszrlxdm("01");
		// 3.list.add 4s
		if (zbvo.getQysdsjmba().isEmpty() && type.equals("ADD")) {
			Jmba07Vo mxvo = new Jmba07Vo();
			mxvo.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1
					+ "");
			mxvo.setLrr(jbsj.getJsjdm());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			mxvo.setLrrq(format.format(new Date(System.currentTimeMillis())));
			zbvo.getQysdsjmba().add(mxvo);
		}
		// 资料清单查询VO处理,功能页面暂时不调用
		// zbvo.getBajlzlqd().add(zlqdVOs);
		// 1.list.add 3s
		vo.getJmsbajl().add(zbvo);
		vo.setNsrxx(nsrxx);
		vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA07);
		// vo.setYwczlx("");
		return vo;
	}

	// private JmbaVO convertToXmlVO(Map map,UserData ud,SWDJJBSJ jbsj) {
	// //1 最上层VO
	// JmbaVO vo = new JmbaVO();
	// //2 纳税人VO 1.set 2
	// NsrxxVO nsrxx = new NsrxxVO();
	// nsrxx.setJsjdm(jbsj.getJsjdm());
	// nsrxx.setNsrmc(jbsj.getNsrmc());
	// nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
	// //1.set 2
	// vo.setNsrxx(nsrxx);
	// //3 主表VO 1-n 备案文书号 1.list.add 3s
	// JmbaZbVO zbvo = (JmbaZbVO) map.get("JmbaZbVO");
	// //db query result,set zbvo data
	// zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM07);
	// zbvo.setBasqwsh("062008100001");
	// //SF_JL_QYSDSJMSBAJLZLQD
	// //List zlqdVOs = (List) map.get("JmbaZlqdVO");
	// Jmba07Vo vo07 = (Jmba07Vo)map.get("Jmba07VO");
	// vo07.setJszrlxdm("01");
	// //3.list.add 4s
	// zbvo.getQysdsjmba().add(vo07);
	// // 资料清单查询VO处理,功能页面暂时不调用
	// // zbvo.getBajlzlqd().add(zlqdVOs);
	// //1.list.add 3s
	// vo.getJmsbajl().add(zbvo);
	// vo.setNsrxx(nsrxx);
	// vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
	// vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
	// vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA07);
	// //vo.setYwczlx("");
	// return vo;
	// }

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		System.out.println("jmba xmldata" + xmldata);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO jmbavo = new JmbaVO();

		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e) {
					throw ExceptionUtil.getBaseException(e);
				}
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++");
			try {
				// XMLParseHelper.parseXMLString(wsksbvo, xmldata,
				// XMLParseHelper.XERCES_PARSER, true,true);
				// wsksbvo = new WsksbVO();
				XMLParseHelper.parseXMLString(jmbavo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++");

			dzyj.setYwczlx(jmbavo.getYwczlx());
			dzyj.setYwdm(jmbavo.getYwlx());
			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			voPackage.setUserData(ud);
			HashMap hm = new HashMap();
			// hm.put(ZhsbMapConstant.SBSJ, jmbavo);
			// hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			hm.put("type", "Save");
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.FHTJJSZRSD13B_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
			System.out
					.println("执行操作之前要++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			 ShenbaoProxy.getInstance().process(voPackage);
			System.out
					.println("执行操作之后要+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			// 签名原件暂不实现 20091227
			// dzyj = (DzyjsjVO) ((HashMap)
			// voPackage.getData()).get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
			// 如果是CA户则进行签名回写

			// jmbavo = convert2XMLVOResult(request, jbsj, wynsksb, now);
			// String tmpxml = jmbavo.toXML();
			// request.setAttribute("CA_XML_DATA", tmpxml);
			// request.setAttribute("CA_XML_XSLT_VERSION",
			// jmbavo.getXsltVersion());
			// request.setAttribute("CA_XML_SCHEMA_VERSION",
			// jmbavo.getSchemaVersion());

			// 设置回执下载页面的后续操作。
			// 签名原件暂不实现 20091227
			/*
			 * if (ud.getCaflag()) {
			 * request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,
			 * Long.toString(dzyj.getLsh())); } else {
			 * request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, ""); }
			 */
			System.out
					.println("执行操作之后要++++++++++++++++++2222++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"符合条件的技术转让所得减免备案保存成功！");
			// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
			// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"符合条件的技术转让所得减免备案保存",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
			System.out
					.println("执行操作之后要+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			return doShow(request, response);
			// return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
			// CAConstants.SUCCESSSB);
		} catch (Exception e) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					jmbavo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					jmbavo.getSchemaVersion());
			System.out.println("减免备案异常信息: ===== " + e.getMessage());

			throw ExceptionUtil.getBaseException(e);
		}

	}
	 public String doUpdate(HttpServletRequest request, HttpServletResponse response) throws BaseException
	  {

	      if (!isTokenValid(request))
	      {
	          return CAConstants.INVALIDTOKEN;
	      }
	      String selIndex = (String) request.getSession(false).getAttribute("selIndex");
	      System.out.println("selIndex !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!= "+selIndex);

	      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
	      // 生成VOPackage
	      VOPackage voPackage = new VOPackage();
	      UserData ud = (UserData) this.getUserData(request);
	      DzyjHelper dh = new DzyjHelper();
	      System.out.println("jmba xmldata" + xmldata);
	      DzyjsjVO dzyj =  new DzyjsjVO();
	      JmbaVO jmbavo = new JmbaVO();

	      try
	      {

	          if (ud.getCaflag())
	          {
	              try
	              {
	                  dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
	              }
	              catch (ApplicationException e)
	              {
	                  throw ExceptionUtil.getBaseException(e);
	              }
	          }
	          System.out.println("执行操作之前要+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++" );
	          try
	          {
	             // XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.XERCES_PARSER, true,true);
	              //wsksbvo = new WsksbVO();
	              System.out.println(jmbavo.toString());
	              System.out.println("-------------------");
	              System.out.println(xmldata);
	              XMLParseHelper.parseXMLString(jmbavo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
	          }
	          catch (ApplicationException e)
	          {
	              throw ExceptionUtil.getBaseException(e);
	          }
	          System.out.println("执行操作之前要+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++" );

	              dzyj.setYwczlx(jmbavo.getYwczlx());
	              dzyj.setYwdm(jmbavo.getYwlx());
	              // 取得登记数据
	              Map djMap = (Map) FriendHelper.getDjInfo(request);
	              SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

	              Timestamp now = new Timestamp(System.currentTimeMillis());
	              voPackage.setUserData(ud);
	              HashMap hm = new HashMap();

	              hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
	              hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
	              hm.put("type","Update");
	              hm.put("selIndex",selIndex);
	              voPackage.setData(hm);
	              voPackage.setProcessor(ProcessorNames.FHTJJSZRSD07_PROCESSOR);
	              voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	              System.out.println("执行操作之前要++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
	      voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
	      System.out.println("执行操作之后要+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++" );

	              System.out.println("执行操作之后要++++++++++++++++++2222++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
	              request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "xxxxxxxxxxxxxxxxx保存成功！");
	              LogUtil.getInstance().log(FriendHelper.getUserData(request), "xxxxxxxxxxxxxxxxxxxxxxx保存",
	                      (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
	              System.out.println("执行操作之后要+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );

	              return  doShow(request,response);

	      }
	      catch (Exception e)
	      {
	          // /3.9.设置空xml，转向失败页面
	          request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo.toXML());
	          request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jmbavo.getXsltVersion());
	          request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jmbavo.getSchemaVersion());
	          System.out.println("减免备案异常信息: ===== " + e.getMessage());

	          throw ExceptionUtil.getBaseException(e);
	      }

	  }


	/**
	 * 对请求的权限进行检查
	 */
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限 暂设置没有检查条件
		// System.out.println("beforePerform");
		// if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
		// {
		//
		// return CAConstants.NOPERMISSION;
		// }
		return null;
	}
}
