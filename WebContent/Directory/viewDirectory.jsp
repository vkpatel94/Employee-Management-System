<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<%
	if(session.getAttribute("username")== null)
	{
		response.sendRedirect("../index.jsp");
	}
%>
<%
String first_name = (String) session.getAttribute("first_name");
String last_name = (String) session.getAttribute("last_name");
if(first_name!=null)
out.println(" Welcome, "+ first_name+last_name); %>
<body>
<div align = "right">
	<form action="<%=request.getContextPath() %>/Logout">
		<input type="submit" value="Logout" />
		</form>
	</div>
	<div align = "left">
		
	<table>
	<tr>
		<td><a href="<%=request.getContextPath() %>/ViewDirectoryController?flag=public">Public Directory
		</a></td>
	</tr>
	<tr></tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/ViewDirectoryController?flag=private">Private Directory
		</a></td>
	</tr>
	<br>
	<tr>
		<td><a href="<%=request.getContextPath() %>/ViewDirectoryController?flag=protected">Protected Directory
		</a></td>
	</tr>
	<tr>
		<td><a href="<%=request.getContextPath() %>/ViewDirectoryController?flag=default">Default Directory
		</a></td>
	</tr>
	</table>

	</div>
</body>
</html>