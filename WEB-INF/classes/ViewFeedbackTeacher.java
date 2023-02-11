import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ViewFeedbackTeacher extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String uid=(String)ses.getAttribute("uId");
			String uName=(String)ses.getAttribute("uName");
			ResultSet rs1=st.executeQuery("Select count(*) from Feedback where uName='"+uName+"' and uId='"+uid+"'");
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=gray><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Your feedback</center></h1></font>"+
			"<font color=red><h1><center>Feedback given by total no. of students :</center></h1></font>");
			if(rs1.next())
			{
				pw.println(rs1.getString()
			}
			"<center><table border=\"1\"><tr><th>Teacher Name</th><th>Department Name</th></tr>");
			while(rs1.next())
			{
				pw.println("<tr><td><a href=\"RemoveTeacherClerk\">"+rs1.getString("uName")+"</a></td><td>"+rs1.getString("uDept")+"</td><td><a href=\"RemoveTeacherClerk?uid="+rs1.getInt("uid")+"\">Remove</a></td></tr>");
			}
			pw.println("</table></center><br><center><a href=\"HODTask.html\">BACK</a></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}