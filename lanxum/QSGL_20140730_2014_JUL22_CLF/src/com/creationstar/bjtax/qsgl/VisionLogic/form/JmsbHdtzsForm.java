package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JmsbHdtzsForm extends BaseForm {
    /**
     * 申报表号
     */
    private String sbbh;

    /**
     * 核定通知书号
     */
    private String hdtzsh;

    /**
     * 核定通知书号_修该
     */
    private String hdtzsh_xg;
    /**
     * 防伪号码
     */
    private String fwhm = " ";

    /**
     * HdtzsBo
     */
    private HdtzsBo hdtzsbo;

    public JmsbHdtzsForm() {
    }

    public HdtzsBo getHdtzsbo() {
        return hdtzsbo;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setHdtzsbo(HdtzsBo hdtzsbo) {
        this.hdtzsbo = hdtzsbo;
    }

    public String getFwhm() {
        return fwhm;
    }

    public void setFwhm(String fwhm) {
        this.fwhm = fwhm;
    }

    public String getHdtzsh() {
        return hdtzsh;
    }

    public void setHdtzsh(String hdtzsh) {
        this.hdtzsh = hdtzsh;
    }

    public String getHdtzsh_xg() {
        return hdtzsh_xg;
    }

    public void setHdtzsh_xg(String hdtzsh_xg) {
        this.hdtzsh_xg = hdtzsh_xg;
    }
}
