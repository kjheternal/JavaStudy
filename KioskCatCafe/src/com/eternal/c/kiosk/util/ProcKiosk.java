package com.eternal.c.kiosk.util;

import java.util.Iterator;

import com.eternal.c.kiosk.catcafe.KioskObj;
import com.eternal.c.kiosk.catcafe.Order;
import com.eternal.c.kiosk.catcafe.product.Product;

public class ProcKiosk {
	public static int getPrice(String product) {
	    for (Product p : KioskObj.products) {
	        if (p.name.equals(product)) {
	            return p.price;
	        }
	    }
	    return 0; // 상품의 가격을 찾지 못하면 0으로 출력
	}
	
	public static void amountBuy(Order s, int n) {
		for (int i=1; i<=n; i++) {
			if ( s.optionHotCold > 0 && s.optionHotCold <= 2 ) {
				KioskObj.shoppingList.add(new Order(s.selectedProduct, s.optionHotCold));
			} else {
				KioskObj.shoppingList.add(new Order(s.selectedProduct));
			}
		}
	}
	
	public static void amountRemove(String p, int q) {
	    Iterator<Order> iterator = KioskObj.shoppingList.iterator();
	    boolean removed = false;
	    int removedCount = 0;

	    while (iterator.hasNext() && removedCount < q) {
	    	Order order = iterator.next();
	    	
	    	if (order.selectedProduct.name.equals(p)) {
	            iterator.remove();
	    		removed = true;
	    		removedCount++;
	    	}
	    }

	    if (removed && removedCount > 0) {
	        Common.wn(removedCount + " 개의 상품이 삭제되었습니다.");
	    } else {
	        Common.wn("상품이 장바구니에 존재하지 않습니다!");
	    }
	}
	
	public static void Buy(Order s, int n) {
		try {
			int value = n;
			
			if ( value > 0 ) {
				ProcKiosk.amountBuy(s, value);
				
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
	
	public static void Remove(String p, String n) {
		try {
			int value = Integer.parseInt(n);
			
			if ( value > 0 ) {
				ProcKiosk.amountRemove(p, value);
			} else {
				Common.wn("반드시 1개 이상 입력해주세요!");
			}
        } catch (NumberFormatException e) {
        	Common.wn("잘못된 값입니다! 숫자를 입력해주세요.");
        }
	}
}
