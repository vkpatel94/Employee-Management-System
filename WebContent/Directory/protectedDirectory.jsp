<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
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
<body>
		<center>
            <table border="5" width="25%" cellpadding="8">
                <thead>
                    <tr>
                        <th colspan="6">List of Protected Directories</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>Directory Name</th>
               	 		<th>Created By</th>
                		<th>Access Type</th>
                		
            		</tr>
            	<c:forEach var="i" items="${sessionScope.protectedDirectory}">
      			<input type="hidden" name="dict_id" value="${i.dict_id }"/>
                    <tr>
                    		
                    		<td><a href="<%=request.getContextPath() %>/ViewDirectoryController?flag=upload&dict_name=${i.dict_name}&dict_id=${i.dict_id}&created_by=${i.created_by}"><c:out value="${i.dict_name}" /></a></td>
                       		<td><c:out value="${i.created_by}" /></td>
                        	<td><c:out value="${i.access_type}" /></td>
                        	
                   	</tr>
                   	</c:forEach>
                   	<c:forEach var="i" items="${sessionScope.l2}">
      			<input type="hidden" name="dict_id" value="${i.dict_id }"/>
                    <tr>
                    		
                    		<td><a href="<%=request.getContextPath() %>/ViewDirectoryController?flag=upload&dict_name=${i.dict_name}&dict_id=${i.dict_id}&created_by=${i.created_by}"><c:out value="${i.dict_name}" /></a></td>
                       		<td><c:out value="${i.created_by}" /></td>
                        	<td><c:out value="${i.access_type}" /></td>
                        	
                   	</tr>
                   	</c:forEach>
                    </tbody>
            </table>
		</center>
	

</body>
</html>