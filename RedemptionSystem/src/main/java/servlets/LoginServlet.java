package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AccountDBAO;
import utility.VerifyRecaptcha;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	PrintWriter out = response.getWriter();
		String gRecaptchaResponse = request
				.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
	
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean result = false ;
		
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.authenticate(id, password);
		}
		catch (Exception e)
		{
		 e.printStackTrace();
			
		}	
		//to ensure is legitimate access
		if (!(verify)){
			response.sendRedirect("login_captcha_fail.jsp");
			return;		
		}
		//to ensure cred is correct and legitimate access
		if (result && verify){
			System.out.print("Credential Correct!");
			out.println("<br> <b>Credential Correct</b>");
			return;		
		}
		else { 		
			response.sendRedirect("login.jsp");
			return;
		}*/
		
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();
		String gRecaptchaResponse = request
				.getParameter("g-recaptcha-response");
		//System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		boolean IDcheck = true;
	
		String id = request.getParameter("email");
		String password = request.getParameter("password");
		boolean result = false ;
		
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.authenticate(id, password);
			IDcheck = account.CheckUniqueID (id);
		}
		catch (Exception e)
		{
		 e.printStackTrace();
			
		}	
		//to ensure recaptcha is done. 
		if (!(verify)){
			response.sendRedirect("login_captcha_fail.jsp");
			return;		
		}
		//if no such member, need to flag out. 
		if ((IDcheck)){
			//System.out.print("ID do not exist. Please verify.");
			out.println("<br> <b>ID do not exist. Please verify.</b>");
			out.println("<br></br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click Here to return to Login Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/Register.jsp\">Click Here to return to Registration Page.</a>");
			//out.println(IDcheck);//to troubleshoot
			return;
		}
		//to ensure cred is correct and legitimate access
		if (result && verify){
			//System.out.print("Credential Correct!");
			out.println("<br> <b>Credential Correct</b>");
			return;		
		}
		else {
			//System.out.print("ID or Password is incorrect. Please verify.");
			out.println("<br> <b>ID or Password is incorrect. Please verify.</b>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click Here to return to Login Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/Register.jsp\">Click Here to return to Registration Page.</a>");
			return;
		}
	}

}



