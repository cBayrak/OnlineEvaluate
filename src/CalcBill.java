import db.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class CalcBill extends  HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		db.onBean obj=new db.onBean();
		PrintWriter out = res.getWriter();
	    HttpSession ses=req.getSession(false);
	  	String ses_cust_id=(String)ses.getAttribute("ses_cust_id");
		Hashtable cart = new Hashtable();
		cart = (Hashtable)ses.getAttribute("cart");
		obj.setCommitState(false);
		String itemId;
		float subTot = 0f;
		float price = 0f;
		int qty = 0; 
		int i=0,j=0,k=0,l=0,m=0,n=0;
		String query;
		int tr_id = 0;
		for(Enumeration e = cart.keys() ; e.hasMoreElements() ;)
		{
			itemId=(String)e.nextElement();
			Hashtable h = obj.getBookDetails(itemId);
			qty = ((Integer)cart.get(itemId)).intValue();
			price = ((Float)h.get("BOOK_PRICE")).floatValue(); 
			subTot = subTot + qty*price;
		}
		String p_option=req.getParameter("selec");
		String result="";
		String c_no=req.getParameter("cr_no");
		String c_day=req.getParameter("c_day");
		String c_month=req.getParameter("c_month");
		String c_year=req.getParameter("c_year");
		String c_bank=req.getParameter("c_bank");
		String p_date=c_day+"-"+c_month+"-"+c_year;
		String pub_id = new String();
		String delAddr = req.getParameter("address");
		if(p_option.equalsIgnoreCase("ByCreditCard"))
		{
			result=obj.creditCheck(c_no,subTot,ses_cust_id);
			System.out.println(result+"             inifo");
		}
		else if(p_option.equalsIgnoreCase("CashOndelivery"))
		{
			result="true";
		}
		else
		{
			p_option = "ByDD";
			query="INSERT INTO SDDDETAILS VALUES("+c_no+",'"+p_date+"','"+c_bank+"',"+subTot+",'"+ses_cust_id+"')";
			n = obj.insertData(query);
			if(n>0)
				result="true";
		}
		if(result.equalsIgnoreCase("true"))
		{
			System.out.println(result+"            in inner if");
			int dDays=0;
			tr_id=obj.getMaxId("TRANSACTION","TRANS_ID")+1;
			String data="INSERT INTO TRANSACTION VALUES("+tr_id+",sysdate,"+subTot+",'"+ses_cust_id+"')";
			i=obj.insertData(data);
			for(Enumeration e = cart.keys() ; e.hasMoreElements() ;)
			{
				itemId=(String)e.nextElement();
				Hashtable h = obj.getBookDetails(itemId);
				qty = ((Integer)cart.get(itemId)).intValue();
				price = ((Float)h.get("BOOK_PRICE")).floatValue();
				float tot = qty*price;
				dDays = ((Integer)h.get("BOOK_DELIVERYTIME")).intValue();
				int tr_dt_id=obj.getMaxId("TRANSACTIONDETAILS","TRANS_DETAIL_NO")+1;
				query = "INSERT INTO TRANSACTIONDETAILS VALUES("+tr_dt_id+",'"+itemId+"',"+qty+","+tot+",sysdate+"+dDays+","+tr_id+",'"+p_option+"','"+delAddr+"')";
				j=obj.insertData(query);

				query="UPDATE BOOK SET BOOK_QTY=BOOK_QTY-"+qty+" WHERE BOOK_ID='"+itemId+"'";
				k=obj.insertData(query);
			}
			int py_id = obj.getMaxId("PAYEMENT","PAY_ID")+1;
			if(p_option.equalsIgnoreCase("ByCreditCard")|| p_option.equalsIgnoreCase("CashOndelivery"))
			{
				query="INSERT INTO PAYEMENT VALUES("+py_id+","+subTot+",sysdate+"+dDays+",'"+p_option+"',"+tr_id+",'"+ses_cust_id+"')";
				l = obj.insertData(query);
			}
			else
			{
				if(n > 0)
				{
					query="INSERT INTO PAYEMENT VALUES("+py_id+","+subTot+",sysdate+"+dDays+"+15,'"+p_option+"',"+tr_id+",'"+ses_cust_id+"')";
					l = obj.insertData(query);
				}
			}
			if(i!=0 && j!=0 && k!=0 && l!=0)
			{
				obj.setCommit(true);
				cart = null;
				ses.setAttribute("cart",cart);
			    res.sendRedirect("customer/display.jsp?content=Transaction completed successfully...!\nYour Transaction id : "+tr_id);
			}
			else
			{
				obj.setCommit(false);
				cart = null;
				ses.setAttribute("cart",cart);
				res.sendRedirect("customer/display.jsp?content=Transaction terminated...!");
			}

		}
		else
		{
			cart = null;
			ses.setAttribute("cart",cart);
			res.sendRedirect("customer/display.jsp?content=Transaction terminated due to invalid Creditcard...!");
		}
	}
}
