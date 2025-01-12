package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import shop.dao.ShoppingDao;
import shop.dao.ShoppingDaoImpl;
import shop.model.Cart;
import shop.model.Customer;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Cart> cartlist=new ArrayList<Cart>();
		PrintWriter pw=response.getWriter();
		
		int p_id=Integer.parseInt(request.getParameter("id"));
		Cart ct=new Cart();
		ct.setProdid(p_id);
		ct.setQuantity(1);
		String flag=null;
		Customer auth=(Customer)request.getSession().getAttribute("auth");
		HttpSession session=request.getSession();
		if(auth!=null) {
		ShoppingDao dao=new ShoppingDaoImpl();
		ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
		
		if(cart_list==null)
		{
			cartlist.add(ct);
			session.setAttribute("cart-list", cartlist);
			response.sendRedirect("ProductView.jsp");
			flag="added";
	
		}
		else {
			cartlist=cart_list;
			boolean exist=false;
		
		for(Cart c:cartlist)
		{
		if(c.getProdid()==p_id)
		{
			exist=true;
			flag="exists";
			response.sendRedirect("ProductView.jsp");
			
		}
	}
		
		if(!exist)
		{
			cartlist.add(ct);
			flag="added";
			response.sendRedirect("ProductView.jsp");
		}
		}
	}
		else {
			response.sendRedirect("LoginSignUp.jsp");
		}
		request.getSession().setAttribute("flag", flag);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
