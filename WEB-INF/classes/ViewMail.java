import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ViewMail extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String mId=(String)req.getParameter("mid");
			String senderId=(String)ses.getAttribute("senderId");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet  rs1=st.executeQuery("select uName from Users where uID="+senderId);
			
			PrintWriter pw=res.getWriter();
			
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><h1><center><marquee>Faculty Feedback and Meeting Management System</marquee></center></h1></font><br>"+
			"<font color=red><h1><center>Mail Details</center></h1></font>");
			//pw.println("<img src=\"images/admin2.jpg\" align=\"left\" width=20%  height=45% alt=\"\" border=\"1\" Hspace=\"70\" Vspace=\"20\"></img><table border=\"1\">");
			if(rs1.next())
			{
				pw.println("<br><center><font size=5 color=Blue>Sender Id : </font><font size=4>"+senderId+"</font><br>"+
				"<font size=5 color=Blue>Sender Name : </font><font size=4>"+rs1.getString(1)+"</font><br></center>");
			}
			ResultSet  rs2=st.executeQuery("select * from mailbox where mId="+mId);
			if(rs2.next())
			{
				pw.println("<center><br><font size=5 color=Blue>Message Id : </font><font size=4>"+mId+"</font><br>"+
				"<br><font size=5 color=Blue>Message :</font><font size=4> "+rs2.getString("mDetails")+"</font><br>"+
				"<font size=5 color=Blue>Date : </font><font size=4>"+rs2.getString("mDate")+"</font><br>"+
				"<font size=5 color=Blue>Time : </font><font size=4>"+rs2.getString("mTime")+"</font><br></center>");
			}
			pw.println("<br><center><h2><a href=\"ViewMailList\"><font size=4>OK</font></a></h2></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}