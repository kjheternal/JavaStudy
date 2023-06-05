package com.eternal.c.kiosk.catcafe.product;

import com.eternal.c.kiosk.util.Common;

public class Product {
	// 멤버 변수 설정
	public String name;
	public int price;
	public int kcal;
	
	// 생성자 함수
	public Product(String name, int price, int kcal){
		this.name = name;
		this.price = price;
		this.kcal = kcal;
	}
	
	// 오버로드 함수 ( 가격 설정 귀찮을때 쓰면 좋음 )
	public Product(String name, int kcal){
		this.name = name;
		this.price = 5000;
		this.kcal = kcal;
	}
	
	// 오버로드 함수 ( 0 칼로리 음식일때 쓰면 좋음 )
	// 단 가격은 따로 정해줘야함
	public Product(String name){
		this.name = name;
		this.price = 0;
		this.kcal = 0;
	}
	
	// 상품 정보 출력
	public void info() {
		String freePrice = "무료";
		String zeroKcal = "제로 칼로리";
		
		if ( this.price <= 0 && this.kcal <= 0 ) {
			Common.wn(this.name + " 가격: " + freePrice + " 칼로리: " + zeroKcal);
		} else if ( this.price <= 0 ) {
			Common.wn(this.name + " 가격: " + freePrice + " 칼로리: " + this.kcal + " Kcal");
		} else if ( this.kcal <= 0 ) {
			Common.wn(this.name + " 가격: " + this.price + " 칼로리: " + zeroKcal);
		} else {
			Common.wn(this.name + " 가격: " + this.price + " 칼로리: " + this.kcal + " Kcal");
		}
	}
}
