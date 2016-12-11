import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class BatchInsert extends  HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	System.out.println("in servlet");
	onBean obj=new onBean();
	PrintWriter out = response.getWriter();


int d=Integer.parseInt(request.getParameter("sday"));
int m=Integer.parseInt(request.getParameter("smonth"));
int y=Integer.parseInt(request.getParameter("syear"));
java.sql.Date sdts=new java.sql.Date(y-1900,m,d);


d=Integer.parseInt(request.getParameter("eday"));
m=Integer.parseInt(request.getParameter("emonth"));
y=Integer.parseInt(request.getParameter("eyear"));	
java.sql.Date edts=new java.sql.Date(y-1900,m,d);



String  tbid=request.getParameter("bid");
int bid=Integer.parseInt(tbid);
String bname=request.getParameter("bname");
String  tcid=request.getParameter("cid");
int cid=Integer.parseInt(tcid);
String descp=request.getParameter("descp");
String  str=request.getParameter("str");
int stre=Integer.parseInt(str);
String  ttid=request.getParameter("tid");
int tid=Integer.parseInt(ttid);


int j= obj.pData(bid,bname,descp,sdts,edts,cid,tid,stre);

	 if( j!=0)
	{
		 String dpp="You Have successfully Inserted  data ";
		response.sendRedirect("admin/display.jsp?content="+dpp);
	}
	else
	{
		String dpp="Data not inserted data ";

		response.sendRedirect("admin/display.jsp?content="+dpp);
	}
	
	}
}
