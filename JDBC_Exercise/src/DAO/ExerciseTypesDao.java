package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.ExerciseTypes;
import UTILS.ConnectionHelper;

public class ExerciseTypesDao {
	Scanner sc= new Scanner(System.in); 
	// 운동 종목 조회
	//@SuppressWarnings("null")
	public List<ExerciseTypes> exerciseTypeAll() {
		
		List<ExerciseTypes> list = null;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("조회하실 운동 종목을 보여드릴께요!!^^");
		
		
		
		try {
			
			conn = ConnectionHelper.getConnection("oracle");
			/*
			String sql = "select ETypeCode, ETypeName, ETypeDesc, CalPerHour from"
					+ " ExerciseTypes";
			*/
			String sql = "select * from ExerciseTypes";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while (rs.next()) {
				ExerciseTypes type = new ExerciseTypes(); 
				type.setETypeCode(rs.getString(1));
				type.setETypeName(rs.getString(2));
				type.setETypeDesc(rs.getString(3));
				type.setCalPerHour(rs.getInt(4));
				System.out.println(type);
				list.add(type);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("추가 사용하실 메뉴를 선택해주세요");
		System.out.println("------------------------------");
		System.out.println("1. 운동 코드 추가하기");
		System.out.println("2. 운동 코드 삭제하기");
		System.out.println("3. 운동 코드 수정하기");
		System.out.println("------------------------------");
		
		int strnum = sc.nextInt();
		switch(strnum) {
		case 1: addExercise(null);
		case 2: deleteExercise(null);
		case 3: updateExercise(null);
			
		}
		return list;
		
	}
		




	//운동 종목 추가
	@SuppressWarnings("null")
	public void addExercise(ExerciseTypes type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		    conn = ConnectionHelper.getConnection("oracle");

		    // 운동 종목 추가를 위한 쿼리
		    String sql = "INSERT INTO ExerciseTypes (ETypeCode, ETypeName, ETypeDesc, CalPerHour) VALUES (?, ?, ?, ?)";
		    pstmt = conn.prepareStatement(sql);

		    System.out.println("추가하실 운동 종목의 정보를 입력해주세요.");

		    System.out.println("운동 종목 코드: ");
		    String code = sc.nextLine();
		    pstmt.setString(1, code);

		    System.out.println("운동 종목 이름: ");
		    String name = sc.nextLine();
		    pstmt.setString(2, name);

		    System.out.println("운동 종목 설명: ");
		    String desc = sc.nextLine();
		    pstmt.setString(3, desc);

		    System.out.print("1시간당 소모 칼로리: \n");
		    int cal = sc.nextInt();
		    pstmt.setInt(4, cal);

		    int count = pstmt.executeUpdate();

		    if(count > 0) {
		        System.out.println("운동 종목이 추가되었습니다.");
		    } else {
		        System.out.println("운동 종목 추가에 실패하였습니다.");
		    }
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		} finally {
		    try {
		        conn.close();
		        pstmt.close();
		        rs.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	}
	
	
		//운동 종목 삭제
	@SuppressWarnings("null")
	public void deleteExercise(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		    conn = ConnectionHelper.getConnection("oracle");

		    // 운동 종목 삭제를 위한 쿼리
		    String sql = "DELETE FROM ExerciseTypes WHERE ETypeCode = ?";
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, code);

		    int count = pstmt.executeUpdate();

		    if(count > 0) {
		        System.out.println("운동 종목이 삭제되었습니다.");
		    } else {
		        System.out.println("운동 종목 삭제에 실패하였습니다.");
		    }
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		} finally {
		    try {
		        conn.close();
		        pstmt.close();
		        rs.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	}
	
				
		//운동 종목 수정
	@SuppressWarnings("null")
	public void updateExercise(ExerciseTypes type) {
		
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = ConnectionHelper.getConnection("oracle");

	        // 운동 종목 수정을 위한 쿼리
	        String sql = "UPDATE ExerciseTypes SET ETypeName = ?, ETypeDesc = ?, CalPerHour = ? WHERE ETypeCode = ?";
	        pstmt = conn.prepareStatement(sql);

	        System.out.println("수정하실 운동 종목의 정보를 입력해주세요.");

	        System.out.print("운동 종목 이름: ");
	        String name = sc.nextLine();
	        pstmt.setString(1, name);

	        System.out.print("운동 종목 설명: ");
	        String desc = sc.nextLine();
	        pstmt.setString(2, desc);

	        System.out.print("1시간당 소모 칼로리: ");
	        int cal = sc.nextInt();
	        sc.nextLine(); // 개행 문자 제거
	        pstmt.setInt(3, cal);

	        pstmt.setString(4, desc);

	        int cnt = pstmt.executeUpdate();

	        if (cnt > 0) {
	            System.out.println("운동 종목 정보 수정이 완료되었습니다.");
	        } else {
	            System.out.println("운동 종목 정보 수정에 실패하였습니다.");
	        }

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally {
	        try {
	            conn.close();
	            pstmt.close();
	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return;
	}
	
}
		

