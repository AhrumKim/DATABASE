package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import DTO.Users;
import UTILS.ConnectionHelper;

public class UsersDao {
		
	Scanner sc = new Scanner(System.in);
	
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
	public int addUser(Users user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;
		try {
		conn = ConnectionHelper.getConnection("oracle","kosa","1004");
		String sql = "INSERT INTO users(U_ID, U_PWD, U_NAME, Weight, Height, Gender) VALUES(?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getU_ID());
		pstmt.setString(2, user.getU_PWD());
		pstmt.setString(3, user.getU_Name());
		pstmt.setDouble(4, user.getWeight());
		pstmt.setDouble(5, user.getHeight());
		pstmt.setString(6, user.getGender());
		rowcount = pstmt.executeUpdate();
		System.out.println(rowcount);
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		} finally {
		ConnectionHelper.close(pstmt);
		ConnectionHelper.close(conn);
		}
		return rowcount;
			} 
}