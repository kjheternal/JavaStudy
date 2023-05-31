package com.eternal.c.kiosk.catcafe.product;

public class Food extends Product {

	public Food(String name, int price, int kcal) {
		super(name, price, kcal);
	}
	
	public Food(String name, int kcal) {
		super(name, kcal);
	}
	
	public String expiryDate;

}
