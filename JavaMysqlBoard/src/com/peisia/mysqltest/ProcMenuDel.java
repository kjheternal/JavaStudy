package com.peisia.mysqltest;

import com.peisia.mysqlutil.Common;
import com.peisia.mysqlutil.Database;
import com.peisia.mysqlutil.Input;

public class ProcMenuDel {
	static void run() {
		String no = Input.r("삭제 할 글 번호를 입력하세요");
		
		Database.dbExecuteUpdate("delete from myboard where b_no='"+ no +"';");
		
		Common.wn(no + " 번 글 삭제 완료!");
	}
}