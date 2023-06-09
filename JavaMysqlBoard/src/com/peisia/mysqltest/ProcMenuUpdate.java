package com.peisia.mysqltest;

import com.peisia.mysqlutil.Common;
import com.peisia.mysqlutil.Database;
import com.peisia.mysqlutil.Input;

public class ProcMenuUpdate {
	static void run() {
		String no = Input.r("수정 할 글 번호를 입력하세요");
		String content = Input.rl("수정 할 글 내용을 입력해주세요");
		
		Database.dbExecuteUpdate("update myboard set b_text = '"+ content +"' where b_no = '"+ no +"';");
		
		Common.wn(no + " 번 글 내용 수정 완료!");
	}
}