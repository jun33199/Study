/**
 * Copyright (c) Beans factory Pte Ltd. All Rights Reserved.
 * Generated: 10-����-2003 16:35:36
 *
 * <p>
 * This SOURCE CODE FILE, which has been generated by Designer for use
 * ONLY by licensed users of the product, includes CONFIDENTIAL and
 * PROPRIETARY information of Beans Factory.
 *
 */
package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

/**
* This is the entity / business object
* We will use this business object to demonstrate OR Mapping in Kernl can
* be used for composition.
**/
public class Czqrl implements ORObject {

	Timestamp zqssrqq;
	String zqlxmc;
	Timestamp zqssrqz;
	Timestamp zqqsrq;
	Timestamp zqzzrq;
	String zqlxdm;
  private java.sql.Timestamp cjrq;
  private java.sql.Timestamp lrrq;
  private String swjgzzjgdm;
    private String nd;
	public Czqrl () {}
	public Timestamp getZqssrqq() {
		return zqssrqq;
	}
	public String getZqlxmc() {
		return zqlxmc;
	}
	public Timestamp getZqssrqz() {
		return zqssrqz;
	}
	public Timestamp getZqqsrq() {
		return zqqsrq;
	}
	public Timestamp getZqzzrq() {
		return zqzzrq;
	}
	public String getZqlxdm() {
		return zqlxdm;
	}
	public void setZqssrqq( Timestamp _zqssrqq ) {
		zqssrqq = _zqssrqq;
	}
	public void setZqlxmc( String _zqlxmc ) {
		zqlxmc = _zqlxmc;
	}
	public void setZqssrqz( Timestamp _zqssrqz ) {
		zqssrqz = _zqssrqz;
	}
	public void setZqqsrq( Timestamp _zqqsrq ) {
		zqqsrq = _zqqsrq;
	}
	public void setZqzzrq( Timestamp _zqzzrq ) {
		zqzzrq = _zqzzrq;
	}
	public void setZqlxdm( String _zqlxdm ) {
		zqlxdm = _zqlxdm;
	}
  public java.sql.Timestamp getCjrq() {
    return cjrq;
  }
  public void setCjrq(java.sql.Timestamp cjrq) {
    this.cjrq = cjrq;
  }
  public java.sql.Timestamp getLrrq() {
    return lrrq;
  }
  public void setLrrq(java.sql.Timestamp lrrq) {
    this.lrrq = lrrq;
  }
  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }
  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }
    public String getNd()
    {
        return nd;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }

}
