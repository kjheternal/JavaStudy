package com.peisia.mysqltest;

import com.peisia.mysqlutil.Common;
import com.peisia.mysqlutil.Database;
import com.peisia.mysqlutil.Input;

public class ProcMenuWrite {
	static void run() {
		String nick = Input.r("닉네임을 입력해주세요");
		
		String title = Input.rl("글 제목을 입력해주세요");
		
		String content = Input.rl("글 내용을 입력해주세요");
		
		Database.dbExecuteUpdate("insert into myboard (b_title,b_id,b_datetime,b_text,b_hit) values ('"+ title +"','"+ nick +"',now(),'"+ content +"',0);");
		
		Common.wn("글 작성 완료!");
	}
}