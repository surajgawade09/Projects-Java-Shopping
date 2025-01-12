package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.apache.catalina.User;
import org.apache.jasper.tagplugins.jstl.core.Out;

import shop.dao.ShoppingDao;
import shop.dao.ShoppingDaoImpl;
import shop.model.Cart;
import shop.model.Customer;
import shop.model.Order;

/**
 * Servlet implementation class BuyNowController
 */
@WebServlet("/BuyNowController")
public class BuyNowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyNowController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw=response.getWriter();
		Customer auth=(Customer)request.getSession().getAttribute("auth");
		
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		
		Date date=new Date();
		
		if(auth!=null) {
			
			int id=Integer.parseInt(request.getParameter("id"));
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			if(quantity<=0)
			{
				quantity=1;
			}
			
			Order ordermodel=new Order();
			ordermodel.setProdid(id);
			ordermodel.setUid(auth.getPhonenumber());
			ordermodel.setQuantity(quantity);
			ordermodel.setDate(formatter.format(date));
			
			ShoppingDao dao=new ShoppingDaoImpl();
			
			boolean res=dao.InsertOrder(ordermodel);
			if(res)
			{
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
				}
				response.sendRedirect("OrderView.jsp");
			}
			else
			{
				pw.print("order failed");
			}
		}
		else {
			response.sendRedirect("LoginSignUp.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
