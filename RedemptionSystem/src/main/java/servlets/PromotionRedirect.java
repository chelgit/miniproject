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

import database.PromotionPage;

/**
 * Servlet implementation class PromotionRedirect
 */
@WebServlet("/PromotionRedirect")
public class PromotionRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromotionRedirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("id"); //from loginservlet	
		int pointbalance = -999;
		List<Map> list = new ArrayList<Map>();
		
	
		//to ensure cred is correct and legitimate access
		if (true){
			//System.out.print("Credential Correct!");
			//out.println("<br> <b>Credential Correct</b>");
			try {
				PromotionPage account = new PromotionPage();
				pointbalance = account.GetPointBalance(id);			}
			catch (Exception e)
			{
			 e.printStackTrace();	
			}	
			
			try {
				PromotionPage items = new PromotionPage();
				list = items.GetPromotionItems();			}
			catch (Exception e)
			{
			 e.printStackTrace();	
			}					
			
			session.setAttribute("key_list",list);
			session.setAttribute("id", id);//for redemptionservlet
			request.setAttribute("id", id);//for promotionpage.jsp
			//request.setAttribute("id", id);
			session.setAttribute("pointbalance", pointbalance);
			//session.setAttribute("password", password);//testing to redirect from redemptionservlet
			request.setAttribute("pointbalance", pointbalance);
			request.getRequestDispatcher("PromotionPage.jsp").forward(request, response);
			response.sendRedirect("PromotionPage.jsp");
			return;		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//recalculate promotionpage table
				/*doGet(request, response);
				PrintWriter out = response.getWriter();
				HttpSession session = request.getSession(true);
				String id = (String) session.getAttribute("id"); //from loginservlet	
				int pointbalance = -999;
				List<Map> list = new ArrayList<Map>();
				
			
				//to ensure cred is correct and legitimate access
				if (true){
					//System.out.print("Credential Correct!");
					//out.println("<br> <b>Credential Correct</b>");
					try {
						PromotionPage account = new PromotionPage();
						pointbalance = account.GetPointBalance(id);			}
					catch (Exception e)
					{
					 e.printStackTrace();	
					}	
					
					try {
						PromotionPage items = new PromotionPage();
						list = items.GetPromotionItems();			}
					catch (Exception e)
					{
					 e.printStackTrace();	
					}					
					
					session.setAttribute("key_list",list);
					session.setAttribute("id", id);//for redemptionservlet
					request.setAttribute("id", id);//for promotionpage.jsp
					//request.setAttribute("id", id);
					session.setAttribute("pointbalance", pointbalance);
					//session.setAttribute("password", password);//testing to redirect from redemptionservlet
					request.setAttribute("pointbalance", pointbalance);
					request.getRequestDispatcher("PromotionPage.jsp").forward(request, response);
					response.sendRedirect("PromotionPage.jsp");
					return;		
				}*/

			}

		}

