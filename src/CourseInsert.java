import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class CourseInsert extends  HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	System.out.println("in servlet");
	onBean obj=new onBean();
	PrintWriter out = response.getWriter();
	
	String data="insert into course values("+request.getParameter("cid")+",'";
  	 data=data+request.getParameter("cname")+"','";
	 data=data+request.getParameter("cdur")+"',";
   	 data=data+request.getParameter("cost")+",'";
   	 data=data+request.getParameter("descp")+"')";
   	  
	int j=obj.insertData(data);	
	 if( j!=0)
	{
		 String d="You Have successfully Inserted course data ";
		response.sendRedirect("admin/display.jsp?content="+d);
	}
	else
	{
		String d="course not inserted data some error has occured";

		response.sendRedirect("admin/display.jsp?content="+d);
	}
	
	}
}