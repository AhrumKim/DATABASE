package DTO;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ExerciseRecords {
	private String ERecordID;		// 운동기록코드
	private String ETypeCode;		// 운동코드
	private String U_ID;			// 사용자아이디
	private Timestamp startTime;	// 시작시간
	private Timestamp endTime;		// 끝난시간
	private int totalTime;			// 총 시간
	private int calBurned;			// 총 칼로리
	private Date RecordDate;	// 일자
	@Override
	public String toString() {
		return "날짜 : " + RecordDate + " 종목 : " + ETypeCode +  " 총시간 : " + totalTime + "분" + " 소모 칼로리 : " + calBurned
				+ "\n";
	}
	
	
	
	
	
}
