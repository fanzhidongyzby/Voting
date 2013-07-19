<%@ page language="java" import="java.util.*" import="dao.*" import="model.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>管理员</title>
<link rel="stylesheet" type="text/css" href="./src/common.css">
</head>
<body>
<table class="top" border="0" style="background-image:url(src/head.png)">
<tr>
<td rowspan="2" width="80%"><font style="font-family:楷体; color:#FFFFFF"  size="+4">&nbsp;选票管理系统</font></td>
<td>&nbsp;</td>
</tr>
<tr><td align="right"><a style="color:#FFFFFF" target="_blank" href="../login.jsp">信息维护</a></td></tr>
</table>
<div style="height:10px"></div>
<div class="head">
	<div class="head_left"></div>
    <div class="head_middle"><font class="head_txt">&nbsp;候选人管理</font></div>
    <div class="head_right"></div>
</div>
<div class="main" >
<table align="center" width="800" border="0">
<tr>
<td>
<div style="height:10px;"></div>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr  align="center" height="20" bgcolor="#999999">
<td width="9%" >序号</td>
<td width="20%" >姓名</td>
<td width="32%" >账号</td>
<td width="30%" >密码</td>
<td width="9%" >删除</td>
</tr>
<%
ArrayList<Candidate>cands=new Dao().getCandidateList();
int len=cands.size();
for(int i=0;i<len;i++)
{
	Candidate can=cands.get(i);
%>
<tr height="24" align="center">
<td ><%=i+1 %></td>
<td ><%=can.getName() %></td>
<td ><a target='_blank' href="../candidate.jsp?id=<%=can.getId() %>"><%=can.getIdNo() %></a></td>
<td ><%=can.getPassword() %></td>
<td  align="center"><img src="src/del.png" style="cursor:pointer" 
onclick="if(confirm('确定删除该候选人吗？'))location.href='./ManageCandidates?method=delete&id=<%=can.getId() %>';" /></td>
</tr>
<%
}
%>
</table>
</div>
</td>
</tr>
<tr><td colspan="10"  align="left" valign="middle">
<button type="button" onclick="location.href='addcandidate.jsp'" class="button"><font class="btn_txt" style=" margin-left:-10px;">添加候选人</font></button></td></tr>
</table>
<div class="bottom"></div>
</div>
<div style="height:10px"></div>
<div class="head">
	<div class="head_left"></div>
    <div class="head_middle"><font class="head_txt">&nbsp;系统管理</font></div>
    <div class="head_right"></div>
</div>
<div class="main">
<table align="center" width="800">
<tr><td>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
  <table width="100%"  border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
  <tr><td>
  <div style="height:10px;"></div>
<%
String nextStatus="";
String btnMsg="";
Object sw=this.getServletContext().getAttribute("sysStatus");//系统开关
if(sw==null||sw.toString().equals("closed"))//系统刚运行或者关闭
{
	nextStatus="open";
	btnMsg="启动投票";
}
else
{
	nextStatus="closed";
	btnMsg="关闭投票";
}
%>
  <form id='f1' action="./System" method='post' onsubmit='return valid();' enctype="multipart/form-data">
  <table width="100%" border="0">
    <tr valign="middle" height="37px">
      <td width="23%" align="right"> 选民信息导入:</td>
      <td width="31%"><input name="voters" type="file" /></td>
      <td width="18%" ><button type="submit" onclick="" class="button"><font class="btn_txt" style=" margin-left:-10px;">导入</font></button></td>
	  <td colspan="2"><a onclick="alert('请上传.txt文格式文本文件，文件内需要将选民的身份证号按行排列。')" style="cursor:pointer; color:#FF0000">
	  <img src="src/help.png" height="30" width="30" /></a></td>
    </tr>
	  <tr valign="middle"><td height="60" colspan="3">&nbsp;</td>
	  <td width="4%" align="right">&nbsp;</td>
	  <td width="24%"  align="left"><button type="button" class="back_btn"
	   onclick="location.href='./System?sysStatus=<%=nextStatus %>'">
	  <font class="btn_txt" style=" margin-left:10px;"><%=btnMsg %></font></button></td>
    </tr>
  </table>
  </form>
</td></tr></table>

</div>
</td></tr></table>
<div class="bottom"></div>
</div>
<script>
function valid()
{
	if(document.all.voters.value=="")
	{
		alert('请选择要上传的选民信息，详细请查看帮助！');
		return false;
	}
	return true;
}
</script>
</body>
</html>
