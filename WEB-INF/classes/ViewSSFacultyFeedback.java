import conn.DBConnect;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class ViewSSFacultyFeedback extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			HttpSession ses=req.getSession(false);
			//HttpSession ses1=req.getSession(true);
			Connection cn=DBConnect.getConnect();
			Statement st=cn.createStatement();
		//	String sDept=(String)ses.getAttribute("uDept");
		//	System.out.println("Dept="+sDept);
			String uid=(String)req.getParameter("uid");
			System.out.println("UId="+uid);
		//	String subName =req.getParameter("ComboSubjects");
		//	System.out.println("Subject="+subName);
		
			int pvgood,pgood,psatis,ppoor;
		//	int kvgood,kgood,ksatis,kpoor;
		//	int prvgood,prgood,prsatis,prpoor;
			int ivgood,igood,isatis,ipoor;
			int davgood,dagood,dasatis,dapoor;
		//	int tavgood,tagood,tasatis,tapoor;
			int disvgood,disgood,dissatis,dispoor;
			int opvgood,opgood,opsatis,oppoor;
			
			pvgood=DBConnect.getCount("feedback",uid,"Punctuality","Very Good");
			pgood=DBConnect.getCount("feedback",uid,"Punctuality","Good");
			psatis=DBConnect.getCount("feedback",uid,"Punctuality","Satisfactory");
			ppoor=DBConnect.getCount("feedback",uid,"Punctuality","Poor");
			System.out.println("punctuality "+pvgood+" "+pgood+" "+psatis+" "+ppoor);
						
		/*	kvgood=DBConnect.getCount("feedback",uid,subName,"Knowledge","Very Good");
			kgood=DBConnect.getCount("feedback",uid,subName,"Knowledge","Good");
			ksatis=DBConnect.getCount("feedback",uid,subName,"Knowledge","Satisfactory");
			kpoor=DBConnect.getCount("feedback",uid,subName,"Knowledge","Poor");
			System.out.println("knowledge "+kvgood+" "+kgood+" "+ksatis+" "+kpoor);

			prvgood=DBConnect.getCount("feedback",uid,subName,"Presentation","Very Good");
			prgood=DBConnect.getCount("feedback",uid,subName,"Presentation","Good");
			prsatis=DBConnect.getCount("feedback",uid,subName,"Presentation","Satisfactory");
			prpoor=DBConnect.getCount("feedback",uid,subName,"Presentation","Poor");
			System.out.println("punctuality "+prvgood+" "+prgood+" "+prsatis+" "+prpoor);*/

			ivgood=DBConnect.getCount("feedback",uid,"Interaction","Very Good");
			igood=DBConnect.getCount("feedback",uid,"Interaction","Good");
			isatis=DBConnect.getCount("feedback",uid,"Interaction","Satisfactory");
			ipoor=DBConnect.getCount("feedback",uid,"Interaction","Poor");
			System.out.println("punctuality "+ivgood+" "+igood+" "+isatis+" "+ipoor);

			davgood=DBConnect.getCount("feedback",uid,"DAttitude","Very Good");
			dagood=DBConnect.getCount("feedback",uid,"DAttitude","Good");
			dasatis=DBConnect.getCount("feedback",uid,"DAttitude","Satisfactory");
			dapoor=DBConnect.getCount("feedback",uid,"DAttitude","Poor");
			System.out.println("punctuality "+davgood+" "+dagood+" "+dasatis+" "+dapoor);

		/*	tavgood=DBConnect.getCount("feedback",uid,subName,"TeachingAids","Very Good");
			tagood=DBConnect.getCount("feedback",uid,subName,"TeachingAids","Good");
			tasatis=DBConnect.getCount("feedback",uid,subName,"TeachingAids","Satisfactory");
			tapoor=DBConnect.getCount("feedback",uid,subName,"TeachingAids","Poor");
			System.out.println("punctuality "+tavgood+" "+tagood+" "+tasatis+" "+tapoor);*/

			disvgood=DBConnect.getCount("feedback",uid,"Discipline","Very Good");
			disgood=DBConnect.getCount("feedback",uid,"Discipline","Good");
			dissatis=DBConnect.getCount("feedback",uid,"Discipline","Satisfactory");
			dispoor=DBConnect.getCount("feedback",uid,"Discipline","Poor");
			System.out.println("punctuality "+disvgood+" "+disgood+" "+dissatis+" "+dispoor);

			opvgood=DBConnect.getCount("feedback",uid,"OPerformance","Very Good");
			opgood=DBConnect.getCount("feedback",uid,"OPerformance","Good");
			opsatis=DBConnect.getCount("feedback",uid,"OPerformance","Satisfactory");
			oppoor=DBConnect.getCount("feedback",uid,"OPerformance","Poor");
			System.out.println("punctuality "+opvgood+" "+opgood+" "+opsatis+" "+oppoor);
			
			
			PrintWriter pw=res.getWriter();
			pw.println("<html><body bgcolor=#d9d9d9><font color=brown><h1><center>"+
			"<marquee>Faculty Feedback and Meeting Management System</marquee></center>"+
			"</h1></font><br><center>"+
			"<table border=1><tr><th><font size=4> SR NO </font></th><th><font size=4>Parameter</font></th><th><font size=4>Result</font></th><th><font size=4>Performance in percentage</font></th></tr>");
			String feedback=getFeedBack(pvgood,pgood,psatis,ppoor);
			pw.println("<tr><td>1</td><td>Punctuality</td><td>"+
			 feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");
			 
		/*	feedback=getFeedBack(kvgood,kgood,ksatis,kpoor);
			pw.println("<tr><td>2</td><td>Knowledge of subject</td><td>"+
			 feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");
			 
			 feedback=getFeedBack(prvgood,prgood,prsatis,prpoor);
			pw.println("<tr><td>3</td><td>Presentation skill</td><td>"+
			feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");*/
			
			feedback=getFeedBack(ivgood,igood,isatis,ipoor);
			pw.println("<tr><td>2</td><td>Interaction with student</td><td>"+
			feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");
			
			feedback=getFeedBack(davgood,dagood,dasatis,dapoor);
			pw.println("<tr><td>3</td><td>Difficulty Attitude</td><td>"+
			feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");
			
	/*		feedback=getFeedBack(tavgood,tagood,tasatis,tapoor);
			pw.println("<tr><td>6</td><td>Use of teaching aids</td><td>"+
			feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");*/
			
			feedback=getFeedBack(disvgood,disgood,dissatis,dispoor) ;
			pw.println("<tr><td>4</td><td>Ability to maintain discipline</td><td>"+
			feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");
			
			feedback=getFeedBack(opvgood,opgood,opsatis,oppoor);
			pw.println("<tr><td>5</td><td>Overall performance</td><td>"+
			feedback+"</td><td>"+getPercentage(feedback)+"</td></tr>");
			
			pw.println("</table><center><a href=\"ADMINTask.html\"><font size=4>Back</font></a></center></body></html>");
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
	}
	String getFeedBack(int vgood,int good,int satis,int poor)
	{
		
		String ret="";
		if(vgood>=good&&vgood>=satis&&vgood>=poor)
		{
			ret="Very Good";
		}
		else if(good>=vgood&&good>=satis&&good>=poor)
		{
			ret="Good";
		}
		else if(satis>=vgood&&satis>=good&&satis>=poor)
		{
			ret="Satisfactory";
		}
		else if(poor>=vgood&&poor>=good&&poor>=satis)
		{
			ret="Poor";
		}
		else if(poor==0&&vgood==0&&good==0&&satis==0)
		{
			ret="No feedback ";
		}
		
		return ret;
	}
	float getPercentage(String res)
	{
		float ret=0f;
		float vgood=100f,good=80f,satis=50f,poor=30f,no=0f;
		if(res.equals("Very Good"))
		{
			ret=vgood;
		}
		else if(res.equals("Good"))
		{
			ret=good;
		}
		else if(res.equals("Satisfactory"))
		{
			ret=satis;
		}
		else if(res.equals("Poor"))
		{
			ret=poor;
		}
		else if(res.equals("No Feedback"))
		{
			ret=no;
		}
		return(ret);
	}
}