package com.peisia.mysqlutil;

public class Display {
	static private String TITLE_BAR = "🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈"; 
	static private String TITLE 	= "🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈     게시판     🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈🐈"; 
	
	static private String MAIN_MENU_BAR = "================================================================";
	static private String MAIN_MENU	 	= "[1]글리스트 [2]글읽기 [3]글쓰기 [4]글삭제 [5]글수정 [0]관리자 [e]종료";
	
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
}