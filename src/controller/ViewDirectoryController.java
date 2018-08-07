package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DirectoryDAO;
import dao.FileDAO;
import sun.security.util.Length;
import vo.AdminVO;
import vo.DirectoryVO;
import vo.FileVO;
import vo.ShowFileVO;

/**
 * Servlet implementation class ViewDirectoryController
 */
@WebServlet("/ViewDirectoryController")
public class ViewDirectoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDirectoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		HttpSession session = request.getSession();
		//session.setAttribute("username", username);
		String username = (String) session.getAttribute("username");
		String type = (String) session.getAttribute("type");
		
		System.out.println(username + "    " + "madar");
		String flag = request.getParameter("flag");
		DirectoryDAO directorydao = new DirectoryDAO();
		System.out.println(flag);
		
		if(flag.equals("public")){
			DirectoryVO DirectoryVO1= new DirectoryVO();
			List<DirectoryVO> publicDirectory = new ArrayList<DirectoryVO>();
			
			
			try{
				publicDirectory=directorydao.publicDirectory(DirectoryVO1);
				System.out.println(publicDirectory);
				session.setAttribute("publicDirectory", publicDirectory);
				response.sendRedirect("Directory/publicDirectory.jsp");
			}
			catch(Exception e){
				
			}
			
		}
		else if(flag.equals("private")){
			System.out.println("flag" + flag + "private");
			DirectoryVO DirectoryVO1= new DirectoryVO();
			List<DirectoryVO> privateDirectory = new ArrayList<DirectoryVO>();
			//List<DirectoryVO> privateDirectoryRestriction = new ArrayList<DirectoryVO>();
			
			try{
							
				System.out.println("Hi");
				privateDirectory=directorydao.privateDirectory(DirectoryVO1, username);
		
				session.setAttribute("privateDirectory", privateDirectory);
				response.sendRedirect("Directory/privateDirectory.jsp");
				
			}
			catch(Exception e){
				
			}
		}
		else if(flag.equals("protected")){
			System.out.println("flag" + flag + "protected");
			DirectoryVO DirectoryVO1= new DirectoryVO();
			List<DirectoryVO> protectedDirectory = new ArrayList<DirectoryVO>();
			List<DirectoryVO> managerMatch = new ArrayList<DirectoryVO>();
			List managerMatch1 = new ArrayList();
			//List<DirectoryVO> privateDirectoryRestriction = new ArrayList<DirectoryVO>();
			
			try{
							
				System.out.println("Hi");
				protectedDirectory=directorydao.protectedDirectory(DirectoryVO1, username);
				String managerlist1 = directorydao.managerlist1(DirectoryVO1, username);
				//DirectoryVO1.setManagerlist(managerlist1);
				System.out.println("Controller" + managerlist1);
				String man[]= managerlist1.split("_");
				for(int i=0;i<man.length;i++)
				{
					managerMatch= directorydao.managerMatch(DirectoryVO1, man[i]);
					System.out.println(managerMatch.size());
					for(int k=0;k<managerMatch.size();k++)
					{
						managerMatch1.add(managerMatch.get(k));
					}
				}
				
				System.out.println(managerMatch1.size());
				session.setAttribute("protectedDirectory", protectedDirectory);
				session.setAttribute("l2", managerMatch1);
				response.sendRedirect("Directory/protectedDirectory.jsp");
				
			}
			catch(Exception e){
				
			}
		}
		
		else if(flag.equals("default")){
			System.out.println("flag" + flag + "default");
			DirectoryVO DirectoryVO1= new DirectoryVO();
			List<DirectoryVO> defaultDirectory = new ArrayList<DirectoryVO>();
			List<DirectoryVO> managerMatchDefault = new ArrayList<DirectoryVO>();
			List managerMatch1 = new ArrayList();
			//List<DirectoryVO> privateDirectoryRestriction = new ArrayList<DirectoryVO>();
			
			try{
							
				System.out.println("Hi");
				defaultDirectory=directorydao.defaultDirectory(DirectoryVO1, username);
				String managerlist1 = directorydao.managerlist1(DirectoryVO1, username);
				//DirectoryVO1.setManagerlist(managerlist1);
				System.out.println("Controller" + managerlist1);
				String man[]= managerlist1.split("_");
				for(int i=0;i<man.length-1;i++)
				{
					managerMatchDefault= directorydao.managerMatchDefault(DirectoryVO1, man[i]);
					System.out.println(managerMatchDefault.size());
					for(int k=0;k<managerMatchDefault.size();k++)
					{
						managerMatch1.add(managerMatchDefault.get(k));
						
					}
				}
				
				System.out.println(managerMatch1.size());
				System.out.println("default size"+defaultDirectory.size());
				session.setAttribute("defaultDirectory", defaultDirectory);
				session.setAttribute("l2", managerMatch1);
				response.sendRedirect("Directory/defaultDirectory.jsp");
				
			}
			catch(Exception e){
				
			}
		}
		
		else if(flag.equals("upload")){
			String dict_name=request.getParameter("dict_name");
			session.setAttribute("dict_name", dict_name);
			System.out.println("go go go "+dict_name);
			int dict_id= Integer.parseInt(request.getParameter("dict_id"));
			session.setAttribute("dict_id", dict_id);
			System.out.println(dict_id);
			String created_by= request.getParameter("created_by");
			session.setAttribute("created_by", created_by);
		
			//FileVO FileVO=new FileVO();
			ShowFileVO ShowFileVO = new ShowFileVO();
			ShowFileVO.setDict_name(dict_name);
			FileDAO FileDAO=new FileDAO();
			List<ShowFileVO> l4= new ArrayList<ShowFileVO>();
			try {
				l4= FileDAO.show(ShowFileVO);
				System.out.println(l4.size());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("fileList", l4);
			response.sendRedirect("Directory/uploadFile.jsp");
			
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
