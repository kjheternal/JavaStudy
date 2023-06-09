package com.peisia.mysqltest;

import com.peisia.mysqlutil.Database;
import com.peisia.mysqlutil.Display;

public class ProcBoard {
	void run() {
		Database.dbInit();
		Display.showTitle();
		ProcMenu.run();
	}
}
