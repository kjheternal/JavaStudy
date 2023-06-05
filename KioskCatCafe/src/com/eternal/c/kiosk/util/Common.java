package com.eternal.c.kiosk.util;

import java.text.DecimalFormat;

import com.eternal.c.kiosk.catcafe.KioskObj;
import com.eternal.c.kiosk.catcafe.Order;
import com.eternal.c.kiosk.catcafe.product.Product;

public class Common {
	public static void w(String s) {
		System.out.print(s);
	}
	public static void wn(String s) {
		System.out.println(s);
	}
	
	// wn 함수 - 오버로딩
	// 그냥 엔터하나 넣어주는 함수.
	public static void wn() {
		System.out.println();
	}
	
	public static String nf(String s) {
	    // 문자열을 숫자로 변환
	    long number = Long.parseLong(s);
	    
	    // 3자리마다 콤마를 찍어주는 형식화
	    DecimalFormat decimalFormat = new DecimalFormat("#,###");
	    return decimalFormat.format(number);
	}
	
	public static int getPrice(String product) {
	    for (Product p : KioskObj.products) {
	        if (p.name.equals(product)) {
	            return p.price;
	        }
	    }
	    return 0; // 상품의 가격을 찾지 못하면 0으로 출력
	}
	
	public static void amount(Order s, int n) {
		for (int i=1; i<=n; i++) {
			if ( s.optionHotCold > 0 && s.optionHotCold <= 2 ) {
				KioskObj.shoppingList.add(new Order(s.selectedProduct, s.optionHotCold));
			} else {
				KioskObj.shoppingList.add(new Order(s.selectedProduct));
			}
		}
	}
	
	public static void Buy(Order s, String n) {
		try {
			int value = Integer.parseInt(n);
			
			if ( value > 0 ) {
				Common.amount(s, value);
				
				if ( s.optionHotCold > 0 && s.optionHotCold <= 2 ) {
					String str = null;
					
					if ( s.optionHotCold == 1 ) {
						str = "HOT";
					} else if ( s.optionHotCold == 2 ) {
						str = "ICE";
					}
					
					Common.wn(s.selectedProduct.name + " " + str + " " + value + " 개 가 추가되었습니다. 가격: " + s.selectedProduct.price * value);
				} else {
					Common.wn(s.selectedProduct.name + " " + value + " 개 가 추가되었습니다. 가격: " + s.selectedProduct.price * value);
				}
			} else {
				Common.wn("반드시 1개 이상 입력해주세요!");
			}
        } catch (NumberFormatException e) {
        	Common.wn("잘못된 값입니다! 숫자를 입력해주세요.");
        }
	}
}
