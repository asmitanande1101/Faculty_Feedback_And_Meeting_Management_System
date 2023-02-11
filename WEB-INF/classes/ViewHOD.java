import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ViewHOD extends HttpServlet
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
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>HOD Details</center></h1></font>");
			pw.println("<img src=\"images/admin2.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"></img><table border=\"1\">");
			if(rs1.next())
			{
				pw.println("<tr><th><font size=5 color=black>HOD ID         :</font> </th><td><font size=5 color=blue>"+rs1.getString("uId")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>HOD Name       : </font></th><td><font size=5 color=blue>"+rs1.getString("uName")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>HOD Department : </font></th><td><font size=5 color=blue>"+rs1.getString("uDept")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>HOD Address    :</font> </th><td><font size=5 color=blue>"+rs1.getString("uAddress")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>HOD Contact No :</font> </th><td><font size=5 color=blue>"+rs1.getString("uContact")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>HOD Email ID   :</font> </th><td><font size=5 color=blue>"+rs1.getString("uEmail")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>HOD Login ID   :</font> </th><td><font size=5 color=blue>"+rs1.getString("uLoginId")+"</font></td></tr><br>"+
				"<tr><th><font size=5 color=black>HOD Password   :</font> </th><td><font size=5 color=blue>"+rs1.getString("uPassword")+"</font></td></tr><br>"+
				"<tr><th><font  size=5 color=black>HOD Status     :</font> </th><td><font size=5 color=blue>"+rs1.getString("uStatus")+"</font></td></tr><br>");
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("ViewError.html");
					rd.forward(req,res);
			}
			pw.println("</table><br><center><a href=\"ADMINTask.html\"><font size=4>BACK</font></a></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}