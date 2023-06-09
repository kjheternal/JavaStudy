package com.peisia.mysqlutil;

import java.text.DecimalFormat;

public class Common {
	private static final String DOT = "🐈‍";
	private static final int LINE_LENGTH = 32;
	
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
	
	/* 
	 * 선 긋는 함수
	 * */
	public static void line() {
		for(int i=0;i<LINE_LENGTH;i++) {
			w(DOT);
		}
		wn();
	}
	public static void dot() {
		w(DOT);
	}
	public static void space(int c) {
		for(int i=0;i<c;i++) {
			w(" ");
		}
	}
}
