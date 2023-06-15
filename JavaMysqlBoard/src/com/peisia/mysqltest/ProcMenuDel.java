package com.peisia.mysqltest;

import com.peisia.mysqlutil.Common;
import com.peisia.mysqlutil.Database;
import com.peisia.mysqlutil.Input;

public class ProcMenuDel {
	static void run() {
		String no = null;
		Boolean isValidNo = false;

		while (!isValidNo) {
		    no = Input.r("삭제 할 글 번호를 입력하세요");

		    // 글 번호 유효성 검사
		    try {
		        int number = Integer.parseInt(no);
		        
		        // 해당 번호의 글이 존재하는지 확인
		        Boolean isExist = checkIfPostExists(number);
		        if (isExist) {
		            isValidNo = true;
		        } else {
		            Common.wn("해당 글 번호가 존재하지 않습니다.");
		        }
		    } catch (NumberFormatException e) {
		        Common.wn("유효한 숫자를 입력해주세요.");
		    }
		}
		
		if ( isValidNo ) {
			String sql = String.format("delete from myboard where b_no='%s';", no);
			
			Database.dbExecuteUpdate(sql);
			
			Common.wn(no + " 번 글 삭제 완료!");
		}
	}
	
	// 글 번호 유효성 검사 및 데이터베이스에서 존재 여부 확인을 위한 메소드
	private static boolean checkIfPostExists(int postNumber) {
		String sql = String.format("select * from myboard where b_no=%d;", postNumber);
		
		Boolean isVaildSQL = Database.dbExistQuery(sql); // 글번호로 찾음
		
		if ( isVaildSQL ) {
			return true;
		} else {
			return false;
		}
	}
}