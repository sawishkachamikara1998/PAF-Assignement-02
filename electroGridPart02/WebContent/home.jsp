<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HomePage</title>

	<!-- font awasome icon -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--font awesom CDN-->
    <script src="https://kit.fontawesome.com/2a0f6dfc30.js" crossorigin="anonymous"></script>
    
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>




<body>
<%@include file="/header.jsp" %>

<br>

<div style="background-color:#B0C4DE;text-align: center;">

<a href="login.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">Login</button></a><br><br>
<a href="register.jsp"><button type="button" class="btn btn-secondary btn-lg btn-block">Register</button></a><br><br>
<a href="newPassword.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">Update Password</button></a><br><br>
<a href="viewUser.jsp"><button type="button" class="btn btn-secondary btn-lg btn-block">View registerd User</button></a>


</div>

<br>




 <%@include file="/footer.jsp" %>

</body>
</html>