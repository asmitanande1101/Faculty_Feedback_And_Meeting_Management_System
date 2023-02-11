import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class AddStudentSectionFaculty extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String facultynm,pwd,loginID,address,contactNo,emailID;
			facultynm=req.getParameter("txtSSFacultyName");
			pwd=req.getParameter("txtSSFacultyPassword");
			loginID=req.getParameter("txtSSFacultyId");
			address=req.getParameter("txtSSFacultyAddress");
			contactNo=req.getParameter("txtSSFacultyContact");
			emailID=req.getParameter("txtSSFacultyEmailID");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String query="insert into Users (uName,uAddress,uContact,uEmail,uRole,uLoginId,uPassword,uDept,uStatus) values('"+facultynm+"','"+address+"','"+contactNo+"','"+emailID+"','StudentSectionFaculty','"+loginID+"','"+pwd+"','StudentSection','ENABLE')";
			ResultSet rs1=st.executeQuery("(select * from Users where uRole='StudentSectionFaculty')");
			int flag=0;
				if((loginID.equals(""))||(pwd.equals(""))||(facultynm.equals(""))||(contactNo.equals(""))||(emailID.equals(""))||(address.equals("")))
				{
					flag=1;
				}
			while(rs1.next())
			{
			
				if(((rs1.getString("uLoginId").equals(loginID))&&(rs1.getString("uPassword").equals(pwd))&&(rs1.getString("uName").equals(facultynm))))
				{
					flag=1;
					break;
				}
				/*else
				{
					flag=0;
				}*/
			}
			if(flag==0)
			{
					st.execute(query);
					RequestDispatcher rd=req.getRequestDispatcher("HodAdded.html");
					rd.forward(req,res);
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("FacultyAddingError.html");
					rd.forward(req,res);
			}	
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}