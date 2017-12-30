package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class testWilliam {

	public static void main(String[] args) {
//		String format = "dd-MM-yyyy"; 
//
//		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
//		java.util.Date date = new java.util.Date(); 
//
//		System.out.println( formater.format( date ) );

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("url", "jdbc:postgresql://rhodes.ima.uco.fr:5432/vdemaeyer");
			prop.setProperty("user", "postgres");
			prop.setProperty("password", "");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
		

}
