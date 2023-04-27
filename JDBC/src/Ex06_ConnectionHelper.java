import java.sql.Connection;
import java.sql.SQLException;

import kr.or.kosa.utils.ConnectionHelper;

public class Ex06_ConnectionHelper {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		conn = ConnectionHelper.getConnection("mariadb");
		
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseProductVersion());
		System.out.println(conn.isClosed());
		ConnectionHelper.close(conn);
		System.out.println(conn.isClosed());
		
		conn = ConnectionHelper.getConnection("oracle", "HR", "1004");
		System.out.println(conn.toString());
		//oracle.jdbc.driver.T4CConnection@4009e306
		//connection 요청시 마다 새로운 객체 생성
		//현업 (Connection 요청시 마다 새로운 객체 생성
		//현업 (Connection Pool) > 미리 연결 객체 생성해 놓고 > 가져다 쓰고 반환하는 과정
		
		
		
		
		
	}

}