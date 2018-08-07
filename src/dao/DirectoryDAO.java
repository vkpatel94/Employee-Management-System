package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import util.DataConnection;
import vo.DirectoryVO;
import vo.EmployeeVO;

public class DirectoryDAO {
	
	

	public String createDict(DirectoryVO directoryvo){
		
		String dict_name = directoryvo.getDict_name();
		System.out.println(dict_name);
		String access_type = directoryvo.getAccess_type();
		String created_by = directoryvo.getCreated_by();
		String managerlist = directoryvo.getManagerlist();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try{
			con =DataConnection.createConnection();
			String query = "insert into directory (dict_name, created_by, access_type, managerlist) values (?,?,?,?)";
			//String q = "Update directory SET directory.managerlist = employee.managerlist from directory, employee where employee.username = '"+created_by+"'";
			//System.out.println(q);
			System.out.println(query);
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, dict_name);
			preparedStatement.setString(2, created_by);
			preparedStatement.setString(3, access_type);
			preparedStatement.setString(4, managerlist);
			//preparedStatement.executeUpdate(q);
			
			int i= preparedStatement.executeUpdate();

			if (i!=0)
			return "Success";
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "Opppsss... Something went Wrong";
		
		
	}
	public String managerlist(DirectoryVO directoryvo){
		String created_by = directoryvo.getCreated_by();
		String managerlist = null;
		Connection con = null;
		//PreparedStatement preparedStatement = null;
		try{
			con =DataConnection.createConnection();
			String query = "Select managerlist from employee where username='"+created_by+"'";
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(query);
			ResultSet resultSet = preparedStatement1.executeQuery();
			while(resultSet.next()){
				managerlist =resultSet.getString("managerlist");
			}
		}
		catch(Exception e){}
		return managerlist;
		
	}
	
	public List<DirectoryVO> select(String username) throws Exception{
		List<DirectoryVO> selectUser = new ArrayList<DirectoryVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT username, managerlist FROM term_project.employee where username='"+username+"'";
			System.out.println(s);
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in employee dao getiimg update details in while loop");
				DirectoryVO directoryvo = new DirectoryVO();
				//System.out.println(resultSet.getShort("mangerlist"));
				directoryvo.setUsername(resultSet.getString("username"));
				//directoryvo.setManagerlist(resultSet.getString("managerlist"));
				selectUser.add(directoryvo);
								
			}
		
		}
		catch(Exception e){}
		
		return selectUser;
		
	}
	
	
	
	public List<DirectoryVO>  publicDirectory (DirectoryVO DirectoryVO1) throws Exception{
		List<DirectoryVO> publicDirectory = new ArrayList<DirectoryVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT * FROM term_project.directory where access_type = 'Public'";
			System.out.println(s);
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in directory dao");
				DirectoryVO directoryvo = new DirectoryVO();
				//directoryvo.setUsername(resultSet.getString("username"));
				directoryvo.setDict_name(resultSet.getString("dict_name"));
				directoryvo.setCreated_by(resultSet.getString("created_by"));
				directoryvo.setAccess_type(resultSet.getString("access_type"));
				directoryvo.setDict_id(resultSet.getInt("dict_id"));
				//directoryvo.setManagerlist(resultSet.getString("managerlist"));
				publicDirectory.add(directoryvo);
								
			}
		
		}
		catch(Exception e){}
		
		return publicDirectory;
		
	}
	
	public List<DirectoryVO>  privateDirectory (DirectoryVO directoryvo, String username) throws Exception{
		List<DirectoryVO> privateDirectory = new ArrayList<DirectoryVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
		//	String username = directoryvo.getUsername();
			String s = "Select * from directory where managerlist LIKE '%"+username+"%' and access_type='Private'";
			System.out.println(s);
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in directory dao");
				DirectoryVO directoryvo1 = new DirectoryVO();
				//directoryvo.setUsername(resultSet.getString("username"));
				directoryvo1.setDict_name(resultSet.getString("dict_name"));
				directoryvo1.setCreated_by(resultSet.getString("created_by"));
				directoryvo1.setAccess_type(resultSet.getString("access_type"));
				directoryvo1.setDict_id(resultSet.getInt("dict_id"));
				//directoryvo.setManagerlist(resultSet.getString("managerlist"));
				privateDirectory.add(directoryvo1);
								
			}
		
		}
		catch(Exception e){}
		
		return privateDirectory;
		
	}
	
	public List<DirectoryVO>  protectedDirectory (DirectoryVO directoryvo, String username) throws Exception{
		List<DirectoryVO> protectedDirectory = new ArrayList<DirectoryVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
		//	String username = directoryvo.getUsername();
			String s = "Select * from directory where managerlist LIKE '%"+username+"%' and access_type='Protected'";
		//	String s1 = "SELECT managerlist from employee where username='"+username+"'";
			System.out.println(s);
			//System.out.println(s1);
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			//PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(s1);
			//preparedStatement = (PreparedStatement) con.prepareStatement(s1);
			ResultSet resultSet = preparedStatement.executeQuery();
		//	resultSet = preparedStatement1.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in directory dao");
				//DirectoryVO directoryvo = new DirectoryVO();
				//directoryvo.setUsername(resultSet.getString("username"));
				directoryvo.setDict_name(resultSet.getString("dict_name"));
				directoryvo.setCreated_by(resultSet.getString("created_by"));
				directoryvo.setAccess_type(resultSet.getString("access_type"));
				directoryvo.setDict_id(resultSet.getInt("dict_id"));
				//directoryvo.setManagerlist(resultSet.getString("managerlist"));
				protectedDirectory.add(directoryvo);
								
			}
		
		}
		catch(Exception e){}
		
		return protectedDirectory;
		
	}
	
	public String managerlist1(DirectoryVO directoryvo, String username){
		//String created_by = directoryvo.getCreated_by();
		//String username = directoryvo.getUsername();
		String managerlist1 = null;
		Connection con = null;
		//PreparedStatement preparedStatement = null;
		System.out.println(username);
		try{
			con =DataConnection.createConnection();
			String query = "Select managerlist from employee where username='"+username+"'";
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(query);
			ResultSet resultSet = preparedStatement1.executeQuery();
			System.out.println("Dao Mangerlist 1 after query");
			while(resultSet.next()){
				//DirectoryVO directoryVO2 = new DirectoryVO();
				managerlist1 = resultSet.getString("managerlist");
				//System.out.println(resultSet.getString("managerlist1"));
			}
		}
		catch(Exception e){}
		return managerlist1;
		
	}

	public List<DirectoryVO>  managerMatchDefault (DirectoryVO directoryvo, String username) throws Exception{
		List<DirectoryVO> managerMatchDefault = new ArrayList<DirectoryVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			String s = "Select * from directory where created_by='"+username+"' and access_type='Default'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				DirectoryVO DirectoryVO1=new DirectoryVO();
				System.out.println("in directory dao");
				DirectoryVO1.setDict_name(resultSet.getString("dict_name"));
				DirectoryVO1.setCreated_by(resultSet.getString("created_by"));
				DirectoryVO1.setAccess_type(resultSet.getString("access_type"));
				managerMatchDefault.add(DirectoryVO1);
								
			}
		
		}
		catch(Exception e){}
		
		return managerMatchDefault;
		
	}
	
	public List<DirectoryVO>  managerMatch (DirectoryVO directoryvo, String username) throws Exception{
		List<DirectoryVO> managerMatch = new ArrayList<DirectoryVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			String s = "Select * from directory where created_by='"+username+"' and access_type='Protected'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				DirectoryVO DirectoryVO1=new DirectoryVO();
				System.out.println("in directory dao");
				DirectoryVO1.setDict_name(resultSet.getString("dict_name"));
				DirectoryVO1.setCreated_by(resultSet.getString("created_by"));
				DirectoryVO1.setAccess_type(resultSet.getString("access_type"));
				managerMatch.add(DirectoryVO1);
								
			}
		
		}
		catch(Exception e){}
		
		return managerMatch;
		
	}
	
	public List<DirectoryVO>  defaultDirectory (DirectoryVO directoryvo, String username) throws Exception{
		List<DirectoryVO> defaultDirectory = new ArrayList<DirectoryVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			String s = "Select * from directory where managerlist LIKE '%"+username+"%' and access_type='Default'";
			System.out.println(s);
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in directory dao");
				DirectoryVO directoryvo1 = new DirectoryVO();
				directoryvo1.setDict_name(resultSet.getString("dict_name"));
				directoryvo1.setCreated_by(resultSet.getString("created_by"));
				directoryvo1.setAccess_type(resultSet.getString("access_type"));
				directoryvo1.setDict_id(resultSet.getInt("dict_id"));
				//directoryvo.setManagerlist(resultSet.getString("managerlist"));
				defaultDirectory.add(directoryvo1);
								
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(defaultDirectory.size());
		return defaultDirectory;
		
	}
	
public String approveLeave(int leave_id){
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try{
			con =DataConnection.createConnection();
			//String query = "insert into directory (dict_name, created_by, access_type, managerlist) values (?,?,?,?)";
			String q = "Update term_project.leave SET status='Approved' where leave_id='"+leave_id+"'";
			//System.out.println(q);
			System.out.println(q);
			//preparedStatement.executeUpdate(q);
			preparedStatement = (PreparedStatement) con.prepareStatement(q);
			int i= preparedStatement.executeUpdate();

			if (i!=0)
			return "Success";
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "Opppsss... Something went Wrong";
		
		
	}

public String DisapproveLeave(int leave_id){
	
	Connection con = null;
	PreparedStatement preparedStatement = null;
	try{
		con =DataConnection.createConnection();
		//String query = "insert into directory (dict_name, created_by, access_type, managerlist) values (?,?,?,?)";
		String q = "Update term_project.leave SET status='Disapproved by Manager' where leave_id='"+leave_id+"'";
		//System.out.println(q);
		System.out.println(q);
		//preparedStatement.executeUpdate(q);
		preparedStatement = (PreparedStatement) con.prepareStatement(q);
		int i= preparedStatement.executeUpdate();

		if (i!=0)
		return "Success";
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return "Opppsss... Something went Wrong";
	
	
}




}
