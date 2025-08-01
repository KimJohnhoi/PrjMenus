package menus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	//연결 문자열//
	private String  driver  = "oracle.jdbc.OracleDriver";
	private String  dburl   = "jdbc:oracle:thin:@192.168.0.43:1521:xe";
	private String  dbuid   = "test";
	private String  dbpwd   = "1234";
	
	private Connection conn = null;
	
	public Connection getConnection() {
		try {
			Class.forName(driver); //OracleDriver oracle = new OracleDriver(); 
			conn = DriverManager.getConnection(dburl, dbuid, dbpwd);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 설정을 확인하세요");
		} catch (SQLException e) {
			System.out.println("DB연결이 잘못되었습니다");
		}
		return conn;
	}
	//DB 연결//
}