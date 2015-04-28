<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx08.web.Basx08Form" %>
<html>  
  <head>
    <title>减免税备案记录</title>
    <link href="../../../css/text.css" rel="stylesheet" type="text/css">
	
	<script language="JavaScript" type="text/javascript"
		src="../../../js/My97DatePicker/WdatePicker.js"></script>
    <script language=JavaScript src="../../../js/treatImage.js">
    </script>
    <script language=JavaScript src="../../../js/compute.js">
    </script>
    <script language=JavaScript src="../../../js/function.js">
    </script>
    <script language=JavaScript src="../../../js/smsb_common.js">
    </script>
    <script language=JavaScript src="../../../js/Bolan.js">
    </script>
    <script language=JavaScript src="../../../js/MathString.js">
    </script>
    <script language=JavaScript src="../../../js/Stack.js">
    </script>
    <script language=JavaScript src="../../../js/reader.js">
    </script>
    <script language=JavaScript src="../../../js/gmit_selectcontrol.js">
    </script>
    <script language="JavaScript" src="../../../js/qysdsnew.js">
    </script>
    <script language="JavaScript" src="../../../js/calculate.js">
    </script>
  </head>
    <script language="JavaScript">
	function onKeyDown() { // 禁止刷新，回退
	 if ((event.keyCode == 8 && event.srcElement.type ==undefined) || (event.keyCode == 116)) {
	    event.keyCode = 0;
	    event.returnValue = false;
	   }
	}
	document.onkeydown = onKeyDown;
  </script>
  <script type="text/javascript" language="JavaScript">
	<%/*初始化*/%> 
	function doInit(){
	    //初始化资料
		var zl = document.forms[0].zl.value.split(";");
		for(row_index=0;row_index<zl.length;row_index++){
			var new_row=Table1.insertRow(Table1.rows.length);
			zlnr = zl[row_index].substring(0,zl[row_index].indexOf("|"));
			zlsfkysc = zl[row_index].substring(zl[row_index].length-1,zl[row_index].length);
		    addzlmc(row_index,zlnr,new_row);
		    if(zlsfkysc == "1"){
		    	addzlan("1",row_index,new_row);
		    }else{
		    	addzlan("2",row_index,new_row);
		    }
		    
		}
		var zfsm="<bean:write name="basx08Form" property="zfsm"/>";
		if(zfsm!=null && zfsm.length>0){
			document.getElementById("zfsmDiv").style.display ="block";
		}
	}
	<%/*添加资料名称*/%>
	function addzlmc(row_index,zlmc,new_row){
	    new_row.setAttribute("id", "row"+row_index);   
	    var new_col=new_row.insertCell(0);
	    if(row_index == 0){
	    	new_col.className="2-td2-t-left-textleft";
	    }else{
	    	new_col.className="2-td2-left-textleft";
	    }
	    new_col.setAttribute("id",row_index);
	    new_col.innerHTML=zlmc;
	}
	<%/*初始资料添加按钮*/%>
	function addzlan(lx,row_index,new_row){
	    var new_col=new_row.insertCell(1);
	    if(row_index == 0){
	    	new_col.className="2-td2-t-center";
	    }else{
	    	new_col.className="2-td2-center";
	    }
	    if(lx == "1"){
	    	new_col.innerHTML="&nbsp\;&nbsp\;有&nbsp\;&nbsp\;";  
	    }else{
	    	new_col.innerHTML="&nbsp\;&nbsp\;无&nbsp\;&nbsp\;";  
	    }
	    
	}
	
	<%/*接受申请*/%>
	function wsdy()
	{
		doSubmitForm('Print');
	}
	
	<%/*返回*/%>
	function back()
	{
		doSubmitForm('Back');
	}
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx08Action.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="zl" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
             查看经认定的高新技术企业减免税备案事项 
            </td>
          </tr>
          <br>
          <tr>
            <td class="1-td2" colspan="7" valign="top">
            <br>
            	<table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                    <tr>
                      <td  class="2-td2-t-left">
                          <strong>备案申请编号</strong>
                      </td>
                      <td  width="22%"class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx08Form" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>计算机代码</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx08Form" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>纳税人名称</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx08Form" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>主管税务所</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx08Form" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>经济类型</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx08Form" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>所属行业</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx08Form" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>联系人</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx08Form" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>联系电话</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx08Form" property="lxdh"/>
                      </td>
                    </tr>
                  </table>
                  
                  
                  </br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">            
              <tr>
                <td class="2-td2-t-left" width="10%">
                  起始时间
                </td>
                <td class="2-td2-t-left" width="15%"> 
                	&nbsp;<bean:write name="basx08Form" property="qsrq"/>
                </td>
                <td class="2-td2-t-left" width="10%">
                  截止时间
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx08Form" property="jzrq"/>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  减免税额
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx08Form" property="bajmse"/>&nbsp;元
                </td>
                <td class="2-td2-t-left" width="10%">
                  减免比例
                </td>
                <td class="2-td2-t-center" width="15%">
                	&nbsp;<bean:write name="basx08Form" property="bajmbl"/>&nbsp;%
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">
                  减免税政策
                  <br/>
                  执行情况：
                </td>
                <td class="2-td2-center" colspan="7">
                	<div align="left">
                		&nbsp;&nbsp;&nbsp;
                		<bean:write name="basx08Form" property="jmszczxqk"/>
                	</div>
                  <td>
              </tr>
              <tr>
                <td class="2-td2-center" colspan="8">
                	<br/>
                  <div align="left">
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                    减免税备案资料清单：
                  </div>
                  
                  <table width="95%" cellspacing=0 border=0 id="Table1">                   
                  </table>
                  </br>
                  <td>
              </tr>
            </table>
                  
            	  </br>
            		<table class="table-99" cellspacing="0">
                    <tr>
                      <td colspan="2" class="2-td2-t-left">
                        <div align="left">
                          高新技术领域
                        </div>
                      </td>
                      <td colspan="1" class="2-td2-t-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                        &nbsp;&nbsp;<bean:write name="basx08Form" property="gxjslymc"/>
                        </div>
                      </td>
                		</tr>
                    <tr>
                      <td width="30%" class="2-td2-left" rowspan="3">
                        <div align="left" >
                          高新技术企业证书
                        </div>
                      </td>
                      <td width="20%" class="2-td2-left">
                        <div align="left">
                          证书编号
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                        &nbsp;&nbsp;<bean:write name="basx08Form" property="zsbh"/>
                        </div>
                      </td>
                    </tr>
                      <tr>
                      <td width="20%" class="2-td2-left">
                        <div align="left">
                          证书有效期起
                                                  
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        &nbsp;&nbsp;<bean:write name="basx08Form" property="zsyxqq"/>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" class="2-td2-left">
                        <div align="left">
                          证书有效期止
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        &nbsp;&nbsp;<bean:write name="basx08Form" property="zsyxqz"/>
                        </div>
                      </td>
                    </tr>
          <tr>
            <td colspan="2" class="2-td2-left">
              <div align="left">
                企业具有大学专科以上学历的科技人员占企业当年职工总数的比例
                             
            </td>
            <td colspan="1" class="2-td2-center">
              <div id="mx_zsfsdm_epx_visible_1" align="left">
              &nbsp;&nbsp;<bean:write name="basx08Form" property="zkysbl"/>&nbsp;%
                
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="2-td2-center">
              <div align="left">
                研发人员占企业当年职工总数的比例
              </div>
            </td>
            <td colspan="1" class="2-td2-center">
              <div id="mx_zsfsdm_epx_visible_1" align="left">
              &nbsp;&nbsp;<bean:write name="basx08Form" property="yfrybl"/>
                %
              </div>
            </td>
          </tr>
       
          <tr>
            <td colspan="2" class="2-td2-left">
              <div align="left">
                企业近三个会计年度的研究开发费用总额占销售收入总额的比例
              </div>
            </td>
            <td colspan="1" class="2-td2-center">
              <div id="mx_zsfsdm_epx_visible_1" align="left">
              &nbsp;&nbsp;<bean:write name="basx08Form" property="yffybl"/>
                %
            </td>
          </tr>
          <tr>
            <td colspan="2" class="2-td2-left">
              <div align="left">
                企业当年高新技术产品（服务）收入占企业总收入的比例
              </div>
            </td>
            <td colspan="1" class="2-td2-center">
              <div id="mx_zsfsdm_epx_visible_1" align="left">
              &nbsp;&nbsp;<bean:write name="basx08Form" property="gxcpsrbl"/>
                %
            </td>
          </tr>
          </table>
                 <br/>
                 <div id="zfsmDiv" style="display: none">        
                 <table class="table-99" cellspacing="0">
                    <tr>
                      <td width="20%" class="2-td2-t-left">
                        <div align="center">
                          	作废原因
                        </div>
                      </td>
                      <td width="80%" class="2-td2-t-center">
                        <div align="left">
                        	<bean:write name="basx08Form" property="zfsm"/>
                        </div>
                      </td>
                    </tr>
                  </table>
                  <br/>
                  </div>
                 <table class="table-99" cellspacing="0">
                    <tr>
                      <td width="13%" class="2-td2-t-left">
                        <div align="right">
                          录入日期
                        </div>
                      </td>
                      <td width="27%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_lrrq" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          	备案年度
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_band" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          录入人
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-center">
                        <div align="left">
                        	<html:text property="mr_lrr" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                    </tr>
                  </table>
                  <br/>
                  <table width="100%" border="0" align="center">
                    <tr align="center">      
	                    <td>
	      				  	<a style="cursor:hand" onClick="wsdy()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('dayinws','','<%=static_contextpath%>images//b_scdyws2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b_scdyws1.jpg" name="dayinws" width="95" height="22" border="0" id="dayinws">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        <a style="cursor:hand" onClick="back()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images//fanhui2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//fanhui1.jpg" name="fanhui" width="79" height="22" border="0" id="fanhui">
	                        </a>
	                    </td>
                    </tr>
                  </table> 
                  <br>
                  
                  
                  <br>
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>