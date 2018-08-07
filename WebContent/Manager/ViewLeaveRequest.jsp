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
<%if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Admin") )
{response.sendRedirect("../Admin/adminDash.jsp");}

else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("Employee") )
{response.sendRedirect("../Employee/employeeDash.jsp");}
%>
</head>
</head>
<body>

	 <center>
            <table border="5" width="25%" cellpadding="8">
                <thead>
                    <tr>
                        <th colspan="6">List of Pending Requests</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>Leave Date</th>
               	 		<th>Leave Reason</th>
                		<th>Numbers of Days</th>
                		<th>Employee's Name</th>
                		<th>Status</th>
                		<th>Approve</th>
                		<th>Disapprove</th>
            		</tr>
            		<c:forEach var="i" items="${sessionScope.pendingLeave}">
            		<input type="hidden" value="${i.leave_id }"/>
                    <tr>
                    	
                        	<td><c:out value="${i.leave_date}" /></td>
                       		<td><c:out value="${i.leave_reason}" /></td>
                        	<td><c:out value="${i.days}" /></td>
                        	<td><c:out value="${i.employee}" /></td>
                        	<td><c:out value="${i.status}" /></td>
                        	<td><a href="<%=request.getContextPath()%>/AdminController?flag=approve&leave_id=${i.leave_id}">Approve</a></td>
                        <td><a href="<%=request.getContextPath()%>/AdminController?flag=disapprove&leave_id=${i.leave_id}">Disapprove</a></td>
                        	<td></td>
                        	
                        
                   	</tr>
                   	</c:forEach>
                 
                   </tbody>
            </table>
		</center>
	</form>
</body>
</html>