
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
<form method="post" action = "<%=request.getContextPath() %>/FileController" enctype="multipart/form-data">
	 
	
	<br><br><br><br><br>
	<center>
	<table border="1" width="15%" cellpadding="3">
	<tr>
	<td><input type="file" name = "photo"  /></td>
	</tr>
	<tr>
	<td>
	<label>Description</label>
          <textarea name="description"></textarea>
	</td>
	</tr>
	
	<tr>
	<td><input type="submit" name = "Save" value="Save" /></td>
	</tr>
	
	</table>
	</center>
</form>
 <table border="1px">
 	
 <tr>
<th>File Name</th>
<th>Description</th>
<th>Directory name</th>
</tr>
<c:forEach items="${sessionScope.fileList}" var="a">
<tr>
<td><a href="downloadFile.jsp?file_id=${a.file_id}">${a.filename}</a></td>
<td>${a.description}</td>
<td>${a.dict_name }</td>
</tr>
</c:forEach>
</table>      

</body>
</html>