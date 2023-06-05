package com.eternal.c.kiosk.catcafe;

import com.eternal.c.kiosk.util.Common;
import com.eternal.c.kiosk.util.ProcKiosk;

public class ProcMenuOptionHotCold {
	public static void run() {
		loop:while(true) {
			Common.wn("[1. HOT/2. COLD/x. 이전메뉴로]");
			
			Common.cmd = Common.sc.next();
			
			switch(Common.cmd) {
			case "1":
				Common.wn("hot 선택됨.");
				Common.wn("수량을 입력해주세요! (1개 이상)");
				
				Common.cmd = Common.sc.next();
				int value = Integer.parseInt(Common.cmd);
				
				ProcKiosk.Buy(new Order(KioskObj.products.get(0), 1), value); // 오더 추가
				break;
			case "2":
				Common.wn("ice 선택됨.");
				Common.wn("수량을 입력해주세요! (1개 이상)");
				
				Common.cmd = Common.sc.next();
				int value2 = Integer.parseInt(Common.cmd);
				
				ProcKiosk.Buy(new Order(KioskObj.products.get(0), 2), value2); // 오더 추가
				break;
			case "x":
				Common.wn("이전 메뉴 이동");
				break loop;
			}
		}
	}
}
