package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

public class Vote extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		Object sw=this.getServletContext().getAttribute("sysStatus");//ϵͳ����
		if(sw==null||sw.toString().equals("closed"))//ϵͳ�����л��߹ر�
		{
			out.print("<script>" +
					"alert('���ڲ���ѡƱ��ʱ��');" +
					"location.href='home.jsp';" +
					"</script>");
			out.flush();
			out.close();
			return;
		}
		
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>��֤</TITLE></HEAD>");
		out.println("  <BODY>");
		
		HttpSession session=request.getSession();
		Object rand=session.getAttribute("rand");
		int errNum=0;//�쳣
		if(rand!=null)//��������֤��
		{
			String s=request.getParameter("validNo");
			if(rand.toString().equals(s))//��֤��ƥ��
			{
				Dao dao=new Dao();
				String idNo=request.getParameter("idNo");//���֤��
				if(idNo!=null)//����
				{
					int rs=dao.isVoter(idNo);
					if(rs==1)//�Ϸ��û�
					{
						//������ѡ��ID
						Object candIds=session.getAttribute("candIds");
						if(candIds!=null&&!"".equals(candIds.toString())&&!"null".equals(candIds.toString()))
						{
							StringTokenizer token=new StringTokenizer(candIds.toString(),"-");
							ArrayList<String>ids=new ArrayList<String>();
							int i=0;
							while(token.hasMoreTokens())
							{
								ids.add(token.nextToken());
								i++;
							}
							if(i>=0&&i<10)//�Ϸ�ѡƱ
							{
								if(dao.vote(idNo, ids)==1)
									errNum=4;
							}
						}
						else
							errNum=5;//ֱ�ӷ���
					}
					else if(rs==0)//������
						errNum=2;
					else//Ͷ��Ʊ�û�
						errNum=3;
				}
				else
					errNum=2;//���֤�Ŵ���
			}
			else
				errNum=1;//��֤�����
		}
		String msg="",next="./home.jsp";
		Object tcan=session.getAttribute("candIds");
		switch(errNum)
		{
		case 0:msg="������ѡ���ѡ�ˣ�";session.removeAttribute("candIds");break;
		case 1:msg="��������ȷ����֤�룡";
			next="./validate.jsp";
			if(tcan!=null)
				next+="?candIds="+tcan.toString();
			break;
		case 2:msg="��������ȷ�����֤�ţ�";
			next="./validate.jsp";
			if(tcan!=null)
				next+="?candIds="+tcan.toString();
			break;
		case 3:msg="���Ѿ�Ͷ��Ʊ��";session.removeAttribute("candIds");break;
		case 4:msg="ͶƱ�ɹ���";session.removeAttribute("candIds");break;
		case 5:msg="����ѡ���ѡ�ˣ���ȷ�ϣ�";break;
		}
		
		out.println("<script>" +
				"alert('" + msg +"');" +
				"location.href='" + next +	"'" +
				"</script>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
