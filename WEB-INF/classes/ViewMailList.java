import conn.DBConnect;
import javax.servlet.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ViewMailList extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String userId=(String)ses.getAttribute("uId");
			String uRole=(String)ses.getAttribute("uRole");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet rs1=st.executeQuery("Select * from MailBox where mReceiverID='"+userId+"'");
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Select mail from given Table</center></h1></font>"+
			//"<img src=\"images/Admin23.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"/><br>"+
			"<center><table border=\"1\"><tr><th><font size=4>Sender Id</font></th><th><font size=4>Message</font></th><th><font size=4>Date</font></th><th><font size=4>Time</font></th></tr>");
			while(rs1.next())
			{
				if(rs1.getString("isReaded").equals("0"))
				{
					pw.println("<tr><td><font color=blue size=4>"+rs1.getString("mSenderID")+"</font></td><td><font color=blue size=4>"+rs1.getString("mDetails")+"</font></td><td><font color=blue size=4>"+rs1.getString("mDate")+"</font></td><td><font color=blue size=4>"+rs1.getString("mTime")+"</font></td><td><a href=\"ViewMail?mid="+rs1.getInt("mId")+"\"><font color=blue size=4>View</font></a></td></tr>");
					ses.setAttribute("senderId",rs1.getString("mSenderID")+"");
					st.execute("update mailbox set isReaded='1' where isReaded='0'");
				}
				else if(rs1.getString("isReaded").equals("1"))
				{
					pw.println("<tr><td><font color=black size=4>"+rs1.getString("mSenderID")+"</font></td><td><font color=black size=4>"+rs1.getString("mDetails")+"</font></td><td><font color=black size=4>"+rs1.getString("mDate")+"</font></td><td><font color=black size=4>"+rs1.getString("mTime")+"</font></td><td><a href=\"ViewMail?mid="+rs1.getInt("mId")+"\">View</a></td></tr>");
					ses.setAttribute("senderId",rs1.getString("mSenderID")+"");
				}
			}
			pw.println("</table></center>");
			if(uRole.equalsIgnoreCase("HOD"))
			{
				pw.println("<br><center><a href=\"HODTask.html\"><font size=4>BACK</font></a></center></body></html>");
			}
			 else if(uRole.equalsIgnoreCase("TEACHER"))
			{
				pw.println("<br><center><a href=\"TeacherTask.html\"><font size=4>BACK</font></a></center></body></html>");
			}
			else if(uRole.equalsIgnoreCase("StudentSectionFaculty"))
			{
				pw.println("<br><center><a href=\"SSFacultyTask.html\"><font size=4>BACK</font></a></center></body></html>");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}