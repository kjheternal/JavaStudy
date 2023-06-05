package com.eternal.c.kiosk.catcafe;

import java.util.HashSet;

import com.eternal.c.kiosk.util.Common;
import com.eternal.c.kiosk.util.ProcKiosk;

public class ProcMenuExit {
	public void Run() {
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
		Common.wn("계산 할 총 금액: " + Common.nf(strNumber) + " 원");
		Common.wn("===========================");
	}
}
