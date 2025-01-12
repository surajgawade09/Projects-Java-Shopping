package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.Cart;

/**
 * Servlet implementation class QuantityIncDecController
 */
@WebServlet("/QuantityIncDecController")
public class QuantityIncDecController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuantityIncDecController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		
		String action=request.getParameter("action");
		int id=Integer.parseInt(request.getParameter("id"));
		
		ArrayList<Cart> cartlist=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
		
		if(action!=null && id>=1)
		{
			if(action.equals("inc"))
			{
				for(Cart c:cartlist)
				{
					if(c.getProdid()==id)
					{
						int quantity=c.getQuantity();
						quantity++;
						c.setQuantity(quantity);
						response.sendRedirect("CartView.jsp");
					}
				}
			}
			if(action.equals("dec"))
			{
				for(Cart c:cartlist)
				{
					if(c.getProdid()==id && c.getQuantity()>1)
					{
						int quantity=c.getQuantity();
						quantity--;
						c.setQuantity(quantity);
						break;
					}
				}
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
