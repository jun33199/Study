<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>�����еط�˰����걨�ɿ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="JavaScript" src="../js/MathString.js"></script>

<script language="JavaScript">
var tableArray =new Array();
function fnReturn()
{
	location.href="PG3_SBZS_000.htm"
}

</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <%@ include file="./include/header.jsp"%>
<!-- ������˰ -->
<html:form action="/webapp/smsb/zhsbPgbsjksypdsAction.do" method="POST" >
<html:hidden property="actionType" value="Query"/>
<html:hidden property="forward" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr> 
             <!-- ������˰ -->     
            <td class="1-td1"  colspan="7">�� �� �� �� �� �� �� ˰ �� �� �� ת �� ר �� �� ˰ ƾ 
              ֤<ttsoft:write name="zhsbPgbsjksypdsActionForm" property="jkpzh"/>��</td>
                </tr>
                <tr> 
                  <td class="1-td2"  colspan="7" valign="top"> <br>
					<table cellpadding="2" border="0" cellspacing="2" width="100%">
                      <tr> 
					  	<td width="3%">&nbsp;</td>
						<!-- ������˰ -->
						<td width="77%"><div align="left" class="black9">����ڣ�<ttsoft:write name="zhsbPgbsjksypdsActionForm" property="lrrq"/>&nbsp;</div></td>
						<td width="20%">&nbsp;</td>
					  </tr>
					</table>
					
              <table class="table-99" cellspacing="0">
                <tr> 
                  <td width="12%" class="2-td2-t-left"> <strong>˰��ǼǴ���</strong> 
                  </td>
                  <!-- ������˰ -->
                  <td width="37%" class="2-td2-t-left"><ttsoft:write name="zhsbPgbsjksypdsActionForm" property="jsjdm"/>&nbsp;</td>
                  <td width="10%" class="2-td2-t-left"><strong>���ջ���</strong></td>
                  <td width="41%" class="2-td2-t-center"><ttsoft:write name="zhsbPgbsjksypdsActionForm" property="swjgzzjgmc"/>&nbsp;</td>
                </tr>
                <tr>
                  <td class="2-td2-left"><strong>��˰��ȫ��</strong></td>
                  <!-- ������˰ -->
                  <td class="2-td2-left"><ttsoft:write name="zhsbPgbsjksypdsActionForm" property="nsrmc"/><html:hidden property="nsrmc"/>&nbsp;</td>
                  <td class="2-td2-left"><strong>�տ�����(�ʾ�)</strong></td>
                  <td class="2-td2-center"><ttsoft:write name="zhsbPgbsjksypdsActionForm" property="yhmc"/>&nbsp;</td>
                </tr>
              </table>
					<br>
             <!-- ������˰ -->
             <bean:define id="gzlist" name="zhsbPgbsjksypdsActionForm" property="dataList"/>
              <logic:iterate id="items" name="gzlist" indexId="index">
							 <bean:define id="item" name="items"/>
							  <bean:define id="formlist" name="item" property="dataList"/>
                  <br>
                <table class="table_99" cellspacing="0" width="98%" id='d<ttsoft:write name="item" property="jkpzh"/>' onkeydown='return dokeydown(this.id,"d<ttsoft:write name="item" property="jkpzh"/>");'  onmouseup='return dokeyUp(this.id)'>
                <tr> 
								 <input type="hidden" name='d<ttsoft:write name="item" property="jkpzh"/>_focus'>
                  <td nowrap class="2-td1-left">˰��</td>
                  <td nowrap class="2-td1-left"><b><ttsoft:write name="item" property="szmc"/>&nbsp
                    </b></td>
                  <td class="2-td1-left"> <strong>˰����������</strong> </td>
                  <td colspan="2" class="2-td1-center"><div align="left">�� 
                     <ttsoft:write name="item" property="skssksrq"/>
                      �� 
                     <ttsoft:write name="item" property="skssjsrq"/>
                    </div></td>
                </tr>
                <tr> 
                  <td width="16%" nowrap class="2-td1-Bleft">��˰��Ŀ����</td>
                  <td width="32%" nowrap class="2-td1-Bleft">��˰��Ŀ����</td>
                  <td width="17%" nowrap class="2-td1-Bleft">��˰����</td>
                  <td width="19%" nowrap class="2-td1-Bleft">��˰���</td>
                  <td width="16%" nowrap class="2-td1-Bcenter">ʵ�ʽ�˰��</td>
                </tr>
								<logic:iterate id="mxitems" name="formlist" >
							  <bean:define id="mxitem" name="mxitems"/>
								<tr id='tr_d<ttsoft:write name="item" property="jkpzh"/>'>
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="szsmdm" value='<ttsoft:write name="mxitem" property="szsmdm"/>'  size="15" onKeyUp=setday("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) onKeyDown=doEnterDown("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this)  onblur=resetRow("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) onfocus=setday("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this)>
								</td>
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="szsmmc" value='<ttsoft:write name="mxitem" property="szsmmc"/>'  class="inputnoborder "  size="30"  readOnly tabIndex="-1" >
								</td>
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="kssl" value='<ttsoft:write name="mxitem" property="kssl"/>'  size="15" onkeydown="isNum(this,0,null,null,null,0)" onchange="isNum(this,0,null,null,null,0)" class="inputalignright"></td>
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="jsje" value='<ttsoft:write name="mxitem" property="jsje"/>'  size="15" onchange="isNum(this,0)" class="inputalignright">
								</td>
								<td nowrap class="2-td2-center" align="center">
								<input type="textfield" name="sjse" value='<ttsoft:write name="mxitem" property="sjse"/>'  size="15" onkeydown="isNum(this,0)" onchange="if(isNum(this,0)){hj();}" class="inputalignright">
								</td>
								<input type="hidden" name="jkpzh" value='<ttsoft:write name="mxitem" property="jkpzh"/>'> 
								
								<input type="hidden" name="jsjdm" value='<ttsoft:write name="mxitem" property="jsjdm"/>'>
								<input type="hidden" name="yskmdm" value='<ttsoft:write name="mxitem" property="yskmdm"/>'>
								<input type="hidden" name="ysjcdm" value='<ttsoft:write name="mxitem" property="ysjcdm"/>'>
								<input type="hidden" name="skssksrq" value='<ttsoft:write name="mxitem" property="skssksrq"/>'>
								<input type="hidden" name="skssjsrq" value='<ttsoft:write name="mxitem" property="skssjsrq"/>'>
								<input type="hidden" name="rkje" value='<ttsoft:write name="mxitem" property="rkje"/>'>
								<input type="hidden" name="sbbh" value='<ttsoft:write name="mxitem" property="sbbh"/>'>
								<input type="hidden" name="sjfc" value='<ttsoft:write name="mxitem" property="sjfc"/>'>
								<input type="hidden" name="qjfc" value='<ttsoft:write name="mxitem" property="qjfc"/>'>
								<input type="hidden" name="szsmdm_old" value='<ttsoft:write name="mxitem" property="szsmdm"/>'>						
								<input type="hidden" name="nd" value='<ttsoft:write name="mxitem" property="nd"/>'>
								
								</tr>

	              </logic:iterate>
                <DIV id=divSfTemp></DIV>

                <tr> 
                  <td nowrap class="2-td2-left" height="29"> <strong>�ϼ�</strong></td>
                  <td nowrap class="2-td2-left">&nbsp;</td>
                  <td nowrap class="2-td2-left" height="29">&nbsp;</td>
                  <td nowrap class="2-td2-left" height="29">&nbsp;</td>
                  <td nowrap class="2-td2-center" height="29"><input type="text" name="xthj" value='<ttsoft:write name="item" property="sjje"/>' class="inputnoborder" readonly size ="15"></td>
                </tr>
                <tr> 
                  <td nowrap class="2-td2-left" height="29"><strong>���ϼ�(��д)</strong></td>
                  <td height="29" colspan="4" nowrap class="2-td2-center" id="xthjdx">ϵͳ����</td>
                </tr>
                <tr> 
                  <td nowrap  colspan="5" class="2-td2-center">
												<table border="0" width="100%">
													<tr>
													<td width="47%"  nowrap ><div align="right"><img onClick='addRow("d<ttsoft:write name="item" property="jkpzh"/>")'  onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/tianjia2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="zj1" src="<%=static_contextpath%>images/tianjia1.jpg" width="79" height="22"></div></td>
													<td width="26%" nowrap ><div align="center"><img onClick='deleteRow("d<ttsoft:write name="item" property="jkpzh"/>",null,"d<ttsoft:write name="item" property="jkpzh"/>")'  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ɾ��" id="sc2" src="<%=static_contextpath%>images/shanchu1.jpg" width="79" height="22"></div></td>
													<td width="24%"  nowrap >&nbsp;</td>
													<td width="3%"  nowrap >&nbsp;</td>
													</tr>
												</table>
									  </td>
                </tr>
              </table>
							<div id=d<ttsoft:write name="item" property="jkpzh"/>_epodDateLayer style="position: absolute; width: 20; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>") onkeyup=selectMove("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>")  onfocusout=selectClick("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>") onkeydown=if(window.event.keyCode==13)selectClick("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>")></select></div>	
							<script language="javascript"> 

							var jsArr_d<ttsoft:write name="item" property="jkpzh"/>='<tr id=tr_d<ttsoft:write name="item" property="jkpzh"/>><td nowrap class="2-td2-left" align="center"><input type="textfield" name="szsmdm" value=""  size="15" onKeyUp=setday("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) onKeyDown=doEnterDown("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this)  onblur=resetRow("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) onfocus=setday("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) ></td><td nowrap class="2-td2-left" align="center"><input type="textfield" name="szsmmc" value="" class="inputnoborder "  size="30"  readOnly tabIndex="-1" ></td><td nowrap class="2-td2-left" align="center"><input type="textfield" name="kssl" value=""  size="15" class="inputalignright " onkeydown="isNum(this,0,null,null,null,0)" onchange="isNum(this,0,null,null,null,0)"></td><td nowrap class="2-td2-left" align="center">	<input type="textfield" name="jsje" value=""  size="15" class="inputalignright "  onkeydown="isNum(this,0)" onchange="isNum(this,0)"></td><td nowrap class="2-td2-center" align="center" ><input type="textfield" name="sjse" value="" size="15" class="inputalignright " onkeydown="isNum(this,0)" onchange="if(isNum(this,0)){hj();}"></td><input type="hidden" name="jkpzh" value=<ttsoft:write name="mxitem" property="jkpzh"/>><input type="hidden" name="jsjdm" value=<ttsoft:write name="mxitem" property="jsjdm"/>><input type="hidden" name="yskmdm" value=<ttsoft:write name="mxitem" property="yskmdm"/>><input type="hidden" name="ysjcdm" value=<ttsoft:write name="mxitem" property="ysjcdm"/>><input type="hidden" name="skssksrq" value=<ttsoft:write name="mxitem" property="skssksrq"/>><input type="hidden" name="skssjsrq" value=<ttsoft:write name="mxitem" property="skssjsrq"/>><input type="hidden" name="rkje" value=<ttsoft:write name="mxitem" property="rkje"/>><input type="hidden" name="sbbh" value=<ttsoft:write name="mxitem" property="sbbh"/>><input type="hidden" name="sjfc" value=<ttsoft:write name="mxitem" property="sjfc"/>><input type="hidden" name="qjfc" value=<ttsoft:write name="mxitem" property="qjfc"/>><input type="hidden" name="szsmdm_old" ><input type="hidden" name="nd" ></tr>'; 
							var d<ttsoft:write name="item" property="jkpzh"/>_list=new DTable(d<ttsoft:write name="item" property="jkpzh"/>,jsArr_d<ttsoft:write name="item" property="jkpzh"/>);	
							d<ttsoft:write name="item" property="jkpzh"/>_list.tableTail=3;
							d<ttsoft:write name="item" property="jkpzh"/>_list.tableHead=2;

							hjArray.push(new Array('sjse','xthj','d<ttsoft:write name="item" property="jkpzh"/>'));
							tableArray.push(new Array('d<ttsoft:write name="item" property="jkpzh"/>','<ttsoft:write name="item" property="szmc"/>'));
							</script>
			  <br>
				
              </logic:iterate>

              <br>
		
              <table width="94%" border="0" cellpadding="0" cellspacing="4">
                <tr valign="bottom"> 
                  <td width="36%">&nbsp;</td>
                  <td width="10%"><input type="image" accesskey="s" tabIndex="-1" onclick="doSubmitFormPro('Update');return false;" style="cursor:hand" onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" border="0" height="22"></td>
				  <!--<td width="9%"> <img onClick=""  onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="��ӡ" id="dy1" src="<%=static_contextpath%>images/dy-p1.jpg" width="79" height="22"></td>-->
                  <!--<td width="9%"><img onClick="doSubmitForm('Back')" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/fanhui1.jpg" width="79" height="22"></td>-->
                  <td width="10%"><input type="image" accesskey="f" tabIndex="-1" onClick="doSubmitForm('Back');return false;" style="cursor:hand;" onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22"></td>
                  <td width="10%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                  <td width="39%">&nbsp;</td>
                </tr>
              </table>
	     </td>
		</tr>
	  </table>
<%@ include file="./include/footer.jsp"%>
 
 </td>
  </tr>
</table>
<html:hidden property="jkpzh" />
<html:hidden property="jsjdm" />
<html:hidden property="nsrmc" />
<html:hidden property="presbbh" />
<html:hidden property="sbbh" />
<html:hidden property="sjly" />
</html:form>
</body>
  <script language="javascript">
  
  				//������˰
				<ttsoft:write name="zhsbPgbsjksypdsActionForm" property="scriptStr" filter="false"/>
function doSubmitFormPro(action){
	if(!doSubmitCheck()) return false;
	if (!check()) return false;
	doSubmitForm(action);
  }
  //��鲢�ύ����Ҫ����걨���ڡ�˰����������
//��˰��Ŀ���롢˰���������ڡ���˰��������˰��ʵ�ʽ�˰��
function doSubmitCheck(){
	var sjse=document.all('sjse');
	//���ÿһ�е�ֵ�Ƿ�Ϸ�
	for(var ii=0;ii<sjse.length;ii++){		
		if(!isNum(sjse[ii],0,null,true)) return false;
	}
	return true;
	
}
	function check(){
	 	 var succeed =true;
		 var alertStr="";
     alertStr+=checkSzsmdm();
		 alertStr+=checkHjsjje();
		 if(alertStr!="") {
			 succeed =false
       alert(alertStr);
		 }
	 	 return succeed;
	}
	function checkSzsmdm(){
	 var alertStr="";
	 var szsmdm=document.all('szsmdm');
   if(szsmdm){//�ҵ�szsmdm �Ķ���
				if(szsmdm.length){
					for(var i=0;i<szsmdm.length;i++){
						if(szsmdm[i].value==""){
							alertStr+="˰��˰Ŀ���벻��Ϊ��!\n"
							break;
						}//end if
					}//end for
				}//end if
				else{//ֻ����һ��szsmdm
							if(szsmdm.value==""){
								alertStr+="˰��˰Ŀ���벻��Ϊ��!\n"
							}
				}
	 }
	 return alertStr;
	}

	function checkHjsjje(){
		
		var alertStr="";
		var tempTable;
		/*
		for(var i=0;i<tableArray.length;i++){
			eval("tempTable="+tableArray[i][0]);
			if(tempTable.all("sjje").value!=tempTable.all("xthj").value){
				alertStr+="*"+tableArray[i][1]+"* ��¼��ϼƽ���ϵͳ�ϼƽ���!\n"
			}
		}
		*/
   return alertStr;
	}
	hj();


//��д'../js/smsb_common.js'�е�tuchu()����  zzb  090407
	//�˳�
function tuichu(){
	if(returnStr==null || returnStr==""){
		//������˰
		returnStr="zhsbPgbsAction.do";
	}
	//��������ۺ��걨�����걨����ҳ�棬���˳����ۺ��걨ҳ��
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		//������˰
		returnStr="zhsbPgbsAction.do?actionType=Show";
	window.location=returnStr;
}
	</script>


</html:html>