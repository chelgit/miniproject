package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SMTP.EmailFunc;
import SMTP.EmailFunc_Reset;
import database.AccountDBAO;
import database.PromotionPage;

/**
 * Servlet implementation class RedemptionServlet
 */
@WebServlet("/RedemptionServlet")
public class RedemptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedemptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		int boolvalue=-5;//dummy value. 1 is true.0 is false
		int stockcount=-999;//dummy value
		boolean DBupdate=false;
		String VoucherCode="";
		HttpSession session = request.getSession(true);
		String promotion = request.getParameter("txn");
		int pointbalance = (int) session.getAttribute("pointbalance");
		String id = (String) session.getAttribute("id"); //from loginservlet
		List<Map> list = new ArrayList<Map>();
		//System.out.println(promotion);
		//System.out.println(pointbalance);
		//System.out.println(id);
		try {
			PromotionPage account = new PromotionPage();
			boolvalue = account.CheckEnoughPoint(promotion,id);			}
		catch (Exception e)
		{
		 e.printStackTrace();	
		}	
		System.out.println("Enough Point?:"+boolvalue);//return 1 or 0
		//
		try {
			PromotionPage account = new PromotionPage();
			stockcount = account.CheckEnoughStock(promotion);			}
		catch (Exception e)
		{
		 e.printStackTrace();	
		}	
		System.out.println("Stock Count:"+stockcount);//return stock count
		
		if (stockcount>0 && boolvalue>0){
			//System.out.print("Credential Correct!");
			//out.println("<br> <b>Credential Correct</b>");
			try {
				PromotionPage account = new PromotionPage();
				DBupdate = account.UpdateStock_TxnTable_PointBalance(promotion,id);			}
			catch (Exception e)
			{
			 e.printStackTrace();	
			}	
		}
		
		try {
			PromotionPage items = new PromotionPage();
			list = items.GetHistoricalTxn(id);			}
		catch (Exception e)
		{
		 e.printStackTrace();	
		}						
		
		session.setAttribute("historical_list",list);

		if (DBupdate){
			//out.println(confirmation);
		/*	out.println("<br> <b>Your transaction has been processed. You will receive your redemption code shortly in your email.</b>");
			out.println("<br></br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/ContactUs.jsp\">Click here to contact us if you have any issue.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/PromotionRedirect\">Click here to return to Promotion Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/login.jsp\">Click here to return to Login Page.</a>");*/
			//out.println(IDcheck);//to troubleshoot
			request.getRequestDispatcher("TransactionCompleted.jsp").forward(request, response);
			response.sendRedirect("TransactionCompleted.jsp");
			return;
		}
		
		
		
		
		else {
			//System.out.print("ID or Password is incorrect. Please verify.");
			out.println("<b>Not enough points or stock. Please verify.");
			out.println("<br> </br>");
			out.println("<a href=\"http://127.0.0.1:8080/miniapp/PromotionRedirect\">Click here to return to Promotion Page.</a>");
			out.println("<br> </br>");
			out.println("<a href=\"\">To add points, click here to claim from your invoice. [To be added in RedemptionServlet]</a>");
			
			return;
		}
		

	}

}
