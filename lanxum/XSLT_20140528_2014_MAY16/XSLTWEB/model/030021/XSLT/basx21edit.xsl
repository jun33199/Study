<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">
		<input name="xsltVersion" type="hidden" id="xsltVersion">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/xsltVersion" />
			</xsl:attribute>
		</input>
		<input name="schemaVersion" type="hidden" id="schemaVersion">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/schemaVersion" />
			</xsl:attribute>
		</input>
		<input name="ywlx" type="hidden" id="ywlx">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/ywlx" />
			</xsl:attribute>
		</input>
		<input name="ywczlx" type="hidden" id="ywczlx">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/ywczlx" />
			</xsl:attribute>
		</input>
		<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/swjgzzjgdm" />
			</xsl:attribute>
		</input>
		<input name="jsjdm" type="hidden" id="jsjdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/jsjdm" />
			</xsl:attribute>
		</input>
		<input name="nsrmc" type="hidden" id="nsrmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/nsrmc" />
			</xsl:attribute>
		</input>
		<input name="basqbh" type="hidden" id="basqbh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqbh" />
			</xsl:attribute>
		</input>
		<input name="basqwsh" type="hidden" id="basqwsh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqwsh" />
			</xsl:attribute>
		</input>
		<input name="band" type="hidden" id="band">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/band" />
			</xsl:attribute>
		</input>
		<input name="jmbasxmc" type="hidden" id="jmbasxmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxmc" />
			</xsl:attribute>
		</input>
		<input name="lrrq" type="hidden" id="lrrq">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/lrrq" />
			</xsl:attribute>
		</input>
		<input name="ztdm" type="hidden" id="ztdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztdm" />
			</xsl:attribute>
		</input>
		<input name="ztmc" type="hidden" id="ztmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztmc" />
			</xsl:attribute>
		</input>
		<input name="szdm" type="hidden" id="szdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szdm" />
			</xsl:attribute>
		</input>
		<input name="szmc" type="hidden" id="szmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szmc" />
			</xsl:attribute>
		</input>
		<input name="jmbasxdm" type="hidden" id="jmbasxdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxdm" />
			</xsl:attribute>
		</input>
		<input name="bsfsdm" type="hidden" id="bsfsdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsdm" />
			</xsl:attribute>
		</input>
		<input name="bsfsmc" type="hidden" id="bsfsmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsmc" />
			</xsl:attribute>
		</input>
		<input name="xh" type="hidden" id="xh">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/xh" />
			</xsl:attribute>
		</input>

     <table class="table-99" cellspacing="0">
     	
  					<tr>
                  <td  class="2-td2-t-left" >
                      <div align="right">
                          备案申请文书号
                          &#160;
                      </div>
                  </td>
                  <td  class="2-td2-t-center" height="21" colspan="2">
                      <div align="center">
                          <input type="text" name="basqbh1" maxlength="50" size="30" tabindex="-1" disabled="true">
                             		<xsl:attribute name="value">
																		<xsl:value-of select="taxdoc/jmsbajl/basqbh" />
																</xsl:attribute>
													</input>
                      </div>
                  </td>
   					</tr>
   
       
                   
    	 			<tr>
                  <td colspan="2" class="2-td2-left" height="21">
                      <div align="right">
                          免征年度
                          <font color="#FF0000">
                            *
                          </font>  
                      </div>
                  </td>
                  <td class="2-td2-center" height="21">
                      <div align="center">
        									<input type="text" name="mzqsnd" maxlength="50" size="6" tabindex="-1"  readonly="readonly"  class="txtInput"  >
															<xsl:attribute name="value">
																	<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/mzqsnd" />
															</xsl:attribute>
													</input>年
													<input type="text" name="mzzznd" maxlength="50" size="6" tabindex="-1" readonly="true" style="background-color:#efefef;display:none;">
															<xsl:attribute name="value">
																	<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/mzzznd" />
															</xsl:attribute>
													</input>
                			</div>
                  </td>
            </tr>                                  
     </table> 
     <br/>
     <table class="table-99" cellspacing="0">
        <tr>
           <td width="10%" class="2-td2-t-left">
              <div align="right">
                   录入日期
              </div>
           </td>
           <td width="30%" class="2-td2-t-left">
              <div align="left">
                  <input type="text" name="lrrq" size="15" readonly="readonly"  class="txtInput">
										<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/jmsbajl/lrrq" />
										</xsl:attribute>
					 				</input>
              </div>
           </td>
           <td width="10%" class="2-td2-t-left">
           		<div align="right">
                   备案年度
              </div>
           </td>
           <td width="30%" class="2-td2-t-left">
              <div align="left">
                  <input type="text" name="band" size="15" readonly="readonly"  class="txtInput">
										<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/jmsbajl/band" />
										</xsl:attribute>
					 				</input>
              </div>
           </td>
           <td width="10%" class="2-td2-t-left">
               <div align="right">
                     录入人
               </div>
           </td>
           <td width="30%" class="2-td2-t-center">
               <div align="left">
                 <input type="text" name="lrr" size="15" readonly="readonly"  class="txtInput">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/nsrxx/nsrmc" />
									</xsl:attribute>
					 			 </input>
               </div>
           </td>
        </tr>
     </table>           
  	</xsl:template>
</xsl:stylesheet>
                  