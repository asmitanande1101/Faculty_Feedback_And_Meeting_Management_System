package conn;
import java.io.*;
import java.sql.*;

public class DBConnect
{
	public static Connection getConnect()throws Exception
	{
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/FacultyFeedback","root","root");
			return(cn);
	}
	public static long getNewID(String table,String column)throws Exception
	{  
		Connection cn=getConnect();
		Statement st=cn.createStatement();
		String sql="Select max("+column+") from "+table;
		ResultSet rs=st.executeQuery(sql);
		long sr;
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
	public static int getCount(String table,String uId,String sName,String ColumnName,String ColumnValue)throws Exception
	{  
		Connection cn=getConnect();
		Statement st=cn.createStatement();
		String sql="Select count(*) from "+table+" where fReceiver='"+uId+"' and fSubject='"+sName
		+"' and "+ColumnName+"='"+ColumnValue+"'";
		System.out.println(sql);
		ResultSet rs=st.executeQuery(sql);
		System.out.println("Query executed");
		int count=0;
		if(rs.next())
		{
			System.out.println("Query executed");
			count=rs.getInt(1);
		}
		else
		{
			count=0;
		}	
		st.close();
		rs.close();
		return(count);
	}
	public static int getCount(String table,String uId,String ColumnName,String ColumnValue)throws Exception
	{  
		Connection cn=getConnect();
		Statement st=cn.createStatement();
		String sql="Select count(*) from "+table+" where fReceiver='"+uId+"' and "+ColumnName+"='"+ColumnValue+"'";
		System.out.println(sql);
		ResultSet rs=st.executeQuery(sql);
		System.out.println("Query executed");
		int count=0;
		if(rs.next())
		{
			System.out.println("Query executed");
			count=rs.getInt(1);
		}
		else
		{
			count=0;
		}	
		st.close();
		rs.close();
		return(count);
	}

}