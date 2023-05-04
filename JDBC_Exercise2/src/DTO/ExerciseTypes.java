package DTO;

import lombok.Data;

@Data
public class ExerciseTypes {

	private String ETypeCode;	// 운동코드
	private String ETypeName;	// 운동명
	private String ETypeDesc;	// 운동설명
	private int CalPerHour;		// 운동별 칼로리(시간당)
	@Override
	public String toString() {
		return "운동코드 : " + ETypeCode + ", 운동명 : " + ETypeName + ", 설명 : " + ETypeDesc
				+ ", 소모칼로리(시간당) : " + CalPerHour;
	}
	
	
	
	
}
