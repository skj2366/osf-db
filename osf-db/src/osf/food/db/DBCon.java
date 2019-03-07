package osf.food.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";//전화기
	private static final String USER = "osfu";
	private static final String PASSWORD = "12345678";
	private static final String CLASS_NAME = "oracle.jdbc.OracleDriver";//유심칩
	//원래는 써줘야하는게 맞다 (ojdbc를 등록해서 안써도 무관하긴함).

	private static Connection con;
	private static boolean open() {
		try {
			Class.forName(CLASS_NAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
//			System.out.println("성공");
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//Class.forName(CLASS_NAME); 의 오류검출
			//throw new CustomException("클래스가 없어!!!!!");
		} catch (SQLException e) {
			e.printStackTrace();//con = DriverManager.getConnection(URL,USER,PASSWORD);의 오류 검출
		}
		return false;
	}
	
	public static Connection getCon() {
		if(con==null) {
			if(open()) {
				return con;
			}
		}
		return null;
	}
	
	///////////////캡슐화////////////////////
	
	public static void close() {
		if(con!=null) {
			try {
				if(!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		con = null;
	}
//	public static void main(String[] args) {
//		try {
//			open();
//		}catch (CustomException e) {
//			e.printStackTrace();
//		}
//	}
}
