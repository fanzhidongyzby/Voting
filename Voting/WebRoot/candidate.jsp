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
<td rowspan="2" width="80%"><font style="font-family:����; color:#FFFFFF"  size="+4">&nbsp;ѡƱ����ϵͳ</font></td>
<td>&nbsp;</td>
</tr>
<tr><td align="right"><a style="color:#FFFFFF" target="_blank" href="./login.jsp">��Ϣά��</a></td></tr>
</table>
<div style="height:10px"></div>
<div class="head">
	<div class="head_left"></div>
    <div class="head_middle"><font class="head_txt">&nbsp;���</font></div>
    <div class="head_right"></div>
</div>

<%
boolean editable=false;//�Ƿ���Ȩ�ޱ༭
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
<tr><td align="center">Ʊ��:<%=can.getVoteNum() %></td></tr>
</table>
</td></tr>
</table>
</div>
</td>

<td>
<div style="border:solid; border-color:#CCCCCC; border-width:thin; width:100%">
<table width="100%" border="1" bordercolor="#999999" style="BORDER-COLLAPSE: collapse">
<tr><td width="14%">����</td>
<td width="77%"><%=name %></td></tr>
<tr><td>�Ա�</td><td ><%=sex %></td></tr>
<tr><td>����</td><td ><%=age %>��</td></tr>
<tr><td>����</td><td ><%=nation %></td></tr>
<tr><td>ְ��</td><td ><%=pos%></td></tr>
<tr><td>��������</td><td ><%=can.getBirthday() %></td></tr>
<tr><td>ѧ��</td><td ><%=edu %></td></tr>
<tr><td>������ò</td><td ><%=poli %></td></tr>
<tr><td>��ϵ�绰</td><td ><%=tel %></td></tr>
<tr><td>������λ</td><td ><%=work %></td></tr>
<tr><td>���˼���</td><td ><%=rs %></td></tr>
<tr><td>��ע</td><td ><%=oth %></td></tr>
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
<button type="button"  onclick="location.href='./candidate/editcandidate.jsp?id=<%=can.getId()%>'" class="button"><font class="btn_txt" style=" margin-left:-10px;">�޸�����</font></button>
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
