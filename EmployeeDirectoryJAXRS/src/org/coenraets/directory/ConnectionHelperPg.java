package org.coenraets.directory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionHelperPg
{
	private String url;
	private static ConnectionHelperPg instance;

	private ConnectionHelperPg()
	{
    	String driver = null;
		try {
			Class.forName("org.postgresql.Driver");
			// urlmysql = "jdbc:mysql://http://192.168.61.21/directory?user=root&password=toor";
			// url = "jdbc:postgresql://ftp.pangram.ro:5432/SoftySales?user=postgres&password=telinit";
            ResourceBundle bundle = ResourceBundle.getBundle("directory");
            driver = bundle.getString("pg.jdbc.driver");
            Class.forName(driver);
            url=bundle.getString("pg.jdbc.url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelperPg();
		}
		try {
			return DriverManager.getConnection(instance.url);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}