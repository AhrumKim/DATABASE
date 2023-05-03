package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import UTILS.ConnectionHelper;

public class ExerciseRecordsDao {

	// 운동시작 ( 기록 측정 ) insert
	public void exerciseStart(String eTypeCode, String u_ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "INSERT INTO ExerciseRecords (ERecordID, ETypeCode, U_ID) "
					+ "VALUES ('ER' || LPAD(ExerciseRecordsSeq.NEXTVAL, 5, '0'), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eTypeCode);
			pstmt.setString(2, u_ID);

			int result = pstmt.executeUpdate();
			System.out.println("result : " + result);
			System.out.println("insert 성공시 1 실패시 0");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
		}
	}

	// 운동종료 ( 측정 종료 ) update
	public void exerciseEnd(String u_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		CallableStatement cstmt = null;
		String eRecordID = null; // ExerciseRecords테이블에 고유값인 erecordid 을 담을 변수

		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select erecordid from ExerciseRecords where U_ID = ? and endtime is null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eRecordID = rs.getString(1);
			}
			System.out.println(eRecordID);
			cstmt = conn.prepareCall("{call SYSTEM.UpdateExerciseRecord(?)}");
			cstmt.setString(1, eRecordID);
			cstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(conn);
			ConnectionHelper.close(cstmt);
		}
	}

}
