<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
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
  var pw = document.getElementById("psw").value;
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
function validate(evt) {
	  var theEvent = evt || window.event;

	  // Handle paste
	  if (theEvent.type === 'paste') {
	      key = event.clipboardData.getData('text/plain');
	  } else {
	  // Handle key press
	      var key = theEvent.keyCode || theEvent.which;
	      key = String.fromCharCode(key);
	  }
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
	}
</script>
<body>
 <%@ include file="Master/preloginheader.jsp" %>  
<form onsubmit ="return InputVerification()" action="RegisterServlet"  method="POST">
  <div class="container">
    <h1>Registration for Voucher Redemption System</h1>
    Please fill in this form to create an account.
    <br></br>
    Note: Your <b>email</b> will be used to access your account in the future.
    <hr>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" required pattern="[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" oninvalid="setCustomValidity('Please enter a valid email address.')">
    <!-- https://stackoverflow.com/questions/2049502/what-characters-are-allowed-in-an-email-address -->
    <br>
    <label for="mobile"><b>Mobile</b></label>
    <input type="text" placeholder="Enter Number" name="mobile" id="mobile" onkeypress='validate(event)' maxlength="8" required>
            
    <br>
    <label for="firstname"><b>First Name</b></label>
    <input type="text" placeholder="Enter First Name" name="firstname" id="firstname" required>
    
    <br>
    <label for="lastname"><b>Last Name</b></label>
    <input type="text" placeholder="Enter Last Name" name="lastname" id="lastname" required>  
      
	<br>
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
    <span id = "message" style="color:red"> </span> 
    
	<br>
    <label for="psw-confirm"><b>Confirm Password</b></label>
    <input type="password" placeholder="Confirm Password" name="psw-confirm" id="psw-confirm" required>
    <span id = "message2" style="color:red"> </span> 
    
    <br></br>
    <button type="submit" class="registerbtn">Register</button>
    <hr>
    <p>If you face any issue, you can reach us <a href="ContactUs.jsp">here</a>.</p>
  </div>
 
  <div class="container signin">
    <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
  </div>
</form>
	<jsp:include page="/Master/footer.jsp"/>
</body>
</html>