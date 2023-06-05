package com.eternal.c.kiosk.catcafe.product;

import com.eternal.c.kiosk.util.Common;

public class Food extends Product {
	public String expiryDate;

	public Food(String name, int price, int kcal, String expiryDate) {
		super(name, price, kcal);
		this.expiryDate = expiryDate;
	}
	
	public Food(String name, int price, int kcal) {
		super(name, price, kcal);
		this.expiryDate = "제조일로부터 3일";
	}
	
	public Food(String name, int kcal) {
		super(name, kcal);
		this.price = 3000;
		this.expiryDate = "제조일로부터 3일";
	}

	@Override
	public void info() {
		super.info();
		Common.wn(this.name + " 소비 기한: " + this.expiryDate);
	}
}
