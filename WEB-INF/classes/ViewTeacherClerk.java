import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ViewTeacherClerk extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String id=req.getParameter("uid");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet  rs1=st.executeQuery("select * from Users where uID="+id);
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Details</center></h1></font>"+
			//"<img src=\"images/fff.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"><br>"+
			"<center><table border=\"1\">");
				if(rs1.next())
				{
						if(rs1.getString("uRole").equals("TEACHER"))
						{
							pw.println("<tr><th><font size=5 color=black>Teacher ID : </font></th><td><font size=5 color=blue>"+rs1.getString("uId")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Name :</font> </th><td><font size=5 color=blue>"+rs1.getString("uName")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Department : </font></th><td><font size=5 color=blue>"+rs1.getString("uDept")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Address : </font></th><td><font size=5 color=blue>"+rs1.getString("uAddress")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Contact No. :</font> </th><td><font size=5 color=blue>"+rs1.getString("uContact")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Email Address :</font> </th><td><font size=5 color=blue>"+rs1.getString("uEmail")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Login ID : </font></th><td><font size=5 color=blue>"+rs1.getString("uLoginId")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Password :</font> </th><td><font size=5 color=blue>"+rs1.getString("uPassword")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Teacher Status :</font> </th><td><font size=5 color=blue>"+rs1.getString("uStatus")+"</font></td></tr><br>");
						}
						else if(rs1.getString("uRole").equals("CLERK"))
						{	
							pw.println("<tr><th><font size=5 color=black>Clerk ID :</font> </th><td><font size=5 color=blue>"+rs1.getString("uId")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Name : </font></th><td><font size=5 color=blue>"+rs1.getString("uName")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Department : </font></th><td><font size=5 color=blue>"+rs1.getString("uDept")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Address :</font> </th><td><font size=5 color=blue>"+rs1.getString("uAddress")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Contact No. :</font> </th><td><font size=5 color=blue>"+rs1.getString("uContact")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Email Address : </font></th><td><font size=5 color=blue>"+rs1.getString("uEmail")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Login ID : </font></th><td><font size=5 color=blue>"+rs1.getString("uLoginId")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Password : </font></th><td><font size=5 color=blue>"+rs1.getString("uPassword")+"</font></td></tr><br>"+
							"<tr><th><font size=5 color=black>Clerk Status :</font> </th><td><font size=5 color=blue>"+rs1.getString("uStatus")+"</font></td></tr><br>");
						}
				}
				else 
				{
						RequestDispatcher rd=req.getRequestDispatcher("ViewErrorTeacherClerk.html");
						rd.forward(req,res);
				}
				pw.println("</table></center><br><center><a href=\"HODTask.html\"><font size=4>BACK</font></a></center></body></html>");
				pw.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}