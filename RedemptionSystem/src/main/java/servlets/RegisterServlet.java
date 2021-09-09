package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AccountDBAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String id = request.getParameter("email");
		String password = request.getParameter("psw");
		boolean result = false ;
		boolean IDcheck = true; 
		//to check whether id is unique.		
		try {
			AccountDBAO account = new AccountDBAO();
			IDcheck = account.CheckUniqueID (id, password);
		}
		catch (Exception e)
		{
		 e.printStackTrace();		
		}
		if (!(IDcheck)){
			System.out.print("ID already exists in the system. Please contact Admin for verification.");
			out.println("<br> <b>ID already exists in the system. Please contact Admin for verification.</b>");
			out.println("<br></br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click Here to return to Login Page.</a>");
			//out.println(IDcheck);//to troubleshoot
			return;
		}
		//If unique, proceed to create account
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.create(id, password);
			
		}
		catch (Exception e)
		{
		 e.printStackTrace();		
		}
		if (result){
			System.out.print("Registration is scccessful");
			out.println("<br> <b>Registration is scccessful</b>");
			//request.getRequestDispatcher("/bookstore").forward(request,response);
			return;
		}
		else { 		
			response.sendRedirect("Register.jsp");
			return;
		}*/
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String id = request.getParameter("email");
		String password = request.getParameter("psw");
		boolean result = false ;
		boolean IDcheck = true; 
		//to check whether id is unique.		
		try {
			AccountDBAO account = new AccountDBAO();
			IDcheck = account.CheckUniqueID (id, password);
		}
		catch (Exception e)
		{
		 e.printStackTrace();		
		}
		if (!(IDcheck)){
			System.out.print("ID already exists in the system. Please contact Admin for verification.");
			out.println("<br> <b>ID already exists in the system. Please contact Admin for verification.</b>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click Here to return to Login Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/Register.jsp\">Click Here to return to Registration Page.</a>");
			//out.println(IDcheck);//to troubleshoot
			return;
		}
		//If unique, proceed to create account
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.create(id, password);
			
		}
		catch (Exception e)
		{
		 e.printStackTrace();		
		}
		if (result){
			System.out.print("Registration is succcessful");
			out.println("<br> <b>Registration is succcessful</b>");
			//request.getRequestDispatcher("/bookstore").forward(request,response);
			return;
		}
		else { 		
			response.sendRedirect("Register.jsp");
			return;
		}
	}

}
