import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class StoreFacultyId extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String teacherId=req.getParameter("ComboTeacher");
			//String subName=req.getParameter("ComboSubject");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet rs1=st.executeQuery("select uId from Users where uName='"+teacherId+"'");
			while(rs1.next())
			{
				String tid=rs1.getString("uId");
				ses.setAttribute("tId",tid);
			}
		/*	ResultSet rs2=st.executeQuery("select sId from Subjects where sName='"+subName+"'");
			while(rs2.next())
			{
				String sid=rs2.getString("sId");
				ses.setAttribute("subId",subName);
			}	*/
			RequestDispatcher rd=req.getRequestDispatcher("ApplyFeedbackSSFaculty.html");
			rd.forward(req,res);
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}