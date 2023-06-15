package com.peisia.mysqltest;

import com.peisia.mysqlclient.Display;
import com.peisia.mysqlutil.Database;

public class ProcBoard {
	void run() {
		Database.dbInit();
		Display.showTitle();
		ProcMenu.run();
	}
}
