package com.peisia.mysqlutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static String DB_NAME = "my_boarddata";
	private static String DB_ID = "root";
	private static String DB_PW = "root";
	private static Connection con = null;
	private static Statement st = null;
	private static ResultSet result = null;
	
	public static void dbInit() {
		try {
			// (1/n) 디비 접속 정보 넣어서 접속하기
			Database.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
			// (2/n) Statement 객체 얻어오기.
			Database.st = Database.con.createStatement();	// Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다. Statement하나당 한개의 ResultSet 객체만을 열 수있다.
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
		}
	}
	
	public static void dbExecuteQuery(String query, Boolean List) {
		try {
			Database.result = Database.st.executeQuery(query); // SQL SELECT 구문 사용 시 쓰는 함수 result 객체에 담을 수 있다.
			while (Database.result.next()) {	// 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
//				String name = result.getString("p_name");	// p_name 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서)
//				System.out.println(name);
				
				String no = Database.result.getString("b_no");
				String name = Database.result.getString("b_id");
				String titleText = Database.result.getString("b_title"); // b_title 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서) 글 제목 가져오기
				String contentText = Database.result.getString("b_text"); // b_text 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서) 글 내용 가져오기
				String hit = Database.result.getString("b_hit");
				String date = Database.result.getString("b_datetime");
				
				String formatString;
				if ( List ) {
					formatString = String.format("%s %20s %10s %10s %s", no, titleText, name, hit, date);
				} else {
					formatString = String.format("%s %20s %10s %10s %s", titleText, name, hit, date, contentText);
				}
				
				Common.wn(formatString);
			}
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
		}
	}
	
	public static void dbExecuteQueryReply(String query) {
		try {
			Database.result = Database.st.executeQuery(query); // SQL SELECT 구문 사용 시 쓰는 함수 result 객체에 담을 수 있다.
			while (Database.result.next()) {	// 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
				String replyID = Database.result.getString("b_id");
				String replyTime = Database.result.getString("b_datetime");
//				String replyOri = Database.result.getString("b_reply_ori");
				String replyText = Database.result.getString("b_reply_text");
				
				String formatString = String.format("%s %20s %s", replyID, replyTime, replyText);
				
				Common.wn(formatString);
			}
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
		}
	}
	
	public static boolean dbExistQuery(String query) {
		try {
			Database.result = Database.st.executeQuery(query); // SQL SELECT 구문 사용 시 쓰는 함수 result 객체에 담을 수 있다.
			while (Database.result.next()) {	// 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
			
			return false;
		}
	}
	
	public static void dbExecuteUpdate(String query) {
		try {
			// (3/n) Statement 객체의 executeUpdate함수에 sql문 실어서 디비에서 실행되게 하기
			// INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환합니다.
			// CREATE / DROP 관련 구문에서는 -1 을 반환합니다.
			int resultCount = Database.st.executeUpdate(query); // 이거 하는 순간 디비에 sql(쿼리) 날아감. (디비에 반영됨)
			
			Common.wn("처리된 행 수:"+resultCount);
		} catch (SQLException e) {
			Common.wn("SQLException: " + e.getMessage());
			Common.wn("SQLState: " + e.getSQLState());
		}
	}
	
	public static int getPostCount(String table) {	
		String count = "";
		try {
			// 댓글 데이터는 제외하고 계산하기
			String sql = String.format("select count(*) from %s where b_reply_ori is null", table);
			
			Database.result = Database.st.executeQuery(sql);
			Database.result.next();
			
			count = Database.result.getString("count(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int intCount = Integer.parseInt(count);
		
		return intCount;
	}
	
	public static int getPostCountSearch(String table, String searchWord) {	
		String count = "";
		try {
			// 댓글 데이터는 제외하고 계산하기
			String sql = String.format(
					"select count(*) from %s where b_reply_ori is null"
					+
					" and b_title like '%%%s%%'", table, searchWord); // %%는 단일 % 기호 ( % 한개는 인식 오류 일으킴 )
			
			Database.result = Database.st.executeQuery(sql);
			Database.result.next();
			
			count = Database.result.getString("count(*)");
			
			Common.wn("검색한 글 수: " +count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int intCount = Integer.parseInt(count);
		
		return intCount;
	}
}
