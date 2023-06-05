package com.eternal.c.kiosk.catcafe;

import java.util.HashSet;

import com.eternal.c.kiosk.util.Common;
import com.eternal.c.kiosk.util.Display;

/*
 * v9
 * - 하위 메뉴 클래스, KioskObj 클래스 새로 생성
 * - 하위 메뉴 클래스에서 주문 처리하게 변경
 * - 입력 클래스, 상품 배열 KisokObj 클래스로 이전
*/

public class Kiosk {
	
	public static final String VERSION = "0.0.11c";	//버전 표시용.
	
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
			Common.w("[1. 음료/2. 디저트/x. 메뉴 나가기]");
			KioskObj.cmd = KioskObj.sc.next();
			
			switch(KioskObj.cmd) {
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
				case "x":
					// 쇼핑리스트에 넣은 상품 갯수를 출력
					Common.wn("고른 상품 수: " + KioskObj.shoppingList.size() + " 개");
					
					// 합계 금액 초기 값 설정
					int sum = 0;
					
					// 장바구니에 중복된 상품 리스트 빈도수 측정
					// 쇼핑리스트에 넣은 상품 갯수 만큼 반복문을 돌리고 해당 상품의 가격 정보를 불러와서 합침
					for (Order o : KioskObj.shoppingList) {
			            String productName = o.selectedProduct.name;
			            KioskObj.frequencyMap.put(productName, KioskObj.frequencyMap.getOrDefault(productName, 0) + 1);
			            
			            sum = sum + o.selectedProduct.price;
			        }
					
					String strNumber = Integer.toString(sum);
					
					// 중복된 상품을 한번에 묶어서 출력해주는 코드
					HashSet<String> uniqueShoppingList = new HashSet<>(KioskObj.frequencyMap.keySet());
					
					Common.wn("====== 고양이 카페 영수증 ======");
					for ( String productName:uniqueShoppingList ) {
						int frequency = KioskObj.frequencyMap.get(productName);
						int price = Common.getPrice(productName);
						int priceMulti = price * frequency;
						String strPriceNum = Integer.toString(price);
						String strPriceNumMulti = Integer.toString(priceMulti);

						if (frequency > 1) {
							Common.wn(productName + " (x " + frequency + ") " + Common.nf(strPriceNumMulti) + " 원");
			            } else {
			            	Common.wn(productName + " " + Common.nf(strPriceNum) + " 원");
			            }
					}
					Common.wn("계산 할 총 금액: " + Common.nf(strNumber) + " 원");
					Common.wn("===========================");
					
					break loop_a; // 라벨 루프a의 반복문을 끝냄 ( 프로그램 종료 )
			}
		}
		
		loop_b:
			while(true) {
		
			Common.wn("o 입력시 재작동 / x 입력시 프로그램 종료.");
			
			KioskObj.cmd = KioskObj.sc.next();
			
			switch(KioskObj.cmd) {
				case "o":
					KioskObj.shoppingList.clear();
					KioskObj.products.clear();
					
					this.Run();
					
					break loop_b;
				case "x":
					Common.wn("프로그램 종료");
					
					KioskObj.sc.close();
					break loop_b;
			}
			
		}
	}
}
