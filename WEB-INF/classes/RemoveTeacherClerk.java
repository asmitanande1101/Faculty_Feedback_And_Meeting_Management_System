import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class RemoveTeacherClerk extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String id=req.getParameter("uid");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			st.execute("delete from Users where uid="+id);
			ResultSet  rs1=st.executeQuery("select * from Users where uID="+id);
			if(rs1.next())
			{
				if(rs1.getString("uRole").equals("CLERK"))
				{
					RequestDispatcher rd=req.getRequestDispatcher("ClerkDeletingError.html");
					rd.forward(req,res);
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("TeacherDeletingError.html");
					rd.forward(req,res);
				}
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("TeacherClerkDeleted.html");
					rd.forward(req,res);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}