
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RegistrationDAO;
import vo.RegistrationVO;
/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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
		// TODO Auto-generated method stub
	//	doGet(request, response);
		response.setContentType("text/html");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone_number = request.getParameter("phone_number");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		String type = (String) session.getAttribute("type");
		
		session.setAttribute("username", username);
	//	session.setAttribute("password", password);
		session.setAttribute("first_name", first_name);
		session.setAttribute("last_name", last_name);
		if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || phone_number.isEmpty() || address.isEmpty())
			{
				String message = "Please provide all fields";
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				request.setAttribute("msg",message);
				rd.forward(request, response);
			}
		else{
			RegistrationVO registrationvo = new RegistrationVO();
			registrationvo.setFirst_name(first_name);
			registrationvo.setLast_name(last_name);
			registrationvo.setUsername(username);
			registrationvo.setEmail(email);
			registrationvo.setPassword(password);
			registrationvo.setPhone_number(phone_number);
			registrationvo.setAddress(address);
			
			RegistrationDAO rDAO = new RegistrationDAO();
			
			try{
				//System.out.println("in controller before dao");
				rDAO.registerUser(registrationvo);
				//System.out.println("in controller before after dao");
			}
			catch(Exception e){
				String message1 = "Invalid User Name" ;
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				request.setAttribute("msg1",message1);
				rd.forward(request, response);
			}
			String message = "Successfully Registered - " + first_name +" "+ last_name;
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg",message);
			rd.forward(request, response);
		
		}
	}

	

}
