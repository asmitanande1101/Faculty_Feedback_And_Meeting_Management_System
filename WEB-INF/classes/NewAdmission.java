import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class NewAdmission extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String studnm,pwd,loginID,address,contactNo,emailID,dept,year;
			studnm=req.getParameter("txtStudentName");
			dept=req.getParameter("frmDeptName");
			pwd=req.getParameter("txtStudentPassword");
			loginID=req.getParameter("txtStudentId");
			address=req.getParameter("txtStudentAddress");
			contactNo=req.getParameter("txtStudentContact");
			emailID=req.getParameter("txtStudentEmailID");
			year=req.getParameter("cmbYear");
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
			String query="insert into Student (sName,sAddress,sEmail,sContact,sDeptName,sYear,sLoginId,sPassword) values('"+studnm+"','"+address+"','"+emailID+"','"+contactNo+"','"+dept+"','"+year+"','"+loginID+"','"+pwd+"')";
			ResultSet rs1=st.executeQuery("(select * from Users where uRole='STUDENT')");
			int flag=0;
				if((loginID.equals(""))||(pwd.equals(""))||(studnm.equals(""))||(contactNo.equals(""))||(emailID.equals(""))||(dept.equals(""))||(address.equals(""))||(year.equals("")))
				{
					flag=1;
				}
				
			while(rs1.next())
			{
				if(((rs1.getString("sLoginId").equals(loginID))&&(rs1.getString("sPassword").equals(pwd))&&(rs1.getString("sDeptName").equals(dept))&&(rs1.getString("sName").equals(studnm))))
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
					RequestDispatcher rd=req.getRequestDispatcher("StudentAdded.html");
					rd.forward(req,res);
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("StudentAddingError.html");
					rd.forward(req,res);
			}	
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}