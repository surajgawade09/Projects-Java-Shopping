package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ShoppingDao;
import shop.dao.ShoppingDaoImpl;
import shop.model.Customer;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("Lemail");
		String password=request.getParameter("Lpassword");
		ShoppingDao dao=new ShoppingDaoImpl();
		Customer cust=dao.validateUser(email, password);
		String str="Invalid Details";
		if(cust!=null)
		{
			request.getSession().setAttribute("auth",cust);
			response.sendRedirect("Home.jsp");
			
		}
		else {
			request.getSession().setAttribute("loginerror", str);
			response.sendRedirect("LoginSignUp.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
