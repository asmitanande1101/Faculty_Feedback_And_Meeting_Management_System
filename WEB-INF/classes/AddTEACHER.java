import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class AddTEACHER extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String teachernm,pwd,loginID,year,address,contactNo,emailID,dept;
			teachernm=req.getParameter("txtTeacherName");
			dept=req.getParameter("frmDeptName");
			year=req.getParameter("cmbYear");
			pwd=req.getParameter("txtTeacherPassword");
			loginID=req.getParameter("txtTeacherId");
			address=req.getParameter("txtTeacherAddress");
			contactNo=req.getParameter("txtTeacherContact");
			emailID=req.getParameter("txtTeacherEmailID");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String query="insert into Users (uName,uAddress,uContact,uEmail,uRole,uLoginId,uPassword,uDept,uStatus) values('"+teachernm+"','"+address+"','"+contactNo+"','"+emailID+"','TEACHER','"+loginID+"','"+pwd+"','"+dept+"','ENABLE')";
			ResultSet rs1=st.executeQuery("(select * from Users where uRole='TEACHER')");
			int flag=0;
				if((loginID.equals(""))||(pwd.equals(""))||(teachernm.equals(""))||(contactNo.equals(""))||(emailID.equals(""))||(dept.equals(""))||(address.equals("")))
				{
					flag=1;
				}
			while(rs1.next())
			{
				if(((rs1.getString("uLoginId").equals(loginID))&&(rs1.getString("uPassword").equals(pwd))&&(rs1.getString("uDept").equals(dept))&&(rs1.getString("uName").equals(teachernm))))
				{
					flag=1;
					break;
				}
				else
				{
					flag=0;
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
					RequestDispatcher rd=req.getRequestDispatcher("TeacherAddingError.html");
					rd.forward(req,res);
			}	
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}