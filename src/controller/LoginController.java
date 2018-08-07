package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.LoginVO;
import dao.LoginDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside Login servlet");
		//response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String type1 = request.getParameter("type");
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
	//	session.setAttribute("type1", type1);
		//session.setAttribute("password", password);
	
		
		LoginVO loginvo = new LoginVO();
		loginvo.setUsername(username);
		loginvo.setPassword(password);
		LoginDAO logindao = new LoginDAO();
		
		
		
		try{
			boolean isvalidlogin = logindao.Logindao(loginvo);
			String type=loginvo.getType();
			//session.setAttribute("type", type);
			
			//System.out.println("Madar" + type);
			
			if(isvalidlogin){
				if(type.equals("Employee")){
					System.out.println("Hello");
					String first_name = loginvo.getFirst_name();
					session.setAttribute("first_name", first_name);
					String last_name = loginvo.getLast_name();
					session.setAttribute("last_name", last_name);
					System.out.println("fname"+first_name);
					//response.sendRedirect("Employee/employeeDash.jsp");
					session.setAttribute("type", type);
					System.out.println("Madar" + type);
					RequestDispatcher rd = request.getRequestDispatcher("Employee/employeeDash.jsp");
					rd.forward(request, response);
				}
				else if(type.equals("Manager")){
					String first_name = loginvo.getFirst_name();
					session.setAttribute("first_name", first_name);
					String last_name = loginvo.getLast_name();
					session.setAttribute("last_name", last_name);
					String message =  first_name+ " " + last_name;
					//response.sendRedirect("Manager/managerDash.jsp");
					session.setAttribute("type", type);
					System.out.println("Madar" + type);
					
					RequestDispatcher rd = request.getRequestDispatcher("Manager/managerDash.jsp");
					rd.forward(request, response);
					
				}
				else if(type.equals("Admin")){
					String first_name = loginvo.getFirst_name();
					session.setAttribute("first_name", first_name);
					String last_name = loginvo.getLast_name();
					session.setAttribute("last_name", last_name);
					String message =  first_name+ " " + last_name;
					session.setAttribute("type", type);
					System.out.println("Madar" + type);
					
					//response.sendRedirect("Manager/managerDash.jsp");
					
					RequestDispatcher rd = request.getRequestDispatcher("Admin/adminDash.jsp");
					rd.forward(request, response);
					
				}
				
				
			}	
			/*else if(type.equals("Invalid Username & Password")){
					String wronginput = "Invalid Credentials";
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					request.setAttribute("wronginput",wronginput);
					rd.forward(request, response);
			}*/
			else{
				
				String error = "Please provide all fields";
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				request.setAttribute("error",error);
				rd.forward(request, response);
				
			}
		}
		catch(Exception e){
			
		}
	}
}

