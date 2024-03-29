package test;


import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("Hello<br/>");
		
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipartContent) 
		{
			out.println("You are not trying to upload<br/>");
			return;
		}
		out.println("You are trying to upload<br/>");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try 
		{
			List<FileItem> fields = upload.parseRequest(request);
			out.println("Number of fields: " + fields.size() + "<br/><br/>");
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) 
		    {
				out.println("No fields found");
				return;
			}
			out.println("<table border=\"1\">");
			
			while (it.hasNext())
			{
				out.println("<tr>");
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				
				if (isFormField) 
				{
					out.println("<td>regular form field</td><td>FIELD NAME: " + fileItem.getFieldName() + 		"<br/>STRING: " + fileItem.getString());
					out.println("</td>");
				} 
				else 
				{
					out.println("<td>file form field</td><td>FIELD NAME: " + fileItem.getFieldName() +
							//"<br/>STRING: " + fileItem.getString() +
							"<br/>NAME: " + fileItem.getName() +
							"<br/>CONTENT TYPE: " + fileItem.getContentType() +
							"<br/>SIZE (BYTES): " + fileItem.getSize() +
							"<br/>TO STRING: " + fileItem.toString()
							);
					out.println("</td>");
				}
				
			out.println("<tr>");
		
			}
			
		out.println("</table>");
		}
		catch (FileUploadException e)
			{
				e.printStackTrace();
			}
		
		
}
	
	
	
	
}
