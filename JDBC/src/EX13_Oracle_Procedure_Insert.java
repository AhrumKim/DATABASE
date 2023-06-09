import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import kr.or.kosa.utils.SingletonHelper;

/*
     create or replace procedure usp_Insert_Emp
    (
        vempno IN emp.empno&TYPE,
        vename IN emp.ename%TYPE,
        vjob IN emp.job%TYPE.
        p_outmsg OUT varchar2
    )
    is
        begin
            insert into emp(empno, ename, job) values(vempno, vename, vjob);
            commit;
            p_outmsg :='success';
            EXCEPTION WEND OTHERS THEn
            p_outmsg := SQLERRM;
            rollback;
            
        end;
 */
public class EX13_Oracle_Procedure_Insert {

	public static void main(String[] args) {

		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql="{call usp_Insert_Emp(?,?,?,?)}";
			cstmt = conn.prepareCall(sql);
			
			//3개 input,1개 out(문자열 타입)
			cstmt.setInt(1, 9999);
			cstmt.setString(2,"홍길동");
			cstmt.registerOutParameter(4,Types.VARCHAR);

			
			cstmt.execute();
			
			String oracle_msg =(String) cstmt.getObject(4);
			System.out.println("oracle_msg: " + oracle_msg);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(cstmt);
		}
	}

}
