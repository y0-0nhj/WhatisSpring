package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// DB 연결과 DB 해제를 하는 클래스 
public class JDBCUtil {
	// 커넥션 획득 메소드
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "human01";
		String pwd = "1234";
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 커넥션 반납 메소드 - Connection, PreparedStatement
	public static void close(Connection conn, PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally { 
				pstmt = null;
			}
		}
		
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally { 
				conn = null;
			}
		}
	}
	
	// 커넥션 반납 메소드 - Connection, PreparedStatement, ResultSet
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
			try {
				if(!rs.isClosed()) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally { 
				rs = null;
			}
		}
		
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally { 
				pstmt = null;
			}
		}
		
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally { 
				conn = null;
			}
		}
	}
}
