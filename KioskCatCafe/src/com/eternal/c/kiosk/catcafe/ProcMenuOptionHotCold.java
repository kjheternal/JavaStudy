package com.eternal.c.kiosk.catcafe;

import com.eternal.c.kiosk.util.Common;

public class ProcMenuOptionHotCold {
	public static void run() {
		loop:while(true) {
			Common.wn("[1. HOT/2. COLD/x. 이전메뉴로]");
			
			KioskObj.cmd = KioskObj.sc.next();
			
			switch(KioskObj.cmd) {
			case "1":
				Common.wn("hot 선택됨.");
				Common.wn("수량을 입력해주세요! (1개 이상)");
				
				KioskObj.cmd = KioskObj.sc.next();
				
				Common.Buy(new Order(KioskObj.products.get(0),1), KioskObj.cmd);
				break;
			case "2":
				Common.wn("ice 선택됨.");
				Common.wn("수량을 입력해주세요! (1개 이상)");
				
				KioskObj.cmd = KioskObj.sc.next();
				
				Common.Buy(new Order(KioskObj.products.get(0),2), KioskObj.cmd);
				break;
			case "x":
				Common.wn("이전 메뉴 이동");
				break loop;
			}
		}
	}
}
