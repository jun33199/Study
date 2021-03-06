package com.ttsoft.bjtax.shenbao.model.client;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszmx;

public class LsswszInfor implements Serializable
{
    String jzbz;
    Timestamp skssksrq;
    String ysjcdm;
    Timestamp cjrq;
    BigDecimal yjhkc;
    String nd;
    String szdm;
    String szmc;
    BigDecimal jsje;
    BigDecimal kssl;
    String szsmdm;
    String szsmmc;
    String wszh;
    String pzzldm;
    BigDecimal sjse;
    String yskmdm;
    String yskmmc;
    String swjgzzjgdm;
    BigDecimal sl;
    String jsjdm;
    String zjhm;
    Timestamp skssjsrq;
    private String wszxh;
    private String qxdm;
    private java.sql.Timestamp lrrq;
    private String ndzb;
    private Timestamp hzrq;

    public LsswszInfor()
    {
    }

    public void setData(Lsswszmx mx)
    {
        this.setCjrq(mx.getCjrq());
        this.setJsjdm(mx.getJsjdm());
        this.setJsje(mx.getJsje());
        this.setJzbz(mx.getJzbz());
        this.setKssl(mx.getKssl());
        this.setLrrq(mx.getLrrq());
        this.setNd(mx.getNd());
        this.setNdzb(mx.getNdzb());
        this.setPzzldm(mx.getPzzldm());
        this.setQxdm(mx.getQxdm());
        this.setSjse(mx.getSjse());
        this.setSkssjsrq(mx.getSkssjsrq());
        this.setSkssksrq(mx.getSkssksrq());
        this.setSl(mx.getSl());
        this.setSwjgzzjgdm(mx.getSwjgzzjgdm());
        this.setSzdm(mx.getSzdm());
        this.setSzmc(mx.getSzmc());
        this.setSzsmdm(mx.getSzsmdm());
        this.setSzsmmc(mx.getSzsmmc());
        this.setWszh(mx.getWszh());
        this.setWszxh(mx.getWszxh());
        this.setYjhkc(mx.getYjhkc());
        this.setYsjcdm(mx.getYsjcdm());
        this.setYskmdm(mx.getYskmdm());
        this.setYskmmc(mx.getYskmmc());
        this.setZjhm(mx.getZjhm());
    }

    public String getJzbz()
    {
        return jzbz;
    }

    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }

    public String getYsjcdm()
    {
        return ysjcdm;
    }

    public Timestamp getCjrq()
    {
        return cjrq;
    }

    public BigDecimal getYjhkc()
    {
        return yjhkc;
    }

    public String getNd()
    {
        return nd;
    }

    public String getSzdm()
    {
        return szdm;
    }

    public BigDecimal getJsje()
    {
        return jsje;
    }

    public BigDecimal getKssl()
    {
        return kssl;
    }

    public String getSzsmdm()
    {
        return szsmdm;
    }

    public String getWszh()
    {
        return wszh;
    }

    public String getPzzldm()
    {
        return pzzldm;
    }

    public BigDecimal getSjse()
    {
        return sjse;
    }

    public String getYskmdm()
    {
        return yskmdm;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public BigDecimal getSl()
    {
        return sl;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public String getZjhm()
    {
        return zjhm;
    }

    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }

    public void setJzbz(String _jzbz)
    {
        jzbz = _jzbz;
    }

    public void setSkssksrq(Timestamp _skssksrq)
    {
        skssksrq = _skssksrq;
    }

    public void setYsjcdm(String _ysjcdm)
    {
        ysjcdm = _ysjcdm;
    }

    public void setCjrq(Timestamp cjrq)
    {
        this.cjrq = cjrq;
    }

    public void setYjhkc(BigDecimal _yjhkc)
    {
        yjhkc = _yjhkc;
    }

    public void setNd(String _nd)
    {
        nd = _nd;
    }

    public void setSzdm(String _szdm)
    {
        szdm = _szdm;
    }

    public void setJsje(BigDecimal _jsje)
    {
        jsje = _jsje;
    }

    public void setKssl(BigDecimal _kssl)
    {
        kssl = _kssl;
    }

    public void setSzsmdm(String _szsmdm)
    {
        szsmdm = _szsmdm;
    }

    public void setWszh(String _wszh)
    {
        wszh = _wszh;
    }

    public void setPzzldm(String _pzzldm)
    {
        pzzldm = _pzzldm;
    }

    public void setSjse(BigDecimal _sjse)
    {
        sjse = _sjse;
    }

    public void setYskmdm(String _yskmdm)
    {
        yskmdm = _yskmdm;
    }

    public void setSwjgzzjgdm(String _swjgzzjgdm)
    {
        swjgzzjgdm = _swjgzzjgdm;
    }

    public void setSl(BigDecimal _sl)
    {
        sl = _sl;
    }

    public void setJsjdm(String _jsjdm)
    {
        jsjdm = _jsjdm;
    }

    public void setZjhm(String _zjhm)
    {
        zjhm = _zjhm;
    }

    public void setSkssjsrq(Timestamp _skssjsrq)
    {
        skssjsrq = _skssjsrq;
    }

    public String getWszxh()
    {
        return wszxh;
    }

    public void setWszxh(String wszxh)
    {
        this.wszxh = wszxh;
    }

    public String getQxdm()
    {
        return qxdm;
    }

    public void setQxdm(String qxdm)
    {
        this.qxdm = qxdm;
    }

    public java.sql.Timestamp getLrrq()
    {
        return lrrq;
    }

    public void setLrrq(java.sql.Timestamp lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getNdzb()
    {
        return ndzb;
    }

    public void setNdzb(String ndzb)
    {
        this.ndzb = ndzb;
    }

    public String getSzmc()
    {
        return szmc;
    }

    public void setSzmc(String szmc)
    {
        this.szmc = szmc;
    }

    public String getSzsmmc()
    {
        return szsmmc;
    }

    public void setSzsmmc(String szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public String getYskmmc()
    {
        return yskmmc;
    }

    public void setYskmmc(String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public Timestamp getHzrq()
    {
        return hzrq;
    }

    public void setHzrq(Timestamp hzrq)
    {
        this.hzrq = hzrq;
    }
}