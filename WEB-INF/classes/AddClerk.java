import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class AddClerk extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String clerknm,pwd,loginID,address,contactNo,emailID,dept;
			clerknm=req.getParameter("txtClerkName");
			dept=req.getParameter("frmDeptName");
			pwd=req.getParameter("txtClerkPassword");
			loginID=req.getParameter("txtClerkId");
			address=req.getParameter("txtClerkAddress");
			contactNo=req.getParameter("txtClerkContact");
			emailID=req.getParameter("txtClerkEmailID");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String query="insert into Users (uName,uAddress,uContact,uEmail,uRole,uLoginId,uPassword,uDept,uStatus) values('"+clerknm+"','"+address+"','"+contactNo+"','"+emailID+"','CLERK','"+loginID+"','"+pwd+"','"+dept+"','ENABLE')";
			ResultSet rs1=st.executeQuery("(select * from Users where uRole='HOD')");
			int flag=0;
			if((loginID.equals(""))||(pwd.equals(""))||(clerknm.equals(""))||(contactNo.equals(""))||(emailID.equals(""))||(dept.equals(""))||(address.equals("")))
			{
				flag=1;
			}
			while(rs1.next())
			{
				if(((rs1.getString("uLoginId").equals(loginID))&&(rs1.getString("uPassword").equals(pwd))&&(rs1.getString("uDept").equals(dept))&&(rs1.getString("uName").equals(clerknm))))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
					st.execute(query);
					RequestDispatcher rd=req.getRequestDispatcher("TeacherClerkAdded.html");
					rd.forward(req,res);
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("ClerkAddingError.html");
					rd.forward(req,res);
			}	
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}