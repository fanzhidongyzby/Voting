<%@ page language="java" import="java.util.*"
import="dao.*" import="model.*"
 pageEncoding="gb2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>投票</title>
<link rel="stylesheet" type="text/css" href="./src/common.css">
</head>
<body>
<div align='center'>
<table  class="top" border="0" style="background-image:url(src/head.png)">
<tr>
<td rowspan="2" width="80%"><div style="font-family:楷体;"><font style="color:#FFFFFF"  size="+4">&nbsp;选票管理系统</font></div></td>
<td>&nbsp;</td>
</tr>
<tr><td align="right"><a style="color:#FFFFFF" target="_blank" href="./login.jsp">信息维护</a></td></tr>
</table>
<div style="height:10px"></div>
<div class="head">
	<div class="head_left"></div>
    <div class="head_middle" align='left'><font class="head_txt">&nbsp;候选人</font></div>
    <div class="head_right"></div>
</div>
<div class="main" >
<form id='form1' action="./validate.jsp" onsubmit="return beginvote();" method="post">
<table align="center" width="1000" border="0">
<%
ArrayList<Candidate> cands=new Dao().getCandidateList();
int i,j=0,len=cands.size();
while(j<len)
{
%>
<tr height="250">
<%
	for(i=0;i<5&&j<len;i++,j++)
	{
		Candidate can=cands.get(j);
%>
<td width="20%" align="center">
<div style="border:solid; background-color:#FFFFCC;border-color:#CCCCCC; border-width:thin; width:90%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td>
<table width="100%" align="center" border="0">
<tr><td  align="center"><img width="151" height="181" src="<%=can.getPhotoPath()%>" /></td></tr>
<tr><td align="center"><a target="_blank" href="./candidate.jsp?id=<%=can.getId()%>"><%=can.getName()%></a></td></tr>
<tr><td  align="center"><%=can.getVoteNum()%>票&nbsp;<input name='candi' value="<%=can.getId()%>" onclick="alert(document.all.ck.value)" type="checkbox"/></td></tr>
</table>
</td></tr></table>
</div>
</td>
<%
	}
	if(i<=4)
	{
%>
<td colspan="10">&nbsp;</td>
<%
	}
%>
</tr>
<%	
}
%>

<tr><td colspan="10"  align="center" valign="middle">
<button type="submit" class="button"><font class="btn_txt" style=" margin-left:-10px;">开始投票</font></button></td></tr>
<tr>
<td colspan="10"  align="left">
<div style="border:solid; border-color:#CCCCCC; border-width:thin" >
<font color="#FF0000" face="宋体">
<br />
&nbsp;&nbsp;投票细则：<br />
&nbsp;*每个人只有一次投票机会。<br />
&nbsp;*每次投票最多选择10个候选人。<br />
&nbsp;*请珍惜自己的选票的机会。<br />
<br />
</font>
</div>
</td></tr>
</table>
<input type='hidden' value="" name="candIds" />

</form>
<div class="bottom"></div>
</div>
</div>
</body>
<script type="text/javascript">
function beginvote()
{
	var boxs=document.all.form1.getElementsByTagName('input');
	var candiIds="";
	var select=0;
	for(var i=0;i<boxs.length;i++)
	{
		if(boxs[i].type=='checkbox'&&boxs[i].checked==true)
		{
			candiIds+=boxs[i].value+"-";
			select++;
		}
	}
	if(select==0)
	{
		alert('请选择候选人！');
		return false;
	}
	if(select>10)
	{
		alert('候选人个数不能超过10个！');
		return false;
	}
	document.all.candIds.value=candiIds;
	return true;
}
</script>
</html>
