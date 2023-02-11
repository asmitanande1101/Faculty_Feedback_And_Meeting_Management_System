import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class Logout extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			ses.invalidate();
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			pw.println("<html><body bgcolor=#bfbfbf><font color=brown><br><br><center><a href=\"Login.html\"><font size=6 color=blue>Login Again</font></a></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}