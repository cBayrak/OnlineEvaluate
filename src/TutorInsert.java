import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class TutorInsert extends  HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	System.out.println("in servlet");
	onBean obj=new onBean();
	PrintWriter out = response.getWriter();
	
	String tids=request.getParameter("tutid");
	String data="insert into tutor values("+request.getParameter("tutid")+",'";
  	 data=data+request.getParameter("tnam")+"','";
	 data=data+request.getParameter("quali")+"','";
   	 data=data+request.getParameter("exp")+"','";
   	 data=data+request.getParameter("addr")+"','";
   	 data=data+request.getParameter("email")+"',";
   	  data=data+request.getParameter("phno")+")";
	 	System.out.println("lllllllllllllll"+data);
	
	
	 Random r=new Random();
	int p=r.nextInt(20000);
	String pass="tut"+p;

	String qr="insert into login values('"+tids+"','"+pass+"','tut')";
	 
	  obj.setCommitState(false);
int i=obj.insertData(qr);
	int j=obj.insertData(data);	 
	 if( j!=0 && i!=0)
	{
		 
		 obj.setCommit(true);
		 String d="You Have successfully Inserted Tutor data and tutor login id is"+tids+"password is+"+pass;
		response.sendRedirect("admin/display.jsp?content="+d);
	}
	else
	{
		obj.setCommit(false);
		String d="Totor not inserted  Some problem arises ";

		response.sendRedirect("admin/display.jsp?content="+d);
	}
	
	}
}