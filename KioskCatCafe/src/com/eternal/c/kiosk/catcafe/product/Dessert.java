package com.eternal.c.kiosk.catcafe.product;

import com.eternal.c.kiosk.util.Common;

public class Dessert extends Product {

	public Dessert(String name, int price, int kcal) {
		super(name, price, kcal);
	}
	
	public Dessert(String name) {
		super(name);
	}
	
	@Override
	public void info() {
		super.info();
		Common.wn();
	}
}
