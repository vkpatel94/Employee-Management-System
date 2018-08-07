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


<body>
<c:forEach var="i" items="${sessionScope.selectUser}">
<form action="<%=request.getContextPath()%>/DirectoryController" method="post">
<center>
            <table border="5" width="25%" cellpadding="8">
                <thead>
                    <tr>
                        <th colspan="6">Enter Information of Directory</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Directory Name</td>
                        <td><input type="text" name="dict_name" value="" /></td>
                    </tr>
                    <tr>
                        <td>Created By</td>
                        <td><input type="text" name="created_by" value="${i.username }" readonly="readonly"/>${i.username }</td>
                    </tr>
                    <tr>
                    	<td>Permission Type</td>
                    	<td><select id ="access_type" name="access_type">
                    		<option>Default</option>
                    		<option>Public</option>
                    		<option>Private</option>
                    		<option>Protected</option>
                    	</select></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    
                </tbody>
            </table>
            </center>
        </form>
       </c:forEach>
</body>
</html>