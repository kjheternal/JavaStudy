package com.eternal.c.member;

import com.eternal.c.util.Common;
import com.eternal.c.util.Database;
import com.eternal.c.util.Input;

public class ProcMemberLogin {
	public static Boolean login = false;
	public static String memberIDs = "";
	
	public static void run() {
		Common.wn("======== 로그인 =========");
		String memberID = "";
		String memberPassword = "";
		
		Boolean memberIDCheck = false;
		Boolean memberPasswordCheck = false;
		
		while(true) {
			memberID = Input.r("[로그인] 아이디를 입력해주세요");
			
			if ( memberID.length() < 0 ) {
				Common.wn("[로그인] 아이디를 똑바로 입력해주세요.");
			} else {
				memberIDCheck = true;
				break;
			}
		}
		
		while(true) {
			memberPassword = Input.r("[로그인] 비밀번호를 입력해주세요");
			
			if ( memberPassword.length() < 0 ) {
				Common.wn("[로그인] 비밀번호를 똑바로 입력해주세요.");
			} else {
				memberPasswordCheck = true;
				break;
			}
		}
		
		if ( memberIDCheck && memberPasswordCheck ) {
			if (Database.isProcLogin(memberID, memberPassword)) {
				login = true;
				memberIDs = memberID;
			} else {
				Common.wn("[로그인] 로그인 실패.");
				
				login = false;
				memberIDs = null;
			}
		} else {
			login = false;
			memberIDs = null;
		}
	}
}
