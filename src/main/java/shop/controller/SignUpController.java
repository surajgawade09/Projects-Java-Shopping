package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import shop.dao.ShoppingDao;
import shop.dao.ShoppingDaoImpl;
import shop.model.Customer;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long phonenum=Long.parseLong(request.getParameter("Rtel"));
		String custname=request.getParameter("Rcustname");
		String username=request.getParameter("Remail");
		String password=request.getParameter("Rpassword");
		Customer obj=new Customer(custname, phonenum, username, password);
		ShoppingDao dao=new ShoppingDaoImpl();
		String str="Phone Number already exists! retry";
		int i=dao.RegisterUser(obj);
		if(i>0)
		{
			response.sendRedirect("LoginSignUp.jsp");
		}
		else {
			request.getSession().setAttribute("signuperror", str);
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
