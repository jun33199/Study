/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.jcdlscqy12.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba12VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba18VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JcdlscqyProcessor implements Processor {
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
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {

		case JmbaActionConstant.INTI_ACTION_QUERY:
			result = doQuery(vo);
			break;

		case JmbaActionConstant.INTI_ACTION_SAVE:
			result = doSave(vo); // 保存为1
			break;
		case JmbaActionConstant.INTI_ACTION_DELETE:
			result = doDelete(vo);
			break;
		case JmbaActionConstant.INTI_ACTION_COMMIT:
			// result = doCommit(vo, "3"); // 提交为2
			break;

		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
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
		// sqzt 保存是1，提交是2
		List list = new ArrayList();
		DzyjHelper dh = new DzyjHelper();
		Map hm = (Map) vo.getData();
		JmbaVO bavo = (JmbaVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO) bavo.getJmsbajl().get(0);

		Jmba12VO mxvo = (Jmba12VO) vo1.getQysdsjmba().get(0);
		UserData ud = (UserData) vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		try {
			// 电子原件暂不实现
			/*
			 * try { dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0",
			 * vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01); }catch (Exception
			 * ex) { ex.printStackTrace(); throw
			 * ExceptionUtil.getBaseException(ex); }
			 * hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			 */

			conn = DBResource.getConnection();
			JmbaZbVO vo2 = PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			// conn.setHoldability(1);
			// 新增
			if (vo2.getBasqwsh() == null || "".equals(vo2.getBasqwsh())) {
				PublicAccess.saveZb(conn, bavo, ud);
			}

			String sql = "";
//			if (mxvo.getXH() != null && !mxvo.getXH().equals("")) {
//				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_12 SET HLND='"
//						+ mxvo.getHLND() + "',MZQSND='" + mxvo.getMZQSND()
//						+ "',MZZZND='" + mxvo.getMZZZND()+"',JZQSND='" + mxvo.getJZQSND()
//						+ "',JZZZND='" + mxvo.getJZZZND() + "',YJJMSE='"+mxvo.getYJJMSE()+"',LRR='"
//						+ ud.getYhid() + "',LRRQ=sysdate where xh ='"
//						+ mxvo.getXH() + "'";
//			} else {

            sql = "delete from sfdb.sf_jl_qysdsjmsba_12 t where t.BASQWSH = '"+vo1.getBasqwsh()+"'";
            System.out.println("sql = "+sql);
            ps = conn.prepareStatement(sql);
			ps.executeUpdate();

				String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_12 (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM"
						+ ",HLND,MZQSND,MZZZND,JZQSND,JZZZND,YJJMSE "
						+ ",CJR,CJRQ,LRR,LRRQ) VALUES('"
						+ xh
						+ "','"
						+ vo1.getBasqwsh()
						+ "','"
						+ bavo.getNsrxx().getJsjdm()
						+ "','"
						+ vo1.getBand()
						+ "','"
						+ bavo.getNsrxx().getSwjgzzjgdm()
						+ "','"
						+ mxvo.getHLND()
						+ "','"
						+ mxvo.getMZQSND()
						+ "','"
						+ mxvo.getMZZZND()
						+ "','"
						+ mxvo.getJZQSND()
						+ "','"
						+ mxvo.getJZZZND()
						+ "','"
						+ mxvo.getYJJMSE()
						+ "','"
						+ ud.getYhid()
						+ "',sysdate,'"
						+ ud.getYhid()
						+ "',sysdate)";
//			}

			System.out.println("12Processor===doSave===sql=" + sql);
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
	 * doDelete删除对象页面信息要素
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

		DzyjHelper dh = new DzyjHelper();
		Map hm = (Map) vo.getData();
		JmbamxBo bo = (JmbamxBo) hm.get(VOConstants.KEY_JMBA_MX_BO);
		JmbaVO bavo = (JmbaVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO) bavo.getJmsbajl().get(0);
		// Jmba01VO mxvo=(Jmba01VO)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData) vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 电子原件暂不实现
			/*
			 * try { dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0",
			 * vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01); }catch (Exception
			 * ex) { ex.printStackTrace(); throw
			 * ExceptionUtil.getBaseException(ex); }
			 * hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			 */
			conn = DBResource.getConnection();
			String delSQL = "DELETE FROM sfdb.sf_jl_qysdsjmsba_12 t WHERE t.xh = '"
					+ bo.getXh() + "'";
			System.out.println("delSQL===" + delSQL);
			ps = conn.prepareStatement(delSQL);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			throw new ApplicationException("数据库更新记录失败！" + ud.getYhid() + ":"
					+ ex.getMessage());
		} finally {
			DBResource.destroyConnection(conn);
		}
		return null;
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

	private Object doQuery(VOPackage vo) throws BaseException {

		JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1 = null;
		UserData ud = vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBResource.getConnection();
			vo1 = PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String tjsj = df.format(date);
			StringBuffer sb = new StringBuffer();
			List list = new ArrayList();
			// 新增
			if (vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
				vo1 = new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				// vo1.setJmbasxmc("");//到action里填充

				// if (list.size() ==0){//查询往年的
				sb = new StringBuffer();

				sb
						.append("select t.xh,t.HLND,t.MZQSND,t.MZZZND,t.JZQSND,t.JZZZND,t.YJJMSE");

				sb
						.append(" from sfdb.sf_jl_qysdsjmsba_12 t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
				sb
						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
								+ ud.getYhid() + "' and rownum=1 ");
				System.out.println("===query wn sql==" + sb.toString());
				ps = conn.prepareStatement(sb.toString());
				rs = ps.executeQuery();

				while (rs.next()) {
					Jmba12VO mxvo = getBamxvo(rs);
					mxvo.setXH("");

					list.add(mxvo);
				}

				// }
				vo1.setQysdsjmba(list);
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				return vo1;
			}

			sb = new StringBuffer();
			sb.append("select t.xh,t.HLND,t.MZQSND,t.MZZZND,t.JZQSND,t.JZZZND,t.YJJMSE");

			sb.append(" from sfdb.sf_jl_qysdsjmsba_12 t where  basqwsh='"
					+ bo.getBasqwsh() + "'");

			if (bo.getXh() != null && !bo.getXh().equals("")) {
				sb.append(" and a.xh=" + bo.getXh() + " ");
			}

			System.out.println("===query sql==" + sb.toString());

			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				Jmba12VO mxvo = getBamxvo(rs);

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
			DBResource.destroyConnection(conn);
		}
		return vo1;
	}

	private Jmba12VO getBamxvo(ResultSet rs) throws Exception {
		Jmba12VO mxvo = new Jmba12VO();
		// String tbblx = null;
		try {

			mxvo.setXH(rs.getString("xh"));
			mxvo.setHLND(rs.getString("HLND"));
			mxvo.setMZQSND(rs.getString("MZQSND"));
			mxvo.setMZZZND(rs.getString("MZZZND"));
			mxvo.setJZQSND(rs.getString("JZQSND"));
			mxvo.setJZZZND(rs.getString("JZZZND"));
			mxvo.setYJJMSE(MoneyUtils.format(rs.getString("YJJMSE")));
			return mxvo;
		} catch (Exception e) {
			throw e;
		}
	}

}
