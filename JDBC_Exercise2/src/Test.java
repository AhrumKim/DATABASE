import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.ExerciseRecordsDao;
import DAO.ExerciseTypesDao;
import DAO.UsersDao;
import DTO.ExerciseTypes;
import DTO.Users;

public class Test {
	public static void main(String[] args) throws SQLException {
		
	
		//System.out.println(ConnectionHelper.getConnection("oracle").getMetaData().getDatabaseProductName());
		
		//UserDao dao = new UserDao();
		//dao.createUser(new Users("user003", "password3", "김진", 85, 180, "M"));
		
//		
//		ExerciseTypesDao dao2 = new ExerciseTypesDao();
//		
//		List<ExerciseTypes> list = dao2.exerciseTypeAll();
//		
//		System.out.println(list);
//		
//		ExerciseRecordsDao dao3 = new ExerciseRecordsDao();
//		//dao3.exerciseStart("ET005", "user003");
//		
//		
//		//dao3.exerciseEnd("ER00006");
//		String id = "user003";
//		dao3.exerciseEnd(id);
//		
		
		
		
		MainProgram program = new MainProgram();
		program.run();
		
		
		
		
		
		
		
		
		
		
	}
}
