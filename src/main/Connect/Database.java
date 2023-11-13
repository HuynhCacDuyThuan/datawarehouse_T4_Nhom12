package src.main.Connect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Kết nối database
 */
public class Database {
	public static Connection connection() throws SQLException {
		try {
			String url = "jdbc:sqlserver://localhost\\SQLEXPRESSS05:1433;databaseName=mart;"
					+ "encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
			String host = "test";
			String pass = "thuan1301";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(url, host, pass);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("-------");
			e.printStackTrace();
		}
		return null;
	}
    public static void main(String[] args) throws SQLException {
		System.out.println(Database.connection());
		 Date selectedDate = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = dateFormat.format(selectedDate);
	        System.out.println("Selected Date: " + formattedDate);
	}
}
