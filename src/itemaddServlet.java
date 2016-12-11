import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class itemaddServlet extends  HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	onBean rea=new onBean();
	PrintWriter out = response.getWriter();
	HttpSession ses=request.getSession(false);
	String ses_cust_id=(String)ses.getAttribute("ses_cust_id");
	String suggestion=request.getParameter("sugg");
	System.out.println(suggestion+"1111111111");
	String data="";
	int i=0;
	if(suggestion==null)
		suggestion="";
	if(suggestion.equals("do"))
		{
		 int s_no=rea.getMaxId("SUGGESTIONS","sl_no")+1;
			 data="insert into feedback values("+s_no+",'"+ses_cust_id+"',sysdate,'";
		  	 data=data+request.getParameter("txt_desc")+"','";
			 data=data+request.getParameter("item_id")+"')";
		  i=rea.insertData(data);
			if(i>0)
			{
			response.sendRedirect("customer/display.jsp?content=Thank you for Your Suggestion");
			} 	
		}
		else
		{
		String id=request.getParameter("txt_id");
		data="insert into book values(\'"+id+"\','";
  		data=data+request.getParameter("txt_desc")+"',";
		data=data+request.getParameter("txt_price")+",";
   		data=data+request.getParameter("txt_qty")+",'";
	   	data=data+request.getParameter("txt_category")+"','";
   		data=data+request.getParameter("txt_image")+"',";
   		data=data+request.getParameter("txt_days")+",'";
   		data=data+request.getParameter("txt_title")+"','";
   		data=data+request.getParameter("txt_aut")+"')";
		i=rea.insertData(data);
		String content;
		if(i>0)
		{
			content = "Book added...";
			response.sendRedirect("admin/display.jsp?content="+content);
		}
		else
		{
			content = "Problem in adding book...";
			response.sendRedirect("admin/display.jsp?content="+content);

		}
		}
	}
}
