<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page contentType="image/jpeg" import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'num.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
<%!
    Color getRandColor(int cc,int bb)
    {
        Random random = new Random();
        if(cc>255) cc=255;
        if(bb>255) bb=255;
        int r=cc+random.nextInt(bb-cc);
        int g=cc+random.nextInt(bb-cc);
        int b=cc+random.nextInt(bb-cc);
        return new Color(r,g,b);
    } //��ȡ�����ɫ
%>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
 
    int width=80; //������֤��ͼƬ�ĳ���
    int height=30; //������֤��ͼƬ�Ŀ��
    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
 
    Graphics g = image.getGraphics();
 
    Random random = new Random();
 
    g.setColor(getRandColor(200,250));
    g.fillRect(0, 0, width, height);
 
    g.setFont(new Font("Times New Roman",Font.PLAIN,18));
    //����������ʽ
 
    g.setColor(getRandColor(160,200));
    for (int i=0;i<155;i++)
    {
       int i_x = random.nextInt(width);
       int i_y = random.nextInt(height);
       int i_xl = random.nextInt(12);
       int i_yl = random.nextInt(12);
       g.drawLine(i_x,i_y,i_x+i_xl,i_y+i_yl);
    }
    //������������
 
    String s_Rand="";
    for (int i=0;i<4;i++)
    {
       String rand=String.valueOf(random.nextInt(10));
       s_Rand+=rand;
      
       g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
       g.drawString(rand,13*i+6,16);
    }
    //����4λ����� 
 
    session.setAttribute("rand",s_Rand);
    //����֤�����Session��
 
    g.dispose();
   
    ImageIO.write(image, "JPEG", response.getOutputStream());
    //�����֤ͼƬ
    out.clear();
    out=pageContext.pushBody();  
%>
    
  </body>
</html>
