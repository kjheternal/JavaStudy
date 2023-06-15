package com.peisia.mysqltest;

import com.peisia.mysqlutil.Common;
import com.peisia.mysqlutil.Database;
import com.peisia.mysqlutil.Input;

public class ProcMenuWrite {
	static void run() {
		String nick;
		String title;
		String content;
		
		Boolean nickCheck = false;
		Boolean titleCheck = false;
		Boolean contentCheck = false;
		
		while(true) {
			nick = Input.r("닉네임을 입력해주세요");
			
			if ( nick.length() < 2 || nick.length() > 6 ) {
				Common.wn("닉네임을 2자 이상 6자 이하로 입력해주세요.");
			} else {
				nickCheck = true;
				break;
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
			String sql = String.format(
					"insert into myboard (b_title,b_id,b_datetime,b_text,b_hit) "
					+ "values ('%s','%s',now(),'%s',0);"
					,title,nick,content);
			
			Database.dbExecuteUpdate(sql);
			
			Common.wn(title + " 글 작성 완료!");
		}
	}
}