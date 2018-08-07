package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DataConnection;
import javax.servlet.http.Part;

import vo.DirectoryVO;
//import veo.Displayvo;
import vo.FileVO;
import vo.ShowFileVO;

public class FileDAO {
	
	
	public String upload(FileVO filevo) throws Exception{
		Part file = filevo.getFile();
		String filename = filevo.getFilename();
		String dict_name = filevo.getDict_name();
		String created_by = filevo.getCreated_by();
		String description = filevo.getDescription();
		int dict_id= filevo.getDict_id();
		
		System.out.println(dict_name);
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try{
			System.out.println("in upload Dao" + filename +dict_name + created_by+description);
			con =DataConnection.createConnection();
			InputStream inputstream = filevo.getInputstream();
			String query = "INSERT INTO file (file, description, filename, dict_name, dict_id, created_by) values (?, ?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setBlob(1, filevo.getInputstream());
			//preparedStatement.setString(7, filevo.getFile().getContentType());
			preparedStatement.setString(2, description);
			preparedStatement.setString(3, filename);
			preparedStatement.setString(4, dict_name);
			preparedStatement.setInt(5, dict_id);
			preparedStatement.setString(6, created_by);
			/*preparedStatement.setString(4, "Alpha");
			preparedStatement.setString(5, "harsh");
			preparedStatement.setString(5, "101");*/
			/*if(inputstream!=null){
		    	preparedStatement.setBinaryStream(1, filevo.getInputstream(),(int) filevo.getFile().getSize());
		    }*/
			preparedStatement.executeUpdate();
			//PreparedStatement preparedStatement = con.prepareStatement("");    
		    System.out.println("After Query");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}

	/*private String dict(FileVO filevo){
		String created_by = filevo.getCreated_by();
		String managerlist = null;
		Connection con = null;
		//PreparedStatement preparedStatement = null;
		try{
			con =DataConnection.createConnection();
			String query = "Select dict_name, created_by from directory where username='"+created_by+"'";
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(query);
			ResultSet resultSet = preparedStatement1.executeQuery();
			while(resultSet.next()){
				//managerlist =resultSet.getString("managerlist");
				
			}
		}
		catch(Exception e){}
		return dict;
		
	} */
	
	public List<ShowFileVO> show(ShowFileVO ShowFileVO) throws ClassNotFoundException {
		
		List<ShowFileVO> l1=new ArrayList<ShowFileVO>();
		
		Connection con = null;
		//PreparedStatement preparedStatement = null;
		try{
				con =DataConnection.createConnection();
				//preparedStatement = con.prepareStatement("UPDATE employee SET first_name=?, last_name=? WHERE username=? ");							
				String s = "SELECT * FROM file WHERE dict_name='"+ShowFileVO.getDict_name()+"'";
				System.out.println(s);
				
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(s);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					
					System.out.println("in directory dao");
					ShowFileVO filevo = new ShowFileVO();
					//directoryvo.setUsername(resultSet.getString("username"));
					filevo.setFile(resultSet.getBlob("file"));
					
					System.out.println(resultSet.getBlob("file"));
					
					filevo.setFilename(resultSet.getString("filename"));
					//filevo.setDescription(resultSet.getString("description"));
					filevo.setFile_id(resultSet.getInt("file_id"));
					filevo.setDescription(resultSet.getString("description"));
					filevo.setDict_name(resultSet.getString("dict_name"));
					filevo.setCreated_by(resultSet.getString("created_by"));
					filevo.setType(resultSet.getString("type"));
					l1.add(filevo);
				}
		}
			
			catch(Exception e){}
		return l1;
	}
	public static ShowFileVO show1(int file_id) {
		ShowFileVO showfile = new ShowFileVO();
		Connection con=null;
		try{
		con =DataConnection.createConnection();
		
		Statement st=con.createStatement();
		ResultSet rs = st.executeQuery("select * from file where file_id ='"+file_id+"'");
		rs.next();
		System.out.println("madar"+rs.getInt("file_id"));
		showfile.setFile_id(rs.getInt("file_id"));
		showfile.setFile(rs.getBlob("file"));
		showfile.setFilename(rs.getString("filename"));
		showfile.setType(rs.getString("type"));
		showfile.setDescription(rs.getString("description"));
		//.setUsername(rs.getString("username"));

		st.close();
		con.close();
		}	
		catch(Exception e){}
		return showfile;

		}
}
