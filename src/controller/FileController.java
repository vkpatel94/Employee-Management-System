package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.FileDAO;

import vo.FileVO;

/**
 * Servlet implementation class FileController
 */
@WebServlet("/FileController")
@MultipartConfig
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String type = (String) session.getAttribute("type");
		
		String dict_name = (String) session.getAttribute("dict_name");
		String created_by= (String) session.getAttribute("created_by");
		int dict_id= (int) session.getAttribute("dict_id");
		System.out.println(username);
		System.out.println(dict_name + "FileController");
		InputStream inputStream = null; 
		String description=request.getParameter("description");
		FileVO filevo=new FileVO();
		Part filePart = request.getPart("photo");
		FileDAO filedao = new FileDAO();
		String filename = null;
		//Part filePart = request.getPart("photo");
		if (filePart != null) {
		      System.out.println("in if in controller");   
		      inputStream = filePart.getInputStream();
		      String header=filePart.getHeader("content-disposition");
		      filename = header.substring(header.indexOf("filename=\"")).split("\"")[1];
		}
		
				System.out.println("in controller"+filename+description+username);
				filevo.setFile(filePart);
				filevo.setInputstream(inputStream);
				filevo.setDescription(description);
				filevo.setFilename(filename);
				filevo.setDict_name(dict_name);
				filevo.setDict_id(dict_id);
				filevo.setCreated_by(created_by);
		      // filevo.setUser(user);
		
		       //Userdao ob=new Userdao();
		       
		try {
			filedao.upload(filevo);
			response.sendRedirect("Directory/viewDirectory.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		       //List l1=new ArrayList();
		      
		
		       //HttpSession session1 = request.getSession();
		      // session.setAttribute("find", l1);
		
		       
	}    
		}
	

