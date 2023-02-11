import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ChangePasswordSSFaculty extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String pass=(String)ses.getAttribute("uPass");
			String cPass,nPass,rPass;
			cPass=req.getParameter("txtCurrentPassword");
			nPass=req.getParameter("txtNewPassword");
			rPass=req.getParameter("txtRetypePassword");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String q="update Users set uPassword='"+nPass+"'where uPassword='"+cPass+"'";
			if(pass.equals(cPass))
			{
				if(nPass.equals(rPass))
				{
					st.execute(q);
					RequestDispatcher rd=req.getRequestDispatcher("FacultyPasswordChanged.html");
					rd.forward(req,res);	
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("FacultyWrongPasswordCP.html");
					rd.forward(req,res);
				}	
			}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("FacultyWrongPasswordCP.html");
				rd.forward(req,res);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}