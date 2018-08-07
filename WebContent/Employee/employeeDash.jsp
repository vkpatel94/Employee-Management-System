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

else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Manager") )
{response.sendRedirect("../Manager/managerDash.jsp");}
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

<div align = "center">
<%
String update = (String) session.getAttribute("update");
if(update!=null)
out.println(" "+ update); %>
</div>
<body>
<div align = "left">
	<a href="employeeDash.jsp">Home</a>
</div>
<div align = "right">
	<form action="<%=request.getContextPath() %>/Logout">
		<input type="submit" value="Logout" />
		</form>
	</div>
	<div align = "right">
	<table>
	<tr>
		<td><a href="<%=request.getContextPath() %>/UpdateEmployee">Update Profile</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/PageRedirect?flag=dict">View Directory
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/LeaveController?flag=leave">Request For Leave
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/LeaveController?flag=listleave">View Leave Status
		</a></td>
	</tr>
	</table>
	</div>

</body>
</html>