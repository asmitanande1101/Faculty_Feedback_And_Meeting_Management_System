import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(true);
			String user,pwd,type;
			user=req.getParameter("txtUserId");
			pwd=req.getParameter("txtPassword");
			type=req.getParameter("cmbUserType");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			ResultSet rs;
			if(type.equals("STUDENT"))
			{
				rs=st.executeQuery("Select * from Student where sLoginId='"+user+"'and sPassword='"+pwd+"'");
				if(rs.next())
				{				
					if(pwd.equals(rs.getString("sPassword")))
					{
						 if(type.equals("STUDENT"))
						 {
								ses.setAttribute("uRole",(type+""));	
								ses.setAttribute("uPass",(pwd+""));
								ses.setAttribute("sId",rs.getInt("sId")+"");
								ses.setAttribute("uDept",(rs.getString("sDeptName")+""));
								RequestDispatcher rd=req.getRequestDispatcher("StudentTask.html");
								rd.forward(req,res);					
						 }	
					}
					else
					{
  						RequestDispatcher rd=req.getRequestDispatcher("WrongPassword.html");
						rd.forward(req,res);
					}
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("WrongPassword.html");
					rd.forward(req,res);
				}
			}
			else
			{
				rs=st.executeQuery("Select * from Users where uLoginId='"+user+"'and uPassword='"+pwd+"'");
				if(rs.next())
				{
					
					if(pwd.equals(rs.getString("uPassword")))
					{
						if(type.equals("ADMIN"))
						{
							ses.setAttribute("uRole",(type+""));
							ses.setAttribute("uPass",(pwd+""));
							ses.setAttribute("uId",rs.getInt("uId")+"");
								ses.setAttribute("uName",(rs.getString("uName")+""));
							RequestDispatcher rd=req.getRequestDispatcher("ADMINTask.html");
							rd.forward(req,res);
						}
						else if(type.equals("HOD"))
						{
							if(rs.getString("uStatus").equals("ENABLE"))
							{
								ses.setAttribute("uRole",(type+""));
								ses.setAttribute("uPass",(pwd+""));
								ses.setAttribute("uId",rs.getInt("uId")+"");
								ses.setAttribute("uName",(rs.getString("uName")+""));
								ses.setAttribute("uDept",(rs.getString("uDept")+""));
								RequestDispatcher rd=req.getRequestDispatcher("HODTask.html");
								rd.forward(req,res);	
							}
							else
							{
								RequestDispatcher rd=req.getRequestDispatcher("LoginError.html");
								rd.forward(req,res);
							}
						}
						else if(type.equals("StudentSectionFaculty"))
						{
							if(rs.getString("uStatus").equals("ENABLE"))
							{
								ses.setAttribute("uRole",(type+""));
								ses.setAttribute("uPass",(pwd+""));
								ses.setAttribute("uId",rs.getInt("uId")+"");
								ses.setAttribute("uName",(rs.getString("uName")+""));
								RequestDispatcher rd=req.getRequestDispatcher("SSFacultyTask.html");
								rd.forward(req,res);	
							}
							else
							{
								RequestDispatcher rd=req.getRequestDispatcher("LoginError.html");
								rd.forward(req,res);
							}
						}
						else if(type.equals("TEACHER"))
						{
							if(rs.getString("uStatus").equals("ENABLE"))
							{
								ses.setAttribute("uRole",(type+""));
								ses.setAttribute("uPass",(pwd+""));
								ses.setAttribute("uId",rs.getInt("uId")+"");
								ses.setAttribute("uName",(rs.getString("uName")+""));
								RequestDispatcher rd=req.getRequestDispatcher("TeacherTask.html");
								rd.forward(req,res);	
							}
							else
							{
								RequestDispatcher rd=req.getRequestDispatcher("LoginError.html");
								rd.forward(req,res);
							}
						}
						/*else if(type.equals("STUDENT"))
						{
							if(rs.getString("uStatus").equals("ENABLE"))
							{
								ses.setAttribute("uPass",(pwd+""));
								RequestDispatcher rd=req.getRequestDispatcher("StudentTask.html");
								rd.forward(req,res);	
							}
							else
							{
								RequestDispatcher rd=req.getRequestDispatcher("LoginError.html");
								rd.forward(req,res);
							}
						}*/
						else if(type.equals("CLERK"))
						{
							if(rs.getString("uStatus").equals("ENABLE"))
							{
								ses.setAttribute("uRole",(type+""));
								ses.setAttribute("uPass",(pwd+""));
								ses.setAttribute("uId",rs.getInt("uId")+"");
								ses.setAttribute("uName",(rs.getString("uName")+""));
								RequestDispatcher rd=req.getRequestDispatcher("ClerkTask.html");
								rd.forward(req,res);	
							}
							else
							{
								RequestDispatcher rd=req.getRequestDispatcher("LoginError.html");
								rd.forward(req,res);
							}
						}
					}
					else
					{
						RequestDispatcher rd=req.getRequestDispatcher("WrongPassword.html");
						rd.forward(req,res);
					}
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("WrongPassword.html");
					rd.forward(req,res);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}