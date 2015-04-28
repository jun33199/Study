package com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo;

import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class NsrxxVO_QYCW extends NsrxxVO implements XMLVOInterface {
	/**
     * 企业联系电话
     */
	private String qylxdh = "";

	public NsrxxVO_QYCW() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String toXMLChilds() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", super.getJsjdm());
		xmlstr += XMLBuildUtil.appendStringElement("nsrmc", super.getNsrmc());
		xmlstr += XMLBuildUtil.appendStringElement("qylxdh", qylxdh);
		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm",super.getSwjgzzjgdm());
		return xmlstr;
	}

	public String getQylxdh() {
		return qylxdh;
	}

	public void setQylxdh(String qylxdh) {
		this.qylxdh = qylxdh;
	}

}
