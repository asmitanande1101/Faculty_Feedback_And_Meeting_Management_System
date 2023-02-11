package conn;
import java.io.*;
import java.sql.*;

public class DBConnect
{
	public static Connection getConnect()throws Exception
	{
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/FacultyFeedback","root","root1");
			return(cn);
	}
	public static int getNewID(String table,String column)throws Exception
	{
		Connection cn=getConnect();
		Statement st=cn.createStatement();
		String sql="Select max("+column+") from "+table;
		ResultSet rs=st.executeQuery(sql);
		int sr;
		if(rs.next())
		{
			sr=rs.getInt(1)+1;
		}
		else
		{
			sr=1;
		}	
		st.close();
		rs.close();
		return(sr);
	}
}