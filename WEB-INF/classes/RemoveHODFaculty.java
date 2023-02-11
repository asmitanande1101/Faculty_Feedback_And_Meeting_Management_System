import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class RemoveHODFaculty extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String id=req.getParameter("uid");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			st.execute("delete from Users where uid="+id);
			st.execute("delete from TeacherSubject where uId="+id);
			ResultSet  rs1=st.executeQuery("select * from Users where uID="+id);
			if(rs1.next())
			{
				if(rs1.getString("uRole").equals("StudentSectionFaculty"))
				{
					RequestDispatcher rd=req.getRequestDispatcher("FacultyDeletingError.html");
					rd.forward(req,res);
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("HODDeletingError.html");
					rd.forward(req,res);
				}
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("HODFacultyDeleted.html");
					rd.forward(req,res);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}