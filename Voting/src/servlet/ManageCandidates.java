package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Candidate;
import dao.Dao;

public class ManageCandidates extends HttpServlet {

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
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>管理</TITLE></HEAD>");
		out.println("  <BODY>");
		
		String method=request.getParameter("method");
		String canId=request.getParameter("id");
		if("delete".equals(method))//删除操作
		{
			if(new Dao().delCandidate(Integer.parseInt(canId)))
			{
				ServletContext application = this.getServletContext();
				String path=application.getRealPath("/")+"photo/"+canId+".png";
				File file = new File(path);//获得物理路径
				if(file.exists())
					file.delete();
//				out.println("<script>" +
//						"alert('成功删除候选人！');" +
//						"</script>");
			}
			out.println("<script>" +
					"location.href='./admin.jsp'" +
					"</script>");
		}
		else if("add".equals(method))//添加操作
		{
			String name=request.getParameter("canName");
			String idNo=request.getParameter("idNo");
			String psw=request.getParameter("psw");
			Candidate can=new Candidate();
			can.setName(name);
			can.setIdNo(idNo);
			can.setPassword(psw);
			if(new Dao().addCandidate(can))
			{
//				out.println("<script>" +
//						"alert('成功添加候选人！');" +
//						"</script>");
			}
			out.println("<script>" +
					"location.href='./admin.jsp'" +
					"</script>");
		}
		else
		{
			response.sendRedirect("../err.jsp");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
