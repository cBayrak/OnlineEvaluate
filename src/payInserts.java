import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class payInserts extends  HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{ 
		onBean obj=new onBean();
		PrintWriter out = response.getWriter();
	  	String cidd=request.getParameter("cidd");
		String bids=request.getParameter("bids");
		String camount=request.getParameter("camount");
        String selec=request.getParameter("selec");
        int pid=obj.getMaxId("paymentMaster","PAYID");
		String pas=obj.getpass(cidd);
		if(selec.equals("ByD.D")) {
		String c_no=request.getParameter("c_no");
		String c_day=request.getParameter("c_day");
		String c_month=request.getParameter("c_month");
		String c_year=request.getParameter("c_year");
		String c_bank=request.getParameter("c_bank");
		String p_date=c_day+"-"+c_month+"-"+c_year;
				
String qr="insert into paymentMaster values("+pid+",'"+camount+"',now(),'bydd','"+cidd+"')";
String qr2="insert into dddetails values ("+c_no+",'"+p_date+"','"+c_bank+"','"+camount+"',"+pid+")";
String qu="update batch set STRENGTH=STRENGTH+1 where BATCHID='"+bids+"'";
obj.setCommitState(false);
int i=obj.insertData(qr);
int j=obj.insertData(qr2);
int k=obj.insertData(qu);
if(i!=0 && j!=0 && k!=0)
			{

obj.setCommit(true);

String d= "your login Id  is   "+cidd+"  and password is "+pas;
response.sendRedirect("customer/display.jsp?content= "+d);

			}

else
	{
	String d ="some error has occured";
	obj.setCommit(false);
		response.sendRedirect("customer/display.jsp?content="+d);
	}
        
		}
		else if(selec.equalsIgnoreCase("ByCreditCard"))
		{

			String cardno=request.getParameter("cardno");
			float camounts=Float.parseFloat(camount);
			
			String result=obj.creditCheck(cardno,camounts);
			System.out.println(result+"             inifo");
			if(result.equals("true")) {

String qr="insert into paymentMaster values("+pid+",'"+camount+"',sysdate,'bycreditcard','"+cidd+"')";
String qu="update batch set STRENGTH=STRENGTH+1 where BATCHID='"+bids+"'";
obj.setCommitState(false);		
				int i=obj.insertData(qr);
				int j=obj.insertData(qu);	
		if(i!=0 && j!=0 ) {
		obj.setCommit(true);
String d= "your login Id  is   "+cidd+"  and password is "+pas;
response.sendRedirect("customer/display.jsp?content= "+d);
		}
		else {
			obj.setCommit(false);
String d= "some error has occured wjhile inserting data";
response.sendRedirect("customer/display.jsp?content= "+d);
		}
			}
else if(result.equals("false")) {

String d= "The credit check is less cannot be register";
String query="delete from customer where cust_id='"+cidd+"', and password ='"+pas+"'";
response.sendRedirect("customer/display.jsp?content= "+d);
}
else {
String d= "NO CREDITCARD WITH THIS ID ";
response.sendRedirect("customer/display.jsp?content= "+d);
}
		}
else 
			{
	String d ="some error  has occured";
	response.sendRedirect("customer/display.jsp?content="+d);
	}
	}
}

