package shop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ShoppingDao;
import shop.dao.ShoppingDaoImpl;
import shop.model.Cart;
import shop.model.Customer;
import shop.model.Order;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		
		Date date=new Date();
		//user authentication
		Customer auth=(Customer)request.getSession().getAttribute("auth");
		//retrive all carts products
		ArrayList<Cart> cartlist=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
		
		//check auth & cart_list
		
		if(cartlist!=null && auth!=null)
		{
			for(Cart c:cartlist)
			{
			Order ord=new Order();
			ord.setProdid(c.getProdid());
			ord.setUid(auth.getPhonenumber());
			ord.setQuantity(c.getQuantity());
			ord.setDate(formatter.format(date));
			
			ShoppingDao dao=new ShoppingDaoImpl();
			boolean res=dao.InsertOrder(ord);
			if(!res)
			{
				break;
			}
			}
			cartlist.clear();
			response.sendRedirect("OrderView.jsp");
		}
		else {
			if(auth==null)
			{
				response.sendRedirect("LoginSignUp.jsp");
			}
			else {
				response.sendRedirect("CartView.jsp");
			}
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
