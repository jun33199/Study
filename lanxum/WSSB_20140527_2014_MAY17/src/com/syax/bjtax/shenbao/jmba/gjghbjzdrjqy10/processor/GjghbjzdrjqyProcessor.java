package com.syax.bjtax.shenbao.jmba.gjghbjzdrjqy10.processor;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba10Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;

/**
 * 
 * <p>
 * Title: 北京地税核心征管系统－－上门申报
 * </p>
 * 
 * <p>
 * Description: (十)国家规划布局内的重点软件生产企业
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

public class GjghbjzdrjqyProcessor implements Processor {
	/**
	 * 总控数据
	 */
	private UserData userData;

	/**
	 * 处理请求
	 */
	public Object process(VOPackage vo) throws BaseException {
		this.userData = vo.getUserData();
		Object result = null;
		// 根据业务操作类型值来做业务操作
		try {
			switch (vo.getAction()) {
			// 查询
			case JmbaActionConstant.INTI_ACTION_QUERY:
				// vo.setData(this.doQuery((Map)vo.getData()));
				// 初始化页面
			case JmbaActionConstant.INTI_ACTION_SHOW:
				result = doShow(vo);

				return result;
				// 保存操作
			case JmbaActionConstant.INTI_ACTION_SAVE:
				result = doSave(vo);
				return result;
			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 保存功能
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		System.out.println("into Processor save");
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();        
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);

		Jmba10Vo mxvo=(Jmba10Vo)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor save");
		try {
			conn = DBResource.getConnection();
			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//conn.setHoldability(1);
			//新增
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
			}
			String sql = "";
			if(mxvo.getXh()!=null && !mxvo.getXh().equals("")){
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_10 SET "+
				" YJJSE='"+mxvo.getYjjse()+
				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";	
				}else{
					String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_10 (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,"
							+ " YJJSE,CJR,CJRQ,LRR,LRRQ) VALUES('"+
				xh+"','" +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
			
				mxvo.getYjjse()+"','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
			}
			System.out.println("02Processor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}
			

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}

		return list;
	}

	/**
	 * 查询操作
	 */
	private Map doQuery(Map data) throws BaseException {
		Map map = null;

		return map;
	}

	/**
	 * 初始化页面
	 */
	private Jmba10Vo getBamxvo(ResultSet rs) throws Exception {
		Jmba10Vo vo = new Jmba10Vo();
		try {
			vo.setXh(rs.getString("XH"));
			vo.setBand(rs.getString("BAND"));
			vo.setBasqwsh(rs.getString("BASQWSH"));
			vo.setCjr(rs.getString("CJR"));
			vo.setCjrq(rs.getString("CJRQ"));
			vo.setLrr(rs.getString("LRR"));
			vo.setLrrq(rs.getString("LRRQ").substring(0, 10));
			vo.setJsjdm(rs.getString("JSJDM"));
			vo.setQtzl(rs.getString("QTZL"));
			vo.setSfghbjnrjqy(rs.getString("SFGHBJNRJQY"));
			vo.setYjjse(MoneyUtils.format(rs.getString("YJJSE")));
			// mxvo.setZfgz( MoneyUtils.format(rs.getString("zfgz")));
			// mxvo.setJjkcje( MoneyUtils.format(rs.getString("jjkcje")));
			return vo;
		} catch (Exception e) {
			throw e;
		}
	}
	private Object doShow(VOPackage vo) throws BaseException {
		Debug.out("进入业务十processor");
		JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1 = null;
		UserData ud = vo.getUserData();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			con = DBResource.getConnection();
			vo1 = PublicAccess.getJmbaZbVO(con, bo.getBasqwsh());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String tjsj = df.format(date);
			StringBuffer sb = new StringBuffer();
			List list = new ArrayList();
			if (vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
				vo1 = new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				sb = new StringBuffer();

//				sb.append("select * ");
//				sb
//						.append(" from sfdb.sf_jl_qysdsjmsba_10 t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
//								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
//				sb
//						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
//								+ ud.getYhid() + "' and rownum=1 ");
//				System.out.println("===query wn sql==" + sb.toString());
//				ps = con.prepareStatement(sb.toString());
//				rs = ps.executeQuery();
//
//				while (rs.next()) {
//					Jmba10Vo mxvo = getBamxvo(rs);
//					mxvo.setXh("");
//					list.add(mxvo);
//				}
//
//				// }
//				vo1.setQysdsjmba(list);
				return vo1;
			}
			sb=new StringBuffer();
			sb.append("select * " );//t.SFYCJRMC,t.SFYSHBX,t.SFYLDHT,t.SFYCJRZM,t.SFYGZZM,
//					sb.append(" decode(t.SFYCJRMC,'0','是','1','否','') SFYCJRMCmc ,t.QTZL, ");
//            sb.append(" decode(t.SFYSHBX,'0','是','1','否','') SFYSHBXmc , ");
//            sb.append(" decode(t.SFYLDHT,'0','是','1','否','') SFYLDHTmc , ");
//            sb.append(" decode(t.SFYCJRZM,'0','是','1','否','') SFYCJRZMmc , ");
//            sb.append(" decode(t.SFYGZZM,'0','是','1','否','') SFYGZZMmc  ");

            sb.append(" from sfdb.sf_jl_qysdsjmsba_10 t where  basqwsh='"+bo.getBasqwsh()+"'");
			
			
			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}
			
			System.out.println("===query sql=="+sb.toString());
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {	
				 Jmba10Vo mxvo=getBamxvo(rs);	            
	             list.add(mxvo);
			}
			vo1.setQysdsjmba(list);
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(con);
		}

		return vo1;
	}
}
