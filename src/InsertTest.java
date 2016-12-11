import db.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InsertTest extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		db.onBean obj = new db.onBean();
		
		String bid = req.getParameter("batchId");
		String sday = req.getParameter("sday");
		String smonth = req.getParameter("smonth");
		String syear = req.getParameter("syear");
		boolean flag = false;
		HttpSession ses = req.getSession(true);
		Hashtable h = obj.getBatCustIds(bid);
		for(Enumeration e = h.keys();e.hasMoreElements();)
		{
			String custId = (String)e.nextElement();
			int i=obj.getMaxId("ONLINE_TESTLOGIN", "SL_NO");
			//String query = "INSERT INTO ONLINE_TESTLOGIN VALUES("+i+",TO_DATE('"+sday+"-"+smonth+"-"+syear+"','DD-MM-YYYY'),'"+custId+"',"+bid+",'NOTTAKEN','NORESULT')";
			String query = "INSERT INTO ONLINE_TESTLOGIN VALUES("+i+",'NOTTAKEN','"+sday+"-"+smonth+"-"+syear+"','NORESULT','"+custId+"',"+bid+")";
			System.out.println("Query : "+query);
			int k = obj.insertData(query);
			if(k>0)
				flag = true;
			else
				flag = false;
		}
		String content = new String();
		if(flag)
		{
			System.out.println("hai");
			content = "Test saved";
			res.sendRedirect("admin/display.jsp?content="+content);
		}
		else
		{     
			System.out.println("welcome");
			content = "Test not saved";
			res.sendRedirect("admin/display.jsp?content="+content);
		}
	}
}