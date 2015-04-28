/**
 * @Title:       GrsdsJbxxProcessor.java
 * @Description: TODO
 * @date:        2014-12-2����10:16:43
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.Grsds2014Constant;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.GrsdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.web.GrsdsJbxxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-12-2
 */
public class GrsdsJbxxbProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException {
		try {
			switch (vo.getAction()) {
			
			// ��ѯ��Ŀ
			case Grsds2014Constant.PROCESSOR_ACTION_QUERY: {
				return doQuery(vo);
			}
			
			//����
			case Grsds2014Constant.PROCESSOR_ACTION_SAVE: {
				return doSave(vo);
			}
			// ɾ��
			case Grsds2014Constant.PROCESSOR_ACTION_DELETE: {
				return doDelete(vo);
			}

			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	
	/**
	 * @Description: TODO
	 * @param vo
	 * @return
	 */
	private Object doDelete(VOPackage vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Description: TODO ����:������ڸü�¼�͸��·������� 
	 * @param vo
	 * @return
	 * @throws BaseException 
	 */
	private Object doSave(VOPackage vo) throws Exception {
		GrsdsJbxxActionForm jbxxForm = (GrsdsJbxxActionForm)vo.getData();
		Connection conn =null;
		try {
			conn = SfDBResource.getConnection();
			if(GrsdsUtil.hasItemJcxxQy(jbxxForm.getNd(), jbxxForm.getQyxx_jsjdm(), conn))
			{
				doUpdateQy(jbxxForm ,conn);
				if(GrsdsUtil.hasItemJcxxTzz(jbxxForm.getNd(), jbxxForm.getQyxx_jsjdm(), jbxxForm.getGrxx_sfzjlx(), jbxxForm.getGrxx_sfzjhm(), conn)){
					doUpdateTzf(jbxxForm ,conn);
				}else{
					doInsertTzf(jbxxForm, conn, vo.getUserData());
				}
			}else{
				doInsertQy(jbxxForm ,conn ,vo.getUserData());
				doInsertTzf(jbxxForm ,conn ,vo.getUserData());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
				conn.close();
		}
		
		return jbxxForm;
	}
	
	/**
	 * @Description: TODO ���»�����Ϣ��ҵ
	 * @param jbxxbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateQy(GrsdsJbxxActionForm jbxxForm ,Connection conn) throws SQLException{
		String sql_updateCompanyInf = "update sbdb.SB_JL_GRSDS_JCXXB_QY set mc=? ,kjywrbh=?,dz=?,yzbm=?,djzclx=?,gjbzhydm=?,sdszsfs=?,zgswjgdm=? where jsjdm=? and nd=?";
		PreparedStatement ps_company = conn.prepareStatement(sql_updateCompanyInf);
		int i = 1;
		ps_company.setString(i++,jbxxForm.getQyxx_btzzxm());
		ps_company.setString(i++,jbxxForm.getQyxx_kjywrbh());
		ps_company.setString(i++,jbxxForm.getQyxx_dz());
		ps_company.setString(i++,jbxxForm.getQyxx_yzbm());
		ps_company.setString(i++,jbxxForm.getQyxx_djzclx());
		ps_company.setString(i++,jbxxForm.getQyxx_hy());
		ps_company.setString(i++,jbxxForm.getQyxx_sdszsfs());
		ps_company.setString(i++,jbxxForm.getQyxx_zgswjg());
		ps_company.setString(i++,jbxxForm.getQyxx_jsjdm());
		ps_company.setString(i++,jbxxForm.getNd());
		ps_company.executeUpdate();
	}
	
	/**
	 * @Description: TODO ���»�����ϢͶ���߷�
	 * @param jbxxbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateTzf(GrsdsJbxxActionForm jbxxForm ,Connection conn) throws SQLException{
		String sql_updateTzzInf = "update sbdb.SB_JL_GRSDS_JCXXB_TZF set xm=? ,nsrlx=? ,sfyjjnqk=? ,dzyx=? ,jnlxdz=? ,yzbm=? ,lxdh=? ,zy=? ,zw=? ,xl=? " +
		",sfcjrlsgl=? ,cjdjqk=? ,tzzlx=? ,yjwsddzlx=? ,yjwsddz=? ,yjwsdyzbm=? ,wzsnsrgj=? ,wzsnsrcsd=? ,wzsnsrxb=? ,wzsnsrcsrq=to_date(?,'yyyymmdd') ,wzsnsrldjyzhm=? " +
		",wzsnsrsfssxddygdfjm=? ,wzsnsrjnzw=? ,wzsnsrjwzw=? ,wzsnsrlhsj=to_date(?,'yyyymmdd') ,wzsnsrrzqx=? ,wzsnsryjljsj=to_date(?,'yyyymmdd') ,wzsnsryjljdd=? ,wzsnsrjnrzsgdwmc=? ,wzsnsrjnrzsgdwkjywrbm=? " +
		",wzsnsrjnrzsgdwdz=? ,wzsnsrjnrzsgdwyzbm=? ,wzsnsrjnspqydwmc=? ,wzsnsrjnspqydwkjywrbm=? ,wzsnsrjnspqydwdz=? ,wzsnsrjnspqydwyzbm=? ,wzsnsrjwpqdwmc=? ,wzsnsrjwpqdwdz=? " +
		",wzsnsrzfd=? ,wzsnsrjwzfggb=? ,rzsgdwmc=? ,rzsgdwnsrsbh=? ,wzsnsrnsrsbh=? where jsjdm=? and sfzjlxdm=? and sfzjhm=? and nd=?";
		PreparedStatement ps_tzz = conn.prepareStatement(sql_updateTzzInf);
		int j = 1;
		
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_nsrlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfyjjnqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_email());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_jnlxdz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_lxdh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zy());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfcjrlsgl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_cjdjqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_tzzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_gj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_xb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csrq());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_ldjyzhm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_sfssxddygdfjm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_lhsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_rzqx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljdd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_zfd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzfggb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwmc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwnsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_nsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getQyxx_jsjdm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjhm());
		ps_tzz.setString(j++ ,jbxxForm.getNd());
		ps_tzz.executeUpdate();
	}
	
	
	/**
	 * @Description: TODO ����������Ϣ��ҵ
	 * @param jbxxbVO
	 * @param conn
	 * @param ud
	 * @throws SQLException 
	 */
	private void doInsertQy(GrsdsJbxxActionForm jbxxForm ,Connection conn ,UserData ud) throws SQLException{
		String sql_updateCompanyInf = "insert into sbdb.SB_JL_GRSDS_JCXXB_QY (mc,kjywrbh,dz,yzbm,djzclx,gjbzhydm,sdszsfs,zgswjgdm ,jsjdm ,nd ,lrr ,lrsj) values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_company = conn.prepareStatement(sql_updateCompanyInf);
		int i = 1;
		ps_company.setString(i++,jbxxForm.getQyxx_btzzxm());
		ps_company.setString(i++,jbxxForm.getQyxx_kjywrbh());
		ps_company.setString(i++,jbxxForm.getQyxx_dz());
		ps_company.setString(i++,jbxxForm.getQyxx_yzbm());
		ps_company.setString(i++,jbxxForm.getQyxx_djzclx());
		ps_company.setString(i++,jbxxForm.getQyxx_hy());
		ps_company.setString(i++,jbxxForm.getQyxx_sdszsfs());
		ps_company.setString(i++,jbxxForm.getQyxx_zgswjg());
		ps_company.setString(i++,jbxxForm.getQyxx_jsjdm());
		ps_company.setString(i++,jbxxForm.getNd());
		ps_company.setString(i++,ud.yhid);
		ps_company.executeUpdate();
	}
	
	/**
	 * @Description: TODO ����������ϢͶ�ʷ�
	 * @param jbxxbVO
	 * @param conn
	 * @param ud
	 * @throws SQLException 
	 */
	private void doInsertTzf(GrsdsJbxxActionForm jbxxForm ,Connection conn ,UserData ud) throws SQLException{
		String sql_updateTzzInf = "insert into sbdb.SB_JL_GRSDS_JCXXB_TZF(xm ,nsrlx ,sfyjjnqk ,dzyx ,jnlxdz ,yzbm ,lxdh ,zy ,zw ,xl ,sfcjrlsgl ,cjdjqk " +
		",tzzlx ,yjwsddzlx ,yjwsddz ,yjwsdyzbm ,wzsnsrgj ,wzsnsrcsd ,wzsnsrxb ,wzsnsrcsrq ,wzsnsrldjyzhm "+
		",wzsnsrsfssxddygdfjm ,wzsnsrjnzw ,wzsnsrjwzw ,wzsnsrlhsj ,wzsnsrrzqx ,wzsnsryjljsj ,wzsnsryjljdd ,wzsnsrjnrzsgdwmc ,wzsnsrjnrzsgdwkjywrbm "+
		",wzsnsrjnrzsgdwdz ,wzsnsrjnrzsgdwyzbm ,wzsnsrjnspqydwmc ,wzsnsrjnspqydwkjywrbm ,wzsnsrjnspqydwdz ,wzsnsrjnspqydwyzbm ,wzsnsrjwpqdwmc ,wzsnsrjwpqdwdz "+
		",wzsnsrzfd ,wzsnsrjwzfggb ,rzsgdwmc ,rzsgdwnsrsbh ,wzsnsrnsrsbh ,jsjdm ,sfzjlxdm ,sfzjhm ,nd ,cjr ,cjsj) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyymmdd'),?,?,?,?,to_date(?,'yyyymmdd'),?,to_date(?,'yyyymmdd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_tzz = conn.prepareStatement(sql_updateTzzInf);
		int j = 1;
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_nsrlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfyjjnqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_email());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_jnlxdz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_lxdh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zy());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfcjrlsgl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_cjdjqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_tzzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_gj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_xb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csrq());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_ldjyzhm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_sfssxddygdfjm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_lhsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_rzqx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljdd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_zfd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzfggb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwmc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwnsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_nsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getQyxx_jsjdm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjhm());
		ps_tzz.setString(j++ ,jbxxForm.getNd());
		ps_tzz.setString(j++ ,ud.yhid);
		ps_tzz.executeUpdate();
	}
	
	/**
	 * @Description: TODO ��ѯ��Ϣ��������Ϣ��
	 * @param vo
	 * @return
	 * @throws BaseException 
	 */
	private Object doQuery(VOPackage vo) throws Exception	{
		GrsdsJbxxActionForm grsdsJbxxActionForm = (GrsdsJbxxActionForm)vo.getData();
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();
			
			//�����ǰ����Ϣ��ȡ������Ϣ
			getYearQY(grsdsJbxxActionForm,Integer.parseInt(grsdsJbxxActionForm.getNd()) ,conn);
			getYearTzz(grsdsJbxxActionForm ,Integer.parseInt(grsdsJbxxActionForm.getNd()),conn);
				
//			//����ȡǰһ����Ϣ
//			}else if(getYearQY(grsdsJbxxActionForm ,Integer.parseInt(grsdsJbxxActionForm.getNd())-1 ,conn)){
//				getYearTzz(grsdsJbxxActionForm ,Integer.parseInt(grsdsJbxxActionForm.getNd())-1 ,conn);
//				
//			//�����û����ȡϵͳ��Ϣ
//			}else{
//				//ȡ������Ĭ�ϻ�������
//				getDefaultInf(grsdsJbxxActionForm ,conn);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
			conn.close();
		}
		return grsdsJbxxActionForm;
	}
	
	/**
	 * @Description: TODO �����û����Ӻ���ϵͳ��ȡ
	 * @param jcxxb
	 * @param jsjdm
	 * @param sfzjlx
	 * @param sfzjhm
	 * @throws Exception 
	 */
	private void getDefaultInf(GrsdsJbxxActionForm jcxxb ,Connection conn) throws Exception{
		String sqlQY= "select SWJGZZJGDM ,GJBZHYDM ,SWDJZH ,DJZCLXDM ,NSRMC from djdb.dj_jl_jbsj  where jsjdm=?";
		String sqlGR= "select TZZXM from sbdb.SB_JL_TZZSJ2014  where zjlxdm=? and zjhm=?";
		
		PreparedStatement psQy = conn.prepareStatement(sqlQY);
		psQy.setString(1, jcxxb.getQyxx_jsjdm());
		ResultSet rsQy = psQy.executeQuery();
		if(rsQy.next()){
			jcxxb.setQyxx_btzzxm(rsQy.getString("NSRMC")==null?"":rsQy.getString("NSRMC"));
			jcxxb.setQyxx_hy(rsQy.getString("GJBZHYDM")==null?"":rsQy.getString("GJBZHYDM"));
			jcxxb.setQyxx_zgswjg(rsQy.getString("SWJGZZJGDM")==null?"":rsQy.getString("SWJGZZJGDM"));
			jcxxb.setQyxx_kjywrbh(rsQy.getString("SWDJZH")==null?"":rsQy.getString("SWDJZH"));
			jcxxb.setQyxx_djzclx(rsQy.getString("DJZCLXDM")==null?"":rsQy.getString("DJZCLXDM"));
		}
		
		PreparedStatement psGr = conn.prepareStatement(sqlGR);
		psGr.setString(1, jcxxb.getGrxx_sfzjlx());
		psGr.setString(2, jcxxb.getGrxx_sfzjhm());
		ResultSet rsGr = psGr.executeQuery();
		if(rsGr.next()){
			jcxxb.setGrxx_xm(rsGr.getString("TZZXM")==null?"":rsGr.getString("TZZXM"));
		}
	}
	
	/**
	 * @Description: TODO �Ƿ���ڵ�����ҵ��Ϣ ������ڷ���true;���򷵻�false;
	 * @param jsjdm
	 * @return
	 * @throws Exception 
	 */
	private boolean getYearQY(GrsdsJbxxActionForm jbxxb ,int nd ,Connection conn) throws Exception
	{
		boolean result = false;
		String sql = "select JSJDM ,ND ,MC ,KJYWRBH ,DZ ,YZBM ,DJZCLX ,GJBZHYDM ,SDSZSFS,ZGSWJGDM from sbdb.SB_JL_GRSDS_JCXXB_QY where jsjdm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jbxxb.getQyxx_jsjdm());
		ps.setString(2, String.valueOf(nd));
		ResultSet rs = ps.executeQuery();
		if(rs.next())   
		{
			result = true;
			jbxxb.setQyxx_btzzxm(rs.getString("MC")==null?"":rs.getString("MC"));     			        //����
			jbxxb.setQyxx_kjywrbh(rs.getString("KJYWRBH")==null?"":rs.getString("KJYWRBH"));	  			//�۽������˱��
			jbxxb.setQyxx_dz(rs.getString("DZ")==null?"":rs.getString("DZ")); 			     			//��ַ
			jbxxb.setQyxx_yzbm(rs.getString("YZBM")==null?"":rs.getString("YZBM")); 			     		//��������
			jbxxb.setQyxx_djzclx(rs.getString("DJZCLX")==null?"":rs.getString("DJZCLX")); 		     	//�Ǽ�ע������
			jbxxb.setQyxx_hy(rs.getString("GJBZHYDM")==null?"":rs.getString("GJBZHYDM")); 		     	//���ұ�׼��ҵ����
			jbxxb.setQyxx_sdszsfs(rs.getString("SDSZSFS")==null?"":rs.getString("SDSZSFS")); 		     	//����˰���շ�ʽ
			jbxxb.setQyxx_zgswjg(rs.getString("ZGSWJGDM")==null?"":rs.getString("ZGSWJGDM")); 		     //����˰�����
		}
		return result;
	}
	
	/**
	 * @Description: TODO ��ѯͶ����
	 * @param jbxxbVO
	 * @param conn
	 * @param year
	 * @return
	 * @throws Exception 
	 */
	private void getYearTzz(GrsdsJbxxActionForm jbxxb ,int nd ,Connection conn) throws Exception
	{
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
		
		String sql = "select * from sbdb.SB_JL_GRSDS_JCXXB_TZF where jsjdm=? and sfzjlxdm=? and sfzjhm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jbxxb.getQyxx_jsjdm());
		ps.setString(2, jbxxb.getGrxx_sfzjlx());
		ps.setString(3, jbxxb.getGrxx_sfzjhm());
		ps.setString(4, String.valueOf(nd));
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{										
			jbxxb.setGrxx_xm(rs.getString("XM")==null?"":rs.getString("XM"));	//Ͷ�ʷ�����
			jbxxb.setGrxx_nsrlx(rs.getString("NSRLX")==null?"":rs.getString("NSRLX"));	//��˰�����ʹ���
			jbxxb.setGrxx_rzsgdwmc(rs.getString("RZSGDWMC")==null?"":rs.getString("RZSGDWMC"));	//��ְ�ܹ͵�λ����
			jbxxb.setGrxx_rzsgdwnsrsbh(rs.getString("RZSGDWNSRSBH")==null?"":rs.getString("RZSGDWNSRSBH"));	//��ְ�ܹ͵�λ��˰��ʶ���
			jbxxb.setGrxx_sfyjjnqk(rs.getString("SFYJJNQK")==null?"":rs.getString("SFYJJNQK"));	//����һ��������
			jbxxb.setGrxx_email(rs.getString("DZYX")==null?"":rs.getString("DZYX"));	//��������
			jbxxb.setGrxx_jnlxdz(rs.getString("JNLXDZ")==null?"":rs.getString("JNLXDZ"));	//������ϵ��ַ
			jbxxb.setGrxx_yzbm(rs.getString("YZBM")==null?"":rs.getString("YZBM"));	//��������
			jbxxb.setGrxx_lxdh(rs.getString("LXDH")==null?"":rs.getString("LXDH"));	//��ϵ�绰
			jbxxb.setGrxx_zy(rs.getString("ZY")==null?"":rs.getString("ZY"));	//ְҵ
			jbxxb.setGrxx_zw(rs.getString("ZW")==null?"":rs.getString("ZW"));	//ְ��
			jbxxb.setGrxx_xl(rs.getString("XL")==null?"":rs.getString("XL"));	//ѧ��
			jbxxb.setGrxx_sfcjrlsgl(rs.getString("SFCJRLSGL")==null?"":rs.getString("SFCJRLSGL"));	//�Ƿ�м��ˡ�����������
			jbxxb.setGrxx_cjdjqk(rs.getString("CJDJQK")==null?"":rs.getString("CJDJQK"));	//�м��ȼ����
			jbxxb.setGrxx_tzzlx(rs.getString("TZZLX")==null?"":rs.getString("TZZLX"));	//Ͷ��������
			jbxxb.setGrxx_yjwsd_dzlx(rs.getString("YJWSDDZLX")==null?"":rs.getString("YJWSDDZLX"));	//�о������õ�ַ����
			jbxxb.setGrxx_yjwsd_dz(rs.getString("YJWSDDZ")==null?"":rs.getString("YJWSDDZ"));	//�о��������õ�ַ
			jbxxb.setGrxx_yjwsd_yzbm(rs.getString("YJWSDYZBM")==null?"":rs.getString("YJWSDYZBM"));	//�о���������������
			
			jbxxb.setGrxx_wzsnsr_nsrsbh(rs.getString("WZSNSRNSRSBH")==null?"":rs.getString("WZSNSRNSRSBH"));	//��ס����˰����˰��ʶ���
			jbxxb.setGrxx_wzsnsr_gj(rs.getString("WZSNSRGJ")==null?"":rs.getString("WZSNSRGJ"));	//��ס����˰�˹���
			jbxxb.setGrxx_wzsnsr_csd(rs.getString("WZSNSRCSD")==null?"":rs.getString("WZSNSRCSD"));	//��ס����˰�˳����� 
			jbxxb.setGrxx_wzsnsr_xb(rs.getString("WZSNSRXB")==null?"":rs.getString("WZSNSRXB"));	//��ס����˰���Ա�
			jbxxb.setGrxx_wzsnsr_csrq(rs.getDate("WZSNSRCSRQ")==null?"":time.format(rs.getDate("WZSNSRCSRQ")));	//��ס����˰�˳�������
			jbxxb.setGrxx_wzsnsr_ldjyzhm(rs.getString("WZSNSRLDJYZHM")==null?"":rs.getString("WZSNSRLDJYZHM"));	//��ס����˰���Ͷ���ҵ֤����
			jbxxb.setGrxx_wzsnsr_sfssxddygdfjm(rs.getString("WZSNSRSFSSXDDYGDFJM")==null?"":rs.getString("WZSNSRSFSSXDDYGDFJM"));	//��ס����˰���Ƿ�˰��Э����Լ���Է�����
			jbxxb.setGrxx_wzsnsr_jnzw(rs.getString("WZSNSRJNZW")==null?"":rs.getString("WZSNSRJNZW"));	//��ס����˰�˾���ְ��
			jbxxb.setGrxx_wzsnsr_jwzw(rs.getString("WZSNSRJWZW")==null?"":rs.getString("WZSNSRJWZW"));	//��ס����˰�˾���ְ��
			jbxxb.setGrxx_wzsnsr_lhsj(rs.getDate("WZSNSRLHSJ")==null?"":time.format(rs.getDate("WZSNSRLHSJ")));	//��ס����˰������ʱ��
			jbxxb.setGrxx_wzsnsr_rzqx(rs.getString("WZSNSRRZQX")==null?"":rs.getString("WZSNSRRZQX"));	//��ס����˰����ְ����
			jbxxb.setGrxx_wzsnsr_yjljsj(rs.getDate("WZSNSRYJLJSJ")==null?"":time.format(rs.getDate("WZSNSRYJLJSJ")));	//��ס����˰��Ԥ���뾳ʱ��
			jbxxb.setGrxx_wzsnsr_yjljdd(rs.getString("WZSNSRYJLJDD")==null?"":rs.getString("WZSNSRYJLJDD"));	//��ס����˰��Ԥ���뾳�ص�
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_mc(rs.getString("WZSNSRJNRZSGDWMC")==null?"":rs.getString("WZSNSRJNRZSGDWMC"));	//��ס����˰�˾�����ְ�ܹ͵�λ����
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_kjywrbm(rs.getString("WZSNSRJNRZSGDWKJYWRBM")==null?"":rs.getString("WZSNSRJNRZSGDWKJYWRBM"));	//��ס����˰�˾�����ְ�ܹ͵�λ �۽������˱���
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_dz(rs.getString("WZSNSRJNRZSGDWDZ")==null?"":rs.getString("WZSNSRJNRZSGDWDZ"));	//��ס����˰�˾�����ְ�ܹ͵�λ��ַ
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_yzbm(rs.getString("WZSNSRJNRZSGDWYZBM")==null?"":rs.getString("WZSNSRJNRZSGDWYZBM"));	//��ס����˰�˾�����ְ�ܹ͵�λ��������
			jbxxb.setGrxx_wzsnsr_jnspqydw_mc(rs.getString("WZSNSRJNSPQYDWMC")==null?"":rs.getString("WZSNSRJNSPQYDWMC"));	//��ס����˰�˾�����ƸǩԼ��λ����
			jbxxb.setGrxx_wzsnsr_jnspqydw_kjywrbm(rs.getString("WZSNSRJNSPQYDWKJYWRBM")==null?"":rs.getString("WZSNSRJNSPQYDWKJYWRBM"));	//��ס����˰�˾�����ƸǩԼ��λ�۽������˱���
			jbxxb.setGrxx_wzsnsr_jnspqydw_dz(rs.getString("WZSNSRJNSPQYDWDZ")==null?"":rs.getString("WZSNSRJNSPQYDWDZ"));	//��ס����˰�˾�����ƸǩԼ��λ��ַ
			jbxxb.setGrxx_wzsnsr_jnspqydw_yzbm(rs.getString("WZSNSRJNSPQYDWYZBM")==null?"":rs.getString("WZSNSRJNSPQYDWYZBM"));	//��ס����˰�˾�����ƸǩԼ��λ��������
			jbxxb.setGrxx_wzsnsr_jwpqdw_mc(rs.getString("WZSNSRJWPQDWMC")==null?"":rs.getString("WZSNSRJWPQDWMC"));	//��ס����˰�˾�����ǲ��λ����
			jbxxb.setGrxx_wzsnsr_jwpqdw_dz(rs.getString("WZSNSRJWPQDWDZ")==null?"":rs.getString("WZSNSRJWPQDWDZ"));	//��ס����˰�˾�����ǲ��λ��ַ
			jbxxb.setGrxx_wzsnsr_zfd(rs.getString("WZSNSRZFD")==null?"":rs.getString("WZSNSRZFD"));	//��ס����˰��֧����
			jbxxb.setGrxx_wzsnsr_jwzfggb(rs.getString("WZSNSRJWZFGGB")==null?"":rs.getString("WZSNSRJWZFGGB"));	//��ס����˰�˾���֧��������	
		}
		
	}

}