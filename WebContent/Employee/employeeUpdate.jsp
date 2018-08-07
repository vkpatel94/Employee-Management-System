<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Admin") )
{response.sendRedirect("../Admin/adminDash.jsp");}

else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Manager") )
{response.sendRedirect("../Manager/managerDash.jsp");}
%>
<%
	if(session.getAttribute("username")== null)
	{
		response.sendRedirect("../index.jsp");
	}
%>
</head>

<body>
<div align = "left">
	<a href="employeeDash.jsp">Home</a>
</div>
	<c:forEach var="i" items="${sessionScope.updateProfile}">
	<form action="<%=request.getContextPath()%>/UpdateEmployee" method="post">
	<input type="hidden" name="flag" value="emp">	
		<center>
            <table border="5" width="25%" cellpadding="8">
                <thead>
                    <tr>
                        <th colspan="6">Update Information Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="first_name" value="${i.first_name}" /></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="last_name" value="${i.last_name}" /></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="address" value="${i.address}" /></td>
                    </tr>
                    <tr>
                        <td>Phone Number</td>
                        <td><input type="text" name="phone_number" value="${i.phone_number}" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="${i.email}" /></td>
                    </tr>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="username" value="${i.username}" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="${i.password}" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Update" /></td>
                       
                    </tr>
                    
                </tbody>
            </table>
		</center>
	
	</form>
</c:forEach>	
</body>
</html>