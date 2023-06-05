package com.eternal.c.kiosk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Common {
	// 입력 클래스 생성
	public static Scanner sc = new Scanner(System.in);
	public static String cmd;
	
	// 입력 클래스 ( 공백 인식이 가능한 입력 클래스 )
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	static public String rl(String comment) {
		Common.w(comment+":");
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
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
}
