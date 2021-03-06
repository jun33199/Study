package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 缴税方式代码表值对象
 */
public class Jsfs implements Serializable {

    /**
     * 缴税方式代码
     */
    public java.lang.String jsfsdm;

    /**
     * 缴税方式名称
     */
    public java.lang.String jsfsmc;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;

    /**
     * 获得缴税方式代码
     */
    public String getJsfsdm() {
        return jsfsdm;
    }

    /**
     * 获得缴税方式名称
     */
    public String getJsfsmc() {
        return jsfsmc;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得注销标识
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * 赋值缴税方式代码
     * @param jsfsdm 缴税方式代码
     */
    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    /**
     * 赋值缴税方式名称
     * @param jsfsmc 缴税方式名称
     */
    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 赋值注销标识
     * @param zxbs 注销标识
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }


}
