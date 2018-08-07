package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DirectoryDAO;
import dao.EmployeeDAO;
import vo.DirectoryVO;
import vo.EmployeeVO;

/**
 * Servlet implementation class DirectoryController
 */
@WebServlet("/DirectoryController")
public class DirectoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String type = (String) session.getAttribute("type");
		
		System.out.println("updat employee enter doget");
		DirectoryVO directoryvo = new DirectoryVO();
		DirectoryDAO directorydao = new DirectoryDAO();
		
		List<DirectoryVO> selectUser = new ArrayList<DirectoryVO>();
		try {
			selectUser=directorydao.select(username);
			session.setAttribute("selectUser", selectUser);
			//session.setAttribute("dict_name", dict_name);
			System.out.println("sfter dao in update employee list lenght = "+ selectUser.size()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("Manager/createDirectory.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String dict_name = request.getParameter("dict_name");
		String created_by = request.getParameter("created_by");
		String access_type = request.getParameter("access_type");
		//String managerlist = request.getParameter("managerlist");
		HttpSession session = request.getSession();
		session.setAttribute("created_by", created_by);
		session.setAttribute("dict_name", dict_name);
		session.setAttribute("access_type", access_type);
		System.out.println("from session"+session.getAttribute("created_by"));
		System.out.println("from session"+session.getAttribute("dict_name"));
		System.out.println("from session"+session.getAttribute("access_type"));
	
		if(dict_name.isEmpty() || created_by.isEmpty() || access_type.isEmpty()){
			
			String message = "Please provide all fields";
			RequestDispatcher rd = request.getRequestDispatcher("Manager/createDirectory.jsp");
			request.setAttribute("msg",message);
			rd.forward(request, response);
		}
		else{
			System.out.println("into the condition");
			DirectoryVO directoryvo = new DirectoryVO();
			directoryvo.setDict_name(dict_name);
			directoryvo.setCreated_by(created_by);
			directoryvo.setAccess_type(access_type);
			
			DirectoryDAO directorydao = new DirectoryDAO();
			String managerlist = directorydao.managerlist(directoryvo);
			directoryvo.setManagerlist(managerlist);
			try{
				directorydao.createDict(directoryvo);
				//directorydao.managerlist(directoryvo);
			}
			catch(Exception e){}
			String created ="Directory Created";
			session.setAttribute("created", created);
			response.sendRedirect("Manager/managerDash.jsp");
		}
		
	}

}
