<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>复核及收款</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="query" ){
    document.forms[0].operationType.value='Query';
    fnSetZtbs();
  }else if(operationType=="quit"){
    document.forms[0].operationType.value='Return';
  }
  document.forms[0].submit();
}

function fnSetZtbs()
{
	if (document.forms[0].fztbs.value == "4") //未复核
	{
	   if (document.forms[0].jmsbs.value == "0") //正常无减免
	   {
		  document.forms[0].ztbs.value = "1";
	   }
	   else if (document.forms[0].jmsbs.value == "1") //减免
	   {
        document.forms[0].ztbs.value = document.forms[0].fztbs.value;
	   }
	   else
	   {
         document.forms[0].ztbs.value = "";
     }
	}
	else
	{
     document.forms[0].ztbs.value = document.forms[0].fztbs.value;
	}

}

</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" >
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="审核&gt;查询申报数据信息"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<SCRIPT language=javascript>
<!--



//-->
</SCRIPT>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>查询申报数据信息</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/sh/querySbxx2.do">
            <html:hidden property="fromPage" value="3"/>
            <input type = "hidden" name = "operationType" value = "" >
            <logic:equal name="querySbxxForm" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="querySbxxForm" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="querySbxxForm" property="queryCache.pageCount"/>'>
            </logic:equal>
            <br>
             <TABLE border="0" cellSpacing=0 class=table-89>
              <TBODY>

<tr>
                <td class="2-td2-t-left" nowrap="nowrap">批次号</td>
                <td class="2-td2-t-left">
                    <div align=left>
						<html:text property="pch" size="30" maxlength="30"/>
					</div>
                </td>
                <!--增加建委业务编号、合同编号字段-->
                 <td class="2-td2-t-left" nowrap="nowrap">建委业务编号</td>
                <td  class="2-td2-t-left">
                    <div align=left>
						<html:text property="jwywbh" size="30" maxlength="30"/>
					</div>
                </td>
                 <td class="2-td2-t-left" nowrap="nowrap">合同编号</td>
                <td  class="2-td2-t-center">
                    <div align=left>
						<html:text property="htbh" size="30" maxlength="30"/>
					</div>
                </td>
</tr>
               <tr>
                <td class="2-td2-left" nowrap="nowrap">纳税申报表号</td>
                <td class="2-td2-left">
                    <div align=left>
						<html:text property="sbbh" size="16"/>
					</div>
                </td>
                <td class="2-td2-left" nowrap="nowrap">纳税人类型</td>
                <td class="2-td2-left">
					<div align=left>
					  <bean:define id="nsrlxList" name="querySbxxForm" property="nsrlxList" />
                      <html:select property="nsrlx" >
                        <html:option value=""></html:option>
                        <html:option value="99">个人</html:option>
                        <html:options collection="nsrlxList" labelProperty="nsrlxmc" property="nsrlxdm"/>
                      </html:select>
					</div>
				</td>
				<td class="2-td2-left" nowrap="nowrap">计算机代码</td>
                <td class="2-td2-center">
					<div align=left>
						<html:text property="jsjdm" size="16"/>
					</div>
				</td>
              </tr>
              <tr>
                <td class="2-td2-left" nowrap="nowrap">纳税人姓名</td>
                <td  class="2-td2-left">
                     <div align=left>
						<html:text property="nsrmc" size="16"/>
					</div>
                </td>
                <td class="2-td2-left" nowrap="nowrap">身份证件类型</td>
                <td  class="2-td2-left">
                    <div align=left>
					  <bean:define id="zjlxList" name="querySbxxForm" property="sfzjlxList" />
                      <html:select property="zjlxdm" >
                        <html:option value=""></html:option>
                        <html:options collection="zjlxList" labelProperty="zjlxmc" property="zjlxdm"/>
                      </html:select>
					</div>
                </td>
				<td class="2-td2-left" nowrap="nowrap">证件号码</td>
                <td class="2-td2-center">
					<div align=left>
						<html:text property="sfzjhm" size="16"/>
					</div>
				</td>
              </tr>
              <tr>
                <td class="2-td2-left" nowrap="nowrap">申报类别</td>
                <td  class="2-td2-left">
                    <div align=left>
                      <html:select property="jmsbs">
                        <html:option value=""></html:option>
                        <html:option value="1">即时减免情况</html:option>
                        <html:option value="0">无减免情况</html:option>
                      </html:select>
					</div>
                </td>
                <td class="2-td2-left" nowrap="nowrap">申报状态</td>
                <td  class="2-td2-left">
                    <div align=left>
                      <html:select property="fztbs" >
                        <html:option value=""></html:option>
                        <html:option value="4">未复核</html:option>
                        <html:option value="7">已复核未收款</html:option>
                      </html:select>
					</div>
					<html:hidden property="ztbs"/>
                </td>
                <td class="2-td2-left" nowrap="nowrap">申报日期</td>
                <td  class="2-td2-center" >
                    <div align=left>
						<html:text property="sbrq" size="16" maxlength="10"/>
					</div>
                </td>
              </tr>
     </TABLE><BR>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('query');"
          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>

 <logic:equal name="querySbxxForm" property="status" scope="session" value="Result">
	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">数据申报信息查询结果</div></td>
		<td><hr class="hr1"></td>
        <!--如果结果集为空，提示没有记录-->
           <logic:equal name="querySbxxForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
           </logic:equal>
           <logic:notEqual name="querySbxxForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               共<bean:write name="querySbxxForm" property="queryCache.countNumber"/>条记录
               第<bean:write name="querySbxxForm" property="queryCache.currentPageNum"/>/
               <bean:write name="querySbxxForm" property="queryCache.pageCount"/>页
                  <input type="image" name="beginpage" value="第一页" alt="第一页"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="最后一页" alt="最后一页"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--如果只有一页，下面按钮不显示-->
                <logic:equal name="querySbxxForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--如果是第一页，前页不显示-->
                  <logic:equal name="querySbxxForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="前页" alt="前页"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--如果是最后一页，后页不显示-->
                  <logic:equal name="querySbxxForm" property="queryCache.hasNext" scope="session" value="true">
                    <input type="image" name="backpage" value="后页" alt="后页"
                    onclick = "javascript:return FN_QSChangePage('xiayiye');"
                    onMouseOver="MM_swapImage('xiayiye','','<%=static_file%>images/xaiyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/xaiyiye1.jpg" id="xiayiye">
                  </logic:equal>
                </logic:equal>
                </div></td>
             </logic:notEqual>
            </tr>
          </table>
        </logic:equal>
          <!--如果记录结果为空，不显示-->
          <logic:notEqual name="querySbxxForm" property="queryCache.pageCount" scope="session" value="0">
          <br>


     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
       	<TD class="2-td1-left" width = "8%" >申报表号</TD>
        <TD class="2-td1-left" width = "18%" >纳税人名称</TD>
        <TD class="2-td1-left" width = "15%" >计算机代码</TD>
        <TD class="2-td1-left" width = "%18" >房地产项目名称</TD>
        <TD class="2-td1-left" width = "20%" >土地、房屋座落地址</TD>
        <TD class="2-td1-center" width = "15%" >成交价格</TD>
      </TR>
      <logic:iterate id="data" indexId="index" length="length" name="querySbxxForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.JsblBo">
            <tr>
              <td class="2-td2-left">
                  <a href="<%=response.encodeURL("/qsgl/sh/querySbxx2.do?operationType=ShowDetail&viewIndex=") + index.intValue()%>">
                  <bean:write name="data" property="sbbh"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data" property="nsrmc"/></a>
              </td>
              <td class="2-td2-left">
                  <bean:write name="data" property="jsjdm"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data" property="fdcxmmc"/>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data" property="tdfwzldz"/>
              </td>
              <td class="2-td2-center">
                  <%=DataConvert.BigDecimal2String(data.getCjjgrmb())%>
              </td>
            </tr>
      </logic:iterate>
          </table>
          <br>
       <table cellspacing="0" class="table-99">
          <tr>
            <td align="right"><br>
                <input type="image" name="beginpage" value="第一页" alt="第一页"
                onclick = "javascript:return FN_QSChangePage('diyiye');"
                onMouseOver="MM_swapImage('diyiye1','','<%=static_file%>images/diyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg"
                width="79" height="22" id="diyiye1">
                <input type="image" name="endpage" value="最后一页" alt="最后一页"
                onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                onMouseOver="MM_swapImage('zuimoye1','','<%=static_file%>images/zuimoye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg"
                width="79" height="22" id="zuimoye1" >
                <!--如果只有一页，下面按钮不显示-->
               <logic:equal name="querySbxxForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--如果是第一页，前页不显示-->
                  <logic:equal name="querySbxxForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="前页" alt="前页"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--如果是最后一页，后页不显示-->
                  <logic:equal name="querySbxxForm" property="queryCache.hasNext" scope="session" value="true">
                   <input type="image" name="backpage" value="后页" alt="后页"
                   onclick = "javascript:return FN_QSChangePage('xiayiye');"
                   onMouseOver="MM_swapImage('xiayiye1','','<%=static_file%>images/xaiyiye2.jpg',1)"
                   onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/xaiyiye1.jpg"
                   width="79" height="22" id="xiayiye1">
                 </logic:equal>
                </logic:equal>
            </td>
          </tr>
        </table>
        </logic:notEqual>
      </div>


</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
