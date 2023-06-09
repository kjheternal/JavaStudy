package com.peisia.mysqltest;

import com.peisia.mysqlutil.Database;

public class ProcMenuList {
	static void run() {
		Database.dbExecuteQuery("select * from myboard", true);
	}
}
