package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Candidate;
import model.User;

import dao.Dao;

public class login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("user");
		String psw = request.getParameter("psw");
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>管理</TITLE></HEAD>");
		out.println("  <BODY>");
		
		if(userName.equals("")){
			out.println("<script>" + "alert('用户名不能为空');" + "</script>");
			out.println("<script>" + "location.href='./login.jsp'"
					+ "</script>");
		}
		else if(userName.length()>30||psw.length()>30||(userName.indexOf("or"))>0
				||(userName.indexOf("\\"))>0||(userName.indexOf("\'"))>0
			||(userName.indexOf(">"))>0||(userName.indexOf("<"))>0
			||(userName.indexOf("\"\""))>0||(userName.indexOf("\""))>0
			||(userName.indexOf("\'\'"))>0){
			out.println("<script>" + "alert('非法输入');" + "</script>");
			out.println("<script>" + "location.href='./login.jsp'"
					+ "</script>");
		}
		
		else{

		Dao dao = new Dao();
		User user = null;

		user = dao.login(userName, psw);
		
		if (user == null) {
			out.println("<script>" + "alert('用户名或密码错误');" + "</script>");
			out.println("<script>" + "location.href='./login.jsp'"
					+ "</script>");
		} else {
			request.getSession().setAttribute("user", user);
			if (user.getUserType().equals("admin")) {
				response.sendRedirect("./admin/admin.jsp");
//				out.println("<script>" + "location.href='./admin/admin.jsp'"
//						+ "</script>");
			}
			if (user.getUserType().equals("candidate")) {
				int id = user.getId();
				// Candidate can = new Dao().getCandidate(id);
				// request.setAttribute("candidate", can);
				request.setAttribute("id", id);
				String url = "./candidate.jsp?id=" + id;
				/*
				 * out.println("<script>" + "location.href='./candidate.jsp?'" +
				 * "</script>");
				 */
				out.println("<script>" + "location.href='" + url + "'"
						+ "</script>");
			}

		}
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
