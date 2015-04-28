<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web.Basx13BForm" %>
<html>  
  <head>
    <title>����˰������¼</title>
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
  <script type="text/javascript" language="JavaScript">
  <%
  		Basx13BForm basx = (Basx13BForm)request.getAttribute("basx13BForm");
  	// �������˰�����������
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSQYLYDM) != null)
	    {
	        out.print("var arySelect_gxjsly = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] gxjslydm = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSQYLYDM);
	        for(int i = 0 ; i < gxjslydm.length ; i++) 
	        {
	         	out.print(",[\"" + gxjslydm[i][0] + "\",\"" +gxjslydm[i][1] +"\"]");
	        }
	        out.println(");");
	    }
	%>   
	<%/*��ʼ��*/%> 
	function doInit(){
	    //��ʼ������
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
		
		_addOptionsToSelect(document.forms[0].gxjslySelect, arySelect_gxjsly);
		document.forms[0].gxjslySelect.value = document.all("gxjslydm").value;
		
		//���ñ��ػ������ҵ
		var btzqyssd = document.forms[0].btzqyssd.value;
		if(btzqyssd == "0"){
			document.all("bm")[0].checked = "true";
			document.all("btzqyjsjdm").readOnly=false;
			document.all("btzqyjsjdm").className='text-white';
			document.all("btzqyswdjzh").readOnly=true;
			document.all("btzqyswdjzh").className='text-gray';
			document.all("btzqymc").readOnly=true;
			document.all("btzqymc").className='text-gray';
		}
		if(btzqyssd == "1"){
			document.all("bm")[1].checked = "true";
			document.all("btzqyjsjdm").readOnly=true;
			document.all("btzqyjsjdm").className='text-gray';
			document.all("btzqyswdjzh").readOnly=false;
			document.all("btzqyswdjzh").className='text-white';
			document.all("btzqymc").readOnly=false;
			document.all("btzqymc").className='text-white';
		}
		//�ж��Ƿ��в�ѯ���
		if(document.all("tzxxjg").value == "0"){
			//���ø��¼������������򲻿ɱ༭
			document.all("gxjsly").disabled="disabled";
	
			//����Ͷ����ȵ���Ϣ����Ѿ�����ɱ�����ʶ���Ϊ����ɣ��򲻿ɱ༭
			var band = document.all("band").value;
			var tze_v = document.forms[0].tze.value.split(";");
			var kdke_v = document.forms[0].kdke.value.split(";");
			var sjdke_v = document.forms[0].sjdke.value.split(";");
			var jzdke_v = document.forms[0].jzdke.value.split(";");
			var bjkz_v = document.forms[0].bjkz.value.split(";");
			var num = 0;
			for(var n=2006;n<=band;n++){
				var tze = "tze"+n;
				var kdke = "kdke"+n;
				var sjdke = "sjdke"+n;
				var jzdke = "jzdke"+n;
				var bjkz = "bjkz"+n;
				if(tze_v[num] == "null" || tze_v[num] == ""){
					document.all(tze).value = "0.00";
				}else{
					document.all(tze).value = tze_v[num];
					formatCurrency(document.all(tze),0);
				}
				if(kdke_v[num] == "null" || kdke_v[num] == ""){
					document.all(kdke).value = "0.00";
				}else{
					document.all(kdke).value = kdke_v[num];
					formatCurrency(document.all(kdke),0);
				}
				if(sjdke_v[num] == "null" || sjdke_v[num] == ""){
					document.all(sjdke).value = "0.00";
				}else{
					document.all(sjdke).value = sjdke_v[num];
					formatCurrency(document.all(sjdke),0);
				}
				if(jzdke_v[num] == "null" || jzdke_v[num] == ""){
					document.all(jzdke).value = "0.00";
				}else{
					document.all(jzdke).value = jzdke_v[num];
					formatCurrency(document.all(jzdke),0);
				}
				document.all(bjkz).value = bjkz_v[num];
				if(bjkz_v[num]=="0"){
					document.all(tze).readOnly=true;
					document.all(sjdke).readOnly=true;
				}else{
					document.all(tze).readOnly=false;
					document.all(sjdke).readOnly=false;
				}
				//�ж�����ɱ�����ʾ�Ƿ�Ϊ����ɣ�����ɸ�����¼���ɱ༭
				document.all(bjkz).value = bjkz_v[num];
				if(bjkz_v[num]=="0"){
					document.all(tze).readOnly=true;
					document.all(sjdke).readOnly=true;
				}else{
					document.all(tze).readOnly=false;
					document.all(sjdke).readOnly=false;
				}
				num++;
			}
		}else{
			//���ø��¼�������������ɱ༭
			document.all("gxjsly").disabled="";
		}
		//��ʼ����ť����Ϊ�޸�״̬ʱ��ʾ����
		var czlx = document.forms[0].czlx.value;
		if(czlx == "2"){
			document.all.jssq.style.display = "none";
		}else{
			document.all.bc.style.display = "none";
		}
		
	}
	<%/*��������*/%>
	function addzl(){	
		var zlmc=document.getElementById("zlmc").value;	  
		if(zlmc != ""){
			row_index++;  
	    	var new_row=Table1.insertRow(Table1.rows.length); 
	    	addzlmc(row_index,zlmc,new_row);
	    	addzlan("2",row_index,new_row);
	    	document.getElementById("zlmc").value = "";
		}else{
			alert("����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ�������������ݲ���Ϊ��");
		}   
	    
	}
	<%/*������������*/%>
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
	<%/*��ʼ�������Ӱ�ť*/%>
	function addzlan(lx,row_index,new_row){
	    var new_col=new_row.insertCell(1);
	    if(row_index == 0){
	    	new_col.className="2-td2-t-center";
	    }else{
	    	new_col.className="2-td2-center";
	    }
	    if(lx == "1"){
	    	new_col.innerHTML="&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;";  
	    }else{
	    	new_col.innerHTML="<a href=\"#\" onclick=\"delete_row('row"+row_index+ "')\">ɾ��</a>";  
	    }
	    
	}
	<%/*ɾ��������*/%>
	function delete_row(rname){
		if(window.confirm("�Ƿ�ɾ�������ϣ�")){
			var zlstring = "";
	    	var i; 
		    i=Table1.rows(rname).rowIndex;
		    Table1.deleteRow(i);
		    var zl = document.forms[0].zl.value.split(";"); 
		    for(var n=0;n<zl.length;n++){
		    	if(i!=n){
		    		zlstring += zl[n]+"\;";
		    	}
		    }
		    document.all.zl.value=zlstring.substring(0,zlstring.length-1);
		}
    } 
    <%/*���������嵥*/%>
	function zlqd(){
		var zlstring = document.all.zl.value;
		var zl = document.forms[0].zl.value.split(";");
		var i = zl.length+1;
		for(;i<=row_index;i++){
			if(document.getElementById(i)!=null){
				var temp=document.getElementById(i).innerHTML;
				zlstring+="\;"+temp+"|0";
			}
		}
		document.all.zl.value=zlstring;
	}
	<%/*����ƶ�*/%>
	function change(lx,xh){
		var tze = "tze"+xh;
		var kdke = "kdke"+xh;
		var sjdke = "sjdke"+xh;
		var jzdke = "jzdke"+xh;
		if(lx == "1"){
			var tze_v = document.all(tze).value;
			if(tze_v != ""){
				if(!(checkvalue(document.all(tze),0)&&formatCurrency(document.all(tze),0))){
					return false;
				}
			}
		}else{
			var sjdke_v = document.all(sjdke).value;
			if(sjdke_v != ""){
				if(!(checkvalue(document.all(sjdke),0)&&formatCurrency(document.all(sjdke),0))){
					return false;
				}
			}
		}
	}
	
	<%/*����˰��ͼ����������*/%>
	function sehblkz(lx){
		if(lx == "1"){
			document.forms[0].bajmbl.value = "";
			document.getElementById("bajmse").readOnly=false;
			document.getElementById("bajmse").className='text-white';
			document.getElementById("bajmbl").readOnly=true;
			document.getElementById("bajmbl").className='txtInput';
		}else{
			document.forms[0].bajmse.value = "";
			document.getElementById("bajmse").readOnly=true;
			document.getElementById("bajmse").className='txtInput';
			document.getElementById("bajmbl").readOnly=false;
			document.getElementById("bajmbl").className='text-white';
		}
	}
	<%/*���غ������ҵ����*/%>
	function changeEdit(){
    	var btzqyjsjdm=document.getElementById("btzqyjsjdm");
        var btzqyswdjzh=document.getElementById("btzqyswdjzh");
        var btzqymc=document.getElementById("btzqymc");
        btzqyjsjdm.value='';
        btzqyswdjzh.value='';
        btzqymc.value='';
    	if (document.getElementsByName("bm")[0].checked) {       
        	btzqyjsjdm.readOnly=false;
			btzqyjsjdm.className='text-white';
			btzqyswdjzh.readOnly=true;
			btzqyswdjzh.className='text-gray';
			btzqymc.readOnly=true;
			btzqymc.className='text-gray';
			document.forms[0].btzqyssd.value = '0';
        }
        if (document.getElementsByName("bm")[1].checked) {
        	btzqyjsjdm.readOnly=true;
			btzqyjsjdm.className='text-gray';
			btzqyswdjzh.readOnly=false;
			btzqyswdjzh.className='text-white';
			btzqymc.readOnly=false;
			btzqymc.className='text-white';
			document.forms[0].btzqyssd.value = '1';
        }
    }
	<%/*Ч��������Ϣ*/%>
	function checkZbxx(){
		//Ч����ʼ��ֹ����
		var qsrq = document.forms[0].qsrq.value;
		var jzrq = document.forms[0].jzrq.value;
		if(qsrq == ""){
			alert("����д��ʼ���ڣ�");
			document.forms[0].qsrq.focus();
			return false;
		}
		if(jzrq == ""){
			alert("����д��ֹ���ڣ�");
			document.forms[0].jzrq.focus();
			return false;
		}
		//��ʼ���ڲ��ܴ��ڽ�ֹ����
		if(!checkDate(qsrq,jzrq)){
			return false;
		}
		//Ч����˰��ͱ���
		var bajmse = document.forms[0].bajmse.value;
		var bajmbl = document.forms[0].bajmbl.value;
		if(bajmse != ""){
			if(!checkvalue(document.all("bajmse"),0)){
				return false;
			}
		}
		if(bajmbl != ""){
			if(!checkvalue(document.all("bajmbl"),2)){
				return false;
			}
		}
		if(bajmse == "" && bajmbl == ""){
			alert("����д����˰�����������");
			document.forms[0].bajmse.focus();
			return false;
		}
		//����˰����ִ�����
		var jmszczxqk = document.forms[0].jmszczxqk.value;
		if(jmszczxqk == ""){
			alert("����д����˰����ִ�������");
			document.forms[0].jmszczxqk.focus();
			return false;
		}
		
		return true;
	}
	<%/*Ч���ӱ���Ϣ*/%>
	function checkYm(){
		var btzqyswdjzh = document.all("btzqyswdjzh").value;
		if(btzqyswdjzh == "" ){
			alert("��Ͷ����ҵ˰��Ǽ�֤�Ų���Ϊ��!");
			document.forms[0].btzqyswdjzh.focus();
			return false;
		}
		var btzqymc = document.all("btzqymc").value;
		 if(btzqymc == "" ){
			alert("��Ͷ����ҵ���Ʋ���Ϊ��!");
			document.forms[0].btzqymc.focus();
			return false;
		 }
		 var gxjsly = document.all("gxjsly").value;
		 if(gxjsly == "" ){
			alert("��С���¼�����ҵ������Ϊ��!");
			document.forms[0].gxjsly.focus();
			return false;
		 }
		 return true;
		
	}
	<%/*Ч��Ͷ����Ϣ*/%>
	function checkTzxx(){
		var band = document.all("band").value;
		for(var n=2006;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			if(n==2006 || n==2007){
				//�жϸ���ĵ��굱������Ͷ�ʶ��Ƿ�Ϊ��
				if(document.all(tze).value == ""){
					document.all(tze).value = "0.00";
				}
				if(document.all(sjdke).value != "" && document.all(sjdke).value!="0.00"){
					alert(n+"Ͷ����ȵĵ���ʵ�ʵֿ�Ӧ��˰���ö�ӦΪ�㣡");
					document.all(sjdke).focus();
					return false;
				}else{
					document.all(kdke).value = "0.00";
					document.all(sjdke).value = "0.00";
					document.all(jzdke).value = "0.00";
				}
			}else{
				//�жϸ���ĵ��굱������Ͷ�ʶ��Ƿ�Ϊ��
				if(document.all(tze).value == ""){
					document.all(tze).value = "0.00";
				}
				//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ�Ϊ��
				if(document.all(sjdke).value == ""){
					document.all(sjdke).value = "0.00";
				}
				var kdknd = n-2;//ȡ��2��ǰ��Ͷ�����
				var ztze = 0;
				var qnnd = n-1;//ȥ��Ͷ�����
				var zsjdke = 0;
				//��ȡ2��ǰ��Ͷ�ʶ��ܺ�
				for(var m=2006;m<=kdknd;m++){
					var mtze = "tze"+m;
					ztze += Number(document.all(mtze).value);
				}
				//ȡ��1��ǰ�ĵֿ۶��ܺ�
				for(var x=2006;x<=qnnd;x++){
					var xsjdke = "sjdke"+x;
					zsjdke += Number(document.all(xsjdke).value);
				}
				var kdktze = "tze"+kdknd;//ȡ��2��ǰ��Ͷ�����ҳ��ġ���������Ͷ�ʶ��Ӧ�����
				var qnjzdke = "jzdke"+qnnd;//ȡ��ȥ��Ͷ�����ҳ��ġ���ת�Ժ���ȵֿ�Ӧ��˰���ö��Ӧ�����
				//�ж�ǰ��Ͷ�ʶ��Ƿ�Ϊ�գ�ȥ�ꡱ��ת�Ժ���ȵֿ�Ӧ��˰���ö�Ƿ�Ϊ��
				if((document.all(kdktze).value != "" && document.all(kdktze).value!="0.00")||
					(document.all(qnjzdke).value != "" && document.all(qnjzdke).value!="0.00")){
					//�����Ϊ�գ������ĵ���ɵֿ�Ӧ��˰���ö�=2��ǰ�ĵ�������Ͷ�ʶ�*0.7
					document.all(kdke).value = ztze * 0.7 - zsjdke;
					//���ֽ�����������
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//������ת��ΪС�������λ
					formatCurrency(document.all(kdke),0);
				}else{
					//2��ǰͶ����ȵ�Ͷ�ʶ�Ϊ��
					document.all(kdke).value = "0.00";
				}
				
				//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ���ڵ���ɵֿ�Ӧ��˰���ö�
				if((document.all(kdke).value-document.all(sjdke).value)<0){
					alert(n+"����ʵ�ʵֿ�Ӧ��˰���ö����С�ڻ���ڵ���ɵֿ�Ӧ��˰���ö");
					document.all(sjdke).focus();
					return false;
				}else{
					//����������������ת�Ժ���ȵֿ�Ӧ��˰���ö�=����ɵֿ�Ӧ��˰���ö�-����ʵ�ʵֿ�Ӧ��˰���ö�
					document.all(jzdke).value = document.all(kdke).value-document.all(sjdke).value;
					//���ֽ�����������
					var num = round2(document.all(jzdke).value);
					document.all(jzdke).value = num;
					//������ת��ΪС�������λ
					formatCurrency(document.all(jzdke),0);
				}
			}
			
		}
		return true;
		
	}
	<%/*����Ͷ����Ϣ*/%>
	function tzxx(){
		var band = document.all("band").value;
		var tzestring = "";
		var kdkestring = "";
		var sjdkestring = "";
		var jzdkestring = "";
		var bjkzstring = "";
		for(var n=2006;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			var bjkz = "bjkz"+n;
			tzestring = tzestring + "\;"+document.all(tze).value;
			kdkestring = kdkestring+"\;"+document.all(kdke).value;
			sjdkestring = sjdkestring+"\;"+document.all(sjdke).value;
			jzdkestring = jzdkestring+"\;"+document.all(jzdke).value;
			if(document.all(bjkz).value == ""){
				bjkzstring = bjkzstring +"\;1";
			}else{
				bjkzstring = bjkzstring +"\;"+document.all(bjkz).value;
			}
		}
		document.all.tze.value=tzestring.substring(1,tzestring.length);
		document.all.kdke.value=kdkestring.substring(1,kdkestring.length);
		document.all.sjdke.value=sjdkestring.substring(1,sjdkestring.length);
		document.all.jzdke.value=jzdkestring.substring(1,jzdkestring.length);
		document.all.bjkz.value=bjkzstring.substring(1,bjkzstring.length);
	}
	
	<%/*���ݼ���������˰��Ǽ�֤�Ų�ѯ*/%>
	function doSeach(lx){
		if(lx == '1'){
			document.all.btzqyswdjzh.value = "";
			document.all.btzqymc.value = "";
			doSubmitForm('JsjdmSeach');
		}else{
			document.all.btzqyjsjdm.value = "";
			doSubmitForm('SwdjzhSeach');
		}
		
	}
	
	<%/*��������*/%>
	function jssq()
	{
		if(checkZbxx() && checkYm() && checkTzxx()){
			zlqd();
			if(window.confirm("�Ƿ���ܸ���˰�˵ļ���˰�������룿")){
				//�������������򱣴��ҳ�����
				document.forms[0].gxjslydm.value = document.forms[0].gxjsly.value;
				//��������Ͷ����Ϣ
				tzxx();
	      		if(window.confirm("�Ƿ���ת�������ӡԤ��ҳ��")){
	      			document.forms[0].actionType.value="SMShPrint";
					document.forms[0].submit();
	      		}else{
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		}
      		}
		}
	}
	
	<%/*����*/%>
	function back()
	{
		doSubmitForm('Back');
	}
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx13BAction.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />
        <html:hidden property="btzqyssd" />
        <html:hidden property="gxjslydm" />
        <html:hidden property="tze" />
        <html:hidden property="kdke" />
        <html:hidden property="sjdke" />
        <html:hidden property="jzdke" />
        <html:hidden property="bjkz" />
        <html:hidden property="tzxxjg" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              ¼�봴ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö��ʸ񱸰�����  
            </td>
          </tr>
          <br>
          <tr>
            <td class="1-td2" colspan="7" valign="top">
            <br>
            	<table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                    <tr>
                      <td  class="2-td2-t-left">
                          <strong>����������</strong>
                      </td>
                      <td  width="22%"class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx13BForm" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>���������</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx13BForm" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>��˰������</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx13BForm" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>����˰����</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>��������</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>������ҵ</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx13BForm" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>��ϵ��</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>��ϵ�绰</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx13BForm" property="lxdh"/>
                      </td>
                    </tr>
                  </table>
                  
                  
                  </br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">            
              <tr>
                <td class="2-td2-t-left" width="10%">
                  ��ʼʱ��
                </td>
                <td class="2-td2-t-left" width="15%"> 
                  	<html:text property="qsrq" size="12" onclick="WdatePicker()"></html:text>
                </td>
                <td class="2-td2-t-left" width="10%">
                  ��ֹʱ��
                </td>
                <td class="2-td2-t-left" width="15%">
                	<html:text property="jzrq" size="12" onclick="WdatePicker()"></html:text>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  ����˰��
                </td>
                <td class="2-td2-t-left" width="15%">
                  	<html:text property="bajmse" onfocus="sehblkz('1')" size="10" styleClass="txtInput"/>&nbsp;Ԫ
                </td>
                <td class="2-td2-t-left" width="10%">
                  �������
                </td>
                <td class="2-td2-t-center" width="15%">
	                <html:text property="bajmbl" onfocus="sehblkz('2')" size="10" styleClass="txtInput"/>&nbsp;%
                </td>
                
              </tr>
              <tr>
                <td class="2-td2-left">
                  ����˰����
                  <br/>
                  ִ�������
                </td>
                <td class="2-td2-center" colspan="7">
                	<div align="left">
                		&nbsp;&nbsp;&nbsp;<html:textarea property="jmszczxqk" rows="5" cols="90"></html:textarea>
                	</div>
                  <td>
              </tr>
              <tr>
                <td class="2-td2-center" colspan="8">
                	<br/>
                  <div align="left">
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                    ����˰���������嵥��
                  </div>
                  
                  <table width="95%" cellspacing=0 border=0 id="Table1">                   
                  </table>
                  </br>
                  <table width="95%" cellspacing=0 border=0>  
                  	<tr>
                  		<td class="2-td2-t-left">
                  			����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ����������
                  		</td>
                  		<td class="2-td2-t-left">
                  			<input type="text" size="80"  name="zlmc" value="" />
                  		</td>
                  		<td class="2-td2-t-center">
                  			<a href="#" onclick="addzl()">���ӵ������嵥</a>
                  		</td>
                  	</tr>
                  </table>	 
                  </br>
                  <td>
              </tr>
            </table>
                  
            	  </br>
            		<table class="table-99" cellspacing="0">
					
                    <tr>
                      <td rowspan="5" width="30%" class="2-td2-t-left">
                        <div align="center">
                          ��Ͷ����ҵ
                        </div>
                      </td>
                      <td width="30%"  class="2-td2-t-left">
                        <div align="left">&nbsp;&nbsp;
                         	������
                        </div>
                      </td>
                      <td class="2-td2-t-center">
                        <div align="left">
                        	<input type="radio" value="0" name="bm" onclick="changeEdit()" checked="true">����</input>&nbsp;         
							<input type="radio" value="1" name="bm" onclick="changeEdit()">���</input>
                        </div>
                      </td>
                    </tr>
                    <tr>
                     
                      <td width="30%"  class="2-td2-left">
                        <div align="left">&nbsp;&nbsp;
                          	���������
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<html:text property="btzqyjsjdm" size="30" tabindex="-1" maxlength="8" onchange="doSeach('1')"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">&nbsp;&nbsp;
                         	˰��Ǽ�֤��&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<html:text property="btzqyswdjzh" size="30" tabindex="-1" readonly="readonly" styleClass="txtInput" onchange="doSeach('2')"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">&nbsp;&nbsp;
                          	��˰������&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<html:text property="btzqymc" size="30" tabindex="-1" readonly="readonly" styleClass="txtInput"></html:text>
                        </div>
                      </td>
                    </tr>
                  	 <tr>
                      <td width="10%" class="2-td2-left">
                        <div align="left">&nbsp;&nbsp;
                          	��С���¼�����ҵ����&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                      	<div align="left">
	                        <html:select name="basx13BForm" styleId="gxjslySelect" property="gxjsly" />
                      	</div>
                      </td>
                    </tr>   
                  </table>
                   <br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                  <tr>
                  		<td class="2-td2-t-center" colspan="5">
                  			��λ��Ԫ
                  		</td>
                  	</tr>
                     <tr>
                      <td class="2-td2-left" width="11%">
                          Ͷ�����
                      </td>
                      <td class="2-td2-left" width="11%">
                          ��������Ͷ�ʶ�
                      </td>
                      <td class="2-td2-left" width="11%">
                          ����ɵֿ�Ӧ��˰���ö�
                      </td>
                      <td class="2-td2-left" width="11%">
                          ����ʵ�ʵֿ�Ӧ��˰���ö�
                      </td>
                      <td class="2-td2-center" width="11%">
                          ��ת�Ժ���ȵֿ�Ӧ��˰���ö�
                      </td>                                                                                                        
                    <% int band = Integer.parseInt(basx.getBand());
					if(band<2009){
						//band = 2009;
					}
					for(int i = 2006;i<=band;i++){
						String tze = "<input type=\"text\" name=\"tze"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\" onchange=\"change('1',"+i+")\">";
						String kdke = "<input type=\"text\" name=\"kdke"+i+"\" size=\"15\" tabindex=\"-1\" readonly=\"readonly\" value=\"\">";
						String sjdke = "<input type=\"text\" name=\"sjdke"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\" onchange=\"change('2',"+i+")\">";
						String jzdke = "<input type=\"text\" name=\"jzdke"+i+"\" size=\"15\" tabindex=\"-1\" readonly=\"readonly\" value=\"\">";
						String bjkz = "<input type=\"hidden\" value=\"\" name=\"bjkz"+i+"\">";
					%>
					<tr>
                      <td class="2-td2-left" >
                        <div align="center">
                          <%=i %>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=tze %>
                        </div>
                      </td>
                      <td  class="2-td2-left">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=kdke %>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=sjdke %>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=jzdke %>
                          <%=bjkz %>
                        </div>
                      </td>                      
                    </tr>
                    <% }%>                       
                  </table>
                 <br/>
                 <table class="table-99" cellspacing="0">
                    <tr>
                      <td width="13%" class="2-td2-t-left">
                        <div align="right">
                          ¼������
                        </div>
                      </td>
                      <td width="27%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_lrrq" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          	�������
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_band" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          ¼����
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
	                    	<div id="jssq" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="jssq()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('jishoushenqing','','<%=static_contextpath%>images//b_jssq2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b_jssq1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <div id="bc" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="jssq()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images//baocun2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//baocun1.jpg" name="baocun" width="79" height="22" border="0" id="baocun">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <a style="cursor:hand" onClick="back()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images//fanhui2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//fanhui1.jpg" name="fanhui" width="79" height="22" border="0" id="fanhui">
	                        </a>
	                    </td>
                    </tr>
                  </table> 
                  <br>
                  <table width="99%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                      <td width="99" align="center" class="black9">
                        <strong>
                          <font color="#0000FF">
                            ע �� �� ��
                          </font>
                        </strong>
                      </td>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                    </tr>
                  </table>
                  
                  <br>
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>