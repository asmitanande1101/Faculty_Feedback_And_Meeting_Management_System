import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ShowFacultyList extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet rs1=st.executeQuery("Select * from Users where uRole='StudentSectionFaculty'");
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Select faculty from given Table</center></h1></font>"+
			"<img src=\"images/Admin23.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"><br>"+
			"<img src=<center><table border=\"1\"><tr><th><font size=4>HOD Name</font></th><th><font size=4>Department Name</font></th></tr></center>");
			while(rs1.next())
			{
				pw.println("<tr><td><a href=\"RemoveHODFaculty\"><font size=4>"+rs1.getString("uName")+"</font></a></td><td><font size=4>"+rs1.getString("uDept")+"</font></td><td><a href=\"RemoveHODFaculty?uid="+rs1.getInt("uid")+"\"><font size=4>Remove</font></a></td></tr>");
			}
			pw.println("</table></center><br><a href=\"ADMINTask.html\"><font size=4>BACK</font></a></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}