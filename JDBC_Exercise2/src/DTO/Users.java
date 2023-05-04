package DTO;

import lombok.Data;

@Data
public class Users {
	

	private String u_ID;		// 사용자아이디
	private String u_PWD;		// 사용자암호
	private String u_Name;		// 사용자이름
	private double weight;		// 몸무게
	private double height;		// 키
	private String gender;		// 성별
	
	
	public Users(String u_ID, String u_PWD, String u_Name, double weight, double height, String gender) {
		super();
		this.u_ID = u_ID;
		this.u_PWD = u_PWD;
		this.u_Name = u_Name;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
	}
	
	
}
