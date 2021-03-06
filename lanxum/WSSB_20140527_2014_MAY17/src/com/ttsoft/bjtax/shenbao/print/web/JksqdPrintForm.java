/*
 * <p>Title:北京地税市长决策支持</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光科技股份有限公司，版权所有. </p>
 * <p>Company: 四一安信科技股份有限公司</p>
 */
package com.ttsoft.bjtax.shenbao.print.web;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 打印缴款申请单</p>
 * 钱超：  2006。2。12
 *       1。加入国库信息
 * 
 * @author 开发部－－吴有智
 * @version 1.1
 */
public class JksqdPrintForm extends BaseForm {

  private String h_sbbh; //隐藏域-申报编号

  private String h_jsjdm; //隐藏域-计算机代码

  /**
   * 申报，数据来源
   */
  private String headSjly; //申报，数据来源

  private String sbbh; //申报编号

  private String sbrq; //申报日期

  private String jsjdm; //计算机代码

  private String nsrmc; //纳税人名称

  private String yhdm; //银行代码

  private String yhmc; //银行名称

  private String zh; //银行账号

  private String hjjedx; //合计金额大写

  private String hjjexx="0.00"; //合计金额小写

  private String skje="0.00"; //收款金额

  private String znjje="0.00"; //滞纳金金额

  private String swjgzzjgdm; //税务机关组织机构代码

  private String swjgzzjgmc; //税务机关组织机构名称

  private String gkzzjgdm; //国库组织机构代码

  private String gkzzjgmc; //国库组织机构名称

  private String lrr; //录入人

  private String bz; //备注

  //{"szdm","szmc","szsmdm","szsmmc","yskmdm","yskmmc","kssl","jsje","skssksrq","skssjsrq","sjje"}
  private List szitem = new ArrayList(); //按税种条目的明细List

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
      IOException {
    ois.defaultReadObject();
  }

  public String getBz() {
    return bz;
  }

  public String getH_jsjdm() {
    return h_jsjdm;
  }

  public String getH_sbbh() {
    return h_sbbh;
  }

  public String getHjjedx() {
    return hjjedx;
  }

  public String getHjjexx() {
    return hjjexx;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getLrr() {
    return lrr;
  }

  public List getSzitem() {
    return szitem;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public String getSbbh() {
    return sbbh;
  }

  public String getSkje() {
    return skje;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public String getYhdm() {
    return yhdm;
  }

  public String getYhmc() {
    return yhmc;
  }

  public String getZh() {
    return zh;
  }

  public String getZnjje() {
    return znjje;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setH_jsjdm(String h_jsjdm) {
    this.h_jsjdm = h_jsjdm;
  }

  public void setH_sbbh(String h_sbbh) {
    this.h_sbbh = h_sbbh;
  }

  public void setHjjedx(String hjjedx) {
    this.hjjedx = hjjedx;
  }

  public void setHjjexx(String hjjexx) {
    this.hjjexx = hjjexx;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public void setSzitem(List szitem) {
    this.szitem = szitem;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public void setSkje(String skje) {
    this.skje = skje;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public void setYhmc(String yhmc) {
    this.yhmc = yhmc;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }

  public void setZnjje(String znjje) {
    this.znjje = znjje;
  }

  public String getSbrq() {
    return sbrq;
  }

    public String getHeadSjly() {
        return headSjly;
    }

    public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

    public void setHeadSjly(String headSjly) {
        this.headSjly = headSjly;
    }

    public String getGkzzjgdm()
    {
        return gkzzjgdm;
    }

    public void setGkzzjgdm(String gkzzjgdm)
    {
        this.gkzzjgdm = gkzzjgdm;
    }

    public String getGkzzjgmc()
    {
        return gkzzjgmc;
    }

    public void setGkzzjgmc(String gkzzjgmc)
    {
        this.gkzzjgmc = gkzzjgmc;
    }
}
