<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<script src="https://www.google.com/recaptcha/api.js">
</script>
</head>
<body>	
    <h1>Login to Voucher Redemption System</h1>
    <p>Please fill in your credentials to log in.</p>
    <hr>
	<form action="LoginServlet" method="POST">
	
	<label for="id"><b>Email</b></label>
	<input type="text" name="id" id="id" size="20" placeholder="Enter Email" required pattern="[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" oninput="this.reportValidity()"><br> 

	<label for="password"><b>Password</b></label>
	<input type="password" name="password" id="password" placeholder="Enter Password" size="20" required> 
	
	<br></br>
	<input type="submit" value="Submit" ><br> 
	<hr>
	<div class="g-recaptcha"
	data-sitekey="6Ld7ZFEcAAAAAOXqT8KXn4OoyePkoikXWmpZmERC"></div>
	</form>
	<p>Do not have an account? <a href="http://localhost:8080/miniapp/Register.jsp">Create Account</a>.</p>
	<script>
	alert("Please complete your reCAPTCHA!");
	window.location='http://127.0.0.1:8080/miniapp/login.jsp'; <!--after alert, redirect straight to login.jsp. So refresh is ok-->
	</script>	
	<!-- <body onLoad="javascript:ErrorMsg()" >-->
	

	
</body>
</html>
<!--127.0.0.1...cannot use localhost.http://127.0.0.1:8080/miniapp/index.jsp  -->
