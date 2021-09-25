package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SMTP.EmailFunc_Reset;
import database.AccountDBAO;

/**
 * Servlet implementation class PWResetServlet
 */
@WebServlet("/PWResetServlet")
public class PWResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PWResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		PrintWriter out = response.getWriter();
		//System.out.println(gRecaptchaResponse);
		boolean IDcheck = true;
		boolean authentication=true;
	
		String id = request.getParameter("email");
		String lastname = request.getParameter("lastname");
		String mobile = request.getParameter("mobile");
		String resettedpassword;
		boolean pwreset_result = false ;
		
		
		try {
			AccountDBAO account = new AccountDBAO();
			IDcheck = account.CheckUniqueID (id);
			authentication=account.PWresetauthenticate(id,lastname,mobile);
		}
		catch (Exception e)
		{
		 e.printStackTrace();
			
		}	

		//if no such member, need to flag out. True=ID don't exists.
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
		//to ensure cred is correct and then change old password to new password
		if (!(IDcheck)&authentication){
			try {
				AccountDBAO account = new AccountDBAO();
				System.out.println("ID Authenicated and password reset going to take place.");
				resettedpassword=AccountDBAO.getSalt();				
				pwreset_result = account.resetpassword(id, resettedpassword);
				EmailFunc_Reset.main(id,resettedpassword);//send out notification email to user that password change has been triggered (for security)
			}
			catch (Exception e)
			{
			 e.printStackTrace();			
			}		
		}
		if (pwreset_result){
			//System.out.print("ID do not exist. Please verify.");
			out.println("<br> <b>Password has been reset and you will receive a mail shortly. Please verify by logging in.</b>");
			out.println("<br></br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click Here to return to Login Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/Register.jsp\">Click Here to return to Registration Page.</a>");
			//out.println(IDcheck);//to troubleshoot
			return;
		}
		else {
			//System.out.print("ID or Password is incorrect. Please verify.");
			out.println("<br> <b>Details provided are incorrect. Please verify.</b>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click Here to return to Login Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/Register.jsp\">Click Here to return to Registration Page.</a>");
			return;
		}
		
	}

}

