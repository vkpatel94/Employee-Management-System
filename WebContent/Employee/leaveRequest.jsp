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

<body>
<div align = "left">
	<a href="employeeDash.jsp">Home</a>
</div>
<form action="<%=request.getContextPath()%>/LeaveController" method="post"> 


            <center>
            <table border="5" width="25%" cellpadding="8">
                <thead>
                    <tr>
                        <th colspan="6">Enter Information Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Leave Date</td>
                        <td><input type="date" name="leave_date" value="" /></td>
                    </tr>
                    <tr>
                        <td>Leave Reason</td>
                        <td><input type="text" name="leave_reason" value="" /></td>
                    </tr>
                    <tr>
                        <td>Numbers of Days</td>
                        <td><input type="text" name="days" value="" /></td>
                    </tr>
                    
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    
                </tbody>
            </table>
            </center>
        </form>
</body>
</html>