
import java.util.Scanner;
import DAO.ExerciseRecordsDao;
import DAO.ExerciseTypesDao;
import DAO.UsersDao;
import DTO.Users;



public class Main {

	public static void main(String[] args) {
		UsersDao dao =new UsersDao();
		ExerciseTypesDao types = new ExerciseTypesDao();
		ExerciseRecordsDao records = new ExerciseRecordsDao();
				
		Scanner sc = new Scanner(System.in);

		
		System.out.println("안녕하세요^^");
		System.out.println("아래 메뉴 중 사용하실 서비스 번호를 입력해주세요");
		System.out.println("------------------------------");
		System.out.println("1.사용자 등록");
		System.out.println("2.운동 종목 조회");
		System.out.println("3.사용자 운동 기록 조회");
		System.out.println("4.개인 정보 수정");		
		System.out.println("------------------------------");
	
		int strnum =sc.nextInt();
		
		switch(strnum) {
		case 1 : dao.addUser();break;
		case 2 : types.exerciseTypeAll();break;
		case 3 :// 운동 기록;break;
		case 4 : dao.updateUsers(null);
		}
		
		}
		
		
		
}

