import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class batch_Modify extends  HttpServlet
{

public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
	onBean eb=new onBean();
	PrintWriter pw = res.getWriter();
		int b_id=Integer.parseInt(req.getParameter("pid"));
		System.out.println(b_id+"This is cust_id");
		HttpSession ses=req.getSession(false);
		//String sesid=(String)ses.getAttribute("cust_id");
		int i=0;
		String modifycheck = req.getParameter("sub_modify");
		if(modifycheck.equals("Modify"))
		{
				try
				{
				String NAME = req.getParameter("NAME");
				String BDESC = req.getParameter("BDESC");
				String STARTDATE=req.getParameter("startdate");
				String ENDDATE=req.getParameter("enddate");
				String COURSEID=req.getParameter("COURSEID");
				String TUTORID=req.getParameter("TUTORID");
				String STRENGTH=req.getParameter("STRENGTH");
				
				String qry ="update batch set NAME= '"+NAME+"',BDESC='"+BDESC+"',STARTDATE='"+STARTDATE+"',ENDDATE='"+ENDDATE+"',COURSEID="+COURSEID+",TUTORID="+TUTORID+",STRENGTH="+STRENGTH+" where BATCHID="+b_id+"";
				System.out.println("Before invModify");
				 i=eb.insertData(qry);
				}catch(Exception e)
					{System.out.println(e);}
				if(i>0)
				{
					
						res.sendRedirect("/online/admin/batchTotal.jsp");
					
				}
		}
		else if(modifycheck.equalsIgnoreCase("delete"))
		{
			System.out.println("This is delete");
				try
				{
				String qry2 = "delete from batch where BATCHID="+b_id+"";
				 i=eb.insertData(qry2);
				if(i>0)
					res.sendRedirect("/online/admin/batchTotal.jsp");
				}catch(Exception e)
			{System.out.println(e);}
		}
	}
}