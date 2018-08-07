package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dao.DirectoryDAO;
import dao.LeaveDAO;
import vo.AdminVO;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String type = (String) session.getAttribute("type");
		
		//System.out.println(type +"hahahahahahhahahah");
		System.out.println("list employee enter doget");
		String flag = request.getParameter("flag");
		String flag1 = request.getParameter("flag1");

		//AdminVO adminvo = new AdminVO();
		
		AdminDAO admindao = new AdminDAO();
		System.out.println(flag);
		if(flag.equals("inactive")){
		List<AdminVO> emplist= new ArrayList<AdminVO>();
		List<AdminVO> managerlist = new ArrayList<AdminVO>();
		try {
			emplist=admindao.emplist(username);
			session.setAttribute("emplist", emplist);
			System.out.println("after dao in update employee list lenght = "+ emplist.size()); 
			managerlist = admindao.managerlist(username);
			session.setAttribute("managerlist", managerlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.sendRedirect("Admin/list.jsp");
	}
		
		else if(flag.equals("active")){
			List<AdminVO> activelist= new ArrayList<AdminVO>();
			try {
				activelist=admindao.activelist(username);
				session.setAttribute("activelist", activelist);
				System.out.println("AFter dao in list employee list lenght = "+ activelist.size()); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("Admin/activeEmp.jsp");
		}
		else if(flag.equals("assign")){
		//HttpSession session = request.getSession();
		List<AdminVO> emplist= new ArrayList<AdminVO>();
		List<AdminVO> managerlist = new ArrayList<AdminVO>();
		AdminVO adminvo = new AdminVO();
		//AdminDAO admindao = new AdminDAO();
		String username1 = (String) session.getAttribute("username");
		try {
			emplist=admindao.emplist(username1);
			session.setAttribute("emplist", emplist);
			System.out.println("after dao in update employee list lenght = "+ emplist.size()); 
			managerlist = admindao.managerlist(username1);
			session.setAttribute("managerlist", managerlist);
		
		
		
	
			response.sendRedirect("Admin/assignedManager.jsp");
		}
		catch(Exception e){
			}
		}
		else if(flag.equals("update")){
			response.sendRedirect("Admin/updateProfileAdmin.jsp");
			
		}
		
		else if(flag.equals("approve")){
			
			int leave_id= Integer.parseInt(request.getParameter("leave_id"));
			DirectoryDAO DirectoryDAO=new DirectoryDAO();
			DirectoryDAO.approveLeave(leave_id);
			String msg= "Request is Approved";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("Manager/managerDash.jsp").forward(request, response);
			//response.sendRedirect("Manager/managerDash.jsp");
		}
		else if(flag.equals("disapprove")){
			
			int leave_id= Integer.parseInt(request.getParameter("leave_id"));
			DirectoryDAO DirectoryDAO=new DirectoryDAO();
			DirectoryDAO.DisapproveLeave(leave_id);
			String msg= "Request is disapproved";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("Manager/managerDash.jsp").forward(request, response);
	
	//		response.sendRedirect("Manager/managerDash.jsp");
		}
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	/*	HttpSession session = request.getSession();
		List<AdminVO> emplist= new ArrayList<AdminVO>();
		List<AdminVO> managerlist = new ArrayList<AdminVO>();
		AdminVO adminvo = new AdminVO();
		AdminDAO admindao = new AdminDAO();
		String username1 = (String) session.getAttribute("username");
		try {
			emplist=admindao.emplist(username1);
			session.setAttribute("emplist", emplist);
			System.out.println("after dao in update employee list lenght = "+ emplist.size()); 
			managerlist = admindao.managerlist(username1);
			session.setAttribute("managerlist", managerlist);*/

			HttpSession session = request.getSession();
			List<AdminVO> emplist= new ArrayList<AdminVO>();
			List<AdminVO> managerlist = new ArrayList<AdminVO>();
			AdminVO adminvo = new AdminVO();
			AdminDAO admindao = new AdminDAO();
			String type = (String) session.getAttribute("type");
			if(request.getParameter("employee")!= null & request.getParameter("manager")!=null){
			
			
			String username = request.getParameter("employee");
			System.out.println(username);
			String manager = request.getParameter("manager");
			System.out.println(manager);
			
			//HttpSession session = request.getSession();
			//session.setAttribute("username", username);
			
			//AdminVO adminvo = new AdminVO();
			adminvo.setManager(manager);
			System.out.println(adminvo.getManager());
			adminvo.setUsername(username);
			
			//AdminDAO admindao = new AdminDAO();
			 try{
				 admindao.updatemanager(adminvo,manager);
			 }
			 catch(Exception e){
				 
			 }
			 String assigned = "Successfully Assigned - ";
				//RequestDispatcher rd = request.getRequestDispatcher("Admin/adminDash.jsp");
				session.setAttribute("assigned",assigned);
				response.sendRedirect("Admin/adminDash.jsp");
		}
		else{
			String message = "Please provide all fields";
			RequestDispatcher rd = request.getRequestDispatcher("Admin/list.jsp");
			request.setAttribute("msg",message);
			rd.forward(request, response);
		}
		
	
	}
	
}
