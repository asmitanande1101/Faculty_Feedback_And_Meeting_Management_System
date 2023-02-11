import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ApplySubject extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String teacherId=req.getParameter("ComboTeacher");
			System.out.println("Teacher Name="+teacherId);
			String subName=req.getParameter("ComboSubject");
			System.out.println("Subject name="+subName);
			String sDept=(String)ses.getAttribute("uDept");
			System.out.println("Dept="+sDept);
			String tid="",sid="",sYear="";
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet rs1=st.executeQuery("select uId from Users where uName='"+teacherId+"'");
			if(rs1.next())
			{
				 tid=rs1.getString("uId");
				 System.out.println("Teacher Id="+tid);
			}
			ResultSet rs2=st.executeQuery("select sYear from Subjects where sName='"+subName+"'");
			if(rs2.next())
			{
				  sYear=rs2.getString("sYear");
				 System.out.println("Subject Year="+sYear);
			}
			String query="insert into TeacherSubject values ('"+tid+"','"+sDept+"','"+sYear+"','"+subName+"')";
			st.execute(query);
			RequestDispatcher rd=req.getRequestDispatcher("SubjectAssigned.html");
			rd.forward(req,res);	
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}