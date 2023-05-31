package com.eternal.c.kiosk.catcafe;

import com.eternal.c.kiosk.catcafe.product.Product;

public class Order {
	public Product selectedProduct;
	public int optionHotCold = 0;	//1: hot, 2:cold

	public Order(Product selectedProduct) {
		this.selectedProduct = selectedProduct; // 두 값은 같으나 서로 주소는 다르다.
	}

	// 오버 로딩 ( hot, cold 옵션 추가 )
	public Order(Product selectedProduct, int optionHotCold) {
		this.selectedProduct = selectedProduct;
		this.optionHotCold = optionHotCold;
	}
}
