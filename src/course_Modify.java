import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class course_Modify extends  HttpServlet
{

public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
	onBean eb=new onBean();
	PrintWriter pw = res.getWriter();
		int c_id=Integer.parseInt(req.getParameter("pid"));
		System.out.println(c_id+"This is cust_id");
		HttpSession ses=req.getSession(false);
		//String sesid=(String)ses.getAttribute("cust_id");
		int i=0;
		String modifycheck = req.getParameter("sub_modify");
		if(modifycheck.equals("Modify"))
		{
				try
				{
				String NAME = req.getParameter("NAME");
				String DURATION = req.getParameter("DURATION");
				String COST=req.getParameter("COST");
				String CDESC=req.getParameter("CDESC");
				
				String qry = " update course set NAME= '"+NAME+"',DURATION='"+DURATION+"',COST="+COST+",CDESC='"+CDESC+"' where COURSEID="+c_id+"";
				System.out.println("Before invModify");
				 i=eb.insertData(qry);
				}catch(Exception e)
					{System.out.println(e);}
				if(i>0)
				{
					
						res.sendRedirect("/online/admin/courseTotal.jsp");
					
				}
		}
		else if(modifycheck.equalsIgnoreCase("delete"))
		{
			System.out.println("This is delete");
				try
				{
				String qry2 = "delete from course where COURSEID="+c_id+"";
				 i=eb.insertData(qry2);
				if(i>0)
					res.sendRedirect("/online/admin/courseTotal.jsp");
				}catch(Exception e)
			{System.out.println(e);}
		}
	}
}