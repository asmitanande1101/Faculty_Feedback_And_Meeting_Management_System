import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class SelectFaculty extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			//HttpSession ses1=req.getSession(true);
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String sDept=(String)ses.getAttribute("uDept");

			ResultSet rs1=st.executeQuery("Select * from Users where uRole='StudentSectionFaculty' and uDept='StudentSection'");
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#d9d9d9><font color=brown><h1><center>"+
			"<marquee>Faculty Feedback and Meeting Management System</marquee></center>"+
			"</h1></font><br><center><form action=\"StoreFacultyId\" method=\"post\">"+
			"<select name=\"ComboTeacher\">");
			while(rs1.next())
			{
			    //	ses1.setAttribute("uId",(rs1.getString("uId")+""));
			 pw.println("<option name="+rs1.getString("uId")+"><font size=4>"+rs1.getString("uName")+"</font></option>");
			}
			pw.println("</select></center><br><br>"+
			"<center><input type=\"submit\" value=\"Apply\"/></center><center><a href=\"StudentTask.html\"><font size=4>Back</font></a></center></form></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}