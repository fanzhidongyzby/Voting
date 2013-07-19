<%@ page language="java" import="java.util.*" import="dao.*" import="model.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>资料编辑</title>
<link rel="stylesheet" type="text/css" href="./src/common.css">
<script language="javascript" type="text/javascript" src="./My97DatePicker/WdatePicker.js"></script>
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
    <div class="head_middle"><font class="head_txt">&nbsp;资料编辑</font></div>
    <div class="head_right"></div>
</div>
<%
String id = (String)request.getParameter("id");
User u=(User)session.getAttribute("user");
if("candidate".equals(u.getUserType())&&u.getId()!=Integer.parseInt(id))//非法查看
	response.sendRedirect("../err.jsp");
Candidate can = new Dao().getCandidate(Integer.parseInt(id));
%>
<div class="main" >
<form action="editCandidate" method="post" enctype="multipart/form-data">
<table align="center" width="85%" border="0">
<tr><td>&nbsp;</td><td><input type="hidden"value="<%=can.getId() %>"name="id"/></td></tr>
<tr><td>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td>
<table align="center" width="100%" border="0">
<%
String name=can.getName();if(name==null)name="";
String sex=can.getSex();if(sex==null)sex="";
String nation=can.getNaiton();if(nation==null)nation="";
String pos=can.getPosition();if(pos==null)pos="";
String edu=can.getEduBgd();if(edu==null)edu="";
String poli=can.getPoliStatus();if(poli==null)poli="";
String tel=can.getTel();if(tel==null)tel="";
String work=can.getWorkPlace();if(work==null)work="";
String rs=can.getResume();if(rs==null)rs="";
String oth=can.getOther();if(oth==null)oth="";
%>
<tr height="30">
<td width="20%" align="center">姓名:</td>
<td colspan="3"><input  name="name" type="text" value="<%=name%>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center" >密码:</td>
<td width="54%" ><input name="psw" type="text" value="<%=can.getPassword()%>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center" >性别:</td>
<td width="54%" ><input name="rd" type="radio" value='男'
<%
if(sex.equals("男")){
%>
checked
<%
}
%>
 />男
<input name="rd" value='女' type="radio"
<%
if(!sex.equals("男")){
%>
checked
<%
}
%>
 />女
<input type='hidden' name='sex' value="" />
</td>
</tr>
<tr height="30">
<td width="20%" align="center" >职称:</td>
<td width="54%" ><input name="pos" type="text" value="<%=pos %>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center">民族:</td>
<td colspan="3"><input  name="nation" type="text" value="<%=nation%>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center">工作单位:</td>
<td colspan="3"><input  name="work" type="text" value="<%=work%>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center">学历:</td>
<td colspan="3"><input  name="edu" type="text" value="<%=edu%>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center">政治面貌:</td>
<td colspan="3"><input  name="poli" type="text" value="<%=poli%>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center">联系电话:</td>
<td colspan="3"><input  name="tel" type="text" value="<%=tel%>" /></td>
</tr>
<tr height="30">
<td width="20%" align="center">出生年月:</td>
<td colspan="3">
<input class="Wdate" type="text" name="birth" value="<%=can.getBirthday()%>" onclick="WdatePicker()"/> 
</td>
</tr>
<tr height="30">
<td width="20%" align="center">个人简介:</td>
<td colspan="3"><textarea name="resume"style=" width:550px; height:100px;"><%=rs%></textarea></td>
</tr>
<tr height="30">
<td width="20%" align="center">备注:</td>
<td colspan="3"><textarea name="other"style=" width:550px; height:100px;"><%=oth %></textarea></td>
</tr>
<tr height="30">
<td width="20%" align="center">上传头像:</td>
<td colspan="3"><input name="photo" type="file"/></td>
</tr>
<tr><td colspan="10"  align="center" valign="middle">
<button type="submit" onclick='getsex();' class="back_btn" ><font class="btn_txt" style=" margin-left:10px;">确认修改</font></button>
</td></tr>
</table></td></tr></table></div></td></tr>
<tr><td>&nbsp;</td></tr></table>
</form>
<div class="bottom"></div>
</div>
<script >
function getsex()
{
	var sex=document.getElementsByName("rd");
	for(var i=0;i<sex.length;i++)
	{
		if(sex[i].type=='radio'&&sex[i].checked)
		{
			document.all.sex.value=sex[i].value;
		}
	}
}
</script>
</body>
</html>
