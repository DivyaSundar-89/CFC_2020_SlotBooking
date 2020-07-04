package commodity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectMe {
	public Connection connectDB;
	String url = "jdbc:mysql://localhost:3306/";
	String Driver = "com.mysql.cj.jdbc.Driver";
	String DBName = "cus";
	String user = "root";
	String pass = "root";

	public ConnectMe() {
		try {
			Class.forName(Driver);
			connectDB = DriverManager.getConnection(url + DBName, user, pass);
		} catch (Exception e) {
			System.out.println("Error in Connection" + e);
		}
	}

	public static void main(String args[]) throws SQLException {
		ConnectMe connect = new ConnectMe();
		System.out.println(connect.connectDB);
	}

	public Boolean isConnected() {
		return (boolean) ((connectDB == null) ? false : true);

	}

	public void close(PreparedStatement argPs) {
		try {
			if (argPs != null) {
				argPs.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void close(ResultSet argRs) {
		try {
			if (argRs != null) {
				argRs.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
