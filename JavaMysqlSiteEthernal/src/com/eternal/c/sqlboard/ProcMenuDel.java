package com.eternal.c.sqlboard;

import com.eternal.c.member.ProcMemberLogin;
import com.eternal.c.util.Common;
import com.eternal.c.util.Database;
import com.eternal.c.util.Input;

public class ProcMenuDel {
	static void run() {
		String no = "";
		String Nick = "";
		Boolean isValidNo = false;
		
		if ( ProcMemberLogin.login && ProcMemberLogin.memberIDs != null ) {
			Nick = ProcMemberLogin.memberIDs;
			Common.wn("[글 삭제] " + Nick + " 님 어서오세요.");
			
			while (!isValidNo) {
			    no = Input.r("삭제 할 글 번호를 입력하세요");

			    // 글 번호 유효성 검사
			    try {
			        int number = Integer.parseInt(no);
			        
			        // 해당 번호의 글이 존재하는지 확인
			        Boolean isExist = checkIfPostExists(number, Nick);
			        if (isExist) {
			            isValidNo = true;
			        } else {
			            Common.wn("해당 글 번호가 존재하지 않거나 삭제 할 권한이 없습니다.");
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
		} else {
			Common.wn("[글 삭제] 게스트는 글을 삭제 할 권한이 없습니다.");
			
			// TODO: 게스트 글 쓸때 닉네임과 글 비번을 적게하고 닉네임과 글 비번으로 게스트가 쓴 글을 삭제할 수 있도록 개선
		}
	}
	
	// 글 번호 유효성 검사 및 데이터베이스에서 존재 여부 확인을 위한 메소드
	private static boolean checkIfPostExists(int postNumber, String postNick) {
		String sql = String.format("select * from myboard where b_no=%d and b_id='%s';", postNumber, postNick);
		
		Boolean isVaildSQL = Database.dbExistQuery(sql); // 글번호로 찾음
		
		if ( isVaildSQL ) {
			return true;
		} else {
			return false;
		}
	}
}