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

<body>
<div align = "right">
	<form action="<%=request.getContextPath() %>/Logout">
		<input type="submit" value="Logout" />
	</form>
	</div>
<div align = "left">
	<a href="adminDash.jsp">Home</a>
</div>
<form action="<%=request.getContextPath()%>/AdminController" method="post">

	
		<table>
		<thead>
			<tr>
			<th><h2>Assign Manager</h2></th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<th>Select Employee</th>
			<th>Select Manager</th>
			<th></th>
			</tr>
			<tr>
			<th>
				<select name="employee" id="employee">
    	       	<option>Select</option>
    	       	<c:forEach var="x" items="${sessionScope.emplist}">
                <option value = '<c:out value="${x.username}" />'>${x.first_name},${x.username}</option>
              	</c:forEach> 
                </select>
			</th>
			<th>
				<select name="manager" id="manager">
    	       	<option>Select</option>
    	       	<c:forEach var="j" items="${sessionScope.managerlist}">
                <option value = '<c:out value="${j.username}" />'>${j.first_name},${j.username}</option>
              	</c:forEach> 
                </select>
        
			</th>
			
			<th>
				<input type="submit" value="Submit" />
			</th>
			</tr>
		</tbody>
	

	     	 </table>    
                  
	</form>
</body>
</html>