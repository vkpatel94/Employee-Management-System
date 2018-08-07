package dao;

import java.sql.*;
import util.DataConnection;
import vo.LoginVO;
//import util.DataConnection;

public class LoginDAO {
	

	public boolean Logindao(LoginVO loginvo) throws Exception
	{
		System.out.println("inside DAO");
		String username = loginvo.getUsername();
		String password = loginvo.getPassword();
		//System.out.println("Usernameis"+username);
	//	String type = loginvo.getType();
		String dbuser, dbpass;
		//String result = null;
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try{

			con =DataConnection.createConnection();
			System.out.println("yessss");
			preparedStatement = con.prepareStatement("SELECT * FROM term_project.employee where username='"+username+"' and password='"+password+"'");
			System.out.println("NOOOOO");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("Query Executed");
			while(resultSet.next())
			{
				
				dbuser = resultSet.getString("username");
				dbpass = resultSet.getString("password");
				if(dbuser.equals(username) && dbpass.equals(password)){
					loginvo.setFirst_name(resultSet.getString("first_name"));
					loginvo.setLast_name(resultSet.getString("last_name"));
					loginvo.setType(resultSet.getString("type"));
					System.out.println("username and password match");
					return true;
				}
			
			
				else
				{
					return false;
				}
			}
		}
			
		catch(Exception e){
		
		}
	
	return false;
		
	}
}