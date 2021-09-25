<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="SMTP.Main"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>	


 <%@ include file="Master/preloginheader.jsp" %>  
	<!-- Main -->
	  
				<h1 style="text-align: center">Login to Voucher Redemption System</h1>
    			<p style="text-align: center">Please fill in your credentials to log in.</p>
				<div class="wrapper">
				
				
					<div class="container" id="main">
					<div class="row"><section class="col-6 col-12-narrower">
								<form action="LoginServlet" method="POST">
									<div class="row gtr-50">
										<div class="col-12 col-12-mobile">
												<label for="email"><b>Email</b></label>
											<input type="text" name="email" id="email" size="20" placeholder="Enter Email" required>
										</div>
										
										<div class="col-12">
										<label for="password"><b>Password</b></label>
											<input type="password" name="password" id="password" placeholder="Enter Password" size="20" required> 									</div>
										<div class="col-12">
											<ul class="actions">
												<li><input type="submit" value="Login" /></li>
												<li><input type="reset" value="Clear form" /></li>
											</ul>
										</div>
										<div class="col-12">
												<div class="g-recaptcha"	data-sitekey="6Ld7ZFEcAAAAAOXqT8KXn4OoyePkoikXWmpZmERC"></div>
												<p>Do not have an account? <a href="Register.jsp">Create Account</a>.</p>
											<p>Changing your password? <a href="ChangePassword.jsp">Click here</a>.</p>
											<p>Forgot your password? <a href="ResetPassword.jsp">Reset Here</a>.</p>
										</div>
									</div>
								</form>
							</section>
					</div>
						


					</div>
				</div>
				<jsp:include page="/Master/footer.jsp"/>
  
    <hr>

	
	
	
</body>
</html>
<!--127.0.0.1...cannot use localhost.http://127.0.0.1:8080/miniapp/index.jsp  -->
