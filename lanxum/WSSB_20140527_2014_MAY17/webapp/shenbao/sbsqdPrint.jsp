<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.shenbao.print.web.JksqdPrintForm"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	JksqdPrintForm jf = (JksqdPrintForm)request.getAttribute("jksqdPrintForm");
	String rqstr = jf.getSbrq();
%>

<html>
<head>
<title>���ӽɿ�ר�ýɿ���</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<applet name="printer" codebase="<%=static_contextpath%>printer" code="com.ttsoft.bjtax.webprint.DZJKZYJKSPrinter" width="0" height="0" archive="vprinter.jar">
</applet>

<html:form action="/jksqdPrintAction" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="h_jsjdm"/>
<html:hidden property="h_sbbh"/>
<html:errors />
<br>
	<table width="96%" align="center" cellspacing="0" class="table-99" id="pauls">
		<tr>
			<td class="z-ta1-td2" valign="top"> <br>
				<table width="98%" cellpadding="5" cellspacing="5" class="table-99" >
					<tr class="black9">
						<td align="center">
						    <div align="center"><font size="4pt"><b>���ӽɿ�ר�ýɿ���</b></font></div><br>
							<br><div align="center">
								<%
								out.println(rqstr.substring(0,4) + " �� " + rqstr.substring(4,6) + " �� " + rqstr.substring(6) + " ��");
								%>
								</div><br>
						</td>
					</tr>
			    </table>
			    
				<table width="98%" cellpadding="5" cellspacing="5" class="table-99" >
					<tr class="black9">
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;�걨��ţ�<u><bean:write name='jksqdPrintForm' property='sbbh'/></u>
						<td align="right">��λ�������Ԫ&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
			    </table>
				<table class="table-99" cellspacing="0" width="98%" border="1">
				<tr>
					<td    class="2-td2-t-left">
							<div align="center"><strong>��˰�˼��������</strong>&#160;&#160;</div>
						</td>
						<td  colspan="3" class="2-td2-t-center">
								<div align="center"><bean:write name='jksqdPrintForm' property='jsjdm'/>&nbsp;</div>
						</td>
				</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>��˰������</strong>&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center"><bean:write name='jksqdPrintForm' property='nsrmc'/>&nbsp;</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>���ջ��ش���</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">21100000000</div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>����������</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center"><input type="text" name="nsrmc" value="<bean:write name='jksqdPrintForm' property='nsrmc'/>"/>&nbsp;</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>���ջ�������</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center"><bean:write name='jksqdPrintForm' property='swjgzzjgmc'/>&nbsp;</div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>�����˿�����������</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center"><input type="text" name="yhmc" value="<bean:write name='jksqdPrintForm' property='yhmc'/>"/>&nbsp;</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>�տ��������</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center"><bean:write name='jksqdPrintForm' property='gkzzjgmc'/>&nbsp;</div>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>�������ʺ�</strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><input type="text" name="zh" value="<bean:write name='jksqdPrintForm' property='zh'/>"/>&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong>�����������к�</strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-center">
							<div align="center">011100000003</div>
						</td>
					</tr>
				</table>
				<br>
				<table class="table-99" cellspacing="0" width="98%" border="1">
					<tr>
							  <td class="2-td1-left"><div align="center">��˰��Ŀ����</div></td>
							  <td class="2-td1-left"><div align="center">��˰����</div></td>
							  <td class="2-td1-left"><div align="center">��˰���</div></td>
							  <td class="2-td1-center"><div align="center">ʵ�ʽ�˰��</div></td>
					</tr>
				    <bean:define id="itemjksprint" name="jksqdPrintForm" property="szitem"/>
				    <logic:iterate id="itemprint" name="itemjksprint" indexId="index">
				        <tr>
				          <td class="2-td2-left"><%=(((Map)itemprint).get("szdm") + " " + ((Map)itemprint).get("szmc"))%>&nbsp;</td>
				          <td class="2-td2-left">&nbsp;</td>
				          <td class="2-td2-left">&nbsp;</td>
				          <td class="2-td2-center"><%=((Map)itemprint).get("sjje")%>&nbsp;</td>
				        </tr>
				    </logic:iterate>
					
					<tr>
						<td colspan="4" class="2-td2-center">
	                           <table width="100%">
	                               <tr>
	                                   <td class="inputnoborder" width="70%">
	                                       <strong>&nbsp;&nbsp;���ϼ�(��д):</strong><bean:write name='jksqdPrintForm' property='hjjedx'/>&nbsp;
	                                   </td>
	                                   <td class="inputnoborder" width="30%" >
	                                       <strong>���ϼ�(Сд):��</strong><bean:write name='jksqdPrintForm' property='hjjexx'/>&nbsp;
	                                   </td>
	                               </tr>
	                           </table>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong> <br>������ ���� <br>   �����ˣ��£� <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br>˰����� <br>���£� <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br>���м���Ա  <br>���� <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-center">
							<div align="left"><strong>&nbsp;&nbsp;��ע�� <br> <br></strong>&nbsp;&nbsp;</div>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<table class="table-99" cellspacing="0" width="98%" >
					<tr class="black9">
						<td>
							<img style="cursor:hand" onClick="printTab()" src="<%=static_contextpath%>/images/dayin1.jpg" width="79" height="22" id="dayin1" alt="��ӡ"/>&nbsp;&nbsp;
						</td>
						<td>
							<img style="cursor:hand" onClick="window.close()" src="<%=static_contextpath%>/images/guanbi1.jpg" width="79" height="22" id="guanbi" alt="�ر�" />
						</td>
					</tr>
				</table>
				<table class="table-99" cellspacing="0" width="98%" >
					<tr class="black9">
						<td align="right">&nbsp;
							
					  </td>
					</tr>
					<tr class="black9">
						<td align="right">&nbsp;
							
					  </td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
</html:form>

<SCRIPT LANGUAGE="JavaScript" type="text/JavaScript">
<!--
    function printTab(){
	var rqn="<%=rqstr.substring(0,4)%>";
	var rqy="<%=rqstr.substring(4,6)%>";
	var rqr="<%=rqstr.substring(6)%>";
	var ysb="��";
	var sbxh="<%=jf.getSbbh()%>";
	var wsb="";
    var fkrjsjdm = "<%=jf.getJsjdm()%>";//�����˼��������
    var zsjgdm = ""; //���ջ��ش���
    
	var nsrmc = "<%=jf.getNsrmc()%>";//��˰������
    var zsjgmc = "<%=jf.getSwjgzzjgmc()%>";//���ջ�������
	
	var fkrmc = document.forms[0].nsrmc.value;//����������
	var fkrkhyhmc = document.forms[0].yhmc.value;//�����˿�����������
	var fkrzh = document.forms[0].zh.value;//�������˺�
	
    //var fkrkhyhmc = "<%=jf.getYhmc()==null?"":jf.getYhmc()%>";//�����˿�����������
	//var fkrmc = "<%=jf.getNsrmc()%>";//����������
	//var fkrzh = "<%=jf.getZh() == null?"":jf.getZh()%>";//�������˺�
	
    var skgkmc = "<%=jf.getGkzzjgmc()%>";//�տ��������
    
    var gkqshhh = ""; //�����������к�
    <%
        List ar = jf.getSzitem();
    	String szstr = "",jestr = "";
    	Map sz;
    	for (int i = 0;i < ar.size();i++)
    	{
    	    sz = (Map)ar.get(i);
    	    szstr += sz.get("szdm");
    	    szstr += " ";
    	    szstr += sz.get("szmc");
    	    szstr += ";;";
    	    
    	    jestr += sz.get("sjje");
    	    jestr += ";;";
    	}
    	if (szstr.length() > 0)
    	{
    	    szstr = szstr.substring(0,szstr.length() - 2);
    	    jestr = jestr.substring(0,jestr.length() - 2);
    	}
    
    %>
    var nsxmdm ="<%=szstr%>";//��˰��Ŀ���� 
    var kssl = "";//��˰����
    var jsje = "";//��˰���
    var sjse = "<%=jestr%>"; //ʵ�ɽ��
    var jehjdx = "<%=jf.getHjjedx()%>";  //���ϼƣ���д��
    var jehjxx = "��<%=jf.getHjjexx()%>";  //���ϼƣ�Сд��
    var bz = ""; //��ע
     document.printer.setRQN(rqn);
     document.printer.setRQY(rqy);
     document.printer.setRQR(rqr);
     document.printer.setYSB(ysb);
     document.printer.setSBXH(sbxh);
     document.printer.setWSB(wsb);
    document.printer.setFKRJSJDM(fkrjsjdm);
    document.printer.setZSJGDM(zsjgdm);
	document.printer.setNSRMC(nsrmc);
    document.printer.setFKRMC(fkrmc);
    document.printer.setZSJGMC(zsjgmc);
    document.printer.setFKRKHYHMC(fkrkhyhmc);
    document.printer.setSKGKMC(skgkmc);
    document.printer.setFKRZH(fkrzh);
    document.printer.setGKQSHHH(gkqshhh);
    document.printer.setNSXMDM(nsxmdm);
    document.printer.setKSSL(kssl);
    document.printer.setJSJE(jsje);
    document.printer.setSJSE(sjse);
    document.printer.setJEHJDX(jehjdx);
    document.printer.setJEHJXX(jehjxx);
    document.printer.setBZ(bz);
    document.printer.startPrint();
    }
//-->
</SCRIPT>
</body>
</html>