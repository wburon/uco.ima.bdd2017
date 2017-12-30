package Singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingletonConnection {

	private static String url; 
//	= "jdbc:postgresql://rhodes.ima.uco.fr:5432/vdemaeyer";
	private static String user;
//	= "postgres";
	private static String passwd;
//	= "";
	
	private static Connection connection;
	
	
	public static Connection getConnection() {
		if(connection == null) {
			try {
				Properties prop = new Properties();
				InputStream input = null;
				
				input = new FileInputStream("config.properties");

				// load a properties file
				prop.load(input);

				// get the property value
				url = prop.getProperty("url");
				user = prop.getProperty("user");
				passwd = prop.getProperty("password");
				
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException | ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
}
