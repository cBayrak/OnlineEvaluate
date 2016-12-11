import db.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddToCart extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		db.onBean obj = new db.onBean();
		HttpSession ses = req.getSession(true);
		String itemId = req.getParameter("Id");
		System.out.println("ItemId : "+itemId);
		int qty = 1;
		Hashtable cart;
		cart = (Hashtable)ses.getAttribute("cart");
		System.out.println("Firsr cart : "+cart);
		if(cart == null)
			cart = new Hashtable();
		if(cart.isEmpty())
		{
			cart.put(itemId,new Integer(qty));
		}
		else
		{
			boolean found = false;
			for(Enumeration e = cart.keys() ; e.hasMoreElements() ;)
		    {
				int temp = 0;
				String item=(String)e.nextElement();
				if(item.equals(itemId))
				{
					temp = ((Integer)cart.get(item)).intValue() + 1;
					cart.put(item,new Integer(temp));
					found = true;
					break;
				}
			}
			if(!found)
				cart.put(itemId,new Integer(qty));
		}
		Hashtable ht = obj.getBookDetails(itemId);
		String category = (String)ht.get("TECHNOLOGY");
		System.out.println("Second cart : "+cart);
		ses.setAttribute("cart",cart);
		res.sendRedirect("customer/Shopping.jsp?Item="+category);
	}
}