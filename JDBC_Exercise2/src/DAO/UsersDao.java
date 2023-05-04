package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.Users;
import UTILS.ConnectionHelper;

public class UsersDao {

	
	
	// 회원가입
	public void createUser(Users user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "INSERT INTO Users (U_ID, U_PWD, U_Name, Weight, Height, Gender)"
					+ "VALUES (?, ?, ?, ?, ? , ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getU_ID());
			pstmt.setString(2, user.getU_PWD());
			pstmt.setString(3, user.getU_Name());
			pstmt.setDouble(4, user.getWeight());
			pstmt.setDouble(5, user.getHeight());
			pstmt.setString(6,user.getGender());
			int result = pstmt.executeUpdate();
			System.out.println(result);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);

		}
	}
	
	// 로그인
	public Users login(String u_id , String u_pwd) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Users users = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from USERS where u_id = ? and u_pwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setString(2, u_pwd);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				double weight = rs.getInt(4);
				double height = rs.getInt(5);
				String gender = rs.getString(6);
				users = new Users(id, pw, name, weight, height, gender);
			}
			System.out.println(users);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		return users;
	}
	
	
	
	
}
