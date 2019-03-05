package osf.db; //2019.03.05

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "osfu";
	private static final String PASSWORD = "12345678";
	private static Connection con = null;
	
	static {
		openCon();
	}
	
	private static void openCon() {
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getCon() {
		if(con==null) {
			openCon();
		}
		return con;
	}
	
	public static void closeCon() {
		if(con!=null) {
			try {
				if(!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		con = null;
	}
	
}//end DBConnector
