package com.peisia.mysqlutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Connection con = null;
	private static Statement st = null;
	private static ResultSet result = null;
	
	public static void dbInit() {
		try {
			// (1/n) 디비 접속 정보 넣어서 접속하기
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_boarddata", "root", "root");
			// (2/n) Statement 객체 얻어오기.
			st = con.createStatement();	// Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다. Statement하나당 한개의 ResultSet 객체만을 열 수있다.
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
		}
	}
	
	public static void dbExecuteQuery(String query, Boolean List) {
		try {
			result = st.executeQuery(query); // SQL SELECT 구문 사용 시 쓰는 함수 result 객체에 담을 수 있다.
			while (result.next()) {	// 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
//				String name = result.getString("p_name");	// p_name 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서)
//				System.out.println(name);
				
				String no = result.getString("b_no");
				String name = result.getString("b_id");
				String titleText = result.getString("b_title"); // b_title 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서) 글 제목 가져오기
				String contentText = result.getString("b_text"); // b_text 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서) 글 내용 가져오기
				String hit = result.getString("b_hit");
				String date = result.getString("b_datetime");
				
				if ( List ) {
					Common.w("글번호:"+no);
					Common.w(" 글제목:"+titleText);
					Common.w(" 작성자:"+name);
					Common.w(" 조회수:"+hit);
					Common.wn(" 작성일:"+date);
				} else {
					Common.w("글제목:"+titleText);
					Common.w(" 작성자:"+name);
					Common.w(" 조회수:"+hit);
					Common.wn(" 작성일:"+date);
					Common.wn("글내용:"+contentText);
				}
			}
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
		}
	}	
	public static void dbExecuteUpdate(String query) {
		try {
			// (3/n) Statement 객체의 executeUpdate함수에 sql문 실어서 디비에서 실행되게 하기
			// INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환합니다.
			// CREATE / DROP 관련 구문에서는 -1 을 반환합니다.
			int resultCount = st.executeUpdate(query); // 이거 하는 순간 디비에 sql(쿼리) 날아감. (디비에 반영됨)
			Common.wn("처리된 행 수:"+resultCount);
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
		}
	}	
}
