package shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import shop.connection.ShoppingConnection;
import shop.model.Cart;
import shop.model.Customer;
import shop.model.Order;
import shop.model.Product;

public class ShoppingDaoImpl implements ShoppingDao{
	PreparedStatement pstate;
	ResultSet result=null;
	Statement state;
	Connection con=null;
	int i=0;
	ShoppingConnection conn=new ShoppingConnection();
	List<Product> lstarr=new ArrayList<Product>();
	List<Product> lstlin=new LinkedList<Product>();

	@Override
	public int RegisterUser(Customer obj) {
		con=conn.myconnection();
		try {
			pstate=con.prepareStatement("insert into ShoppingCustomer values(?,?,?,?)");
			pstate.setString(1, obj.getCustomername());
			pstate.setLong(2, obj.getPhonenumber());
			pstate.setString(3, obj.getEmail());
			pstate.setString(4, obj.getPassword());
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Customer validateUser(String email, String password) {
		Customer cust=null;
		try {
			con=conn.myconnection();
			pstate=con.prepareStatement("select * from shoppingcustomer where email=? and password=?");
			pstate.setString(1,email);
			pstate.setString(2,password);
			result=pstate.executeQuery();
			if(result.next())
			{
				cust=new Customer();
				cust.setCustomername(result.getString(1));
				cust.setPhonenumber(result.getLong(2));
				cust.setEmail(result.getString(3));
				cust.setPassword(result.getString(4));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cust;
	}

	@Override
	public List<Product> ReturnProducts(String category) {
		
			 
			try {
				con=conn.myconnection();
				if(category.equalsIgnoreCase("all"))
				{
				
				String str="select * from Shopproduct";
				state=con.createStatement();
				result=state.executeQuery(str);
				
				
				while(result.next())
				{
					lstlin.add(new Product(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getString(5)));
				}
				}
				else if(category.equalsIgnoreCase("clothing")){
					
					String strspec="select * from Shopproduct where category='clothing'";
					state=con.createStatement();
					result=state.executeQuery(strspec);
					while(result.next())
					{
						Product prod=new Product(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getString(5));
						lstlin.add(prod);
					}
					
				}
				else if(category.equalsIgnoreCase("electronics"))
				{
					String strspec="select * from ShopProduct where category='electronics'";
					state=con.createStatement();
					result=state.executeQuery(strspec);
					while(result.next())
					{
						Product prod=new Product(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getString(5));
						lstlin.add(prod);
					}
					
				}
				else if(category.equalsIgnoreCase("accessories"))
				{
					String strspec="select * from ShopProduct where category='accessories'";
					state=con.createStatement();
					result=state.executeQuery(strspec);
					while(result.next())
					{
						Product prod=new Product(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getString(5));
						lstlin.add(prod);
					}
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lstlin;
	}

	@Override
	public List<Cart> GetcartProducts(ArrayList<Cart> cartlist) {
		List<Cart> cartlst=new ArrayList<Cart>();
		
		con=conn.myconnection();
		try {
			if(cartlist.size()>0)
			{
				for(Cart c:cartlist)
				{
					pstate=con.prepareStatement("select * from shopProduct where id=?");
					pstate.setInt(1, c.getProdid());
					result=pstate.executeQuery();
					while(result.next())
					{
						Cart ct=new Cart();
						ct.setProdid(result.getInt(1));
						ct.setProdname(result.getString(2));
						ct.setProdcategory(result.getString(3));
						ct.setPordprice(result.getFloat(4)*c.getQuantity());
						ct.setProdimg(result.getString(5));
						ct.setQuantity(c.getQuantity());
						cartlst.add(ct);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cartlst;
	}

	@Override
	public float GetCartTotal(ArrayList<Cart> cartlist) {
		float sum=0;
		con=conn.myconnection();
		try {
			if(cartlist.size()>0)
			{
				for(Cart c:cartlist)
				{
					pstate=con.prepareStatement("select price from shopproduct where id=?");
					pstate.setInt(1, c.getProdid());
					result=pstate.executeQuery();
					
					while(result.next())
					{
						sum=sum+result.getFloat("price")*c.getQuantity();
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sum;
	}

	@Override
	public boolean InsertOrder(Order ord) {
		boolean res=false;
		try {
			
			con=conn.myconnection();
			pstate=con.prepareStatement("insert into Orders(o_id,p_id,u_id,o_quantity,o_date) values(o_id_inc.nextval,?,?,?,?)");
			pstate.setInt(1, ord.getProdid());
			pstate.setLong(2,ord.getUid());
			pstate.setInt(3,ord.getQuantity());
			pstate.setString(4, ord.getDate());
			i=pstate.executeUpdate();
			if(i>0)
			{
				System.out.println("helloooo");
				res=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Order> RetrieveOrder(long number) {
		List<Order> orders=new ArrayList<Order>();
		con=conn.myconnection();
		
		try {
			pstate=con.prepareStatement("select * from orders where u_id=? order by orders.o_id desc");
			pstate.setLong(1, number);
			result=pstate.executeQuery();
			while(result.next())
			{
				Order ord=new Order();
				ShoppingDao dao=new ShoppingDaoImpl();
				
				int prodid=result.getInt("p_id");
				Product prod=dao.getSingleProduct(prodid);
				
				
				ord.setOrderid(result.getInt("o_id"));
				ord.setProdid(result.getInt("p_id"));
				ord.setProdname(prod.getProdname());
				ord.setProdcategory(prod.getProdcategory());
				ord.setPordprice(prod.getPordprice()*result.getInt("o_quantity"));
				ord.setQuantity(result.getInt("o_quantity"));
				ord.setDate(result.getString("o_date"));
				ord.setProdimg(prod.getProdimg());
				ord.setUid(result.getLong("u_id"));
				orders.add(ord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public Product getSingleProduct(int prodid) {
		Product prod=null;
		try {
			con=conn.myconnection();
			pstate=con.prepareStatement("select * from shopproduct where id=?");
			pstate.setInt(1, prodid);
			result=pstate.executeQuery();
			if(result.next())
			{
			 prod=new Product(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prod;
	}

	@Override
	public Boolean CancelOrder(int p_id, long u_id) {
		boolean cancelstat=false;
		try {
			con=conn.myconnection();
			pstate=con.prepareStatement("delete from orders where p_id=? and u_id=?");
			pstate.setInt(1, p_id);
			pstate.setLong(2, u_id);
			i=pstate.executeUpdate();
			if(i>0)
			{
				System.out.println("hellooo");
				cancelstat=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cancelstat;
		
	}

	@Override
	public void AddToCartDB(int p_id,long u_id) {
		con=conn.myconnection();
		try {
			Product p=null;
			pstate=con.prepareStatement("insert into Cart values(?,?,?,?,?,?)");
			pstate.setLong(1, u_id);
			pstate.setInt(2, p_id);
			ShoppingDao dao=new ShoppingDaoImpl();
			p=dao.getSingleProduct(p_id);
			pstate.setString(3,p.getProdname());
			pstate.setString(4,p.getProdcategory());
			pstate.setFloat(5,p.getPordprice());
			pstate.setString(6,p.getProdimg());
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Cart> GetCartDB(long u_id) {
		List<Cart> cartDB=new ArrayList<Cart>();
		Cart c=new Cart();
		try {
			con=conn.myconnection();
			pstate=con.prepareStatement("select p_id,p_name,p_category,p_price,p_img from Cart where u_id=?");
			pstate.setLong(1, u_id);
			result=pstate.executeQuery();
			while(result.next())
			{
				c.setProdid(result.getInt("p_id"));
				c.setProdname(result.getString("p_name"));
				c.setProdcategory(result.getString("p_category"));
				c.setPordprice(result.getFloat("p_price"));
				c.setProdimg(result.getString("p_img"));
				c.setQuantity(1);
				cartDB.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cartDB;
	}

		


}
