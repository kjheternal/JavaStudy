package com.eternal.c.sqlboard;

import com.eternal.c.sqlboard.display.Display;
import com.eternal.c.util.Common;
import com.eternal.c.util.Database;
import com.eternal.c.util.Input;

public class ProcMenuList {
	static public final int PER_PAGE = 3;
	static int startIndex = 0;
	static int currentPage = 1;
	static boolean isSearchMode = false;
	static int totalPage = 0;
	static int postCount = 0;
	static String cmd = "";
	
	static void run() {
		////	전체 페이지 수를 구하기	////
		postCount = Database.getPostCount("myboard"); // 전체 글 수 가져오는 DB 함수
		
		if ( postCount % PER_PAGE > 0 ) { // 나머지 연산
			// 해당 페이지에 글이 하나라도 있다면 총 페이지 갯수에 포함시킴
			totalPage = postCount / PER_PAGE + 1; // 나머지가 있을 경우 총 페이지 갯수에 포함시킴
		} else {
			totalPage = postCount / PER_PAGE; // 나머지가 없을 경우 그대로
		}
		
		String total = String.format("전체 글 수: %d | 총 페이지 수: %d", postCount, totalPage);
		
		Common.wn(total);
		
		while(true) {
			cmd = Input.r("페이지 번호 입력 [ 이전 메뉴 (x) ]");
			
			// 입력한 문자열이 x 면 이전 메뉴로 이동
			if (cmd.equals("x")) {
				break;
			}
			
			// 문자열 (String) 번호 값을 정수형 (int) 값으로 변환시킴
			currentPage = Integer.parseInt(cmd);
			
			// 입력한 페이지 값이 총 페이지 수 값보다 크거나 1 미만 일 경우 예외처리
			if (currentPage > totalPage || currentPage < 1) {
				Common.wn("페이지 범위에 맞는 값을 넣어주세요");
				continue;
			}
			
			// 시작 인덱스=(방당 인원)*(현재방번호-1)
			startIndex = (currentPage - 1) * PER_PAGE; // 페이지의 첫 인덱스를 계산해서 저장하기 ( 0 = ( 1 - 1 ) * 3 , startIndex = 0 )
			
			Display.titleList(true);
			
			// 리스트에서 댓글은 미포함 처리 (select 에 조건문 is null 추가)
			// 원문 댓글 번호가 NULL 인 데이터만 표시
			String sql = String.format("select * from myboard where b_reply_ori is null limit %d, %d", startIndex, PER_PAGE); //	select * from board limit 0,3;
			
			Database.dbExecuteQuery(sql, true); // SQL 문 전송, true 는 글 리스트에 맞게 출력해줌
		}
	}
	
	/* 검색어를 입력받아 리스트-검색 처리 */
	static public void search() {
		cmd = Input.rl("검색어 입력 [ 이전 메뉴 (x) ]");
		
		if ( cmd.equals("x") ) {
			return;
		} else {
			searchList(cmd);
		}
	}
	
	/* 리스트-검색 처리 */
	static public void searchList(String searchWord) {
		////	전체 페이지 수를 구하기	////
		postCount = Database.getPostCountSearch("myboard", searchWord); // 전체 글 수 가져오는 DB 함수
		
		if ( postCount % PER_PAGE > 0 ) { // 나머지 연산
			// 해당 페이지에 글이 하나라도 있다면 총 페이지 갯수에 포함시킴
			totalPage = postCount / PER_PAGE + 1; // 나머지가 있을 경우 총 페이지 갯수에 포함시킴
		} else {
			totalPage = postCount / PER_PAGE; // 나머지가 없을 경우 그대로
		}
		
		String total = String.format("검색 한 글 수: %d | 총 페이지 수: %d", postCount, totalPage);
		
		Common.wn(total);
		
		while(true) {
			cmd = Input.r("페이지 번호 입력 <검색 모드> [ 이전 메뉴 (x) ]");
			
			// 입력한 문자열이 x 면 이전 메뉴로 이동
			if (cmd.equals("x")) {
				break;
			}
			
			// 문자열 (String) 번호 값을 정수형 (int) 값으로 변환시킴
			currentPage = Integer.parseInt(cmd);
			
			// 입력한 페이지 값이 총 페이지 수 값보다 크거나 1 미만 일 경우 예외처리
			if (currentPage > totalPage || currentPage < 1) {
				Common.wn("페이지 범위에 맞는 값을 넣어주세요");
				continue;
			}
			
			// 시작 인덱스=(방당 인원)*(현재방번호-1)
			startIndex = (currentPage - 1) * PER_PAGE; // 페이지의 첫 인덱스를 계산해서 저장하기 ( 0 = ( 1 - 1 ) * 3 , startIndex = 0 )
			
			Display.titleList(true);
			
			// 리스트에서 댓글은 미포함 처리 (select 에 조건문 is null 추가)
			// 원문 댓글 번호가 NULL 인 데이터만 표시
			String sql = String.format(
					"select * from myboard where b_reply_ori is null"
					+ " and b_title like '%%%s%%'" // %%는 단일 % 기호 ( % 한개는 인식 오류 일으킴 )
					+ " limit %d, %d", searchWord, startIndex, PER_PAGE); //	select * from board limit 0,3;
			
			Database.dbExecuteQuery(sql, true); // SQL 문 전송, true 는 글 리스트에 맞게 출력해줌
		}
	}
}
