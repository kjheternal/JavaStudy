package com.eternal.c.kiosk.catcafe.product;

import com.eternal.c.kiosk.util.Common;

public class Drink extends Product {
	public int capacityDrink;

	public Drink(String name, int price, int kcal, int capacityDrink) {
		super(name, price, kcal);
		this.capacityDrink = capacityDrink;
	}
	
	@Override
	public void info() {
		super.info();
		
		if ( this.capacityDrink >= 1000 ) {
//			Common.wn(this.name + " 용량: " + String.format("%.2g", (float)this.capacityDrink / 1000) + "L");
			Common.wn(String.format("%s 용량 %.2g L", this.name, (float)this.capacityDrink / 1000));
		} else {
//			Common.wn(this.name + " 용량: " + this.capacityDrink + "ML");
			Common.wn(String.format("%s 용량 %d ML", this.name, this.capacityDrink));
		}
	}

}
