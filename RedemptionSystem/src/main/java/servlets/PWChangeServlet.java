package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AccountDBAO;
import SMTP.EmailFunc;

/**
 * Servlet implementation class PWChangeServlet
 */
@WebServlet("/PWChangeServlet")
public class PWChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PWChangeServlet() {
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
	
		String id = request.getParameter("email");
		String password = request.getParameter("psw");
		String newpassword = request.getParameter("pswn");
		boolean result = false ;
		boolean pwchange_result = false ;
		EmailFunc.main(id);//send out notification email to user that password change has been triggered (for security)
		
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.authenticate(id, password);
			IDcheck = account.CheckUniqueID (id);
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
			out.println("<a href=\"http://127.0.0.1:8080/miniapp5/login.jsp\">Click Here to return to Login Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp5/Register.jsp\">Click Here to return to Registration Page.</a>");
			//out.println(IDcheck);//to troubleshoot
			return;
		}
		//to ensure cred is correct and then change old password to new password
		if (result){
			try {
				AccountDBAO account = new AccountDBAO();
				pwchange_result = account.changepassword(id, newpassword);
			}
			catch (Exception e)
			{
			 e.printStackTrace();			
			}		
		}
		if ((pwchange_result)){
			//System.out.print("ID do not exist. Please verify.");
			out.println("<br> <b>Password has been changed. Please verify by logging in.</b>");
			out.println("<br></br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click Here to return to Login Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/Register.jsp\">Click Here to return to Registration Page.</a>");
			//out.println(IDcheck);//to troubleshoot
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



