import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class CreateMailHodTask extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String senderId=(String)ses.getAttribute("uId");
			System.out.println(senderId);
			String uDept=(String)ses.getAttribute("uDept");
			System.out.println(uDept);
			String uRole=(String)ses.getAttribute("rRole");
			System.out.println(uRole);
			
			String receiverId=(String)req.getParameter("teacherid");
			System.out.println(receiverId);
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			
			ResultSet rs2=st.executeQuery("Select uName from users where uId="+receiverId);
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<form action=\"SendMailHodTask\" method=\"GET\">");
			if(rs2.next())
			{
				pw.println("<center><font color=blue><h2>To</h2></font><input type=\"text\" name=\"txtTo\" value=\""+rs2.getString(1)+"\"/></center>");
				ses.setAttribute("teacherName",rs2.getString(1)+"");
			}
			ResultSet rs1=st.executeQuery("Select * from TeacherSubject where uId="+receiverId);
			pw.println("<br><center><font color=blue><h2>Select Subject</h2></font>"+
			"<select name=\"ComboSubjects\">");
			while(rs1.next())
			{
			    pw.println("<option name="+rs1.getString("subjectName")+"><font size=4>"+
			    rs1.getString("subjectName")+"</font></option>");
			}
			pw.println("</select></center>");
			pw.println("<center><font color=blue><h2>Details</h2></font>"+
			"<textarea name=\"txtDetail\" rows=\"8\" cols=\"32\"></textarea></center>"+
			"<br><center><input type=\"submit\" value=\"Send\"/>"+
			"<input type=\"reset\" value=\"clear\"/><a href=\"HODTask.html\"><font size=4>Back</font></a></center></form></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}	