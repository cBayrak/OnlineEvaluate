import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class tutor_Modify extends  HttpServlet
{

public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
	onBean eb=new onBean();
	PrintWriter pw = res.getWriter();
		int tutor_id=Integer.parseInt(req.getParameter("pid"));
		System.out.println(tutor_id+"This is cust_id");
		HttpSession ses=req.getSession(false);
		//String sesid=(String)ses.getAttribute("cust_id");
		int i=0;
		String modifycheck = req.getParameter("sub_modify");
		if(modifycheck.equals("Modify"))
		{
				try
				{
				String NAME = req.getParameter("NAME");
				String QUALI = req.getParameter("QUALI");
				String EXP=req.getParameter("EXP");
				String ADDRESS=req.getParameter("ADDRESS");
				String EMAIL=req.getParameter("EMAIL");
				String PHONE=req.getParameter("PHONE");
				String qry = " update tutor set NAME= '"+NAME+"',QUALI='"+QUALI+"',EXP='"+EXP+"',ADDRESS='"+ADDRESS+"',EMAIL='"+EMAIL+"',PHONE="+PHONE+" where TUTORID="+tutor_id+"";
				System.out.println("Before invModify");
				 i=eb.insertData(qry);
				}catch(Exception e)
					{System.out.println(e);}
				if(i>0)
				{
					
						res.sendRedirect("/online/admin/tutorTotal.jsp");
					
				}
		}
		else if(modifycheck.equalsIgnoreCase("delete"))
		{
			System.out.println("This is delete");
				try
				{
				String qry2 = "delete from tutor where TUTORID='"+tutor_id+"'";
				 String rp="delete from login where type='tut' and loginid='"+tutor_id+"'";
				  eb.setCommitState(false);
				int p1=eb.insertData(qry2);
				 int p2=eb.insertData(rp);
				if(p1>0 && p2>0) {
					eb.setCommit(true);
					res.sendRedirect("/online/admin/tutorTotal.jsp"); }
					else 	{ 
					eb.setCommit(false);
					String d="Some Error is occured please check";
					res.sendRedirect("/online/admin/display.jsp?content="+d);
					}

				}catch(Exception e)
			{System.out.println(e);}
		}
	}
}