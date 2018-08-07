package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.EmployeeDAO;
import util.DataConnection;
import vo.EmployeeVO;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String type = (String) session.getAttribute("type");
		
		System.out.println("updat employee enter doget");
		EmployeeVO empvo = new EmployeeVO();
		EmployeeDAO empdao = new EmployeeDAO();
				
		
		List<EmployeeVO> updateProfile= new ArrayList<EmployeeVO>();
		try {
			updateProfile=empdao.select(username);
			session.setAttribute("updateProfile", updateProfile);
			System.out.println("sfter dao in update employee list lenght = "+ updateProfile.size()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("Employee/employeeUpdate.jsp");
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		//String flag = request.getParameter("flag");
		//System.out.println("check flag"+flag);
		//(flag.equals("updateProfile")){
			//int emp_id = Integer.parseInt(request.getParameter("emp_id"));
			//ystem.out.println("emp check"+emp_id);
		/*	EmployeeVO EmployeeVO = new EmployeeVO();
			EmployeeVO.setEmp_id(emp_id);
			EmployeeDAO employeedao = new EmployeeDAO();
			System.out.println("InController update profile");
			List<EmployeeVO> updateProfile= new ArrayList<EmployeeVO>();
			try {
				updateProfile=employeedao.update();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//session.setAttribute("update",updateProfile.);
			System.out.println(emp_id);
			
			
		}*/
			response.setContentType("text/html");
			String flag = request.getParameter("flag");
			System.out.println(flag);
			if(flag.equals("emp")){
				HttpSession session = request.getSession();
				String username = (String) session.getAttribute("username");
				String first_name = request.getParameter("first_name");
				String last_name = request.getParameter("last_name");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				String phone_number = request.getParameter("phone_number");
				String password = request.getParameter("password");
			
			System.out.println(first_name);

				EmployeeVO employeevo = new EmployeeVO();
				employeevo.setUsername(username);
				employeevo.setFirst_name(first_name);
				employeevo.setLast_name(last_name);
				employeevo.setAddress(address);
				employeevo.setPhone_number(phone_number);
				employeevo.setEmail(email);
				employeevo.setPassword(password);
			
				EmployeeDAO employeedao = new EmployeeDAO();
					
			try{
				employeedao.updateEmployee(employeevo);
				//System.out.println(str);
				
				
			}
			catch(Exception e){
				
			}
			String update ="Profile Updated";
			session.setAttribute("update", update);
			response.sendRedirect("Employee/employeeDash.jsp");
			
			
			}
	}
		
}

