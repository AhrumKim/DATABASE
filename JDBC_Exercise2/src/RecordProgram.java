import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.ExerciseRecordsDao;
import DTO.ExerciseRecords;
import UTILS.InputChk;

public class RecordProgram {
	
	Scanner sc = new Scanner(System.in);
	ExerciseRecordsDao exerciseRecordsDao = new ExerciseRecordsDao();
	
	public void run(String u_id) {
		
		recordChk: while (true) {
			System.out.println("\n운동기록을 조회합니다.");
			System.out.println("1.모든 운동기록 조회 , 2. 날짜 지정검색 3. 날짜 범위검색 4. 메뉴 나가기");
			String num = sc.nextLine(); 
			
			switch (num) {
				case "1": {
					//System.out.println(exerciseRecordsDao.getAllExerciseRecordsByU_ID(u_id));
					Map<Integer, Object> map = exerciseRecordsDao.getAll(u_id);
					
					System.out.println((List)map.get(1));
					System.out.println("총 소모 시간(분) : " + (Integer)map.get(2)  + " / 총 소모 칼로리 : " + (Integer)map.get(3));
					
					break;
				}case "2": {
					Date date = InputChk.dateChk();
					Map<Integer, Object> map = exerciseRecordsDao.getExerciseRecordsSelectDate(u_id, date);
					
					List<ExerciseRecords> list = (List)map.get(1);
					if(list.size() == 0) {
						System.out.println(date +" 운동기록이 없습니다.");
					}else {
						System.out.println(list);
						System.out.println("총 소모 시간(분) : " + (Integer)map.get(2)  + " / 총 소모 칼로리 : " + (Integer)map.get(3));
					}
					break;
				}case "3": {
					Date date = InputChk.dateChk();
					Date date2 = InputChk.dateChk(); 
					Map<Integer, Object> map = exerciseRecordsDao.getExerciseRecordsSelectDatetoDate(u_id, date,date2);
					
					List<ExerciseRecords> list = (List)map.get(1);
					if(list.size() == 0) {
						System.out.println(date + " ~ " +date2 + " 사이에 기록이 없습니다.");
					}else {
						System.out.println(list);
						System.out.println("총 소모 시간(분) : " + (Integer)map.get(2)  + " / 총 소모 칼로리 : " + (Integer)map.get(3));
					}
					
					break;
					
				}case "4": {
					break recordChk;
				}	
			}// switch
		}// while
		
		
	}// run
}// class
