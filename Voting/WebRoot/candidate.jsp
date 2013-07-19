<%@ page language="java" import="java.util.*" import="dao.*" 
import="model.*"
pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<%
String id = (String)request.getParameter("id");
User u=(User)session.getAttribute("user");
Candidate can=new Dao().getCandidate(Integer.parseInt(id));
%>
<%
int age=0;
if(can.getBirthday()!=null)
{
	String s=can.getBirthday();
	age=Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(s.substring(0,s.indexOf("-")));
}
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
<title><%=name %></title>
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
    <div class="head_middle"><font class="head_txt">&nbsp;简介</font></div>
    <div class="head_right"></div>
</div>

<%
boolean editable=false;//是否有权限编辑
if(u!=null&&("admin".equals(u.getUserType())
||"candidate".equals(u.getUserType())&&u.getId()==Integer.parseInt(id)))
{
	editable=true;
}

%>
<div class="main" >
<table align="center" width="1000" border="0">
<tr height="5px"><td>&nbsp;</td></tr>
<tr>
<td width="20%" valign="top">
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:90%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td>
<table width="100%" >
<%
String s=can.getPhotoPath()+"?rd="+Calendar.getInstance().getTimeInMillis();
%>
<tr><td align="center"><img name='head' src="<%=s %>" width="155"  height="200"/></td></tr>
<tr><td align="center">票数:<%=can.getVoteNum() %></td></tr>
</table>
</td></tr>
</table>
</div>
</td>

<td>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td width="14%">姓名</td>
<td width="77%"><%=name %></td></tr>
<tr><td>性别</td><td ><%=sex %></td></tr>
<tr><td>年龄</td><td ><%=age %>岁</td></tr>
<tr><td>民族</td><td ><%=nation %></td></tr>
<tr><td>职称</td><td ><%=pos%></td></tr>
<tr><td>出生年月</td><td ><%=can.getBirthday() %></td></tr>
<tr><td>学历</td><td ><%=edu %></td></tr>
<tr><td>政治面貌</td><td ><%=poli %></td></tr>
<tr><td>联系电话</td><td ><%=tel %></td></tr>
<tr><td>工作单位</td><td ><%=work %></td></tr>
<tr><td>个人简历</td><td ><%=rs %></td></tr>
<tr><td>备注</td><td ><%=oth %></td></tr>
</table>
</div>
</td>
<tr>
</table>
<table align="center" width="1000" border="0">

<%
if(editable){%>
<tr>
<td align="right">
<button type="button"  onclick="location.href='./candidate/editcandidate.jsp?id=<%=can.getId()%>'" class="button"><font class="btn_txt" style=" margin-left:-10px;">修改资料</font></button>
</td>
</tr>
<% 	
}
%>

</table>
<div class="bottom"></div>
</div>

</body>
</html>
