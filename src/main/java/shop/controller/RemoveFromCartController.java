package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import shop.model.Cart;

/**
 * Servlet implementation class RemoveFromCartController
 */
@WebServlet("/RemoveFromCartController")
public class RemoveFromCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		ArrayList<Cart> cartlist=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
		

		if(cartlist!=null)
		{
			for(Cart c:cartlist)
			{
				if(c.getProdid()==id)
				{
					cartlist.remove(cartlist.indexOf(c));
					break;
				}
				
			}
			
			response.sendRedirect("CartView.jsp");
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
