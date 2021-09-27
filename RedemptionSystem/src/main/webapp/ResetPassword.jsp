<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Reset Page</title>
</head>
<body>
<form action="PWResetServlet"  method="POST">
  <div class="container">
    <h1>Password Reset for Voucher Redemption System</h1>
    Please fill in the form below to change your password. <br></br>
    A new password will be sent to your email. Please change it immediately for security reason.
    <br></br>
    Note: Your <b>email</b> will be used to access your account in the future.
    <hr>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" required pattern="[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" oninput="this.reportValidity()">
    <!-- https://stackoverflow.com/questions/2049502/what-characters-are-allowed-in-an-email-address -->
	<br>
	<br>
	For security reason, please key in the last name and mobile number you used in the registration.<br>
	<br>
    <label for="lastname"><b>Last Name</b></label>
    <input type="text" placeholder="Enter Last Name" name="lastname" id="lastname" required> 
    <br>
    <label for="mobile"><b>Mobile</b></label>
    <input type="number" placeholder="Enter Number" name="mobile" id="mobile" required>
    <br>
    <br>
    <button type="submit" class="registerbtn">Confirm Password Reset</button>
    <hr>
    <p>If you face any issue, you can reach us <a href="http://localhost:8080/miniapp/ContactUs.jsp">here</a>.</p>
  </div>
 
  <div class="container signin">
    <p>To return back to login page, click <a href="http://127.0.0.1:8080/miniapp/login.jsp">here</a>.</p>
  </div>
</form>
</body>
</html>