package shop.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ShoppingConnection {
	static String url="jdbc:oracle:thin:@localhost:1521:XE";
	static String driver="oracle.jdbc.OracleDriver";
	static String username="System";
	static String password="123456789";
	static Connection con=null;
	int i=0;
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection myconnection()
	{
		try {
			con=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}



}
