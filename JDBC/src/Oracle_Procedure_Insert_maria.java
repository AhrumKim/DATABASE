import kr.or.kosa.utils.SingletonHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
public class Oracle_Procedure_Insert_maria {
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cstmt = null;//객체 만들기

        try {
            conn = SingletonHelper.getConnection("mariadb");
            String sql = "{call usp_Insert_Emp(?,?,?,?)}";
            cstmt = conn.prepareCall(sql);

            //3개 input , 1개 out (문자열 타입)
            cstmt.setInt(1, 9999);
            cstmt.setString(2, "홍길동");
            cstmt.setString(3, "IT");
            cstmt.registerOutParameter(4, Types.VARCHAR);  //p_outmsg OUT varchar2

            cstmt.execute();//실행

            String mariadb_msg = (String) cstmt.getObject(4);
            System.out.println("mariadb_msg : " + mariadb_msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            SingletonHelper.close(cstmt);
        }
    }
}