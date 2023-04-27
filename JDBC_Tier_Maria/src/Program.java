import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import DAO.DeptDao;
import DTO.Dept;
import DTO.Emp;
import UTILS.SingletonHelper;


/*
App 서버 구성 ( MVC 패턴) >> 웹 
잘 하는 것만 해 

Model (java) >> DTO(데이터를 담을 수 있는 클래스) , DAO(데이터를 처리할 수 있는 클래스(JDBC API)) , SERVICE


View (UI)    >> html , jsp 등등 >> 현재 console 화면제어 (main 정도)


Controller   >> 중앙제어 (통제) >> 웹의 접근 통제 >> 요청과 응답처리 >> JAVA >> Servlet(웹용 자바파일) (main 정도)


*/

public class Program {
	public static void main(String[] args) {
		
		Connection conn = SingletonHelper.getConnection("oracle");
				
		try {
			System.out.println(conn.getMetaData().getDatabaseProductName());
			System.out.println(conn.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}










