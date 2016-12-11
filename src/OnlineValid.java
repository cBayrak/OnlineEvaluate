import db.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class OnlineValid extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		db.onBean obj = new db.onBean();
		int sloginid;
		String str;
		ResultSet rs1;
		String pass = new String();
		HttpSession ses = req.getSession(true);

		sloginid = Integer.parseInt(req.getParameter("uname"));
		pass = req.getParameter("password");

		try
		{
			rs1 = obj.seekLogin1("select *  from seek_per where  seek_code="+sloginid);
			if(rs1.next())
			{
				String ds=rs1.getString(2);
				System.out.println(ds);
				Integer seek = new Integer(sloginid);
				ses.setAttribute("slogin1",seek);
				ses.setAttribute("str1",ds);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		System.out.println(sloginid);
		System.out.println(pass);
		String query="select *  from online_testlogin where  seek_code="+sloginid+" and  key="+pass; 
		boolean b=obj.seekLogin(query);
		if(!b)
		{
			System.out.println("hai");
			res.sendRedirect("onlinelogin.htm");
		}
		else
		{     
			System.out.println("welcome");
			res.sendRedirect("onlineright.jsp");
		}
	}
}