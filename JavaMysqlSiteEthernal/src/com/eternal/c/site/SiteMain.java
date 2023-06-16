package com.eternal.c.site;

import com.eternal.c.member.ProcMemberLogin;
import com.eternal.c.member.ProcMemberRegister;

import com.eternal.c.site.display.DisplaySite;
import com.eternal.c.sqlboard.ProcBoard;

import com.eternal.c.util.Common;
import com.eternal.c.util.Input;

public class SiteMain {
	static private String cmd = "";

	public static void run() {
		loop: while (true) {
			DisplaySite.showTitle();
			
			if ( !ProcMemberLogin.login ) {
				cmd = Input.r("[r]회원가입 [l]로그인 [b]게시판(게스트) [e]프로그램종료");
			} else {
				cmd = Input.r("[l]로그아웃 [b]게시판 [a]관리자 [e]프로그램종료");
			}
			
			switch (cmd) {
			case "r":
				if ( !ProcMemberLogin.login ) {
					ProcMemberRegister.run();
				} else {
					if ( ProcMemberLogin.memberIDs != null ) {
						Common.wn(ProcMemberLogin.memberIDs + " 님으로 이미 로그인하셨습니다!");
						Common.wn("로그아웃 하시려면 [l]로그아웃 메뉴를 실행해주세요!");
					}
				}
				break;
			case "l":
				if ( !ProcMemberLogin.login ) {
					ProcMemberLogin.run();
				} else {
					ProcMemberLogin.login = false;
					ProcMemberLogin.memberIDs = null;
					
					Common.wn("성공적으로 로그아웃 되었습니다!");
				}
				break;
			case "a":
				if ( !ProcMemberLogin.login ) {
					Common.wn("접근 하실 수 없습니다.");
				} else {
					Common.wn("[관리자 메뉴] 구현 중 입니다.");
				}
				break;
			case "e":
				Common.wn("프로그램 종료");
				break loop;
			case "b":
				ProcBoard.run();
				break;
			default:
				Common.wn("올바른 명령어를 입력해주세요");
			}
		}
	}
}
