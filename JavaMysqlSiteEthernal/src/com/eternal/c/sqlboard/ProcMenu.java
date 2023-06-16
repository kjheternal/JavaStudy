package com.eternal.c.sqlboard;

import com.eternal.c.member.ProcMemberLogin;
import com.eternal.c.sqlboard.display.Display;
import com.eternal.c.util.Common;
import com.eternal.c.util.Input;

public class ProcMenu {
	static void run() {
		if ( ProcMemberLogin.login ) {
			Display.showMainMenuLogin();
			if ( ProcMemberLogin.memberIDs != null ) {
				Common.wn(ProcMemberLogin.memberIDs + " 님 환영합니다!");
			}
		} else {
			Display.showMainMenu();
		}
		
		loop:
		while(true) {
			String cmd = Input.r("명령");
			switch(cmd) {
			case "1":	//리스트
				ProcMenuList.run();
				break;
			case "2":	//읽기
				ProcMenuRead.run();
				break;
			case "3":	//쓰기
				ProcMenuWrite.run();
				break;
			case "4":	//삭제
				ProcMenuDel.run();
				break;
			case "5":	//수정
				ProcMenuUpdate.run();
				break;
			case "6":	//글리스트 - 검색
				ProcMenuList.search();
				break;
			case "e":
				Common.wn("프로그램 종료");
				break loop;
			default:
				Common.wn("올바른 값을 입력해주세요.");
				break;
			}
		}
	}

}
