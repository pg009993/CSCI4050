package logiclayer;

import java.sql.ResultSet;

import object.User;
import persist.SQLConnector;

public class LogicConnector {
	SQLConnector conn;
	
	public LogicConnector() {
		conn = new SQLConnector();
	}
	
	public int addUser(User u) {
		return conn.InsertUser(u);
	}
	
	public ResultSet UserCheck(String query) {
		return conn.UserCheck(query);
	}
	
	public ResultSet Login(String query) {
		return conn.Login(query);
	}
	
	public int UpdateUser(User u) {
		return conn.UpdateUser(u);
	}
	
	public ResultSet ReturnBooks(String isbn) {
		return conn.ReturnBooks(isbn);
	}
	
	public int AddToCart(String query) {
		return conn.AddToCart(query);
	}
	
	public int AddOrder(String query) {
		return conn.AddOrder(query);
	}
	
	public void remove(int cartid) {
		conn.remove(cartid);
	}
	
	public ResultSet CheckCart(String query) {
		return conn.CheckCart(query);
	}

	public ResultSet CheckPromo(String query) {
		return conn.CheckPromo(query);
	}
	
	public ResultSet Total(String query) {
		return conn.Total(query);
	}
	
	public int Delete(String query)
	{
		return conn.Delete(query);
	}
	
	public int UpdateQuantity(String query) {
		return conn.UpdateQuantity(query);
	}
}
