package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DataConnection;
import vo.RegistrationVO;
import controller.RegistrationController;

public class RegistrationDAO {
	public String registerUser(RegistrationVO registrationvo){
		
		String first_name = registrationvo.getFirst_name();
		String last_name = registrationvo.getLast_name();
		String email = registrationvo.getEmail();
		String address = registrationvo.getAddress();
		String phone_number = registrationvo.getPhone_number();
		String username = registrationvo.getUsername();
		String password = registrationvo.getPassword();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try{
			con =DataConnection.createConnection();
			String query = "insert into employee (first_name,last_name,email,address,phone_number,username,password) values (?,?,?,?,?,?,?)";
			System.out.println(query);
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, first_name);
			preparedStatement.setString(2, last_name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, phone_number);
			preparedStatement.setString(6, username);
			preparedStatement.setString(7, password);
			
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
