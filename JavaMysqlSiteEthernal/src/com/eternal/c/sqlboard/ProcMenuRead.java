package com.eternal.c.sqlboard;

import com.eternal.c.member.ProcMemberLogin;
import com.eternal.c.sqlboard.display.Display;
import com.eternal.c.util.Common;
import com.eternal.c.util.Database;
import com.eternal.c.util.Input;

public class ProcMenuRead {
	static void run() {
		String no = "";
		String replyNick = "";
		Boolean isValidNo = false;
		Boolean isGuest = false;

		while (!isValidNo) {
		    no = Input.r("읽을 글 번호를 입력하세요");

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
			String sql = String.format("select * from myboard where b_no=%d;", Integer.parseInt(no));
			String sqlReply = String.format("select * from myboard where b_reply_ori=%d;", Integer.parseInt(no));
			
			Display.titleList(false);
			Database.dbExecuteQuery(sql, false);
			
			Display.replyBar();
			Database.dbExecuteQueryReply(sqlReply);

		
			loop:while(true) {
				String cmd = Input.r("[r] 댓글쓰기, [x] 나가기");
				
				switch(cmd) {
				case "r":
					if ( ProcMemberLogin.login && ProcMemberLogin.memberIDs != null ) {
						replyNick = ProcMemberLogin.memberIDs;
						Common.wn("[댓글] " + replyNick + " 님 어서오세요.");
						
						isGuest = false;
					} else {
						while(true) {
							replyNick = Input.r("게스트 닉네임을 입력해주세요");
							
							if ( replyNick.length() < 2 || replyNick.length() > 6 ) {
								Common.wn("게스트 닉네임을 2자 이상 6자 이하로 입력해주세요.");
							} else {
								isGuest = true;
								break;
							}
						}
					}
					
					String replyText = Input.rl("댓글 입력");
					
					if ( isGuest ) {
						String insertSQL = String.format("insert into myboard (b_id,b_datetime,b_reply_ori,b_reply_text) "
						+ "values ( '%s', now(), %d, '%s' );", "guest_" +replyNick, Integer.parseInt(no), replyText);
						
						Database.dbExecuteUpdate(insertSQL);
					} else {
						String insertSQL = String.format("insert into myboard (b_id,b_datetime,b_reply_ori,b_reply_text) "
						+ "values ( '%s', now(), %d, '%s' );", replyNick, Integer.parseInt(no), replyText);

						Database.dbExecuteUpdate(insertSQL);
					}
					break;
				case "x":
					break loop;
				default:
					Common.wn("올바른 값을 입력해주세요.");
				}
			}
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
