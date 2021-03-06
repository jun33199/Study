package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx10.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx09.web.Basx09Form;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx10.web.Basx10Form;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class Basx10Processor implements Processor{
	
	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
	 */

	public Object process(VOPackage vo) throws BaseException {

		Object result = null;
		
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_BGACTION:
			result = doBg(vo);
			break;	
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_UPDATEACTION:
			result = doCheck(vo);
			break;
		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}
	
	
	
	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		
		Basx10Form basx10Form = (Basx10Form) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		List list=new ArrayList();
		try {
			//为获取纳税人基本信息准备
			//获得UserData
			UserData ud = (UserData)vo.getUserData();
			//获得纳税人基本信息
			HashMap djMap = InterfaceDj.getDjInfo(basx10Form.getJsjdm(), ud);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			System.out.println("=====getSwjgzzjgdm======>>>>" + jbsj.getSwjgzzjgdm());
			
			conn = SfDBResource.getConnection();	
//			String sql = "select GXJSLYDM,GXJSLYMC from DMDB.SF_DM_GXJSLY order by GXJSLYDM";
//			
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			while (rs.next()) {	
//				String dm=rs.getString("GXJSLYDM");
//				String mc=rs.getString("GXJSLYMC");
//				DmVo dmvo=new DmVo();
//				dmvo.setDm(dm);
//				dmvo.setMc(mc);
//				list.add(dmvo);		
//			}
//			basx10Form.setGxjslyList(list);
			
			StringBuffer sb=new StringBuffer();
			sb.append(" select a.jsjdm,a.nsrmc nsrmc,");
			sb.append(" (select b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm=a.swjgzzjgdm) zgsws,");
			sb.append(" (select b.djzclxmc from dmdb.dj_dm_djzclx b where b.djzclxdm=a.djzclxdm) jjlx,");
			sb.append(" (select b.gjbzhymc from dmdb.gy_dm_gjbzhy b where b.gjbzhydm=a.gjbzhydm) sshy,");
			sb.append(" (select b.xm from djdb.dj_jl_qyry b where a.jsjdm=b.jsjdm and b.zwdm='05' and rownum=1)  lxr,");
			sb.append(" (select  case when b.gddh is null and b.yddh is not null then b.yddh");
			sb.append(" when b.gddh is not null and b.yddh is null then b.gddh");
			sb.append(" when b.gddh is not null and b.yddh is not null then b.gddh||'  '||b.yddh else null");
			sb.append(" end  from djdb.dj_jl_qyry b where a.jsjdm=b.jsjdm and b.zwdm='05' and rownum=1) lxdh");
			sb.append(" from djdb.dj_jl_jbsj a where a.jsjdm='"+basx10Form.getJsjdm()+"'");
			
			
			ps1 = conn.prepareStatement(sb.toString());
			rs1 = ps1.executeQuery();
			while (rs1.next()){
				basx10Form.setJsjdm(rs1.getString("JSJDM"));
				basx10Form.setNsrmc(rs1.getString("NSRMC"));
				basx10Form.setZgsws(rs1.getString("ZGSWS"));
				basx10Form.setJjlx(rs1.getString("JJLX"));
				basx10Form.setSshy(rs1.getString("SSHY"));
				basx10Form.setLxr(rs1.getString("LXR"));
				basx10Form.setLxdh(rs1.getString("LXDH"));
			}
			String zl = "";
			String zlsql = "";
			//判断是否已经保存过
			if("0".equals(basx10Form.getClbs())){
				//zlsql = "select t.zlqdmc,t.sfkysc from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '0100'   and t.zfbs = '0' ORDER BY T.zlqddm";
				zlsql = "select t.zlqdmc,t.sfkysc,t.zlqddm from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '0100'   and t.zfbs = '0' ORDER BY T.zlqddm";
			}else{
				//判断是否为审核和查看
				if("3".equals(basx10Form.getCzlx())){
					zlsql = "select t.zlqd,t.xh from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx10Form.getBasqwsh()+"' ORDER BY T.xh";
				}else if("4".equals(basx10Form.getCzlx())){
					zlsql = "select t.zlqd,t.sfshtg from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx10Form.getBasqwsh()+"' ORDER BY T.sfshtg";
				}else{
					zlsql = "select t.zlqd,t.sfkysc from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx10Form.getBasqwsh()+"' ORDER BY T.SFKYSC DESC";
				}
				
			}
			
			ps2 = conn.prepareStatement(zlsql);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				//判断是否已经保存过
				if("0".equals(basx10Form.getClbs())){
					
					String qxdm = jbsj.getSwjgzzjgdm().substring(0,2);
					System.out.println("============qxdm=====>>" + qxdm);
					if("01".equals(rs2.getString("ZLQDDM"))||rs2.getString("ZLQDDM").equals("01")){
						zl =zl + QysdsUtil.getZlqddm01mc(qxdm)+"|"+rs2.getString("SFKYSC")+";"; 
					}else{
						zl =zl + rs2.getString("ZLQDMC")+"|"+rs2.getString("SFKYSC")+";";	
					}
					System.out.println("=====zl=====>>>" + zl);
					//zl =zl + rs2.getString("ZLQDMC")+"|"+rs2.getString("SFKYSC")+";";
				}else{
					//判断是否为审核和查看
					if("3".equals(basx10Form.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("XH")+";";
					}else if("4".equals(basx10Form.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFSHTG")+";";
					}else{
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFKYSC")+";";
					}
					
				}
			}
			basx10Form.setZl(zl.toString().substring(0,(zl.toString().length()-1)));
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs1 != null) {
				rs1.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库查询记录失败！" + basx10Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}

		return basx10Form;
	}
	
	/**
	 * doSave保存对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		
		Basx10Form basx10Form = (Basx10Form) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//备案申请文书号
		String basqwsh = basx10Form.getBasqwsh();
		//备案申请编号
		String basqbh = basx10Form.getBasqbh();
		//计算机代码
		String jsjdm = basx10Form.getJsjdm();
		//备案年度
		String band = basx10Form.getBand();
		//减免税类别代码
		String jmbasxdm = "0100";
		//减免税政策执行情况
		String jmszczxqk=QysdsUtil.strNotNull(basx10Form.getJmszczxqk())?basx10Form.getJmszczxqk():"";
		//起始时间
		String qsrq=QysdsUtil.strNotNull(basx10Form.getQsrq())?"to_date('"+basx10Form.getQsrq()+"','yyyy-mm-dd')":"null";
		//截止时间
		String jzrq=QysdsUtil.strNotNull(basx10Form.getJzrq())?"to_date('"+basx10Form.getJzrq()+"','yyyy-mm-dd')":"null";
		//设置减免税额
		String bajmse = QysdsUtil.strNotNull(basx10Form.getBajmse())?basx10Form.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx10Form.getBajmbl())?basx10Form.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String zbsql = "";
			if("1".equals(basx10Form.getClbs())){
				//更新主表
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx10Form.getBasqwsh()+"'";
				//更新子表
				zbsql = "UPDATE sfdb.sf_jl_qysdsjmsba_10 SET YJJSE ='"+basx10Form.getYjjse()+
				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where basqwsh ='"+basx10Form.getBasqwsh()+"'";
			}else{
				//插入主表
				sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,BASQBH,JSJDM,BAND,JMBASXDM,SZDM,SWJGZZJGDM,SQZT,TJR,TJSJ,SHR,SHSJ," +
						"SQLXDM,BAJMSE,BAJMBL,FHWJMC,QSRQ,JZRQ,CJR,CJRQ,LRR,LRRQ)values('"+basqwsh+"','"+basqbh+"','"+jsjdm+"','"+band+"','"+
						jmbasxdm+"','30','"+ud.getSsdwdm()+"','4','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate,'1','"+bajmse+
						"','"+bajmbl+"',?,"+qsrq+","+jzrq+",'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+
						"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
				
				//获取数据库表序号
				basx10Form.setXh(qysdsUtil.getSequence(conn));
				//插入子表
				zbsql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_10 (XH,BASQWSH,YJJSE,JSJDM,BAND," +
				"SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ) VALUES('"+basx10Form.getXh()+"','" +
				basqwsh+"',"+basx10Form.getYjjse()+",'"+jsjdm+"','"+band+"'"+
				",(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"')"+",'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			}
			//主表操作
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			
			//子表操作
			
			ps1 = conn.prepareStatement(zbsql);
			
			ps1.executeQuery();
			
			//更新资料清单表
			if(!"".equals(basx10Form.getZl())){
				//删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps2=conn.prepareStatement(delzlqd);
				ps2.execute();
				//插入该备案申请文书号的资料清单
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx10Form.getZl().split(";");
				ps3=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
					
					String xh = qysdsUtil.getSequence(conn);
					ps3.setString(1, xh);
					ps3.setString(2, basqwsh);
					ps3.setString(3, zlnr);
					ps3.setString(4, jsjdm);
					ps3.setString(5, ud.getYhid());
					ps3.setString(6, ud.getYhid());
					ps3.setString(7, "1");
					ps3.setString(8, zlsfkysc);
					ps3.addBatch();
				}
				ps3.executeBatch();
			}
			//设置处理标示为保存状态
			basx10Form.setClbs("1");
			
			basx10Form = (Basx10Form)doShow(vo);
			
			if (ps != null) {
				ps.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
			if (ps3 != null) {
				ps3.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库保存记录失败！" + basx10Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx10Form;
	}
	
	
	/**
	 * doSave保存对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doBg(VOPackage vo) throws BaseException {
		
		Basx10Form basx10Form = (Basx10Form) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//备案申请文书号
		String basqwsh = basx10Form.getBasqwsh();
	
		//计算机代码
		String jsjdm = basx10Form.getJsjdm();
		
		//减免税政策执行情况
		String jmszczxqk=QysdsUtil.strNotNull(basx10Form.getJmszczxqk())?basx10Form.getJmszczxqk():"";
		//起始时间
		String qsrq=QysdsUtil.strNotNull(basx10Form.getQsrq())?"to_date('"+basx10Form.getQsrq()+"','yyyy-mm-dd')":"null";
		//截止时间
		String jzrq=QysdsUtil.strNotNull(basx10Form.getJzrq())?"to_date('"+basx10Form.getJzrq()+"','yyyy-mm-dd')":"null";
		//设置减免税额
		String bajmse = QysdsUtil.strNotNull(basx10Form.getBajmse())?basx10Form.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx10Form.getBajmbl())?basx10Form.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String zbsql = "";
			
				//更新主表
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set  sqzt='4',bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx10Form.getBasqwsh()+"'";
				//更新子表
				zbsql = "UPDATE sfdb.sf_jl_qysdsjmsba_10 SET YJJSE ='"+basx10Form.getYjjse()+
				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where basqwsh ='"+basx10Form.getBasqwsh()+"'";
			
			//主表操作
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			
			//子表操作
			
			ps1 = conn.prepareStatement(zbsql);
			
			ps1.executeQuery();
			
			//更新资料清单表
			if(!"".equals(basx10Form.getZl())){
				//删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps2=conn.prepareStatement(delzlqd);
				ps2.execute();
				//插入该备案申请文书号的资料清单
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx10Form.getZl().split(";");
				ps3=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
					
					String xh = qysdsUtil.getSequence(conn);
					ps3.setString(1, xh);
					ps3.setString(2, basqwsh);
					ps3.setString(3, zlnr);
					ps3.setString(4, jsjdm);
					ps3.setString(5, ud.getYhid());
					ps3.setString(6, ud.getYhid());
					ps3.setString(7, "1");
					ps3.setString(8, zlsfkysc);
					ps3.addBatch();
				}
				ps3.executeBatch();
			}
			//设置处理标示为保存状态
			basx10Form.setClbs("1");
			
			basx10Form = (Basx10Form)doShow(vo);
			
			if (ps != null) {
				ps.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
			if (ps3 != null) {
				ps3.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库保存记录失败！" + basx10Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx10Form;
	}
	
	
	/**
	 * doQuery查询对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doQuery(VOPackage vo) throws BaseException {
		
		Basx10Form basx10Form = (Basx10Form) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();	
			String sql = "SELECT T.YJJSE," +
					" S.QSRQ,S.JZRQ,S.BAJMBL,S.BAJMSE,S.FHWJMC,S.JSJDM,S.BASQBH,S.BAND,S.CJR,to_char(S.CJRQ,'yyyymmdd')cjrq,S.zfsm " +
					" FROM sfdb.sf_jl_qysdsjmsba_10 T,sfdb.sf_jl_qysdsjmsbajl S " +
					" WHERE T.BASQWSH = S.BASQWSH AND T.BASQWSH = '"+basx10Form.getBasqwsh()+"'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				basx10Form.setYjjse(rs.getString("YJJSE"));
				basx10Form.setJmszczxqk(rs.getString("FHWJMC"));
				basx10Form.setJsjdm(rs.getString("JSJDM"));
				basx10Form.setBasqbh(rs.getString("BASQBH"));
				basx10Form.setBajmbl(rs.getString("BAJMBL"));
				basx10Form.setBajmse(rs.getString("BAJMSE"));
				basx10Form.setMr_band(rs.getString("BAND"));
				basx10Form.setMr_lrr(rs.getString("CJR"));
				basx10Form.setMr_lrrq(rs.getString("CJRQ"));
				basx10Form.setZfsm(rs.getString("ZFSM"));
				//处理数字如果为.123则改成0.123
				if(rs.getString("YJJSE").indexOf(".")==0){
					basx10Form.setYjjse("0"+rs.getString("YJJSE"));
				}else{
					basx10Form.setYjjse(rs.getString("YJJSE"));
				}
				
				//对日期进行处理，只取2001-01-01
				if(!"".equals(rs.getString("QSRQ"))){
					basx10Form.setQsrq(rs.getString("QSRQ").substring(0, 10));
				}else{
					basx10Form.setQsrq(rs.getString("QSRQ"));
				}
				if(!"".equals(rs.getString("JZRQ"))){
					basx10Form.setJzrq(rs.getString("JZRQ").substring(0, 10));
				}else{
					basx10Form.setJzrq(rs.getString("JZRQ"));
				}
				
			}
			//设置处理标示为保存状态
			basx10Form.setClbs("1");
			basx10Form = (Basx10Form)doShow(vo);
			
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库查询记录失败！" + basx10Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx10Form;
	}
	
	
	/**
	 * doCheck网上-接受或拒绝申请
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doCheck(VOPackage vo) throws BaseException {
		
		Basx10Form basx10Form = (Basx10Form) vo.getData();
		UserData ud = (UserData) vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String SQL = "";
		String sql = "";
		String SELECT = " SELECT ";
		String XH = " XH, ";
		String SHBJ = " SFSHTG FROM DUAL ";
		String UNION = " UNION ALL";
		String s = basx10Form.getShzl();
		String[] ss = s.split(";");
		for(int i = 0;i<ss.length;i++){
			String[] sa = ss[i].split(",");
			for(int j =0;j<sa.length;j++){
				if(j==0){
					sql = SELECT+sa[j]+XH;
				}else{
					sql = sql+sa[j]+SHBJ;
				}
				
			}
			if(i!=(ss.length-1)){
				sql = sql + UNION;
			}
			SQL = SQL+sql;
		}
		
		try {
			conn = SfDBResource.getConnection();
			//检查当前减免税备案申请是否为保存未审核或提交未审核
			boolean sqzt = QysdsUtil.checkSqzt(basx10Form.getBasqwsh(), conn);
			if (!sqzt) {
				throw new ApplicationException("此减免税备案申请已被纳税人撤回！");
			}
			//更新主表状态为审核通过或审核未通过
			QysdsUtil.updateSqzt(basx10Form.getBasqwsh(), basx10Form.getSqzt(), ud.getYhid(),conn);
			
			//更新子表状态为审核通过或审核未通过
//			StringBuffer shzbSql = new StringBuffer("UPDATE sfdb.sf_jl_qysdsjmsba_10 t SET t.shbj = ");
//			if("4".equals(basx10Form.getSqzt())){
//				shzbSql.append("'0'");
//			}else{
//				shzbSql.append("'1'");
//			}
//			shzbSql.append(" ,t.lrr = '"+ud.getYhid()+"',t.lrrq = sysdate WHERE t.basqwsh = '"+basx10Form.getBasqwsh()+"'");
//			
//			ps = conn.prepareStatement(shzbSql.toString());
//			ps.execute();
			
			//更新资料清单表中资料是否审核通过
			SQL = "UPDATE SFDB.SF_JL_QYSDSJMSBAJLZLQD A SET A.SFSHTG = (SELECT B.SFSHTG FROM (" +SQL+
					") B WHERE A.XH = B.XH)WHERE A.BASQWSH = '"+basx10Form.getBasqwsh()+"'";
			
			ps1 = conn.prepareStatement(SQL);
			ps1.execute();
			
			if (ps != null) {
				ps.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库审核记录失败！" + basx10Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return basx10Form;
	}
	private Object doPrint(VOPackage vo) throws BaseException {
		Connection conn = null;
		PreparedStatement printPst = null;
		ResultSet rs = null;
		Basx10Form basx10Form = (Basx10Form) vo.getData();
		String zlqd = "";
		String sql = "SELECT A.NSRMC,T.JSJDM,S.JMBASXMC,T.QSRQ,T.JZRQ,T.BAJMSEHBL,T.FHWJMC,Q.ZLQD " +
				"FROM SFDB.SF_JL_QYSDSJMSBAJL T,SFDB.SF_JL_QYSDSJMSBAJLZLQD Q,DJDB.DJ_JL_JBSJ A,DMDB.SF_DM_JMBASXDM S " +
				"WHERE T.BASQWSH = Q.BASQWSH(+) AND T.JSJDM = A.JSJDM  AND T.JMBASXDM = S.JMBASXDM AND  T.BASQWSH = '"+ basx10Form.getBasqwsh() + "'";
		try {
			conn = SfDBResource.getConnection();
			printPst = conn.prepareStatement(sql);
			rs = printPst.executeQuery();
			while(rs.next()){
				
			}
		} catch (Exception ex) {
			throw new ApplicationException("获取打印信息错误！");
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}

		return basx10Form;
	}
}
