package com.eternal.c.kiosk.catcafe;

import com.eternal.c.kiosk.catcafe.product.Dessert;
import com.eternal.c.kiosk.catcafe.product.Food;
import com.eternal.c.kiosk.catcafe.product.Product;

import com.eternal.c.kiosk.util.Common;
import com.eternal.c.kiosk.util.ProcKiosk;

public class ProcMenuDessert {
	public void Run() {
		// 라벨 루프
		loop:
		while(true) {
			Common.wn("[주문을 할 디저트 번호를 입력해 주문하세요.]");
			
			// 음료 메뉴 리스트 출력
			Common.wn("====== 고양이 카페 디저트 메뉴 ======");
			Common.wn("1. " + KioskObj.products.get(2).name);
			Common.wn("2. " + KioskObj.products.get(3).name);
			Common.wn("3. 고양이 카페 디저트 상품 정보 보기");
			Common.wn("========== x. 이전 메뉴 =========");
			
			Common.cmd = Common.sc.next();
			
			switch(Common.cmd) {
				case "1":
					KioskObj.products.get(2).price = 10000; // 케이크 가격을 생성자 함수에서 생략했으므로 여기서 처리
					
					Common.wn("수량을 입력해주세요! (1개 이상)");
					
					Common.cmd = Common.sc.next();
					int value = Integer.parseInt(Common.cmd);
					
					ProcKiosk.Buy(new Order(KioskObj.products.get(2)), value); // 오더 추가
					
					break;
				case "2":
					Common.wn("수량을 입력해주세요! (1개 이상)");
					
					Common.cmd = Common.sc.next();
					int value2 = Integer.parseInt(Common.cmd);
					
					ProcKiosk.Buy(new Order(KioskObj.products.get(3)), value2);
					
					break;
				case "3":
					KioskObj.products.get(2).price = 10000; // 케이크 가격을 생성자 함수에서 생략했으므로 여기서 처리
					
//					int count = 0;
					
					for(Product p:KioskObj.products) {	//메뉴출력
						
						/*
						// 0, 1 ( 음료 메뉴 배열 ) 이면 스킵
						if (count <= 1) {
							count++;
					        continue;
					    }
						
						// 2, 3 ( 디저트 메뉴 배열 ) 이면 표시
						p.info();
						count++;
						
						// 4에는 데이터가 없어서 빠져나감
						if (count == 4) {
					        break;
					    }
					    */
						
						// 상속 응용 ( Product 클래스의 원형이 디저트이거나 음식이면~ )
						if (p instanceof Dessert || p instanceof Food) {
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
