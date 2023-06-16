package com.eternal.c.site;

import com.eternal.c.util.Database;

public class Main {
	public static void main(String[] args) {
		Database.dbInit(); // 데이터베이스 시작 ( 안하면 NullPointerException 오류남 )
		SiteMain.run(); //기존 게시판에서 site로 확장함
	}
}