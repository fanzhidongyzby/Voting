<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��¼</title>
<link rel="stylesheet" type="text/css" href="./src/common.css">
</head>
<body>
<table class="top" border="0" style="background-image:url(src/head.png)">
<tr>
<td rowspan="2" width="80%"><font style="font-family:����; color:#FFFFFF"  size="+4">&nbsp;ѡƱ����ϵͳ</font></td>
<td>&nbsp;</td>
</tr>
<tr><td align="right"><a style="color:#FFFFFF" target="_blank" href="./login.jsp">��Ϣά��</a></td></tr>
</table>
<div style="height:10px"></div>
<div class="head">
	<div class="head_left"></div>
    <div class="head_middle"><font class="head_txt">&nbsp;��¼</font></div>
    <div class="head_right"></div>
</div>
<div class="main" >
<form action="login" method="post">
<table align="center" width="600" border="0">
<tr><td>&nbsp;</td></tr>
<tr><td>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td>
<table align="center" width="100%" border="0">
<tr height="30">
<td width="43%" align="right">�˺�:</td>
<td colspan="3"><input name="user" type="text" value="" /></td>
</tr>
<tr height="30">
<td width="43%" align="right">����:</td>
<td width="57%" ><input name="psw" type="password" value="" /></td>
</tr>
<tr><td colspan="10"  align="center" valign="middle">
<button type="submit"  class="button"><font class="btn_txt" style=" margin-left:-10px;">��¼</font></button></td></tr>
</table></td></tr></table></div></td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</form>
<script>
function login()
{
	if(document.all.user.value=='admin')
		location.href="./admin/admin.jsp";
	else
		location.href="./candidate.jsp";
	
}
</script>
<div class="bottom"></div>
</div>

</body>
</html>
