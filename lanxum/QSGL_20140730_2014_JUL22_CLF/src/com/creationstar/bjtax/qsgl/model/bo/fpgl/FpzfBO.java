package com.creationstar.bjtax.qsgl.model.bo.fpgl;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Htypzdzgxb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;

/**
*
* 发票作废BO
* @author tutu
* @version 1.0
* @time 2013-05-02
*/
public class FpzfBO implements Serializable {

	/**
     * 发票种类代码
     */
    public String fpzldm;
    
    /**
     * 发票种类名称
     */
    public String fpzlmc;
    
    /**
     * 前台输入的库存起始号码
     */
    public String qshm;
    
    /**
     * 查询结果发票种类代码
     */
    public String cxfpzldm;
    
    /**
     * 查询结果发票种类名称
     */
    public String cxfpzlmc;
    
    /**
     * 前台查询结果的库存起始号码
     */
    public String cxqshm;
    
    /**
     * 要提交的合同编号组
     */
    public String htbhs;
    
	/**
     * 税务机关组织结构代码
     */
    private String swjgzzjgdm;

    /**
     * 税务机关组织结构名称
     */
    private String swjgzzjgmc;
	
	/**
     * 合同编号
     */
    private String htbh;
    
    /**
     * 发票库房代码
     */
    public String fpkfdm;
    
    /**
     * 用户ID
     */
    public String yhid;
    
    /**
     * 用户MC
     */
    public String yhmc;
    
    /**
     * 票证账户代码
     */
    public String zhdm;
    
    /**
     * 房屋产权证号
     */
    private String fwcqzh;
    
    /**
     * 房屋坐落地址
     */
    private String fwzldz;
    
    /**
     * 金额
     */
    private String je;
    
    /**
     * 纳税人名称(买方)
     */
    private String nsrmc_buyer;
    
    /**
     * 纳税人名称(卖方)
     */
    private String nsrmc_seller;
    
    /**
     * 开票人
     */
    private String kpr;
    
    /**
     * 录入人
     */
    private String lrr;
    
    /**
     * 录入日期
     */
    private String lrrq;
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * cxfpzlList
     */
    public ArrayList cxfpzlList = new ArrayList();
    
    /**
     * fpkcList
     */
    public ArrayList fpkcList = new ArrayList();
    
    
    /**
     * cxList
     */
    public ArrayList cxList = new ArrayList();
    
    /**
     * fpkpList
     */
    public ArrayList fpkpList = new ArrayList();
	
	
	/**
     * 发票开票主表vo
     */
    private Fpczz fpczz = new Fpczz();

    /**
     * 明细表vo
     */
    private Fpczmx fpczmx = new Fpczmx();
    
    /**
     * 合同与凭证对照关系表vo
     */
    private Htypzdzgxb htypzdzgxb = new Htypzdzgxb();

	public String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public String getFpzlmc() {
		return fpzlmc;
	}

	public void setFpzlmc(String fpzlmc) {
		this.fpzlmc = fpzlmc;
	}

	public String getQshm() {
		return qshm;
	}

	public void setQshm(String qshm) {
		this.qshm = qshm;
	}

	public String getCxfpzldm() {
		return cxfpzldm;
	}

	public void setCxfpzldm(String cxfpzldm) {
		this.cxfpzldm = cxfpzldm;
	}

	public String getCxfpzlmc() {
		return cxfpzlmc;
	}

	public void setCxfpzlmc(String cxfpzlmc) {
		this.cxfpzlmc = cxfpzlmc;
	}

	public String getCxqshm() {
		return cxqshm;
	}

	public void setCxqshm(String cxqshm) {
		this.cxqshm = cxqshm;
	}

	public String getHtbhs() {
		return htbhs;
	}

	public void setHtbhs(String htbhs) {
		this.htbhs = htbhs;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}

	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getZhdm() {
		return zhdm;
	}

	public void setZhdm(String zhdm) {
		this.zhdm = zhdm;
	}

	public String getFwcqzh() {
		return fwcqzh;
	}

	public void setFwcqzh(String fwcqzh) {
		this.fwcqzh = fwcqzh;
	}

	public String getFwzldz() {
		return fwzldz;
	}

	public void setFwzldz(String fwzldz) {
		this.fwzldz = fwzldz;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getNsrmc_buyer() {
		return nsrmc_buyer;
	}

	public void setNsrmc_buyer(String nsrmc_buyer) {
		this.nsrmc_buyer = nsrmc_buyer;
	}

	public String getNsrmc_seller() {
		return nsrmc_seller;
	}

	public void setNsrmc_seller(String nsrmc_seller) {
		this.nsrmc_seller = nsrmc_seller;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public ArrayList getFpzlList() {
		return fpzlList;
	}

	public void setFpzlList(ArrayList fpzlList) {
		this.fpzlList = fpzlList;
	}

	public ArrayList getCxfpzlList() {
		return cxfpzlList;
	}

	public void setCxfpzlList(ArrayList cxfpzlList) {
		this.cxfpzlList = cxfpzlList;
	}

	public ArrayList getFpkcList() {
		return fpkcList;
	}

	public void setFpkcList(ArrayList fpkcList) {
		this.fpkcList = fpkcList;
	}

	public ArrayList getCxList() {
		return cxList;
	}

	public void setCxList(ArrayList cxList) {
		this.cxList = cxList;
	}

	public ArrayList getFpkpList() {
		return fpkpList;
	}

	public void setFpkpList(ArrayList fpkpList) {
		this.fpkpList = fpkpList;
	}

	public Fpczz getFpczz() {
		return fpczz;
	}

	public void setFpczz(Fpczz fpczz) {
		this.fpczz = fpczz;
	}

	public Fpczmx getFpczmx() {
		return fpczmx;
	}

	public void setFpczmx(Fpczmx fpczmx) {
		this.fpczmx = fpczmx;
	}

	public Htypzdzgxb getHtypzdzgxb() {
		return htypzdzgxb;
	}

	public void setHtypzdzgxb(Htypzdzgxb htypzdzgxb) {
		this.htypzdzgxb = htypzdzgxb;
	}
}
