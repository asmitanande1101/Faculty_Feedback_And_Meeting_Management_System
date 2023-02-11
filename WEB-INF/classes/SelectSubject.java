import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class SelectSubject extends HttpServlet
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

			ResultSet rs1=st.executeQuery("Select * from Users where (uRole='TEACHER' or uRole='HOD') and uDept='"+sDept+"'");
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#d9d9d9><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br><center><form action=\"ApplySubject\" method=\"post\"><center><font size=4>Select Teacher</font><select name=\"ComboTeacher\">");
			while(rs1.next())
			{
			    //	ses1.setAttribute("uId",(rs1.getString("uId")+""));
			 pw.println("<option name="+rs1.getString("uId")+"><font size=4>"+rs1.getString("uName")+"</font></option>");
			}
			pw.println("</select></center><br><br><center><font size=4>Select Subject</font><select name=\"ComboSubject\">");
			ResultSet rs2=st.executeQuery("Select * from Subjects where sDept='"+sDept+"'");
			while(rs2.next())
			{
			 pw.println("<option name="+rs2.getString("sName")+"><font size=4>"+rs2.getString("sName")+"</font></option>");
			}
			pw.println("</select></center><br><center><input type=\"submit\" value=\"Apply\"/></center><a href=\"HODTask.html\"><font size=4>Back</font></a></form></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}