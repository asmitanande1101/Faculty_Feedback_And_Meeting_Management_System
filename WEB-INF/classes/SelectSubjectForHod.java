import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class SelectSubjectForHod extends HttpServlet
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
			System.out.println("Dept="+sDept);
			String uid=(String)ses.getAttribute("uId");
			System.out.println("uId="+uid);
			String sql="Select subjectName from TeacherSubject where uId="+uid;
			System.out.println("query="+sql);
			ResultSet rs1=st.executeQuery(sql);
			
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#d9d9d9><font color=brown>"+
			"<h1><center><marquee>Faculty Feedback and Meeting Management System</marquee>"+
			"</center></h1></font><br><center>"+
			"<form action=\"ShowFeedBackInShort\" method=\"post\">"+
			"<font size=4>Select subject</font><select name=\"ComboSubjects\">");
			/*if(rs1.next())
			{*/
				while(rs1.next())
				{
				    //	ses1.setAttribute("uId",(rs1.getString("uId")+""));
				    System.out.println(rs1.getString("subjectName"));
				 pw.println("<option name="+rs1.getString("subjectName")+"><font size=4>"+rs1.getString("subjectName")+"</font></option>");
				}
				pw.println("</select></center><br><br><center><input type=\"submit\" value=\"Show FeedBack\"/></center><center><a href=\"HODTask\"><font size=4>Back</font></a></center></form></body></html>");
			/*}
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