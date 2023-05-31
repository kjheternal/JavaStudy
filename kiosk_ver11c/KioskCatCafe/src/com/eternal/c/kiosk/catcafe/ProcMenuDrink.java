package com.eternal.c.kiosk.catcafe;

import com.eternal.c.kiosk.catcafe.product.Drink;
import com.eternal.c.kiosk.catcafe.product.Product;

//import java.util.Scanner;

import com.eternal.c.kiosk.util.Common;

public class ProcMenuDrink {
	public void Run() {
//	public void Run(Scanner zz) {
		// 라벨 루프
		loop:
		while(true) {
			Common.wn("[주문을 할 음료 번호를 입력해 주문하세요.]");
			
			// 음료 메뉴 리스트 출력
			Common.wn("====== 고양이 카페 음료 메뉴 ======");
			Common.wn("1. " + KioskObj.products.get(0).name);
			Common.wn("2. " + KioskObj.products.get(1).name);
			Common.wn("3. 고양이 카페 음료 상품 정보 보기");
			Common.wn("========== x. 이전 메뉴 =========");
			
			KioskObj.cmd = KioskObj.sc.next();
			
//			Kiosk.cmd = zz.next();
			
			switch(KioskObj.cmd) {
				case "1":
					ProcMenuOptionHotCold.run();
					
					break;
				case "2":
					Common.wn("수량을 입력해주세요! (1개 이상)");
					
					KioskObj.cmd = KioskObj.sc.next();
					
					Common.Buy(new Order(KioskObj.products.get(1)), KioskObj.cmd);
					
					break;
				case "3":
//					int count = 0;
					
					for(Product p:KioskObj.products) {	//메뉴출력
						/*
						p.info();
						count++;
						
						if (count == 2) {
					        break;
					    }
					    */
						
						// 상속 응용 ( Product 클래스의 원형이 음료면~ )
						if (p instanceof Drink) {
							p.info();
						}
					}
					
					break;
				case "x":
					break loop; // 라벨 루프의 반복문을 끝냄 ( 처음 선택 메뉴로 되돌아감 )
				}
		}
	}
}
