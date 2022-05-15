<%@page import="com.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/register.js"></script>
<link rel="stylesheet" href="Views/style.css" type="text/css"/>

</head>
<body>

			
		<div class="container">
		<h1 class="label">System Users</h1>
		
		
		<div style="position:relative;right:20px;" id="divItemsGrid">
					<%
					
					UserDao userDao = new UserDao();
					out.print(userDao.readUsers());
					%>
				</div>
				
			<br>
			<a href="home.jsp"><input type="button" value="Home page" class="btn btn-primary"></a>
			<br>
		
		

	</div>	
			
			
			
			
			
			
			
			
			
			
			
			
			
				

</body>
</html>