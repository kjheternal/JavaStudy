package com.eternal.c.sqlboard.display;

import com.eternal.c.util.Common;

public class Display {
	static private String TITLE_BAR = "🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈"; 
	static private String TITLE 	= "🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈     게시판     🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈"; 
	
	static private String MAIN_MENU_BAR = "================================================================";
	static private String MAIN_MENU	 	= "[1]글리스트 [2]글읽기(댓글:비회원) [3]글쓰기(비회원) [4]글삭제 [5]글수정 [6]글검색 [e]종료";
	
	static private String MAIN_MENU_LOGIN = "[1]글리스트 [2]글읽기(댓글:회원) [3]글쓰기(회원) [4]글삭제 [5]글수정 [6]글검색 [e]종료";
	
	static public void showTitle() {
		Common.wn(TITLE_BAR);
		Common.wn(TITLE);
		Common.wn(TITLE_BAR);
	}
	
	static public void showMainMenu() {
		Common.wn(MAIN_MENU_BAR);
		Common.wn(MAIN_MENU);
		Common.wn(MAIN_MENU_BAR);
		
	}
	
	static public void showMainMenuLogin() {
		Common.wn(MAIN_MENU_BAR);
		Common.wn(MAIN_MENU_LOGIN);
		Common.wn(MAIN_MENU_BAR);
		
	}
	
	static public void titleList(Boolean List) {
		if ( List ) {
			Common.wn(TITLE_BAR);
			Common.wn("🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈     글 리스트     🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈");
			Common.wn(TITLE_BAR);
			Common.wn("글번호            글제목           작성자ID       조회수         작성시간");
		} else {
			Common.wn("글제목            글이름           조회수        작성시간         글내용");
		}
	}
	
	public static void replyBar() {
		Common.wn("================= 댓글 리스트 ==================");
	}	
}