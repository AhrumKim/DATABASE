package UTILS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class SingletonHelper {
	
	private static Connection conn = null; //public > private
	SingletonHelper() {}
	
	 public static Connection getConnection(String dsn) {
		   if(conn != null) {
			   return conn;
		   }
		   try {
			   		if(dsn.equals("oracle")) {
			   			conn =	DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.133:orcl","HR","1004");
			   		}else if(dsn.equals("mariadb")) {
			   			conn= DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/itworld","super","1004");
			   		}
		   		} catch (Exception e) {
		   			System.out.println(e.getMessage());
		   		}
		   return conn;
	   }
	  
	   public static Connection getConnection(String dsn , String id , String pwd) {
		   Connection conn = null;
		   try {
			   		if(dsn.equals("oracle")) {
			   			conn =	DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.133:orcl",id,pwd);
			   		}else if(dsn.equals("mariadb")) {
			   			conn= DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/itworld",id,pwd);
			   		}
		   		} catch (Exception e) {
		   			System.out.println(e.getMessage());
		   		}
		   return conn;
	   }
	  
	  
	   public static void dbclose() {
		   if(conn != null) {
			   try {
				   conn.close(); // 연결해제(DB연결 끊기)
				   conn = null;
			   } catch (Exception e) {
				   System.out.println(e.getMessage());
			   }
		   }
	   }
	   public static void close(Connection conn) {
		   if(conn != null) {
			   try {
				   conn.close(); // 연결해제(DB연결 끊기)
				   conn = null;
			} catch (Exception e) {
				  System.out.println(e.getMessage());
			}
		   }
	   }
	   public static void close(ResultSet rs) {
		   if(rs != null) {
			   try {
				   rs.close();
			} catch (Exception e) {
				  System.out.println(e.getMessage());
			}
		   }
	   }
	   public static void close(Statement stsmt) {
		   if(stsmt != null) {
			   try {
				   stsmt.close();
			} catch (Exception e) {
				  System.out.println(e.getMessage());
			}
		   }
	   }
	   public static void close(PreparedStatement pstmt) {
		   if(pstmt != null) {
			   try {
				   pstmt.close();
			} catch (Exception e) {
				  System.out.println(e.getMessage());
			}
		   }
	   }
}