package com.eternal.c.member;

import com.eternal.c.util.Common;
import com.eternal.c.util.Database;
import com.eternal.c.util.Input;

public class ProcMemberRegister {
	public static void run() {
		Common.wn("======== 회원 가입 =========");
		String memberID = "";
		String memberPassword = "";
		String memberPasswordRepeat = "";
		
		Boolean memberIDCheck = false;
		Boolean memberPasswordCheck = false;
		Boolean memberPasswordRepeatCheck = false;
		
		while(true) {
			memberID = Input.r("가입 하실 아이디를 입력해주세요 [ 2자 이상 12자 이하 ]");
			
			// 아이디가 존재하는지 확인 ( 아이디 중복 방지 )
	        Boolean isExist = checkIfIDExists(memberID);
	        if ( isExist ) {
	        	Common.wn("입력하신 아이디는 이미 존재하는 아이디 입니다.");
	        	continue;
	        }
			
			if ( memberID.length() < 2 || memberID.length() > 12 ) {
				Common.wn("아이디를 2자 이상 12자 이하로 입력해주세요.");
			} else {
				memberIDCheck = true;
				break;
			}
		}
		
		while(true) {
			memberPassword = Input.r("비밀번호를 입력해주세요 [ 8자 이상 20자 이하 ]");
			
			if ( memberPassword.length() < 8 || memberPassword.length() > 20 ) {
				Common.wn("비밀번호를 8자 이상 20자 이하로 입력해주세요.");
			} else {
				memberPasswordCheck = true;
				break;
			}
		}
		
		while(true) {
			memberPasswordRepeat = Input.r("비밀번호를 다시 입력해주세요!");
			
			if ( !memberPassword.equals(memberPasswordRepeat) ) {
				Common.wn("비밀번호를 다시 한번 확인해주세요.");
			} else {
				memberPasswordRepeatCheck = true;
				break;
			}
		}
		
		if ( memberIDCheck && memberPasswordCheck && memberPasswordRepeatCheck ) {
			String sql = String.format(
					"insert into member (s_id, s_pw) values ('%s', '%s');"
					,memberID,memberPasswordRepeat);
			
			Database.dbExecuteUpdate(sql);
			
			Common.wn(memberID + " 님 회원가입 하신 것을 진심으로 환영합니다!");
		}
	}
	
	// 아이디 데이터베이스에서 존재 여부 확인을 위한 메소드
	private static boolean checkIfIDExists(String id) {
		String sql = String.format("select * from member where s_id='%s';", id);
		
		Boolean isVaildSQL = Database.dbExistQuery(sql); // 글번호로 찾음
		
		if ( isVaildSQL ) {
			return true;
		} else {
			return false;
		}
	}
}
