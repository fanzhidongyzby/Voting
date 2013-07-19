package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public class CandidateFilter implements Filter {

	private String encoding;
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			PrintWriter out = response.getWriter();
			out.println("<script>" +
					"alert('»¹Ã»µÇÂ¼£¡');" +
					"</script>");
			out.println("<script>" +
					"location.href='../login.jsp'" +
					"</script>");
		}
		else if(user.getUserType().equals("candidate")){
			response.setCharacterEncoding("gbk");
			 filterChain.doFilter(request, response);
		}
		else if(user.getUserType().equals("admin")){
			response.setCharacterEncoding("gbk");
			 filterChain.doFilter(request, response);
			
			}
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
	}

}
