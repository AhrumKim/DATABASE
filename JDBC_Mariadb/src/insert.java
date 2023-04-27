import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import DAO.DeptDaoM;
import DTO.Dept;
import UTILS.SingletonHelper;

public class insert {

	public static void main(String[] args) {
		DeptDaoM daoM = new DeptDaoM();
//		daoM.getTotalDept();
//		daoM.addDept(new Dept
//				.Builder()
//				.setDeptno(5555)
//				.setDname("kosa3")
//				.setLoc("경기도")
//				.build());
		daoM.getTotalDept();
		daoM.updateDept(new Dept
				.Builder()
				.setDeptno(4444)
				 .setDname("kosa3")
				.setLoc("강촌")
				.build());
		daoM.getTotalDept();
		
		daoM.deleteDept(5555);
		
	}
}