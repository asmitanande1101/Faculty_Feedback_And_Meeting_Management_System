import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ViewSSFaculty extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String id=req.getParameter("uid");
			//res.setContentType("admin2.jpeg");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet  rs1=st.executeQuery("select * from Users where uID="+id);
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</font></marquee></center></h1>"+
			"<font color=red><h1><center>Student Section Faculty Details</center></h1></font>");
			pw.println("<center><table border=\"1\">");
			if(rs1.next())
			{
				pw.println("<tr><th><font size=5 color=black>Faculty ID         :</font> </th><td><font size=5 color=blue>"+rs1.getString("uId")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Name       :</font> </th><td><font size=5 color=blue>"+rs1.getString("uName")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Department :</font> </th><td><font size=5 color=blue>"+rs1.getString("uDept")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Address    :</font> </th><td><font size=5 color=blue>"+rs1.getString("uAddress")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Contact No :</font> </th><td><font size=5 color=blue>"+rs1.getString("uContact")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Email ID   :</font> </th><td><font size=5 color=blue>"+rs1.getString("uEmail")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Login ID   :</font> </th><td><font size=5 color=blue>"+rs1.getString("uLoginId")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Password   :</font> </th><td><font size=5 color=blue>"+rs1.getString("uPassword")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>Faculty Status     :</font> </th><td><font size=5 color=blue>"+rs1.getString("uStatus")+"</font></td></tr><br>");
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("ViewError.html");
					rd.forward(req,res);
			}
			pw.println("</table></center><br><center><a href=\"ADMINTask.html\"><font size=5>BACK</font></a></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}