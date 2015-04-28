package com.lscdz.qysds.application.nsrjbxx2014.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.Tools;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.nsrjbxx2014.extDao.NsrjbxxExtDao;
import com.lscdz.qysds.application.nsrjbxx2014.util.CheckUtil;
import com.lscdz.qysds.application.nsrjbxx2014.util.GetQysdsDeclare;
import com.lscdz.qysds.application.nsrjbxx2014.vo.request.NsrjbxxReq;
import com.lscdz.qysds.application.nsrjbxx2014.vo.request.UpdateBbmsf;
import com.lscdz.qysds.application.nsrjbxx2014.vo.response.JbxxVo;
import com.lscdz.qysds.application.nsrjbxx2014.vo.response.NsrjbxxInitCodeTableVo;
import com.lscdz.qysds.application.nsrjbxx2014.vo.response.QyjbxxVo;
import com.lscdz.qysds.application.qysdsnb2014.QysdsNb2014Contant;
import com.lscdz.qysds.application.qysdsnb2014.handler.QysdsNbHandler;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.access.Sb_jl_qysds_dwtzAccess;
import com.lscdz.qysds.common.access.Sb_jl_qysds_nsrjbxxb_2014Access;
import com.lscdz.qysds.common.access.Sb_jl_qysds_nsrjbxxb_2014_hisAccess;
import com.lscdz.qysds.common.access.Sb_jl_qysds_qyzygdAccess;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjdq;
import com.lscdz.qysds.common.codetable.vo.gy_dm_zjlx;
import com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_qtkjzz;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.djjbsj.IDjjbsjService;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.djjbsj.processor.DjjbsjServer;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.persistent.access.QysdsAppAccess;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.util.Qyjbxx;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.qysds.common.util.StringUtil;
import com.lscdz.qysds.common.vo.sb_jl_qysds_dwtz;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014_his;
import com.lscdz.qysds.common.vo.sb_jl_qysds_qyzygd;
import com.lscdz.util.ActionHandler;
import com.lscdz.util.ClientMessage;
import com.lscdz.util.MyLogger;
import com.lscdz.util.codetable.CodeTableManager;

public class QyjbxxHandler extends ActionHandler{
	MyLogger myLogger =new MyLogger(QysdsNbHandler.class);
	//当前登录用户信息
	private Yh userData=null;
	@Override
	public StringBuffer processMsg(ClientMessage msg) throws FrameException {
		try{
			userData=this.getUserData(msg);
		}catch(FrameException e){
			e.printStackTrace();
			this.rtnBizCode=RtnCodeMessage.Error_1010.Code;
			this.rtnBizMessage=RtnCodeMessage.Error_1010.Message;
			return new StringBuffer(this.ConvertVoToXml(null));
		}
		//初始化信息
		if(msg.getAction().equalsIgnoreCase("initJbxx")){
			return doInitJbxx(msg);
		}
		//获取企业基础信息
		if(msg.getAction().equalsIgnoreCase("queryQyjbxx")){
			return doQueryQyjbxx(msg);
		}
		//更新报表描述符
		if(msg.getAction().equalsIgnoreCase("updateBbmsf")){
			return doUpdateBbmsf(msg);
		}
		//保存
		if(msg.getAction().equalsIgnoreCase("saveJbxx")){
			return doSaveNsrjbxx(msg);
		}
		throw new FrameException("暂不支持的Action方法" + msg.getAction());
	}
	/**
	 * 
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	private StringBuffer doUpdateBbmsf(ClientMessage msg) throws FrameException {
		StringBuffer sb=new  StringBuffer();
		UpdateBbmsf vo=new UpdateBbmsf();
		this.ConvertXmlToVo(msg, vo);
		String jsjdm=vo.getJsjdm();
		String bbmsf=vo.getBbmsf();
		String nd=vo.getNd();
		Connection conn=null;
		try{
			conn=ResourceManager.getConnection();
			//2014版企业所得税纳税人基本信息
			String sqlWhere=" where jsjdm='"+jsjdm+"' and nd='"+nd+"' and version='"+
						QysdsNbConstant.REPORT_VERSION_2014+"'";
			Sb_jl_qysds_nsrjbxxb_2014Access.updateRecord(conn, bbmsf, sqlWhere);
		}catch (FrameException ex) {
			ex.printStackTrace();
			this.setRtnBizMessage(ex.getMessage());
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			sb.append(ConvertVoToXml(null));
			return sb;	
		}finally{
			Tools.closeConnection(conn);
		}
		return sb.append(ConvertVoToXml(null));

		
	}
	/**
	 * 初始化基本信息
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@SuppressWarnings("unchecked")
	private StringBuffer doInitJbxx(ClientMessage msg) throws FrameException {
		StringBuffer  sb=new StringBuffer();
		NsrjbxxInitCodeTableVo resVo=new NsrjbxxInitCodeTableVo();
		//证件类型
		List<gy_dm_zjlx> zjlxList=CodeTableManager.getCodeTableList(CodeTableKey.GY_DM_ZJLX);
		resVo.setZjlxList(zjlxList);
		//国籍地区
		List<gy_dm_gjdq> gjdmdzList=CodeTableManager.getCodeTableList(CodeTableKey.GY_DM_GJDQ);
		resVo.setGjdmdzList(gjdmdzList);
		//国家标准行业
		//List<gy_dm_gjbzhy> gjbzhyList=CodeTableManager.getCodeTableList(CodeTableKey.GY_DM_GJBZHY);
		//resVo.setGjbzhyList(gjbzhyList);
		//其他会计制度
		List<sb_dm_qysds_qtkjzz> qtkjzdList=CodeTableManager.getCodeTableList(CodeTableKey.SB_DM_QTKJZZ);
		resVo.setQtkjzzList(qtkjzdList);
		String sknd=QysdsHelperUtil.getYear();
		resVo.setSknd(sknd);
		
		sb.append(this.ConvertVoToXml(resVo));
		return sb;
	}

	/**
	 * 查询企业基础信息
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	private StringBuffer doQueryQyjbxx(ClientMessage msg) throws FrameException {
		StringBuffer sb= new StringBuffer();
		Connection conn=null;
		if (null == msg.getDoc()) {
			//系统异常
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			sb.append(ConvertVoToXml(null));
			return sb;
		}
		try {
			List<QyjbxxVo> qyjcxxList=new ArrayList<QyjbxxVo>();
			String jsjdm ="";
			String sknd = "";
			String startTime="";
			String endTime="";
			String smwsbz="";
			NsrjbxxReq nsrjcxxVo=new NsrjbxxReq();
			this.ConvertXmlToVo(msg, nsrjcxxVo);
			
			if(nsrjcxxVo!=null){
				jsjdm=nsrjcxxVo.getJsjdm();
				sknd=nsrjcxxVo.getNd();
				startTime=nsrjcxxVo.getStartTime();
				endTime=nsrjcxxVo.getEndTime();
				smwsbz=nsrjcxxVo.getSmwsbz();
				//判断税款所属日期
				CheckUtil.checkSkssrq(startTime, endTime, smwsbz, sknd);
			}
			if(StringUtil.isEmpty(jsjdm)){
				this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
				this.setRtnBizMessage("获取不到计算机代码");
				sb.append(ConvertVoToXml(null));
				return sb;	
			}
			//根据jsjdm查询基本信息
			else{
				boolean flag=false;//判断是否是字母的标志
				//判断是否是自然人（前台是否给传yhlb这个字段）
				String yhlb=userData.getYhlb();
				//暂用方法
				char last = jsjdm.charAt(jsjdm.length()-1);
				if((last>='A'&&last<='Z')||(last>='a'&&last<='z')){
					flag=true;
				}
				if(flag||"03".equals(yhlb)){
					throw new FrameException("自然人不能进行企业所得税申报!"); 
				}else{//不是自然人
					CheckBean  checkBean=new CheckBean();
					checkBean=CheckUtil.getCheckBean(checkBean,jsjdm,sknd,startTime,endTime);
					conn=ResourceManager.getConnection();
					ServiceManager.getInstance().getQysdsCheckServer().check(conn, checkBean);
					//查询基本登记信息
					QyjbxxVo qyjcxxVo=new QyjbxxVo();
					//qyjcxxVo.setSfjrqsq(checkBean.isInQsq());
					//qyjcxxVo.setSfjrqsq(checkBean.isFinishQs());
					JbxxVo jbxxVo=new JbxxVo();
					IDjjbsjService jbdj=new DjjbsjServer();
					Djjbsj djjbsjVo=new Djjbsj();
						djjbsjVo=jbdj.query(conn, jsjdm);
						if(djjbsjVo!=null){
							String djzclxdm=djjbsjVo.getDjzclxdm();
							//校验税源鉴定类型
							if("175".equals(djzclxdm)||"410".equals(djzclxdm)||"420".equals(djzclxdm)){
								throw new FrameException("该企业的登记注册类型不符合规范，不能在此申报!");
							}
							//校验纳税人状态
							if(!(djjbsjVo.getNsrzt().equals("10") || djjbsjVo.getNsrzt().equals("90"))){
								throw new FrameException("该纳税人为非正常用户!");
							}
							
							qyjcxxVo=Qyjbxx.getQyjbxxVo(djjbsjVo, startTime, endTime, checkBean);
						}
						if(qyjcxxVo!=null){
							String syjdlxdm=qyjcxxVo.getSyjdlxdm();
							if(QysdsNbConstant.CODE_QYSDS_ZGFWJD_OTHER.equals(syjdlxdm)){
								throw new FrameException("该企业的企业所得税不由地方税务局管辖!");
							}else if(QysdsNbConstant.CODE_QYSDS_ZGFWJD_KSSFZJG.equals(syjdlxdm)){
								throw new FrameException("该企业只需填报企业所得税分支机构年度纳税申报表！");
							}
							
						}
						//获取增收方式并交验
						Zsfs zsfs=Qyjbxx.getZsfs(conn, jsjdm, startTime, endTime);
						CheckUtil.checkZsfs(qyjcxxVo, zsfs);
					qyjcxxList.add(qyjcxxVo);
					jbxxVo.setQyjcxxList(qyjcxxList);
					if(CheckUtil.isForeignCorporation(djjbsjVo)){
						//如果是内陆，民营企业，继续，否则抛出异常
					}
					//2014版企业所得税纳税人基本信息
					String sqlWhere=" where jsjdm='"+jsjdm+"' and nd='"+sknd+"' and version='"+QysdsNbConstant.REPORT_VERSION_2014+"'";
					List<sb_jl_qysds_nsrjbxxb_2014> jbxxList2014=new ArrayList<sb_jl_qysds_nsrjbxxb_2014>();
					jbxxList2014=NsrjbxxExtDao.readNsrjbxxRecords(conn, sqlWhere);
					//所属行业
					String sshydm=djjbsjVo.getGjbzhydm();
					String sshymc=CodeTableManager.getNameByCode(CodeTableKey.GY_DM_GJBZHY,sshydm);
					if(jbxxList2014!=null&&jbxxList2014.size()>0){
						jbxxList2014.get(0).setSshy(sshydm);
						jbxxList2014.get(0).setSshymc(sshymc);
					}
					//企业主要股东id
					String qyzygdid="";
					//对外投资企业id
					String dwtzqyid="";
					if(jbxxList2014!=null&&jbxxList2014.size()>0){
						qyzygdid=jbxxList2014.get(0).getQyzygdid();
						dwtzqyid=jbxxList2014.get(0).getDwtzqyid();
						jbxxVo.setFlag("update");
					}else{
						jbxxVo.setFlag("insert");
					}
					jbxxVo.setJbxxList2014(jbxxList2014);
					
					//主要股东的查询条件
					if(qyzygdid!=null&&qyzygdid.trim().length()>0){
						String	zygdWhere=" where qyzygdid='"+qyzygdid+"' order by tzbl desc";
						List<sb_jl_qysds_qyzygd> qyzygdList=NsrjbxxExtDao.readZygdRecords(conn, zygdWhere);
						//将list内部排序，取前五个
						qyzygdList=Qyjbxx.sortList(qyzygdList);
						jbxxVo.setQyzygdList(qyzygdList);
					}
					//对外投资企业查询条件
					if(dwtzqyid!=null&&dwtzqyid.trim().length()>0){
						String dwtzWhere="where dwtzqyid ='"+dwtzqyid+"' order by tzbl desc";
						List<sb_jl_qysds_dwtz> dwtzList=NsrjbxxExtDao.readDwtzRecords(conn, dwtzWhere);
						dwtzList=Qyjbxx.sortDwtzList(dwtzList);
						jbxxVo.setDwtzList(dwtzList);
					}
					//转换成xml
					sb.append(ConvertVoToXml(jbxxVo));
				}
				
			}
		} catch (FrameException ex) {
			//myLogger.log("获取纳税人基本信息出错："+ex.getMessage());
			ex.printStackTrace();
			this.setRtnBizMessage(ex.getMessage());
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			sb.append(ConvertVoToXml(null));
			return sb;	
		}finally{
			Tools.closeConnection(conn);
		}
		return sb;
	}
	
	/**
	 * 保存2014版企业所得税纳税人基本信息
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	private StringBuffer doSaveNsrjbxx(ClientMessage msg) throws FrameException {
		try {
			userData=this.getUserData(msg);
		} catch (FrameException e) {
			e.printStackTrace();
			this.rtnBizCode=RtnCodeMessage.Error_1010.Code;
			this.rtnBizMessage=RtnCodeMessage.Error_1010.Message;
			return new StringBuffer(this.ConvertVoToXml(null));
		}
		StringBuffer sb=new StringBuffer();
		JbxxVo jbxxVo=new JbxxVo();
		this.ConvertXmlToVo(msg, jbxxVo);
		List<sb_jl_qysds_nsrjbxxb_2014>  jbxxList2014=jbxxVo.getJbxxList2014();
		sb_jl_qysds_nsrjbxxb_2014   nsrjbxx_Vo2014=new sb_jl_qysds_nsrjbxxb_2014();
		sb_jl_qysds_nsrjbxxb_2014_his   nsrjbxxb_2014_hisVo=new sb_jl_qysds_nsrjbxxb_2014_his();
		nsrjbxx_Vo2014=jbxxList2014.get(0);
		String jsjdm=nsrjbxx_Vo2014.getJsjdm();
		//税款年度
		String sknd=nsrjbxx_Vo2014.getNd();
		//判断保存更新的标志
		String flag=jbxxVo.getFlag();
		//创建接口
		Connection conn=null;
		try{
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			QysdsAppAccess qysdsDel=new QysdsAppAccess(new DBAccess(conn));
			//申报年度
			String sbnd=DateUtils.getNowYear();
			String updateWhere=" where jsjdm='"+jsjdm+"' and nd='"+sknd+"' and version='"+QysdsNbConstant.REPORT_VERSION_2014+"'";
			if("insert".equals(flag)){
				List<sb_jl_qysds_nsrjbxxb_2014> qysdsNbList2014=new ArrayList<sb_jl_qysds_nsrjbxxb_2014>();
				qysdsNbList2014=NsrjbxxExtDao.readNsrjbxxRecords(conn, updateWhere);
				if(qysdsNbList2014.size()>0){
					throw new FrameException("该企业的企业所得税已申报!");
				}
				//String zygdid=TinyTools.getXh(jsjdm);
				String zygdid=this.getSeqValue(QysdsNbConstant.SEQ_SB_QYSDSNB,conn);
				Qyjbxx.getNsrjbxxb2014Vo(userData, nsrjbxx_Vo2014, sbnd, zygdid);
				Sb_jl_qysds_nsrjbxxb_2014Access.insertRecord(conn, nsrjbxx_Vo2014);
			}else{
				nsrjbxx_Vo2014.setSbnd(sbnd);
				nsrjbxx_Vo2014.setCjr(userData.getYhid());
				if(nsrjbxx_Vo2014.getCjrq()==null||"".equals(nsrjbxx_Vo2014.getCjrq())){
					nsrjbxx_Vo2014.setCjrq(FrameCommonAccess.getDBDate());
				}
				nsrjbxx_Vo2014.setLrr(userData.getYhid());
				nsrjbxx_Vo2014.setLrrq(FrameCommonAccess.getDBDate());
				nsrjbxx_Vo2014.setVersion(QysdsNbConstant.REPORT_VERSION_2014);
				nsrjbxxb_2014_hisVo=Qyjbxx.getNsrjbxxb_2014_hisVo(conn,nsrjbxx_Vo2014,nsrjbxxb_2014_hisVo);
				//保存数据到历史表
				Sb_jl_qysds_nsrjbxxb_2014_hisAccess.insertRecord(conn, nsrjbxxb_2014_hisVo);
				//修改
				Sb_jl_qysds_nsrjbxxb_2014Access.updateRecord(conn, nsrjbxx_Vo2014, updateWhere);
				//清除原先操作表数据
				QysdsReportsDeclare qysdsReportsVo=new QysdsReportsDeclare();
				qysdsReportsVo=GetQysdsDeclare.getDeclareVo(qysdsReportsVo, nsrjbxx_Vo2014);
				qysdsDel.delete(qysdsReportsVo);
			}
			//主要股东表
			sb_jl_qysds_qyzygd  qyzygdVo=new sb_jl_qysds_qyzygd ();
			List<sb_jl_qysds_qyzygd> qyzygdList=jbxxVo.getQyzygdList();
			//删除
			String zygd_del=jbxxVo.getZygd_del();
			if(zygd_del!=null&&!"".equals(zygd_del)){
				zygd_del = zygd_del.replaceAll(",","','");
				String delZygdWhere="WHERE ID IN ('"+zygd_del+"')";
				Sb_jl_qysds_qyzygdAccess.deleteRecord(conn, delZygdWhere);
			}
			//保存、修改
			String qyzygdid=jbxxVo.getJbxxList2014().get(0).getQyzygdid();
			for(int i=0;i<qyzygdList.size();i++){
				qyzygdVo=qyzygdList.get(i);
				if(qyzygdVo.getId()==null||"".equals(qyzygdVo.getId())){
					qyzygdVo=Qyjbxx.getQyzygdVo(userData, qyzygdVo, jsjdm, qyzygdid);
					Sb_jl_qysds_qyzygdAccess.insertRecord(conn, qyzygdVo);
				}else{
					Sb_jl_qysds_qyzygdAccess.updateRecord(conn, qyzygdVo, " where id='"+qyzygdVo.getId()+"'");
				}
			}
			//对外投资
			sb_jl_qysds_dwtz dwtzVo=new sb_jl_qysds_dwtz();
			List<sb_jl_qysds_dwtz> dwtzList=jbxxVo.getDwtzList();
			//删除
			String dwtz_del=jbxxVo.getDwtz_del();
			if(dwtz_del!=null&&!"".equals(dwtz_del)){
				dwtz_del = dwtz_del.replaceAll(",","','");
				String delDwyzWhere="WHERE ID IN ('"+dwtz_del+"')";
				Sb_jl_qysds_dwtzAccess.deleteRecord(conn, delDwyzWhere);
			}
			//增加、修改
			String dwtzid=jbxxVo.getJbxxList2014().get(0).getDwtzqyid();
			for(int i=0;i<dwtzList.size();i++){
				dwtzVo=dwtzList.get(i);
				if(dwtzVo.getId()==null||"".equals(dwtzVo.getId())){
					dwtzVo=Qyjbxx.getDwtzVo(userData, dwtzVo, jsjdm, dwtzid);
					Sb_jl_qysds_dwtzAccess.insertRecord(conn, dwtzVo);
				}else{
					Sb_jl_qysds_dwtzAccess.updateRecord(conn, dwtzVo, " where id='"+dwtzVo.getId()+"'");
				}
			}
			conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			this.setRtnBizMessage(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			sb.append(ConvertVoToXml(null));
			return sb;	
		}finally{
			Tools.closeConnection(conn);
		}
		return sb.append(ConvertVoToXml(null));
	}
}
