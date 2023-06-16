package com.eternal.c.sqlboard;

import com.eternal.c.member.ProcMemberLogin;

import com.eternal.c.util.Common;
import com.eternal.c.util.Database;
import com.eternal.c.util.Input;

public class ProcMenuWrite {
	static void run() {
		String nick = "";
		String title = "";
		String content = "";
		
		Boolean isGuest = false;
		Boolean nickCheck = false;
		Boolean titleCheck = false;
		Boolean contentCheck = false;
		
		if ( ProcMemberLogin.login && ProcMemberLogin.memberIDs != null ) {
			nick = ProcMemberLogin.memberIDs;
			Common.wn("[글 작성] " + nick + " 님 어서오세요.");
			
			nickCheck = true;
			isGuest = false;
		} else {
			while(true) {
				nick = Input.r("게스트 닉네임을 입력해주세요");
				
				if ( nick.length() < 2 || nick.length() > 6 ) {
					Common.wn("게스트 닉네임을 2자 이상 6자 이하로 입력해주세요.");
				} else {
					nickCheck = true;
					isGuest = true;
					break;
				}
			}
		}
		
		while(true) {
			title = Input.rl("글 제목을 입력해주세요");
			
			if ( title.length() < 2 || title.length() > 100 ) {
				Common.wn("제목을 2자 이상 100자 이하로 입력해주세요.");
			} else {
				titleCheck = true;
				break;
			}
		}
		
		while(true) {
			content = Input.rl("글 내용을 입력해주세요");
			
			if ( content.length() < 2 ) {
				Common.wn("내용을 2자 이상 입력해주세요.");
			} else {
				contentCheck = true;
				break;
			}
		}
		
		if ( nickCheck && titleCheck && contentCheck ) {
			if ( isGuest ) {
				String sql = String.format(
						"insert into myboard (b_title,b_id,b_datetime,b_text,b_hit) "
						+ "values ('%s','%s',now(),'%s',0);"
						,title,"guest_" + nick,content);
				
				Database.dbExecuteUpdate(sql);
				
				Common.wn(title + " 글 작성 완료! (비회원)");
			} else {
				String sql = String.format(
						"insert into myboard (b_title,b_id,b_datetime,b_text,b_hit) "
						+ "values ('%s','%s',now(),'%s',0);"
						,title,nick,content);
				
				Database.dbExecuteUpdate(sql);
				
				Common.wn(title + " 글 작성 완료! (회원)");
			}
		}
	}
}