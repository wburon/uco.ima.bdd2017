package Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

	private static String url = "jdbc:postgresql://rhodes.ima.uco.fr:5432/vdemaeyer";
	private static String user = "postgres";
	private static String passwd = "";
	
	private static Connection connection;
	
	public static Connection getConnection() {
		if(connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
}
