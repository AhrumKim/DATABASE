package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.ExerciseTypes;
import UTILS.ConnectionHelper;

public class ExerciseTypesDao {

	
	// 운동 종목 조회
	public Map<String , ExerciseTypes> exerciseTypeAll() {
		
		Map<String , ExerciseTypes> map = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from ExerciseTypes";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			map = new HashMap<>();
			while (rs.next()) {
				ExerciseTypes type = new ExerciseTypes(); 
				type.setETypeCode(rs.getString(1));
				type.setETypeName(rs.getString(2));
				type.setETypeDesc(rs.getString(3));
				type.setCalPerHour(rs.getInt(4));
				map.put(type.getETypeName(), type);
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
