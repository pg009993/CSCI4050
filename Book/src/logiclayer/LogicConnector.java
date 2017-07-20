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
	
}
