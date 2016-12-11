import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.onBean;
public class LoginServlet extends  HttpServlet
{
 public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	 onBean obj=new onBean();
	 response.setContentType("text/html");
 	 PrintWriter out = response.getWriter();
 	 String login=request.getParameter("login");
	 String password=request.getParameter("pwd");
	 String option = request.getParameter("radio");
	 String as="Login Failed";
	 if(login.equalsIgnoreCase("admin"))
		{
		  boolean flag=obj.adminLogin(login,password);
		  if(flag)
		      response.sendRedirect("admin/home.html");
		  else
			{
			  response.sendRedirect("home.jsp?result="+as);
			}
		}
	 else
		{
		  if(option.equals("customer"))
			{
			String result=obj.check(login,password,"user");
			System.out.println("jjjjjjjjj"+result);
			if(result.equalsIgnoreCase("true"))
			{	
							
				HttpSession ses=request.getSession(true);
			 ses.setAttribute("ses_cust_id",login);
			 response.sendRedirect("customer/home.html");
			
			}
			else
			{
			 System.out.println("in else of login");
			response.sendRedirect("home.jsp?result="+result);
			}
			}
			else if(option.equals("tutor"))
			{
 System.out.println("ppppppppppppppppppppppppppppppppp");
			String result=obj.check(login,password,"tut");
			System.out.println("sssssssssss"+result);
			if(result.equalsIgnoreCase("true"))
				{
			 System.out.println("---------------");
			 HttpSession ses=request.getSession(true);
			 ses.setAttribute("ses_tut_id",login);
			 response.sendRedirect("tutor/home.html");
						}
						else
			{
				System.out.println("in else of login");
			response.sendRedirect("home.jsp?result="+result);}
		
			}
		
			 		
			else
			{
			 System.out.println("in else of login");
			response.sendRedirect("home.jsp?result="+as);
			}
			}
		}
	 
	}

