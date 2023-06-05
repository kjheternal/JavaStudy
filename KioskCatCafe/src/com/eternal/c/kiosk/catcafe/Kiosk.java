package com.eternal.c.kiosk.catcafe;

import com.eternal.c.kiosk.util.Common;
import com.eternal.c.kiosk.util.Display;

/*
 * v12c
 * - 장바구니에 담은 아이템들을 삭제처리 ( 장바구니 비우기 )
 *   > 개별항목 삭제, 전체 비우기
 * - 영수증 출력 클래스 분리
 * - 구매, 제거 공통 함수 클래스 분리
 * - 입력 클래스 공통 함수 KioskObj 에서 Common 클래스로 이동
*/

public class Kiosk {
	
	public static final String VERSION = "0.0.12c";	//버전 표시용.
	
	public void Run() {
		//test
//		Common.wn(String.format("%10s", "고양이"));
		
		// 상품 로드 ( 미리 상품 로드 안하면 상품 처리에서 오류 생김 )
		KioskObj.productLoad();
		
		// 타이틀 출력
		Display.title();
		
		// 라벨 루프a
		loop_a:
		while(true) {
			Common.w("[1. 음료/2. 디저트/i. 장바구니/x. 메뉴 나가기]");
			Common.cmd = Common.sc.next();
			
			switch(Common.cmd) {
				case "1":
					
					// 음료 클래스 호출
					ProcMenuDrink procDrink = new ProcMenuDrink();
					procDrink.Run();
					
					break; // 무한 루프 중이라 다시 처음 선택 메뉴로 되돌아감
				case "2":
					
					// 디저트 클래스 호출
					ProcMenuDessert procDessert = new ProcMenuDessert();
					procDessert.Run();
					
					break; // 무한 루프 중이라 다시 처음 선택 메뉴로 되돌아감
				case "i":
					
					// 인벤토리 클래스 호출
					ProcMenuInventory procInventory = new ProcMenuInventory();
					procInventory.Run();
					
					break; // 무한 루프 중이라 다시 처음 선택 메뉴로 되돌아감
				case "x":
					// 영수증 출력 클래스 호출
					ProcMenuExit procExit = new ProcMenuExit();
					procExit.Run();
					
					break loop_a; // 라벨 루프a의 반복문을 끝냄 ( 프로그램 종료 )
			}
		}
		
		loop_b:
			while(true) {
		
			Common.wn("o 입력시 재작동 / x 입력시 프로그램 종료.");
			
			Common.cmd = Common.sc.next();
			
			switch(Common.cmd) {
				case "o":
					KioskObj.shoppingList.clear();
					KioskObj.products.clear();
					KioskObj.frequencyMap.clear();
					
					this.Run();
					
					break loop_b;
				case "x":
					Common.wn("프로그램 종료");
					
					Common.sc.close();
					break loop_b;
			}
			
		}
	}
}
