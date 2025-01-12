package shop.dao;

import java.util.ArrayList;
import java.util.List;

import shop.model.Cart;
import shop.model.Customer;
import shop.model.Order;
import shop.model.Product;

public interface ShoppingDao {

	int RegisterUser(Customer obj);
	Customer validateUser(String username,String password);
	
	
	void AddToCartDB(int p_id,long u_id);
	List<Cart> GetCartDB(long u_id);
	
	
	List<Product> ReturnProducts(String category);
	List<Cart> GetcartProducts(ArrayList<Cart> cartlist);
	float GetCartTotal(ArrayList<Cart> cartlist);
	Product getSingleProduct(int prodid);
	
	
	boolean InsertOrder(Order ord);
	List<Order> RetrieveOrder(long id);
	Boolean CancelOrder(int id,long u_id);
	
}
