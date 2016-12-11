import db.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateCart extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		HttpSession ses = req.getSession();
		Hashtable cart;
		Hashtable temp = null;
		cart = (Hashtable)ses.getAttribute("cart");
		if(cart == null)
			cart = new Hashtable();
		System.out.println("Cart values to update : "+cart);
		if(!cart.isEmpty())
		{
			temp = new Hashtable();
			for(Enumeration e = cart.keys() ; e.hasMoreElements() ;)
		    {
				String item=(String)e.nextElement();
				String status = req.getParameter(item+"check");
				if(status == null)
				{
					System.out.println("one   One   one");
					String qt = req.getParameter(item+"qty");
					if(qt == null)
						qt = new String("0");
					int qty = Integer.parseInt(qt);
					if(qty != 0)
					{
						System.out.println("Two Two Two");
						temp.put(item,new Integer(qty));
						System.out.println("Updated cart... "+temp);
					}
				}
			}
		}
		if(!temp.isEmpty())
		{
			ses.setAttribute("cart",temp);
			res.sendRedirect("customer/Showcart.jsp");
		}
		else
		{
			cart = null;
			ses.setAttribute("cart",cart);
			res.sendRedirect("customer/Shopping.jsp");
		}		
	}
}