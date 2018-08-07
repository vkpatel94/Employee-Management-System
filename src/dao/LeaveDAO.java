package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import util.DataConnection;
import vo.AdminVO;
import vo.LeaveVO;
import vo.RegistrationVO;

public class LeaveDAO {
	public String manager(LeaveVO leavevo) throws Exception{
		String username = leavevo.getEmployee();
		String manager = null;
		Connection con = null;
		//PreparedStatement preparedStatement = null;
		try{
			con =DataConnection.createConnection();
			String query = "Select manager from employee where username='"+username+"'";
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(query);
			ResultSet resultSet = preparedStatement1.executeQuery();
			while(resultSet.next()){
				manager =resultSet.getString("manager");
			}
		}
		catch(Exception e){}
		return manager;
	}
	public String leaveRequest(LeaveVO leavevo, int n) throws Exception, SQLException{
		
		String leave_date = leavevo.getLeave_date();
		String leave_reason = leavevo.getLeave_reason();
		int days = leavevo.getDays();
		String employee = leavevo.getEmployee();
		String manager = leavevo.getManager();
		System.out.println(manager);
		System.out.println(employee);
		System.out.println(leave_date);
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try{
			con =DataConnection.createConnection();
			String query = "insert into term_project.leave(leave_date,leave_reason,days,employee,manager)values(?,?,?,?,?)";
			//String q= "update employee set availLeave= availLeave-1 where username='"+employee+"'";
			//String q= "update leave set availLeave= availLeave-1 where employee='"+employee+"'";
			System.out.println(query);
			preparedStatement = con.prepareStatement(query);
			//preparedStatement= con.prepareStatement(q);
			preparedStatement.setString(1, leave_date);
			preparedStatement.setString(2, leave_reason);
			preparedStatement.setInt(3, days);
			preparedStatement.setString(4, employee);
			preparedStatement.setString(5, manager);
		//	preparedStatement.setInt(6, "availLeave-1");
			preparedStatement.execute();
preparedStatement.close();

		
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "Oppp Wrong";
		
	}
	
	
public int updateAvailCount(String employee, int days){
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try{
			ResultSet rs=null;
			con =DataConnection.createConnection();
			String q1= "select availLeave from term_project.employee where username='"+employee+"'";
			preparedStatement = (PreparedStatement) con.prepareStatement(q1);
			rs= preparedStatement.executeQuery();
			while(rs.next())
			{
				int day=rs.getInt("availLeave");
				return day;
			}
		
}catch(Exception e){
				e.printStackTrace();
			}
				return 0;

}		
		


public int updateAvailCount1(String employee, int n){
	
	Connection con = null;
	PreparedStatement preparedStatement = null;
	try{
		ResultSet rs=null;
		con =DataConnection.createConnection();
		String q1= "update term_project.employee set availLeave='"+n+"' where username='"+employee+"'";
		preparedStatement = (PreparedStatement) con.prepareStatement(q1);
		preparedStatement.executeUpdate();
		
	
}catch(Exception e){
			e.printStackTrace();
		}
			return 0;

}	
			
			
			
			
			
			
		/*	
			while(rs.next())
				
			{
				String q= "update term_project.employee set availLeave= availLeave-1 where username='"+employee+"'";
				int i=preparedStatement.executeUpdate(q);
			}
			//String query = "insert into directory (dict_name, created_by, access_type, managerlist) values (?,?,?,?)";
			
			//System.out.println(q);
		//	System.out.println(q);
			//preparedStatement.executeUpdate(q);
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 1;
		
		
	}


	*/
	public List<LeaveVO> pendingLeave (String username) throws Exception{
		List<LeaveVO> pendingLeave = new ArrayList<LeaveVO>();
		Connection con =null;
		
		//String manager = leavevo.getManager();
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT * FROM term_project.leave where manager = '"+username+"' and status = 'Not Approved'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				LeaveVO leavevo = new LeaveVO();
				System.out.println("in employee dao getiimg update details in while loop");
				//AdminVO adminvo = new AdminVO();
				leavevo.setLeave_id(resultSet.getInt("leave_id"));
				leavevo.setLeave_date(resultSet.getString("leave_date"));
				leavevo.setLeave_reason(resultSet.getString("leave_reason"));
				leavevo.setDays(resultSet.getInt("days"));
				leavevo.setStatus(resultSet.getString("status"));
				leavevo.setEmployee(resultSet.getString("employee"));
				pendingLeave.add(leavevo);
								
			}
		
		}
		catch(Exception e){}
		
		return pendingLeave;
		
	}
	
	public List<LeaveVO> listLeave (String username) throws Exception{
		List<LeaveVO> listLeave = new ArrayList<LeaveVO>();
		Connection con =null;
		
		//String manager = leavevo.getManager();
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT * FROM term_project.leave where employee = '"+username+"'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				LeaveVO leavevo = new LeaveVO();
				System.out.println("in employee dao getiimg update details in while loop");
				//AdminVO adminvo = new AdminVO();
				//leavevo.setLeave_id(resultSet.getInt("leave_id"));
				leavevo.setLeave_reason(resultSet.getString("leave_reason"));
				leavevo.setLeave_date(resultSet.getString("leave_date"));
				leavevo.setDays(resultSet.getInt("days"));
				leavevo.setStatus(resultSet.getString("status"));
	//			leavevo.setEmployee(resultSet.getString("employee"));
				listLeave.add(leavevo);
								
			}
		
		}
		catch(Exception e){}
		
		return listLeave;
		
	}
	
}
