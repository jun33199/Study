<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.web.QyqssdsBaglForm"%>
<html>  
  <head>
    <title>企业清算所得税备案记录</title>
    <link href="../../../css/text.css" rel="stylesheet" type="text/css">

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
  	var page;
	var total;	
	var alert_str;
  	<%
  		QyqssdsBaglForm form = (QyqssdsBaglForm) request.getAttribute("qyqssdsBaglForm");

  		String sqlx=form.getFilter_sqlx();
  		String sqzt=form.getFilter_sqzt();
  		String zgswjg = form.getFilter_zgswjg();
  		
	
  	
		// 构造申请类型代码
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQLX) != null)
	    {
	        out.print("var arySelect_sqlx = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] jmsbasqlx = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQLX);
	        for(int i = 0 ; i < jmsbasqlx.length ; i++) 
	        {
	         	out.print(",[\"" + jmsbasqlx[i][0] + "\",\"" +jmsbasqlx[i][1] +"\"]");
	        }
	        out.println(");");
	    }
		
		// 构造主管税务机关代码
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSZGSWJG) != null)
	    {
	        out.print("var arySelect_zgswjg = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] jmszgswjg = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSZGSWJG);
	        for(int i = 0 ; i < jmszgswjg.length ; i++) 
	        {
	        	
	         	out.print(",[\"" + jmszgswjg[i][0] + "\",\"" +jmszgswjg[i][1] +"\"]");
	        }
	        out.println(");");
	    }
	 
	 
	%>
  
	
	//初始化
	function doInit(){
		
		_addOptionsToSelect(document.forms[0].zgswjgSelect, arySelect_zgswjg);
		_addOptionsToSelect(document.forms[0].sqlxSelect, arySelect_sqlx);

		<%
		if(null != zgswjg && !"".equals(zgswjg))
		{
		%>
				_selectOptionByValue(document.forms[0].zgswjgSelect, "<%=zgswjg%>");
		<%
		}
		if(null != sqlx && !"".equals(sqlx))
		{
		%>
				_selectOptionByValue(document.forms[0].sqlxSelect, "<%=sqlx%>");
		<%
		}
		%>
		var sqzt=  document.forms[0].filter_sqzt.value ;
		var itemCheck = document.getElementsByName("itemCheck");
	   	var itemCount = itemCheck.length;
	     for (i=0; i < itemCount; i++ ){
	       if(sqzt.indexOf(itemCheck[i].value)>0){
	          itemCheck[i].checked=true;
	       }
	     }
	     page=document.forms[0].currentPage.value;
	     total=<%=request.getAttribute("TOTAL_PAGE")%>;
	}
	
	//申请状态全选
	function selectAllSqzt(){
		
		var selectAll = document.getElementById("selectAll");
		var itemCheck = document.getElementsByName("itemCheck"); 	
		var itemCount = itemCheck.length;	
		 for (i=0; i < itemCount; i++ ){
	       itemCheck[i].checked=selectAll.checked;
	     }
	}
	
	//翻页
	function turnThePage(action){	
		var currentPage=document.forms[0].currentPage.value;
		currentPage=parseInt(currentPage);
		switch(action){
			case 'first': page=1;	break;
			case 'prior': page--;	break;
			case 'next':  page++;	break;
			case 'last':  page=total;	break;	
			case 'jump':  
				if(currentPage>=1 && currentPage<=total){
					page=currentPage;	
				}else{
					alert('输入页数超出范围');
					return;
				} 
				break;
			case 'rows':  page=1;break;						
			default: return; break;					
		}		
		if(page<1){
			page=1;
			alert("已到达首页");
			return;
		}
		if(page>total){
			page=total;
			alert("已到达末页");
			return;
		}
		document.forms[0].currentPage.value=page;
		document.forms[0].actionType.value = "Query";
	    document.forms[0].submit();
	} 
	//页面元素效验
	function ymxy(){
		var jsjdm = document.forms[0].add_jsjdm.value;
		if(jsjdm == ""){
			alert("计算机代码不能为空！");
			document.forms[0].add_jsjdm.focus();
			return false;
		}
		
		var band = document.forms[0].add_band.value;
		if(band == ""){
			alert("备案年度不能为空！");
			document.forms[0].add_band.focus();
			return false;
		}else{
			//判断是否全为数字
			if(!isFullDate(band,1,1,true)){
				document.forms[0].add_band.focus();
				return false;
			}else{
				//判断备案年度只能是2009年至系统年度之前一年
				var today=new Date();
				var todayy=today.getYear()-1; 
				if(band>todayy){
					alert("备案年度不能大于"+todayy);
					document.forms[0].add_band.focus();
					return false;
				}
			}
			
		}
		
	
		return true;
	}
	
	//新增 zhangj
	function doAdd(){
	
		if(trim(document.forms[0].filter_jsjdm.value) == ""){
		alert("请输入纳税企业计算机代码！");
		document.forms[0].jsjdm.focus();
		return false;
		}
		doSubmitForm('Add');
		return false;
	}
	//删除 zhangj
	function doDelete(jsjdm){
		if(window.confirm("此操作将永久删除该条企业清算所得税备案记录，且不可恢复!\n是否删除该条企业清算所得税备案记录？")){
	
		var itemCheck = document.getElementsByName("itemCheck");
	   	var itemCount = itemCheck.length;
	   	var checkedStr='';
	    for (i=0; i < itemCount; i++ ){
	       if(itemCheck[i].checked){
	          checkedStr+="'"+itemCheck[i].value+"',";
	       }
	    }
	    checkedStr=checkedStr.substring(0,checkedStr.length-1);	     
	    document.forms[0].filter_sqzt.value =checkedStr;
		document.forms[0].jsjdm.value = jsjdm;
		//document.forms[0].czlx.value = czlx;
		document.forms[0].actionType.value = "Delete";
	    document.forms[0].submit();
	    }
	}

	//查询
	function doQuery(){
		
		var itemCheck = document.getElementsByName("itemCheck");
	   	var itemCount = itemCheck.length;
	   	var checkedStr='';
	    for (i=0; i < itemCount; i++ ){
	       if(itemCheck[i].checked){
	          checkedStr+="'"+itemCheck[i].value+"',";
	       }
	    }
	    checkedStr=checkedStr.substring(0,checkedStr.length-1);	     
	    document.forms[0].filter_sqzt.value =checkedStr;
		document.forms[0].actionType.value = "Query";
	    document.forms[0].submit();
	}
	//查看 zhangj
	function doView(jsjdm,czlx)
	{
		var itemCheck = document.getElementsByName("itemCheck");
	   	var itemCount = itemCheck.length;
	   	var checkedStr='';
	    for (i=0; i < itemCount; i++ ){
	       if(itemCheck[i].checked){
	          checkedStr+="'"+itemCheck[i].value+"',";
	       }
	    }
	    checkedStr=checkedStr.substring(0,checkedStr.length-1);	     
	    document.forms[0].filter_sqzt.value =checkedStr;
		document.forms[0].jsjdm.value = jsjdm;
		document.forms[0].czlx.value = czlx;
		document.forms[0].actionType.value = "View";
	    document.forms[0].submit();
	
	}
	//修改
	function doOperate(jsjdm,czlx)
	{
		var itemCheck = document.getElementsByName("itemCheck");
	   	var itemCount = itemCheck.length;
	   	var checkedStr='';
	    for (i=0; i < itemCount; i++ ){
	       if(itemCheck[i].checked){
	          checkedStr+="'"+itemCheck[i].value+"',";
	       }
	    }
	    checkedStr=checkedStr.substring(0,checkedStr.length-1);	     
	    document.forms[0].filter_sqzt.value =checkedStr;
		document.forms[0].jsjdm.value = jsjdm;
		document.forms[0].czlx.value = czlx;
		document.forms[0].actionType.value = "Operate";
	    document.forms[0].submit();
	
	}
	//删除
	/*
	function doDelete(basqwsh,jmbasxdm){
		if(window.confirm("此操作将永久删除该条减免税备案记录，且不可恢复!\n是否删除该条减免税备案记录？")){
			
				document.forms[0].basqwsh.value = basqwsh;
				document.forms[0].jmbasxdm.value = jmbasxdm;
				document.forms[0].actionType.value = "Delete";
			    document.forms[0].submit();
	    	
	    }
	}*/
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %>  
	  
      <html:form action="/webapp/smsb/qyqssds/2014/qyqssdsBaglAction2014.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="jsjdm" />
        <html:hidden property="czlx" />
        <html:hidden property="filter_sqzt" />
        <table width="980" border="0" cellpadding="0" cellspacing="0" align="center">
        	<tr>
          		<td valign="top" class="title">           
           			<table width="90%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                  		<tr>
                  			<td class="1-td1">
                  			查询纳税单位企业清算所得税备案申请记录
                  			</td>
						</tr>                  			
                  		<tr>
            						<td class="1-td2">
            							<br>
              							<table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                							<tr>
                  							<td class="2-td2-t-left" width="8%">
          												计算机代码
        												</td>
        												<td class="2-td2-t-left" width="22%">
          												<div align="left">
            													&nbsp;<html:text property="filter_jsjdm" size="20"  maxlength="8"/>
          												</div>
        												</td>
        												<td class="2-td2-t-left" width="11%">
          												纳税人名称
        												</td>
        												<td class="2-td2-t-left" width="23%">
          												<div align="left">
             													&nbsp;<html:text property="filter_nsrmc" size="20"/>
          												</div>
        												</td>
        												<td width="15%" class="2-td2-t-left" width="11%">
          												清算备案年度
        												</td>
        												<td width="35%" class="2-td2-t-center" width="22%">
           												<div align="left">
            													&nbsp;<html:text property="filter_band" size="10" maxlength="4"/>
          												</div>
        												</td>
      											</tr>
      											<tr>
        												<td class="2-td2-left">
          												申请类型
        												</td>
        												<td class="2-td2-left">
	        												<div align="left">
	        													&nbsp;<html:select name="qyqssdsBaglForm" styleId="sqlxSelect" property="filter_sqlx" />
	  															</div>
        												</td>
        												<td class="2-td2-left">
          												申请状态
        												</td>
        												<td class="2-td2-center" colspan="3">
								                	<div align="left">
								                		&nbsp;<input type="checkbox" id="selectAll" value="" onclick="selectAllSqzt()">全选
								                		&nbsp;<input type="checkbox" name="itemCheck" value="1">提交未审核
								                		&nbsp;<input type="checkbox" name="itemCheck" value="2">审核已通过
								                		&nbsp;<input type="checkbox" name="itemCheck" value="3">审核被驳回	
												    </div>
												        </td>
												     </tr>
               							 <tr>
        											<td width="15%" class="2-td2-left" width="11%">
              											主管税务机关
          											</td>
          											<td class="2-td2-left" width="22%">
            											<div align="left">
             												&nbsp;<html:select name="qyqssdsBaglForm" styleId="zgswjgSelect" property="filter_zgswjg" />
            											</div>
          											</td>
      												<td class="2-td2-left">
        												&nbsp;
      												</td>
      												<td colspan="3" class="2-td2-center">
        												&nbsp;
      												</td>
                						</tr>              
      										<tr>
      											
      										<td colspan="6" class="2-td2-center" height="34">
					                 		<a style="cursor:hand" onClick="doAdd()" onMouseOut="MM_swapImgRestore()"	 onMouseOver="MM_swapImage('xinzeng','','<%=static_contextpath%>images//xinzeng2.jpg',1)">
					                        	<img src="<%=static_contextpath%>images//xinzeng1.jpg" name="xinzeng" width="79" height="22" border="0" id="xinzeng">
					                        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                        <a style="cursor:hand" onClick="doQuery()" onMouseOut="MM_swapImgRestore()"	 onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images//chaxun2.jpg',1)">
					                        	<img src="<%=static_contextpath%>images//chaxun1.jpg" name="chaxun" width="79" height="22" border="0" id="chaxun">
					                        </a>
					                      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<img onclick="tuichu()"  onMouseOver="MM_swapImage('tc','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
					                  		  onMouseOut="MM_swapImgRestore()" value="退出" id="tc" src="<%=static_contextpath%>images/tc-c1.jpg" 
					                  		  width="79" height="22" style="cursor:hand"/> 
					  

				                  		</td>                 
      											</tr>
              						</table>
              						<br>
													<div id="gdjl" style="position:static; overflow: auto; height: 300px;">
												    	<%=request.getSession(false).getAttribute("QUERY_HTML")%>									
													</div>	
		  										<br>
													<table cellspacing="0" class="table-99" >
									          <tr>		          	
									            <td align="center">									                 
					                 			<a style="cursor:hand" onClick="turnThePage('first')" onMouseOut="MM_swapImgRestore()"
			                          onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images//diyiye2.jpg',1)">
			                          <img src="<%=static_contextpath%>images//diyiye1.jpg" name="diyiye" width="79" height="22" border="0" id="diyiye">
			                          </a>
			                         <a style="cursor:hand" onClick="turnThePage('prior')" onMouseOut="MM_swapImgRestore()"
			                          onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images//shangyiye2.jpg',1)">
			                          <img src="<%=static_contextpath%>images//shangyiye1.jpg" name="shangyiye" width="79" height="22" border="0" id="shangyiye">
			                          </a>
			                         <a style="cursor:hand" onClick="turnThePage('next')" onMouseOut="MM_swapImgRestore()"
			                          onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images//xaiyiye2.jpg',1)">
			                          <img src="<%=static_contextpath%>images//xaiyiye1.jpg" name="xiayiye" width="79" height="22" border="0" id="xiayiye">
			                          </a>
			                         <a style="cursor:hand" onClick="turnThePage('last')" onMouseOut="MM_swapImgRestore()"
			                          onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images//zuimoye2.jpg',1)">
			                          <img src="<%=static_contextpath%>images//zuimoye1.jpg" name="zuimoye" width="79" height="22" border="0" id="zuimoye">
			                        	</a>
																<html:text property="currentPage" size="3" maxlength="5"  onclick="this.select()"  onkeydown="if (window.event.keyCode==13) {turnThePage('jump');}"/>                          
					                			<span id="pageInfo" class="black9">/<%=request.getAttribute("TOTAL_PAGE")%>页&nbsp;共<%=request.getAttribute("ROWS_COUNT")%>条记录</span> 
						            				&nbsp;
					               				<span class="black9">每页显示</span>
					               				<html:text property="rowsPerPage" size="3" maxlength="5"  onclick="this.select()"  onkeydown="if (window.event.keyCode==13) {turnThePage('rows');}"/>									             
					               				<span class="black9">条记录</span>
															</td> 
									          </tr>
										       </table>	
										      </td>
										     </tr>
												</table>		
 											</td>
										 </tr>
									</table>																						     
            									
										   									
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>