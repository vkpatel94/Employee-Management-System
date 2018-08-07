
package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.regexp.internal.recompile;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import util.DataConnection;
import vo.EmployeeVO;
public class EmployeeDAO {
	
	

	public List<EmployeeVO> select(String username) throws Exception{
		List<EmployeeVO> selectProfile = new ArrayList<EmployeeVO>();
		Connection con =null;
		//PreparedStatement preparedStatement = null;
		
		
		try{
			con =DataConnection.createConnection();
			//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
			String s = "SELECT * FROM term_project.employee where username='"+username+"'";
			System.out.println(s);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				System.out.println("in employee dao getiimg update details in while loop");
				EmployeeVO employeevo = new EmployeeVO();
				employeevo.setEmp_id(resultSet.getInt("emp_id"));
				employeevo.setFirst_name(resultSet.getString("first_name"));
				employeevo.setLast_name(resultSet.getString("last_name"));
				employeevo.setAddress(resultSet.getString("address"));
				employeevo.setEmail(resultSet.getString("email"));
				employeevo.setUsername(resultSet.getString("username"));
				employeevo.setPassword(resultSet.getString("password"));
				employeevo.setPhone_number(resultSet.getString("phone_number"));
				selectProfile.add(employeevo);
								
			}
		
		}
		catch(Exception e){}
		
		return selectProfile;
		
	}
	
	public String updateEmployee(EmployeeVO employeevo){
		Connection con = null;
		con = DataConnection.createConnection();
		String username = employeevo.getUsername();
		try{
			System.out.println("Update DAO"+employeevo.getPassword());
			String s1 = "Update employee set first_name=?, last_name=?, address=?, email=?, password=?, phone_number=? WHERE username='"+username+"'";
			System.out.println(s1);
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s1);
			preparedStatement.setString(1, employeevo.getFirst_name());
			preparedStatement.setString(2, employeevo.getLast_name());
			preparedStatement.setString(3, employeevo.getAddress());
			preparedStatement.setString(4, employeevo.getEmail());
			preparedStatement.setString(5, employeevo.getPassword());
			preparedStatement.setString(6, employeevo.getPhone_number());
			//preparedStatement.setString(7, employeevo.getUsername());
			int i =preparedStatement.executeUpdate();
			System.out.println("Query Executed");
			if(i!=0){
			String str = "success";
			return str;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return "failed";
	}


}
	