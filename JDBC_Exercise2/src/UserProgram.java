import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.ExerciseRecordsDao;
import DAO.ExerciseTypesDao;
import DTO.ExerciseRecords;
import DTO.ExerciseTypes;

public class UserProgram {

	Scanner sc = new Scanner(System.in);
	ExerciseTypesDao exerciseTypesDao = new ExerciseTypesDao(); 
	ExerciseRecordsDao exerciseRecordsDao = new ExerciseRecordsDao();
	
	RecordProgram recordProgram = new RecordProgram();
	
	public void run(String u_id) {
		userMenu: while (true) {
			System.out.println("1.운동시작 2. 운동종료 3. 운동기록조회 4.운동기록삭제 5.로그아웃");
			String num = sc.nextLine();

			switch (num) {
			case "1": {
				Map<String,ExerciseTypes> map = exerciseTypesDao.exerciseTypeAll();
				String type = null;
				
				typeChk : while(true) {
					System.out.println("* 현재 DB에 저장되어있는 운동 목록 *");
					for (String key : map.keySet()) {
						System.out.println("종목 : " + key);
					}
					System.out.println("어떤 운동을 시작하시겠습니까?");
					type = sc.nextLine();
					if (map.containsKey(type)) {
						break typeChk;
					} else {
					    System.out.println("해당종목은 존재하지 않습니다 다시 입력해주세요.");
					}
				}// while
				
				System.out.println(type + "(를)을 시작합니다." );
				exerciseRecordsDao.exerciseStart(map.get(type).getETypeCode(), u_id);
				
				break;
			}
			case "2": {
				System.out.println("현재 진행중인 운동을 종료 합니다.");
				exerciseRecordsDao.exerciseEnd(u_id);
				break;
			}
			case "3": {
				recordProgram.run(u_id);
				break;
			}
			case "4": {

				break;
			}
			case "5": {

				break userMenu;
			}

			} // switch

		}

	}// run

}
