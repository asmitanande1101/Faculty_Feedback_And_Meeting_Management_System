import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class DisableTeacherClerkList extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String udept=(String)ses.getAttribute("uDept");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet rs1=st.executeQuery("Select * from Users where (uRole='TEACHER' or uRole='CLERK') and uStatus='DISABLE' and uDept='"+udept+"'");
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Select Teacher/Clerk from given Table</center></h1></font>"+
			"<img src=\"images/admin2.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"><br>"+
			"<center><table border=\"1\"><tr><th><font size=4>Teacher Name</font></th><th><font size=4>Department Name</font></th><th><font size=4>Role</font></th></tr>");
			while(rs1.next())
			{
				pw.println("<tr><td><a href=\"EnableTeacherClerk\"><font size=4>"+rs1.getString("uName")+"</font></a></td><td><font size=4>"+rs1.getString("uDept")+"</font></td><td><font size=4>"+rs1.getString("uRole")+"</font></td><td><a href=\"EnableTeacherClerk?uid="+rs1.getInt("uid")+"\"><font size=4>Enable</font></a></td></tr>");
			}
			pw.println("</table></center><br><center><a href=\"HODTask.html\"><font size=4>BACK</font></a></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}