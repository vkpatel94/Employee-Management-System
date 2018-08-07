<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%
	if(session.getAttribute("username")== null)
	{
		response.sendRedirect("../index.jsp");
	}
%>
<%if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Employee") )
{response.sendRedirect("../Employee/employeeDash.jsp");}

else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Manager") )
{response.sendRedirect("../Manager/managerDash.jsp");}
%>
</head>


<%
String first_name = (String) session.getAttribute("first_name");
String last_name = (String) session.getAttribute("last_name");
if(first_name!=null)
out.println(" Welcome, "+ first_name+" "+last_name); %>


<body>
<div align = "left">
	<a href="adminDash.jsp">Home</a>
</div>
	<br><br><br>
	<%
String assigned = (String) request.getAttribute("assigned");
if(assigned!=null)
out.println(" "+ assigned); %>
	<div align = "right">
	<form action="<%=request.getContextPath() %>/Logout">
		<input type="submit" value="Logout" />
		</form>
	</div>
	<div align = "left">
		
	<table>
	<tr>
		<td><a href="<%=request.getContextPath() %>/AdminController?flag=inactive">List of In-Active Employees
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/AdminController?flag=active">Active Employees
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/AdminController?flag=assign">Assigned Manager
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/AdminController?flag=update">Update Organization Profile
		</a></td>
	</tr>
	</table>

	</div>
</body>
</html>