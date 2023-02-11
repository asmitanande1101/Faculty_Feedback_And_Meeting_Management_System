import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ShowDeptFacultyList extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String dept=req.getParameter("CmbDept");
			ResultSet rs1=st.executeQuery("Select * from Users where (uRole='HOD' or uRole='TEACHER') and uDept='"+dept+"'");
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Select HOD/Teacher from given Table</center></h1></font>"+
			"<img src=\"images/Admin23.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"><br>"+
			"<img src=<center><table border=\"1\"><tr><th><font size=4> Faculty Name</font></th><th><font size=4>Role</font></th></tr></center>");
			while(rs1.next())
			{
				pw.println("<tr><td><a href=\"SelectSubjectForDeptFacuty\"><font size=4>"+rs1.getString("uName")+"</font></a></td><td><font size=4>"+rs1.getString("uRole")+"</font></td><td><a href=\"SelectSubjectForDeptFacuty?uid="+rs1.getInt("uid")+"\"><font size=4>Show</font></a></td></tr>");
			}
			pw.println("</table></center><br><a href=\"ADMINTask.html\"><font size=4>BACK</font></a></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}