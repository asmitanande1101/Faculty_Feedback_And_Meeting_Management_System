import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class AddHOD extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			String hodnm,pwd,loginID,address,contactNo,emailID,dept;
			hodnm=req.getParameter("txtHODName");
			dept=req.getParameter("frmDeptName");
			pwd=req.getParameter("txtHODPassword");
			loginID=req.getParameter("txtHODId");
			address=req.getParameter("txtHODAddress");
			contactNo=req.getParameter("txtHODContact");
			emailID=req.getParameter("txtHODEmailID");
			Connection cn=DBConnect.getConnect();
			
			
			Statement st=cn.createStatement();
			String query="insert into Users (uName,uAddress,uContact,uEmail,uRole,uLoginId,uPassword,uDept,uStatus) values('"+hodnm+"','"+address+"','"+contactNo+"','"+emailID+"','HOD','"+loginID+"','"+pwd+"','"+dept+"','ENABLE')";
			ResultSet rs1=st.executeQuery("(select * from Users where uRole='HOD')");
			int flag=0;
			if((loginID.equals(""))||(pwd.equals(""))||(hodnm.equals(""))||(contactNo.equals(""))||(emailID.equals(""))||(dept.equals(""))||(address.equals("")))
			{
				flag=1;
			}
			while(rs1.next())
			{
				if(((rs1.getString("uLoginId").equals(loginID))&&(rs1.getString("uPassword").equals(pwd))&&(rs1.getString("uDept").equals(dept))))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
					st.execute(query);
					RequestDispatcher rd=req.getRequestDispatcher("HodAdded.html");
					rd.forward(req,res);
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("HodAddingError.html");
					rd.forward(req,res);
			}	
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
}