<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>���</title>
<link rel="stylesheet" type="text/css" href="./src/common.css">
</head>
<body>
<table class="top" border="0" style="background-image:url(src/head.png)">
<tr>
<td rowspan="2" width="80%"><font style="font-family:����; color:#FFFFFF"  size="+4">&nbsp;ѡƱ����ϵͳ</font></td>
<td>&nbsp;</td>
</tr>
<tr><td align="right"><a style="color:#FFFFFF" target="_blank" href="../login.jsp">��Ϣά��</a></td></tr>
</table>
<div style="height:10px"></div>
<div class="head">
	<div class="head_left"></div>
    <div class="head_middle"><font class="head_txt">&nbsp;��Ӻ�ѡ��</font></div>
    <div class="head_right"></div>
</div>
<script >
function valid()
{
	if(document.all.canName.value=="")
	{
		alert('��ѡ����������Ϊ�գ�');
		return false;
	}
	if(document.all.idNo.value=="")
	{
		alert('��ѡ���˺Ų���Ϊ�գ�');
		return false;
	}
	if(document.all.psw.value=="")
	{
		alert('��ѡ�����벻��Ϊ�գ�');
		return false;
	}
	return true;
}
</script>
<div class="main" >
<form action="./ManageCandidates?method=add" method="post" onsubmit='return valid();'>
<table align="center" width="600" border="0">
<tr><td>&nbsp;</td></tr>
<tr><td>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td>
<table align="center" width="100%" border="0">
<tr height="30">
<td width="46%" align="center">����:</td>
<td colspan="3"><input name='canName'  type="text" value="" /></td>
</tr>
<tr height="30">
<td width="46%" align="center">�˺�:</td>
<td colspan="3"><input name='idNo' type="text" value=""
 onblur='document.all.psw.value=document.all.idNo.value' /></td>
</tr>
<tr height="30">
<td width="46%" align="center" >����:</td>
<td width="54%" ><input name='psw' type="text" value="" /></td>
</tr>
<tr><td colspan="10"  align="center" valign="middle">
<button type="submit" class="back_btn"><font class="btn_txt" style=" margin-left:10px;">ȷ�����</font></button>
</td></tr>
</table></td></tr></table></div></td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</form>
<div class="bottom"></div>
</div>

</body>
</html>
