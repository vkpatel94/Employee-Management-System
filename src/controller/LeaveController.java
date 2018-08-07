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

import dao.LeaveDAO;
import dao.RegistrationDAO;
import vo.AdminVO;
import vo.LeaveVO;
import vo.RegistrationVO;

/**
 * Servlet implementation class LeaveController
 */
@WebServlet("/LeaveController")
public class LeaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String flag = request.getParameter("flag");
		{
		if(flag.equals("leave")){
			response.sendRedirect("Employee/leaveRequest.jsp");
		}
		else if(flag.equals("list")){
			LeaveDAO leavedao = new LeaveDAO();
			LeaveVO leavevo = new LeaveVO();
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			List<LeaveVO> pendingLeave= new ArrayList<LeaveVO>();
		
			try {
				pendingLeave=leavedao.pendingLeave(username);
				session.setAttribute("pendingLeave", pendingLeave);
				System.out.println("AFter dao in list employee list lenght = "+ pendingLeave.size()); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("Manager/ViewLeaveRequest.jsp");
		}
		else if(flag.equals("listleave")){
			LeaveDAO leavedao = new LeaveDAO();
			LeaveVO leavevo = new LeaveVO();
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			List<LeaveVO> listLeave= new ArrayList<LeaveVO>();
		
			try {
				listLeave=leavedao.listLeave(username);
				session.setAttribute("listLeave", listLeave);
				System.out.println("AFter dao in list employee list lenght = "+ listLeave.size()); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("Employee/viewLeaveRequest.jsp");
		}
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		String leave_date = request.getParameter("leave_date");
		String leave_reason = request.getParameter("leave_reason");
		int days = Integer.parseInt(request.getParameter("days"));
		
		HttpSession session = request.getSession();
		String type = (String) session.getAttribute("type");
		String employee = (String) session.getAttribute("username");
			
		
			LeaveVO leavevo = new LeaveVO();
			leavevo.setLeave_reason(leave_reason);
			leavevo.setLeave_date(leave_date);
			leavevo.setDays(days);
			//leavevo.setManager(manager);
			leavevo.setEmployee(employee);
			System.out.println(employee);
			
			LeaveDAO lDAO = new LeaveDAO();
			String manager = null;
			try {
				manager = lDAO.manager(leavevo);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			leavevo.setManager(manager);
			try{
			//System.out.println("in controller before dao");
			
			int m=lDAO.updateAvailCount(employee,days);
			int n= m-days;
			if(n<0)
			{
				response.sendRedirect("Employee/employeeDash.jsp");
			}
			//System.out.println("in controller before after dao");
			
			else
			{
				lDAO.leaveRequest(leavevo,n);
				lDAO.updateAvailCount1(employee, n);
				response.sendRedirect("Employee/employeeDash.jsp");
				
			}
	}
			catch(Exception e){
			
		}
//		response.sendRedirect("Employee/employeeDash.jsp");
	
	}
}


