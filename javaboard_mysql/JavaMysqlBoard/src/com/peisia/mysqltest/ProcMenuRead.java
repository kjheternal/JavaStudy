package com.peisia.mysqltest;

import com.peisia.mysqlutil.Database;
import com.peisia.mysqlutil.Input;

public class ProcMenuRead {
	static void run() {
		String no = Input.r("읽을 글 번호를 입력하세요");
		
		Database.dbExecuteQuery("select * from myboard where b_no='"+ no +"';", false); // 글번호로 찾음
	}

}
