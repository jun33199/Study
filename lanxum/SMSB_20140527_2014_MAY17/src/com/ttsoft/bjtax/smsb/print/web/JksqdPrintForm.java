/*
 * <p>Title:������˰�г�����֧��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�Ƽ��ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ��ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ӡ�ɿ����뵥</p>
 * Ǯ����  2006��2��12
 *       1�����������Ϣ
 * 
 * @author ����������������
 * @version 1.1
 */
public class JksqdPrintForm extends BaseForm {

  private String h_sbbh = ""; //������-�걨���

  private String h_jsjdm = ""; //������-���������
  
  private String kjflag = "";//������-��־�Ƿ��ǿ۽�����˰��Դ

  /**
   * �걨��������Դ
   */
  private String headSjly = ""; //�걨��������Դ

  private String sbbh = ""; //�걨���

  private String sbrq = "       "; //�걨����

  private String jsjdm = ""; //���������

  private String nsrmc = ""; //��˰������

  private String yhdm = ""; //���д���

  private String yhmc = ""; //��������

  private String zh = ""; //�����˺�

  private String hjjedx = ""; //�ϼƽ���д

  private String hjjexx="0.00"; //�ϼƽ��Сд

  private String skje="0.00"; //�տ���

  private String znjje="0.00"; //���ɽ���

  private String swjgzzjgdm = ""; //˰�������֯��������

  private String swjgzzjgmc = ""; //˰�������֯��������

  private String gkzzjgdm = ""; //������֯��������

  private String gkzzjgmc = ""; //������֯��������

  private String lrr = ""; //¼����

  private String bz = ""; //��ע

  //{"szdm","szmc","szsmdm","szsmmc","yskmdm","yskmmc","kssl","jsje","skssksrq","skssjsrq","sjje"}
  private List szitem = new ArrayList(); //��˰����Ŀ����ϸList

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

	public String getKjflag() {
		return kjflag;
	}

	public void setKjflag(String kjflag) {
		this.kjflag = kjflag;
	}
}