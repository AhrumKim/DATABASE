import java.util.Scanner;

import DAO.ExerciseRecordsDao;
import DAO.ExerciseTypesDao;
import DAO.UsersDao;

public class Program {
	UsersDao dao =new UsersDao();
	ExerciseTypesDao types = new ExerciseTypesDao();
	ExerciseRecordsDao records = new ExerciseRecordsDao();
	Scanner sc  = new Scanner(System.in);
	
	public void record(){
		System.out.println("어떤 운동을 시작하시겠습니까?");
		types.exerciseTypeAll();
		System.out.println("시작하실 운동을 입력해주세요^^");
				
		
	}
}


