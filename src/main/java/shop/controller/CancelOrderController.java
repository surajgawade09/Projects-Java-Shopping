package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ShoppingDao;
import shop.dao.ShoppingDaoImpl;
import shop.model.Customer;

/**
 * Servlet implementation class CancelOrderController
 */
@WebServlet("/CancelOrderController")
public class CancelOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String p_id=request.getParameter("p_id");
		long u_id=Long.parseLong(request.getParameter("u_id"));
		Customer auth=(Customer)request.getSession().getAttribute("auth");
		if(p_id!=null && auth!=null)
		{
			ShoppingDao dao=new ShoppingDaoImpl();
			boolean stat=dao.CancelOrder(Integer.parseInt(p_id),u_id);
			if (stat) {
				response.sendRedirect("OrderView.jsp");
			}
			else {
				pw.print("order cancel failed");
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
