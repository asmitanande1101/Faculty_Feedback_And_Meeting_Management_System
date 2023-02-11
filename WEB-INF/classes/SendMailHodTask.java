import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class SendMailHodTask extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String senderId=(String)ses.getAttribute("uId");
			System.out.println("senderId="+senderId);
			String uDept=(String)ses.getAttribute("uDept");
			System.out.println("uDept="+uDept);
			String receiverId=(String)ses.getAttribute("teacherId");
			System.out.println("receiverId="+receiverId);
			String detail=(String)req.getParameter("txtDetail");
			String sub=(String)req.getParameter("ComboSubjects");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			
			String query="insert into mailbox (mSenderID,mReceiverID,mSubject,mDetails,mDate,mTime,isReaded) values('"+senderId+"','"+receiverId+"','"+sub+"','"+detail+"',now(),now(),'0')";
			RequestDispatcher rd=req.getRequestDispatcher("MailSended.html");
			rd.forward(req,res);
			st.execute(query);
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}