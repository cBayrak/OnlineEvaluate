import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class custRegServlet extends  HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	onBean obj=new onBean();
	PrintWriter out = response.getWriter();
	//String pwd=obj.getMaxcustId("cust");
	String bid=request.getParameter("batchid");
Random rsp=new Random();
	int login=rsp.nextInt(1000);

	Random r=new Random();
	int p=r.nextInt(20000);
	String pass="user"+p;
	//int login=obj.getMaxIId("customer","CUST_ID");
    String data="insert into customer values('"+login+"','";
  	 data=data+request.getParameter("txt_name")+"','";
	
	 data=data+request.getParameter("txt_add1")+"','";
   	 data=data+request.getParameter("txt_city")+"','";
   	 data=data+request.getParameter("txt_state")+"','";
   	 data=data+request.getParameter("txt_country")+"','";
   	data=data+request.getParameter("txt_fax")+"','";
    String date=request.getParameter("day")+"-";
	 date=date+request.getParameter("month")+"-";
	 date=date+request.getParameter("year");
	 data=data+date+"','";
	 data=data+request.getParameter("txt_mail")+"','";
	 data=data+request.getParameter("txt_sex")+"','";
	 data=data+request.getParameter("txt_resno")+"','";
	 data=data+request.getParameter("txt_offno")+"','";
 	 data=data+request.getParameter("txt_mobile")+"','";
   	 
  // String date=request.getParameter("day")+"-";
//	 date=date+request.getParameter("month")+"-";
//  date=date+request.getParameter("year");
//	 data=data+date+"','";
 	 
// 	 data=data+request.getParameter("txt_resno")+"','";
//	 data=data+request.getParameter("txt_offno")+"','";
 //	 data=data+request.getParameter("txt_mobile")+"','";
//	 data=data+request.getParameter("txt_add2")+"',";
	 data=data+request.getParameter("txt_add2")+"',";
	 data=data+(Integer.parseInt(request.getParameter("batchid")))+")";
	 obj.setCommitState(false);
	 String qry="insert into login values ('"+login+"','"+pass+"','user')";
	 System.out.println("Data value : "+data);
	 int j=obj.insertData(qry);
	 int i=obj.insertData(data);
		 if(i!=0 && j!=0 )
	{
				// String d="You Have successfully Registered.<BR>Login Id="+login+"<BR> and Password="+pwd+"<br><a href=/estudy/login.jsp>login here</a><BR>We Recomend you to Change password";
			HttpSession ses=request.getSession(true);
			 ses.setAttribute("ses_cust_id",login+"");
			ses.setAttribute("ses_bid_id",bid);
		obj.setCommit(true);
		response.sendRedirect("customer/paycheck.jsp");
	}
	else
	{
	obj.setCommit(false);
		response.sendRedirect("customer/display.jsp?content=ErrorPage");
	}
	
	}
}
