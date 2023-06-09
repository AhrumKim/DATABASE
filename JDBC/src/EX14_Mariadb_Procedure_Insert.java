import java.sql.*;

public class EX14_Mariadb_Procedure_Insert {
    public static void main(String[] args) {
        try {
            // JDBC 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // DB 연결
            String url = "jdbc:mariadb://localhost:3306/DB명";
            String user = "KOSA";
            String password = "1004";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("DB 연결 성공");

            // 작업 수행

            // DB 연결 종료
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("DB 연결 중 오류가 발생하였습니다.");
            e.printStackTrace();
        }
    }
}