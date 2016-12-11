import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class cust_Modify1 extends  HttpServlet
{

public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
	onBean eb=new onBean();
	PrintWriter pw = res.getWriter();
		String cust_id=req.getParameter("pid");
		System.out.println(cust_id+"This is cust_id");
		HttpSession ses=req.getSession(false);
		//String sesid=(String)ses.getAttribute("cust_id");
		int i=0;
		String modifycheck = req.getParameter("sub_modify");
		if(modifycheck.equals("Modify"))
		{
				try
				{
				String CUST_NAME = req.getParameter("CUST_NAME");
				String CUST_ADDRESS = req.getParameter("CUST_ADDRESS");
				String CUST_CITY=req.getParameter("CUST_CITY");
				String CUST_STATE=req.getParameter("CUST_STATE");
				String CUST_COUNTRY=req.getParameter("CUST_COUNTRY");
				String CUST_FAX1=req.getParameter("CUST_FAX1");
				String CUST_DOB=req.getParameter("CUST_DOB");
				String CUST_MAIL=req.getParameter("CUST_MAIL");
				String SEX=req.getParameter("SEX");
				String CUST_RESNO=req.getParameter("CUST_RESNO");
				String CUST_OFFNO=req.getParameter("CUST_OFFNO");
				String CUST_MOBILE=req.getParameter("CUST_MOBILE");
				String CUST_ADD2=req.getParameter("CUST_ADD2");
				String qry = " update customer set CUST_NAME= '"+CUST_NAME+"',CUST_ADDRESS='"+CUST_ADDRESS+"',CUST_CITY='"+CUST_CITY+"',CUST_STATE='"+CUST_STATE+"',CUST_COUNTRY='"+CUST_COUNTRY+"',CUST_FAX1='"+CUST_FAX1+"',CUST_DOB='"+CUST_DOB+"',CUST_MAIL='"+CUST_MAIL+"',SEX='"+SEX+"',CUST_RESNO='"+CUST_RESNO+"',CUST_OFFNO='"+CUST_OFFNO+"',CUST_MOBILE='"+CUST_MOBILE+"',CUST_ADD2='"+CUST_ADD2+"' where CUST_ID='"+cust_id+"'";
				System.out.println("Before invModify");
				 i=eb.insertData(qry);
				}catch(Exception e)
					{System.out.println(e);}
				if(i>0)
				{
					
						res.sendRedirect("/online/customer/customerTotal.jsp");
					
				}
		}
		
	}
}