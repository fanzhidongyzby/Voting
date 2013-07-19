package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class System extends HttpServlet {

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

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");

		String sts=request.getParameter("sysStatus");
		String msg="";
		if(sts!=null&&"open".equals(sts))//��
		{
			this.getServletContext().setAttribute("sysStatus", "open");
			msg="��";
		}
		else
		{
			this.getServletContext().setAttribute("sysStatus", "closed");
			msg="�ر�";
		}
		String url = "./admin.jsp";
		out.println("<script>" + "alert('ͶƱϵͳ��"+msg+"');" + "</script>");
		out.println("<script>" + "location.href='" + url + "'"
				+ "</script>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);// ���û���,���ֵ��������fileinputstream����bytearrayinputstream
		//factory.setRepository(new File("d:\\temp"));//������ʱ���Ŀ¼,Ĭ����new File(System.getProperty("java.io.tmpdir"))
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setSizeMax(1 * 1024 * 1024);//1M
		//sfu.setHeaderEncoding("utf-8");  
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			//������ȡ����
			warn(out, "�����ݽ�������");
			end(out);
			return;
		}
		Iterator it = fileList.iterator();
		FileItem itemFile = null;
		String filePath=null;
		while (it.hasNext()) {
			FileItem item = (FileItem) it.next();

			if (item.isFormField()) {
				
			} else {
				String name = item.getName();
				if(!"".equals(name)&&name!=null)
				{
					ServletContext application = this.getServletContext();
					filePath = application.getRealPath("/")+"admin/info.txt";
					itemFile = item;
					break;
				}
			}
		}
		if(filePath!=null)//�ϴ�����Ϣ
		{
			
			File file = new File(filePath);//�������·��
			try {
				itemFile.write(file);//д������
			} catch (Exception e) {
				//�ļ��ϴ�����
				warn(out, "�ļ��ϴ�ʧ�ܣ�");
				end(out);
				return;
			}
		}
		
		boolean result = false;
		result=new Dao().uploadInfo(filePath);//�ϴ�ѡ����Ϣ�ļ�
		File file = new File(filePath);
		if(file.exists())file.delete();//ɾ���ļ�
		String url = "./admin.jsp";
		if (false == result) {
			 
			out.println("<script>" + "alert('ѡ�������ϴ�ʧ�ܣ�');" + "</script>");
			out.println("<script>" + "location.href='" + url + "'"
					+ "</script>");
		} else {
			out.println("<script>" + "alert('ѡ�������ϴ��ɹ���');" + "</script>");
			out.println("<script>" + "" + "location.href='" + url + "'"
					+ "</script>");
		}

		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	/**
	 * ��ʾ����
	 * @param out ���
	 * @param msg ��Ϣ
	 */
	private void warn(PrintWriter out,String msg)
	{
		msg="ѡ�������ϴ�ʧ�ܣ�";
		out.println("<script language=javascript>" +
				"alert('"+msg+"');" +
				"location.href='./admin.jsp?"+
			"</script>");
	}
	private void end(PrintWriter out)
	{
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
