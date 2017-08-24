package com.bigdata2017.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.emaillist.vo.EmaillistVo;


public class EmaillistDao {
	private Connection getConnection() 
			throws SQLException {

			Connection conn = null;
			
			try {
				// JDBC 드라이버 로딩(JDBC 클래스 로딩)
				Class.forName( "oracle.jdbc.driver.OracleDriver" );

				// Connection 가져오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection( url, "webdb", "webdb" );
			
			} catch (ClassNotFoundException e) {
				System.out.println( "드라이버 로딩 실패:" + e );
			}
			
			return conn;
		}
	
	public int insert(EmaillistVo vo) {
		int count =0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			// Statement 객체 생성
			
			// SQL문 실행
			String sql = "insert into emaillist "+
			"values (seq_emaillist.nextval,?,?,?)"; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getLast_name());
			pstmt.setString(2, vo.getFirst_name());
			pstmt.setString(3, vo.getEmail());
			
			count = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println( "error :" + e );
		} finally {
			// 자원 정리
			try {
			
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return count;
	}
	
	
	public List<EmaillistVo> getList(){
		List<EmaillistVo> list = new ArrayList<EmaillistVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// SQL문 실행
			String sql = 
					"   select no, last_name, first_name, email" + 
							"     from emaillist" +
							" order by no desc";
			rs = stmt.executeQuery(sql);
			
			// 결과 가져오기(사용하기)
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String last_name = rs.getString( 2 );
				String first_name = rs.getString( 3 );
				String email = rs.getString(4);
				
				EmaillistVo vo = new EmaillistVo();
				vo.setNo(no);
				vo.setLast_name(last_name);
				vo.setFirst_name(first_name);
				vo.setEmail(email);
				
				list.add( vo );
			}
		} catch (SQLException e) {
			System.out.println( "error :" + e );
		} finally {
			// 자원 정리
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return list;
	}
}
