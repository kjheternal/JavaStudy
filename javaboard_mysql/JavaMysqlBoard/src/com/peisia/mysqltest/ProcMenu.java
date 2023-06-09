package com.peisia.mysqltest;

import com.peisia.mysqlutil.Common;
import com.peisia.mysqlutil.Display;
import com.peisia.mysqlutil.Input;

public class ProcMenu {
	static void run() {
		Display.showMainMenu();
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
			case "0":	//관리자 기능 ( 구현 중 )
//				ProcMenuAdmin.run();
				Common.wn("구현 중 입니다.");
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
