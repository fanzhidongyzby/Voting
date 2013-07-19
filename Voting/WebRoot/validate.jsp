<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>验证</title>
<link rel="stylesheet" type="text/css" href="./src/common.css">
</head>
<body>
<table class="top" border="0" style="background-image:url(src/head.png)">
<tr>
<td rowspan="2" width="80%"><font style="font-family:楷体; color:#FFFFFF"  size="+4">&nbsp;选票管理系统</font></td>
<td>&nbsp;</td>
</tr>
<tr><td align="right"><a style="color:#FFFFFF" target="_blank" href="./login.jsp">信息维护</a></td></tr>
</table>
<div style="height:10px"></div>
<div class="head">
	<div class="head_left"></div>
    <div class="head_middle"><font class="head_txt">&nbsp;选票验证</font></div>
    <div class="head_right"></div>
</div>
<%
Object sw=this.getServletContext().getAttribute("sysStatus");//系统开关
if(sw==null||sw.toString().equals("closed"))//系统刚运行或者关闭
{
%>
<script>
alert('现在不是选票的时候！');
location.href='home.jsp';
</script>
<%
}
%>
<div class="main" >
<form action="./Vote" method="post">
<table align="center" width="600" border="0">
<tr><td>&nbsp;</td></tr>
<tr><td>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td>
<table align="center" width="100%" border="0">
<tr height="30">
<td width="38%" align="right">身份证号:</td>
<td colspan="3"><input name="idNo" type="text" value="" /></td>
</tr>
<tr height="30">
<td width="38%" align="right">验证码:</td>
<td width="17%" ><input name="validNo" type="text" value="" /></td>
<td width="4%" valign="middle">
<div id='validarea' style="cursor:pointer">
<img height="20" width='50' src="./src/valid.png" alt="点击刷新" id="imgvalid" onclick="refresh()"/></div></td>
<td width="41%">&nbsp;</td>
</tr>
<tr><td>&nbsp;</td><td  align="center" valign="middle">

<button type="submit" class="back_btn" onclick=""><font class="btn_txt" style=" margin-left:10px;">确认投票</font></button>
<%
if(request.getParameter("candIds")!=null&&!"".equals(request.getParameter("candIds")))
	session.setAttribute("candIds",request.getParameter("candIds"));
%>
</td></tr>
</table></td></tr>
</table></div></td></tr>
<tr><td>&nbsp;</td></tr>
</table></form>
<div class="bottom"></div>
</div>
<script>
function refresh()
{
	var d=new Date();
	var rnd=d.getTime();	
	document.all.imgvalid.src="./num.jsp?id="+rnd;	
}
</script>
</body>
</html>
