import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class SendMailListDeptWise extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String uDept=(String)req.getParameter("CmbDept");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet rs1=st.executeQuery("Select * from Users where ((uRole='TEACHER' or uRole='HOD') or uRole='StudentSectionFaculty') and uDept='"+uDept+"'");
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Select Faculty from given Table</center></h1></font>"+
			"<img src=\"images/admin2.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"/><br>"+
			"<center><table border=\"1\"><tr><th><font size=4>Faculty Name</font></th><th><font size=4>Faculty ID</font></th></tr>");
			while(rs1.next())
			{
				pw.println("<tr><td><font size=4>"+rs1.getString("uName")+"</font></td><td><font size=4>"+rs1.getString("uId")+"</font></td><td><a href=\"CreateMail?facultyid="+rs1.getInt("uId")+"\"><font size=4>Send</font></a></td></tr>");
				ses.setAttribute("rRole",rs1.getString("uRole")+"");
			}
			pw.println("</table></center><br><center><a href=\"ADMINTask.html\"><font size=4>BACK</font></a></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}