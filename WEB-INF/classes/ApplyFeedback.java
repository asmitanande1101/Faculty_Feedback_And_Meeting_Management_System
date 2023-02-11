import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ApplyFeedback extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			String Q1=req.getParameter("rdoPunctuality");
			String Q2=req.getParameter("rdoKnowledge");
			String Q3=req.getParameter("rdoPresentation");
			String Q4=req.getParameter("rdoInteraction");
			String Q5=req.getParameter("rdoDAttitude");
			String Q6=req.getParameter("rdoAids");
			String Q7=req.getParameter("rdoDiscipline");
			String Q8=req.getParameter("rdoOPerformance");
			
			/*System.out.println(Q1);
			System.out.println(Q2);
			System.out.println(Q3);
			System.out.println(Q4);
			System.out.println(Q5);
			System.out.println(Q6);
			System.out.println(Q7);
			System.out.println(Q8);*/
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String id=(String)ses.getAttribute("sId");
			//System.out.println(id);
			String sid=(String)ses.getAttribute("subId");
			//System.out.println(sid);
			String tid=(String)ses.getAttribute("tId");
			//System.out.println(tid);
			String str="insert into Feedback (fSenderId,fReceiver,fSubject,Punctuality,Knowledge,Presentation,Interaction,DAttitude,TeachingAids,Discipline,OPerformance,fDate,fTime) values("+id+","+tid+",'"+sid+"','"+Q1+"','"+Q2+"','"+Q3+"','"+Q4+"','"+Q5+"','"+Q6+"','"+Q7+"','"+Q8+"',now(),now())";
			//System.out.println(str);
			st.execute(str);
			//System.out.println("Query executed");
			RequestDispatcher rd=req.getRequestDispatcher("FeedbackAppliedSuccessfully.html");
			rd.forward(req,res);
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}