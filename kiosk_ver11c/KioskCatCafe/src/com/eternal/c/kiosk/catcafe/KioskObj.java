package com.eternal.c.kiosk.catcafe;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Scanner;

import com.eternal.c.kiosk.catcafe.product.Food;
import com.eternal.c.kiosk.catcafe.product.Product;
import com.eternal.c.kiosk.catcafe.product.Dessert;
import com.eternal.c.kiosk.catcafe.product.Drink;

public class KioskObj {
	// static 은 메인 함수가 호출되기 전에 미리 세팅됨
	// static 추가로 인해서 패키지내에 다른곳에서도 쓸 수 있음
	// 같은 패키지 내에 있어서 public 은 빼도 됨
	
	// 상품 클래스 배열 생성
	public static ArrayList<Order> shoppingList = new ArrayList<>(); // 주문 배열 ( 명함철 ) 생성
	public static ArrayList<Product> products = new ArrayList<>(); // 상품 배열 ( 명함철 ) 생성
	
	public static HashMap<String, Integer> frequencyMap = new HashMap<>(); // 중복 빈도수 체크 배열 ( 명함철 ) 생성
	
	// 입력 클래스 생성
	public static Scanner sc = new Scanner(System.in);
	public static String cmd;
	
	//// 상품목록 처리 ////
	public static void productLoad() {
		products.add(new Drink("아메리카노",1000,350));
		products.add(new Drink("오렌지쥬스",2000,700));
		products.add(new Dessert("케이크"));
		products.add(new Food("토스트",1090));
	}
}
