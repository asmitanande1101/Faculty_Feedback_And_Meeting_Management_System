import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class EnableHODFaculty extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String id=req.getParameter("uid");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			st.execute("update Users set uStatus='ENABLE' where uID="+id);
			ResultSet  rs1=st.executeQuery("select * from Users where uStatus='DISABLE'");
			if(rs1.next())
			{
				if(rs1.getString("uID").equals(id))
				{
					RequestDispatcher rd=req.getRequestDispatcher("HODFacultyEnablingError.html");
					rd.forward(req,res);
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("HODFacultyEnabled.html");
					rd.forward(req,res);
				}	
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("HODFacultyEnabled.html");
					rd.forward(req,res);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}