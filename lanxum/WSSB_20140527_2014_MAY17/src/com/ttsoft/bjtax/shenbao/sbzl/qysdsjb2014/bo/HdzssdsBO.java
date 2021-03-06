package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 *用于ejb交互的数据对象
 *
 */
public class HdzssdsBO implements Serializable {

	private String jsjdm = "";

	private String nsrmc = "";
	
	private String nsrsbh="";

	private String swjgzzjgdm = "";

	private String jmzg = "";

	private String ybjmsl = "";

	private String qyzslx = "";

	private String cyl = "";

	private String xzqy = "";

	private String dezsse = "";

	private String fsdm = "";

	private String jd = "";

	private String nd = "";

	private Timestamp sbrq;

	private Timestamp skssjsrq;

	private Timestamp skssksrq;

	private String sbrqshow = "";

	private HashMap sbsj = new HashMap();

	private String syze = "";  //1h

	private String bzssr = "";  //2h

	private String mssr = "";  //3h

	private String yssre = "";  //4h

	private String yssdl = "";  //5h

	private String ynssde = "";  //6h

	private String sl = "";    //10h

	private String ynsdse = ""; //11h
	//减：符合条件的小型微利企业减免所得税额
	private String xwqyjmsdse = ""; //12h
	
	private String yyjsdse = ""; //13h

	private String ybsdse = "";  //14h
	//税务机关核定应纳所得税额
	private String swjghdynsdse = "";  //15h

	private String lrrdm = "";
	private String zczb = ""; //16h
	private String zcze = "";  //17h
	private String zgrs = "";  //18h,职工人数
	private String sshydm = "";  //19h，所属行业代码
	private String sshy = "";  //所属行业名称
	public String getLrrdm() {
		return lrrdm;
	}

	public void setLrrdm(String lrrdm) {
		this.lrrdm = lrrdm;
	}

	public String getCyl() {
		return cyl;
	}

	public void setCyl(String cyl) {
		this.cyl = cyl;
	}

	public String getDezsse() {
		return dezsse;
	}

	public void setDezsse(String dezsse) {
		this.dezsse = dezsse;
	}

	public String getFsdm() {
		return fsdm;
	}

	public void setFsdm(String fsdm) {
		this.fsdm = fsdm;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getJmzg() {
		return jmzg;
	}

	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getQyzslx() {
		return qyzslx;
	}

	public void setQyzslx(String qyzslx) {
		this.qyzslx = qyzslx;
	}

	public Timestamp getSbrq() {
		return sbrq;
	}

	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	public String getSbrqshow() {
		return sbrqshow;
	}

	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getSyze() {
		return syze;
	}

	public void setSyze(String syze) {
		this.syze = syze;
	}

	public String getYbsdse() {
		return ybsdse;
	}

	public void setYbsdse(String ybsdse) {
		this.ybsdse = ybsdse;
	}

	public String getYnsdse() {
		return ynsdse;
	}

	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}

	public String getYnssde() {
		return ynssde;
	}

	public void setYnssde(String ynssde) {
		this.ynssde = ynssde;
	}

	public String getYssdl() {
		return yssdl;
	}

	public void setYssdl(String yssdl) {
		this.yssdl = yssdl;
	}

	public String getYyjsdse() {
		return yyjsdse;
	}

	public void setYyjsdse(String yyjsdse) {
		this.yyjsdse = yyjsdse;
	}

	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	public Timestamp getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}


	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}


	public String getXzqy() {
		return xzqy;
	}

	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}


	public String getYbjmsl() {
		return ybjmsl;
	}

	public void setYbjmsl(String ybjmsl) {
		this.ybjmsl = ybjmsl;
	}



	public HashMap getSbsj() {
		return sbsj;
	}

	public void setSbsj(HashMap sbsj) {
		this.sbsj = sbsj;
		System.out.println("========"+sbsj);
		if (sbsj != null&&sbsj.size()>0) {
			this.syze = sbsj.get("syze").toString();
			this.bzssr = sbsj.get("bzssr").toString();
			this.mssr = sbsj.get("mssr").toString();
			this.yssre = sbsj.get("yssre").toString();
			this.yssdl = sbsj.get("yssdl").toString();
			this.ynssde = sbsj.get("ynssde").toString();
			this.sl = sbsj.get("sl").toString();
			this.ynsdse = sbsj.get("ynsdse").toString();
			this.yyjsdse = sbsj.get("yyjsdse").toString();
			this.ybsdse = sbsj.get("ybsdse").toString();
			this.xwqyjmsdse = sbsj.get("xwqyjmsdse").toString();
			this.swjghdynsdse = sbsj.get("swjghdynsdse").toString();
			
			this.zczb = sbsj.get("zczb").toString();
			this.zcze = sbsj.get("zcze").toString();
			this.zgrs = sbsj.get("zgrs").toString();
			this.sshydm = sbsj.get("sshydm").toString();
		}
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getBzssr() {
		return bzssr;
	}

	public void setBzssr(String bzssr) {
		this.bzssr = bzssr;
	}

	public String getMssr() {
		return mssr;
	}

	public void setMssr(String mssr) {
		this.mssr = mssr;
	}

	public String getYssre() {
		return yssre;
	}

	public void setYssre(String yssre) {
		this.yssre = yssre;
	}

	public String getXwqyjmsdse() {
		return xwqyjmsdse;
	}

	public void setXwqyjmsdse(String xwqyjmsdse) {
		this.xwqyjmsdse = xwqyjmsdse;
	}

	public String getSwjghdynsdse() {
		return swjghdynsdse;
	}

	public void setSwjghdynsdse(String swjghdynsdse) {
		this.swjghdynsdse = swjghdynsdse;
	}

	public String getZcze() {
		return zcze;
	}

	public void setZcze(String zcze) {
		this.zcze = zcze;
	}

	public String getZgrs() {
		return zgrs;
	}

	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}

	public String getSshydm() {
		return sshydm;
	}

	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getZczb() {
		return zczb;
	}

	public void setZczb(String zczb) {
		this.zczb = zczb;
	}

}
