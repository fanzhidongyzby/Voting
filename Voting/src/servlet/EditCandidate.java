package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Candidate;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.Dao;

public class EditCandidate extends HttpServlet {
	int id;

	/**
	 * Constructor of the object.
	 */
	public EditCandidate() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
		//String name=request.getParameter("file");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		Candidate can = new Candidate();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);// 设置缓冲,这个值决定了是fileinputstream还是bytearrayinputstream
		//factory.setRepository(new File("d:\\temp"));//设置临时存放目录,默认是new File(System.getProperty("java.io.tmpdir"))
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setSizeMax(1 * 1024 * 1024);//1M
		//sfu.setHeaderEncoding("utf-8");  
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			//数据提取错误
			warn(out, "表单数据解析错误！");
			end(out);
			return;
		}
		Iterator it = fileList.iterator();
		FileItem itemFile = null;
		String filePath=null;
		while (it.hasNext()) {
			FileItem item = (FileItem) it.next();

			if (item.isFormField()) {
				String name = item.getFieldName();//字段名称
				String value = item.getString("gbk");//字段值
				if(value==null)value="";
				if("id".equals(name)){can.setId(id=Integer.parseInt(value));}
				if("name".equals(name)){can.setName(value);}
				if("sex".equals(name)){can.setSex(value);}
				if("psw".equals(name)){can.setPassword(value);}
				if("pos".equals(name)){can.setPosition(value);}
				if("nation".equals(name)){can.setNation(value);}
				if("work".equals(name)){can.setWorkPlace(value);}
				if("edu".equals(name)){can.setEduBgd(value);}
				if("poli".equals(name)){can.setPoliStatus(value);}
				if("tel".equals(name)){can.setTel(value);}
				if("birth".equals(name)){can.setBirthday(value);}
				if("resume".equals(name)){can.setResume(value);}
				if("other".equals(name)){can.setOther(value);}
				
			} else {
				String name = item.getName();
				if(!"".equals(name)&&name!=null)
				{
					filePath = "photo/" + id+".png";
					can.setPhotoPath(filePath);
					itemFile = item;
				}
			}
		}
		if(filePath!=null)//上传了头像
		{
			ServletContext application = this.getServletContext();
			File file = new File(application.getRealPath("/")+filePath);//获得物理路径
			try {
				itemFile.write(file);//写入数据
			} catch (Exception e) {
				//文件上传错误
				warn(out, "文件上传失败！");
				end(out);
				return;
			}
		}
		
		boolean result = false;
		if(filePath!=null)//上传了头像
			result = new Dao().editCandidate(can);
		else
			result = new Dao().editCandidateWithoutImg(can);
		if (false == result) {
			 
			String url = "./editcandidate.jsp?id=" + id;
			out.println("<script>" + "alert('资料更新失败');" + "</script>");
			out.println("<script>" + "location.href='" + url + "'"
					+ "</script>");
		} else {
			String url = "../candidate.jsp?id=" + id;
			//out.println("<script>" + "alert('资料更新成功');" + "</script>");
			out.println("<script>" + "" + "location.href='" + url + "'"
					+ "</script>");
		}
	}
	
	/**
	 * 提示错误
	 * @param out 输出
	 * @param msg 信息
	 */
	private void warn(PrintWriter out,String msg)
	{
		msg="资料更新失败";
		out.println("<script language=javascript>" +
				"alert('"+msg+"');" +
				"location.href='./editcandidate.jsp?id=" + id+"';"+
			"</script>");
	}
	private void end(PrintWriter out)
	{
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
