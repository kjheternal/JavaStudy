package com.eternal.c.kiosk.catcafe;

import java.util.HashSet;

import com.eternal.c.kiosk.util.Common;
import com.eternal.c.kiosk.util.ProcKiosk;

public class ProcMenuInventory {
	public void Run() {
		// 라벨 루프
		loop:
		while(true) {
			Common.wn("[장바구니에 넣은 상품들을 관리합니다.]");
			
			// 장바구니 메뉴 리스트 출력
			Common.wn("====== 고양이 카페 장바구니 메뉴 ======");
			Common.wn("1. 상품 개별 제거");
			Common.wn("2. 상품 전체 제거");
			Common.wn("3. 현재 장바구니에 추가한 상품들");
			Common.wn("========== x. 이전 메뉴 ============");
			
			Common.cmd = Common.sc.next();
			
			switch(Common.cmd) {
				case "1":
					Common.wn("[주의!] 삭제하고 싶은 갯수가 장바구니에 담긴 상품보다 많으면 해당 상품을 전부 삭제함.");
					
					String cmd = Common.rl("[삭제하고 싶은 상품을 입력하세요.]");
					
					String cmd2 = Common.rl("[삭제하고 싶은 상품의 수량을 입력하세요.]");
					
					boolean found = false;
					
					// 상품 리스트 반복문을 돌려서 입력한 값과 상품의 이름이 일치하면 입력한 수량만큼 삭제
					for (Order o : KioskObj.shoppingList) {
						if ( KioskObj.shoppingList.size() > 0 && cmd.equals(o.selectedProduct.name) ) {
							ProcKiosk.Remove(cmd, cmd2);
							found = true;
							
							break;
						}
					}
					
					if (!found) {
				        Common.wn("장바구니에 담긴 상품이 없거나 일치하지 않습니다.");
				    }
					
					break;
				case "2":
					if ( KioskObj.shoppingList.size() > 0 ) {
						KioskObj.shoppingList.clear();
						
						Common.wn("장바구니에 담긴 상품을 모두 삭제했습니다.");
					} else {
						Common.wn("장바구니에 담긴 상품이 없습니다.");
					}
					break;
				case "3":
					if ( KioskObj.shoppingList.size() > 0 ) {
						Common.wn("고른 상품 수: " + KioskObj.shoppingList.size() + " 개");
						
						for (Order o : KioskObj.shoppingList) {
				            String productName = o.selectedProduct.name;
				            KioskObj.frequencyMap.put(productName, KioskObj.frequencyMap.getOrDefault(productName, 0) + 1);
				        }
						
						HashSet<String> uniqueShoppingList = new HashSet<>(KioskObj.frequencyMap.keySet());
						
						for ( String productName:uniqueShoppingList ) {
							int frequency = KioskObj.frequencyMap.get(productName);
							int price = ProcKiosk.getPrice(productName);
							int priceMulti = price * frequency;
							String strPriceNum = Integer.toString(price);
							String strPriceNumMulti = Integer.toString(priceMulti);

							if (frequency > 1) {
								Common.wn(productName + " (x " + frequency + ") " + Common.nf(strPriceNumMulti) + " 원");
				            } else {
				            	Common.wn(productName + " " + Common.nf(strPriceNum) + " 원");
				            }
						}
						
						KioskObj.frequencyMap.clear();
					} else {
						Common.wn("장바구니에 담긴 상품이 없습니다.");
					}
					
					break;
				case "x":
					break loop; // 라벨 루프의 반복문을 끝냄 ( 처음 선택 메뉴로 되돌아감 )
				}
		}
	}
}
