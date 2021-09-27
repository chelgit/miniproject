<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Change Page</title>
</head>

<script>
function ValidateEmail() {
	var email = document.getElementById("email").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(!(email.value.match(mailformat))){
		document.getElementById("message3").innerHTML = "**WrongEmailFormat**";
		return false;
	}
}


function InputVerification() {
  var pw = document.getElementById("pswn").value;
  var pwConfirmation = document.getElementById("psw-confirm").value;

 //check empty password field
 //minimum password length validation
  if(pw.length < 10) {
     document.getElementById("message").innerHTML = "**Password length must be at least 10 characters**";
     return false;
  }
  if(pw != pwConfirmation) {
	     document.getElementById("message2").innerHTML = "**Please make sure your passwords match.**";
	     return false;
	  }
}
</script>
<body>
<form onsubmit ="return InputVerification()" action="PWChangeServlet"  method="POST">
  <div class="container">
    <h1>Password Change for Voucher Redemption System</h1>
    Please fill in the form below to change your password.
    <br></br>
    Note: Your <b>email</b> will be used to access your account in the future.
    <hr>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" required pattern="[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" oninput="this.reportValidity()">
    <!-- https://stackoverflow.com/questions/2049502/what-characters-are-allowed-in-an-email-address -->
	<br>
    <label for="psw"><b>Old Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
    <br>
    <label for="pswn"><b>New Password</b></label>
    <input type="password" placeholder="Enter Password" name="pswn" id="pswn" required>
    <span id = "message" style="color:red"> </span> 
    
	<br>
    <label for="psw-confirm"><b>Confirm Password</b></label>
    <input type="password" placeholder="Confirm Password" name="psw-confirm" id="psw-confirm" required>
    <span id = "message2" style="color:red"> </span> 
    
    <br></br>
    <button type="submit" class="registerbtn">Confirm Password Change</button>
    <hr>
    <p>If you face any issue, you can reach us <a href="http://localhost:8080/miniapp/ContactUs.jsp">here</a>.</p>
  </div>
 
  <div class="container signin">
    <p>To return back to login page, click <a href="http://127.0.0.1:8080/miniapp/login.jsp">here</a>.</p>
  </div>
</form>
</body>
</html>