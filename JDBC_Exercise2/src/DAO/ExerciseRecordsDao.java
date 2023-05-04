package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.ExerciseRecords;
import DTO.ExerciseTypes;
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
	
	public Map<Integer,Object>  getExerciseRecordsSelectDate(String u_id,Date date){
		Map<Integer, Object> map = new HashMap<>();
		
		List<ExerciseRecords> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			// 운동 종료 되지 않은 항목은 가져오지 않음
			String sql = "select r.erecordid,t.etypename,r.starttime,r.endtime,r.totaltime,r.calburned,r.recorddate from "
					+ "ExerciseRecords r join ExerciseTypes t "
					+ "on r.etypecode = t.etypecode "
					+ "where r.u_id= ? and r.recorddate is not null and r.recorddate = ?"
					+ "order by r.recorddate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setDate(2, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExerciseRecords records = new ExerciseRecords();
				records.setERecordID(rs.getString(1));
				records.setETypeCode(rs.getString(2));
				//records.setU_ID(rs.getString(3));
				records.setStartTime(rs.getTimestamp(3));
				records.setEndTime(rs.getTimestamp(4));
				records.setTotalTime(rs.getInt(5));
				records.setCalBurned(rs.getInt(6));
				records.setRecordDate(rs.getDate(7));
				list.add(records);
			}
			map.put(1, list);
			sql = "select sum(r.totaltime),sum(r.calburned) from "
					+ "ExerciseRecords r join ExerciseTypes t "
					+ "on r.etypecode = t.etypecode "
					+ "where r.u_id= ? and r.recorddate is not null and r.recorddate = ? "
					+ "order by r.recorddate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setDate(2, date);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				map.put(2,rs.getInt(1));
				map.put(3, rs.getInt(2));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		
		return map;
	}
	
	public Map<Integer,Object>  getExerciseRecordsSelectDatetoDate(String u_id,Date date,Date date2){
		Map<Integer, Object> map = new HashMap<>();
		
		List<ExerciseRecords> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			// 운동 종료 되지 않은 항목은 가져오지 않음
			String sql = "select r.erecordid,t.etypename,r.starttime,r.endtime,r.totaltime,r.calburned,r.recorddate from "
					+ "ExerciseRecords r join ExerciseTypes t "
					+ "on r.etypecode = t.etypecode "
					+ "where r.u_id= ? and r.recorddate is not null and r.recorddate >= ? and r.recorddate <= ?"
					+ "order by r.recorddate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setDate(2, date);
			pstmt.setDate(3, date2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExerciseRecords records = new ExerciseRecords();
				records.setERecordID(rs.getString(1));
				records.setETypeCode(rs.getString(2));
				//records.setU_ID(rs.getString(3));
				records.setStartTime(rs.getTimestamp(3));
				records.setEndTime(rs.getTimestamp(4));
				records.setTotalTime(rs.getInt(5));
				records.setCalBurned(rs.getInt(6));
				records.setRecordDate(rs.getDate(7));
				list.add(records);
			}
			map.put(1, list);
			sql = "select sum(r.totaltime),sum(r.calburned) from "
					+ "ExerciseRecords r join ExerciseTypes t "
					+ "on r.etypecode = t.etypecode "
					+ "where r.u_id= ? and r.recorddate is not null and r.recorddate >= ? and r.recorddate <= ?"
					+ "order by r.recorddate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setDate(2, date);
			pstmt.setDate(3, date2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				map.put(2,rs.getInt(1));
				map.put(3, rs.getInt(2));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		
		return map;
	}
	
	public Map<Integer,Object> getAll(String u_id){
		Map<Integer, Object> map = new HashMap<>();
		
		List<ExerciseRecords> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			// 운동 종료 되지 않은 항목은 가져오지 않음
			String sql = "select r.erecordid,t.etypename,r.starttime,r.endtime,r.totaltime,r.calburned,r.recorddate from "
					+ "ExerciseRecords r join ExerciseTypes t "
					+ "on r.etypecode = t.etypecode "
					+ "where r.u_id= ? and r.recorddate is not null "
					+ "order by r.recorddate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExerciseRecords records = new ExerciseRecords();
				records.setERecordID(rs.getString(1));
				records.setETypeCode(rs.getString(2));
				//records.setU_ID(rs.getString(3));
				records.setStartTime(rs.getTimestamp(3));
				records.setEndTime(rs.getTimestamp(4));
				records.setTotalTime(rs.getInt(5));
				records.setCalBurned(rs.getInt(6));
				records.setRecordDate(rs.getDate(7));
				list.add(records);
			}
			map.put(1, list);
			sql = "select sum(r.totaltime),sum(r.calburned) from "
					+ "ExerciseRecords r join ExerciseTypes t "
					+ "on r.etypecode = t.etypecode "
					+ "where r.u_id= ? and r.recorddate is not null "
					+ "order by r.recorddate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				map.put(2,rs.getInt(1));
				map.put(3, rs.getInt(2));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		
		return map;
	}
	
	
}
