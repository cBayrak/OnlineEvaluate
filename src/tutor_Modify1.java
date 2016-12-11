import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class tutor_Modify1 extends  HttpServlet
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
					
						res.sendRedirect("/online/tutor/tutorTotal.jsp");
					
				}
		}
		
	}
}