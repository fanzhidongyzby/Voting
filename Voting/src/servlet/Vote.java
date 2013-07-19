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
		Object sw=this.getServletContext().getAttribute("sysStatus");//系统开关
		if(sw==null||sw.toString().equals("closed"))//系统刚运行或者关闭
		{
			out.print("<script>" +
					"alert('现在不是选票的时候！');" +
					"location.href='home.jsp';" +
					"</script>");
			out.flush();
			out.close();
			return;
		}
		
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>验证</TITLE></HEAD>");
		out.println("  <BODY>");
		
		HttpSession session=request.getSession();
		Object rand=session.getAttribute("rand");
		int errNum=0;//异常
		if(rand!=null)//产生了验证码
		{
			String s=request.getParameter("validNo");
			if(rand.toString().equals(s))//验证码匹配
			{
				Dao dao=new Dao();
				String idNo=request.getParameter("idNo");//身份证号
				if(idNo!=null)//存在
				{
					int rs=dao.isVoter(idNo);
					if(rs==1)//合法用户
					{
						//分析候选人ID
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
							if(i>=0&&i<10)//合法选票
							{
								if(dao.vote(idNo, ids)==1)
									errNum=4;
							}
						}
						else
							errNum=5;//直接访问
					}
					else if(rs==0)//不存在
						errNum=2;
					else//投过票用户
						errNum=3;
				}
				else
					errNum=2;//身份证号错误
			}
			else
				errNum=1;//验证码错误
		}
		String msg="",next="./home.jsp";
		Object tcan=session.getAttribute("candIds");
		switch(errNum)
		{
		case 0:msg="请重新选择候选人！";session.removeAttribute("candIds");break;
		case 1:msg="请输入正确的验证码！";
			next="./validate.jsp";
			if(tcan!=null)
				next+="?candIds="+tcan.toString();
			break;
		case 2:msg="请输入正确的身份证号！";
			next="./validate.jsp";
			if(tcan!=null)
				next+="?candIds="+tcan.toString();
			break;
		case 3:msg="你已经投过票！";session.removeAttribute("candIds");break;
		case 4:msg="投票成功！";session.removeAttribute("candIds");break;
		case 5:msg="请先选择候选人，再确认！";break;
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
