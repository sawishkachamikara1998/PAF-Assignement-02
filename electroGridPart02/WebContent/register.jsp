<%@page import="com.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/register.js"></script>

<link rel="stylesheet" href="Views/style.css" type="text/css"/>
<link rel="stylesheet" href="Views/split.css" type="text/css"/>

</head>
<body>



<div class="split left">
  <div class="centered">
  
  	<div class="container">
		<h1 class="label">Register</h1>
		
		
		<form class="login_form" id="formRegister" name="formRegister">
		
		<div class="font">E-mail</div>
			<input id="register_email" name="register_email" type="email" class="form-control form-control-sm">
		
		    <div class="font">Name</div>
			<input id="register_name" name="register_name" type="text" class="form-control form-control-sm">
			
			
			
			<div class="font">Password</div>
			<input id="register_password" name="register_password" type="text" class="form-control form-control-sm">
			
			<div class="font font2">Mobile</div>
			<input id="register_mobile" name="register_mobile" type="text" class="form-control form-control-sm">
			
			<br><br>
			<input id="btnSave" name="btnSave" type="button" value="Register" class="btn btn-primary"> 
			<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
			
			<br>
			<a href="home.jsp"><input type="button" value="Home page" class="btn btn-primary"></a>
			<br>
			
		</form>
		
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		
	


	</div>	



  </div>
</div>








<div class="split right">
  <div class="centered">

<div class="edit">



<h1 class="label">User Details</h1>

  
	<div id="divItemsGrid">
	
	<%
	UserDao userDao = new UserDao();
	out.print(userDao.readCard());
	%>
	        

	</div>


</div>






  </div>
</div>



















</body>
</html>