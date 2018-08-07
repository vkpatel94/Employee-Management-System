<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<%if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Admin") )
{response.sendRedirect("../Admin/adminDash.jsp");}

else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Employee") )
{response.sendRedirect("../Employee/employeeDash.jsp");}
%>
</head>
</head>
<%
String first_name = (String) session.getAttribute("first_name");
String last_name = (String) session.getAttribute("last_name");
if(first_name!=null)
out.println(" Welcome, "+ first_name+last_name); %>


<%
	if(session.getAttribute("username")== null)
	{
		response.sendRedirect("../index.jsp");
	}
%>
<br>
<%
String message= (String) request.getAttribute("msg");
if(message!=null)
{
	out.println("\n"+message);
}
%>
<body>

<div align = "right">
	<form action="<%=request.getContextPath() %>/Logout">
	
		<input type="submit" value="Logout" />
		</form>
	</div>
	<div align = "left">
	
	<table>
	<tr>
		<td><a href="#">Assign Bonus
		</a></td>
	</tr>
	<tr>
		<td><a href="#">Pending Leave Request
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/DirectoryController?flag=create">Create Directory
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/PageRedirect?flag=dict">View Directory
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/LeaveController?flag=list"">View Leave Request
		</a></td>
	</tr>
	</table>

	</div>

</body>
</html>