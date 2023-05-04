package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import DTO.Users;
import UTILS.ConnectionHelper;

public class UsersDao {
		
	Scanner sc = new Scanner(System.in);
	
	//사용자 정보 수정하기
	public int updateUsers(Users users) {
		System.out.println("아이디를 입력하세요!");
		users.setU_ID(sc.nextLine());
		System.out.println("몸무게를 입력하세요!( kg )");		
		users.setWeight(Double.parseDouble(sc.nextLine()));
		System.out.println("키를 입력하세요!( cm )");
		users.setHeight(Double.parseDouble(sc.nextLine()));
		System.out.println("성별을 입력하세요!( M / F )");
		users.setGender(sc.nextLine());
		int updaterow = updateUsers(users);
		if(updaterow > 0) {
			System.out.println("update 성공!");
		} else {
			System.out.println("update fail...");
		}
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rownum=0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle", "KOSA", "1004");
			String sql = "update Users set weight=?, height=?, gender=? where U_ID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1, users.getWeight());
			pstmt.setDouble(2, users.getHeight());
			pstmt.setString(3, users.getGender());
			pstmt.setString(4,  users.getU_ID());
			
			rownum = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return rownum;
	}

		// 사용자 정보 추가하기
	public void addUser() {
		Connection conn = null;
		PreparedStatement pstmt = null ;
		
		try {
		conn = ConnectionHelper.getConnection("oracle","kosa","1004");
		String sql = "INSERT INTO users(U_ID, U_PWD, U_NAME, Weight, Height, Gender) VALUES(?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		
		System.out.println("추가하실 사용자의 정보를 입력해주세요.");
		System.out.println("사용자 ID: ");
		String U_ID=sc.nextLine();
		pstmt.setString(1,U_ID);
		
		System.out.println("추가하실 사용자의 정보를 입력해주세요.");
		System.out.println("사용자 PWD: ");
		String U_PWD =sc.nextLine();
		pstmt.setString(2, U_PWD);
		
		System.out.println("추가하실 사용자의 정보를 입력해주세요.");
		System.out.println("사용자 이름: ");
		String U_Name=sc.nextLine();
		pstmt.setString(3, U_Name);
		
		System.out.println("추가하실 사용자의 정보를 입력해주세요.");
		System.out.println("사용자 몸무게: ");
		double Weight=sc.nextDouble();
		pstmt.setDouble(4, Weight);
		
		System.out.println("추가하실 사용자의 정보를 입력해주세요.");
		System.out.println("사용자 키: ");
		double Height=sc.nextDouble();
		pstmt.setDouble(5, Height);
		
		System.out.println("추가하실 사용자의 정보를 입력해주세요.");
		System.out.println("사용자 성별(M/F): ");
		String Gender=sc.nextLine();
		pstmt.setString(6, Gender);
		
		int count = pstmt.executeUpdate();

		if(count>0){
	        System.out.println("사용자 정보가 추가되었습니다.");
	    } else {
	        System.out.println("사용자 정보추가에 실패하였습니다.");
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	} finally {
	    try {
	        conn.close();
	        pstmt.close();
	     
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	}
}
		
	


