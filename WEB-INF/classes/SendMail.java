import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class SendMail extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String senderId=(String)ses.getAttribute("uId");
			String uDept=(String)ses.getAttribute("uDept");
			String receiverId=(String)ses.getAttribute("facultyId");
			System.out.println("ReceiverId="+receiverId);
			String detail=(String)req.getParameter("txtDetail");
			String sub=(String)req.getParameter("ComboSubjects");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			
			String query="insert into mailbox (mSenderID,mReceiverID,mSubject,mDetails,mDate,mTime,isReaded) values('"+senderId+"','"+receiverId+"','"+sub+"','"+detail+"',now(),now(),'0')";
			RequestDispatcher rd=req.getRequestDispatcher("AdminMailSended.html");
				rd.forward(req,res);
			st.execute(query);
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}