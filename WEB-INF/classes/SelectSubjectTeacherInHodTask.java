import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class SelectSubjectTeacherInHodTask extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			//HttpSession ses1=req.getSession(true);
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String uid=req.getParameter("userid");
			System.out.println("UId="+uid);
			ses.setAttribute("userId",uid);
			ResultSet rs1=st.executeQuery("Select * from teacherSubject where uId="+uid);
			
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#d9d9d9><font color=brown>"+
			"<h1><center><marquee>Faculty Feedback and Meeting Management System</marquee>"+
			"</center></h1></font><br><center>"+
			"<form action=\"ViewFeedback\" method=\"post\"><h2>select Subject</h2>"+
			"<select name=\"ComboSubjects\">");
		/*	if(rs1.next())
			{*/
				while(rs1.next())
				{
				    //	ses1.setAttribute("uId",(rs1.getString("uId")+""));
				 pw.println("<option name="+rs1.getString("subjectName")+"><font size=4>"+rs1.getString("subjectName")+"</font></option>");
				}
				pw.println("</select></center><br><br><center><input type=\"submit\" value=\"Show FeedBack\"/></center>");
				pw.println("<center><a href=\"ShowTeacherListFeedback\"><font size=4>Back</font></a></center></form></body></html>");
		/*	}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("SelectSubjectErrorInHOD.html");
				rd.forward(req,res);
			}*/
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}