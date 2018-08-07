package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import util.DataConnection;
import vo.AdminVO;
import vo.EmployeeVO;

public class AdminDAO {
	
	public List<AdminVO> emplist (String username) throws Exception{
		List<AdminVO> emplist = new ArrayList<AdminVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT * FROM term_project.employee where username != '"+username+"' and status = 'InActive'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in employee dao getiimg update details in while loop");
				AdminVO adminvo = new AdminVO();
				adminvo.setEmp_id(resultSet.getInt("emp_id"));
				adminvo.setFirst_name(resultSet.getString("first_name"));
				adminvo.setLast_name(resultSet.getString("last_name"));
				adminvo.setStatus(resultSet.getString("type"));
				adminvo.setUsername(resultSet.getString("username"));
				adminvo.setStatus(resultSet.getString("status"));
				emplist.add(adminvo);
								
			}
		
		}
		catch(Exception e){}
		
		return emplist;
		
	}
	
	public List<AdminVO> managerlist (String username) throws Exception{
		List<AdminVO> managerlist = new ArrayList<AdminVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT * FROM term_project.employee where status = 'Active'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in employee dao getiimg update details in while loop for manager");
				AdminVO adminvo = new AdminVO();
				adminvo.setFirst_name(resultSet.getString("first_name"));
				adminvo.setStatus(resultSet.getString("type"));
				adminvo.setUsername(resultSet.getString("username"));
				
				managerlist.add(adminvo);
								
			}
		
		}
		catch(Exception e){}
		
		return managerlist;
		
	}

	public List<AdminVO> activelist (String username) throws Exception{
		List<AdminVO> activelist = new ArrayList<AdminVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT * FROM term_project.employee where status = 'Active' and username != 'admin'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in employee dao getiimg active list in while loop ");
				AdminVO adminvo = new AdminVO();
				adminvo.setEmp_id(resultSet.getInt("emp_id"));
				adminvo.setFirst_name(resultSet.getString("first_name"));
				adminvo.setLast_name(resultSet.getString("last_name"));
				adminvo.setStatus(resultSet.getString("type"));
				adminvo.setUsername(resultSet.getString("username"));
				adminvo.setStatus(resultSet.getString("status"));
				adminvo.setManager(resultSet.getString("manager"));
				adminvo.setManagerlist(resultSet.getString("managerlist"));
				activelist.add(adminvo);
								
			}
		
		}
		catch(Exception e){}
		
		return activelist;
		
	}
	
	public void updatemanager(AdminVO adminvo, String manager){
		//String manager = adminvo.getManager();
		//66System.out.println("Dao"+ manager);
		String username = adminvo.getUsername();

		System.out.println("Dao"+username);
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try{
			
			con =DataConnection.createConnection();
			//String manager = adminvo.getManager();
			String q = "Select managerlist from term_project.employee where username='"+manager+"'";
			
			System.out.println(q);
			preparedStatement = (PreparedStatement) con.prepareStatement(q);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
			String managerlist = resultSet.getString("managerlist");
			
			System.out.println(managerlist);
			
			String query = "UPDATE employee set manager ='"+manager+"' , status='Active' where username='"+username+"'";
			String query1 = "UPDATE employee set type ='Manager' where username='"+manager+"'";
			if(managerlist != null){
			managerlist = managerlist + "_" + username;
			System.out.println(query1);
			}
			else{
				managerlist = manager;
			}
			String up = "Update employee set managerlist = '"+managerlist+"' where username='"+username+"'";
			System.out.println(up);
			preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement = (PreparedStatement) con.prepareStatement(query1);
			preparedStatement.executeUpdate(query);
			preparedStatement.executeUpdate(query1);
			preparedStatement.executeUpdate(up);
			preparedStatement.setString(1, adminvo.getManager());
			
			
			
			
			}	
		}
		catch (Exception e){}
	
		
	}
}
