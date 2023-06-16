package com.eternal.c.sqlboard;

import com.eternal.c.util.Common;
import com.eternal.c.util.Database;
import com.eternal.c.util.Input;

public class ProcMenuUpdate {
	static void run() {
		String no = null;
		Boolean isValidNo = false;

		while (!isValidNo) {
		    no = Input.r("수정 할 글 번호를 입력하세요");

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
		
		String titleEdit;
		String contentEdit;
		
		Boolean titleEditCheck = false;
		Boolean contentEditCheck = false;
		
		while(true) {
			titleEdit = Input.rl("수정 할 글 제목을 입력해주세요");
			
			if ( titleEdit.length() < 2 || titleEdit.length() > 100 ) {
				Common.wn("제목을 2자 이상 100자 이하로 입력해주세요.");
			} else {
				titleEditCheck = true;
				break;
			}
		}
		
		while(true) {
			contentEdit = Input.rl("수정 할 글 내용을 입력해주세요");
			
			if ( contentEdit.length() < 2 ) {
				Common.wn("내용을 2자 이상 입력해주세요.");
			} else {
				contentEditCheck = true;
				break;
			}
		}
		
		if ( isValidNo && titleEditCheck && contentEditCheck ) {
			String sql = String.format("update myboard set b_title = '%s', b_text = '%s' where b_no = %d;", titleEdit, contentEdit, Integer.parseInt(no));
			
			Database.dbExecuteUpdate(sql);
			
			Common.wn(Integer.parseInt(no) + " 번 글 내용 수정 완료!");
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