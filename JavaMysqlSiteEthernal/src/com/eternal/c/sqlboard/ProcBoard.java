package com.eternal.c.sqlboard;

import com.eternal.c.sqlboard.display.Display;

public class ProcBoard {
	public static void run() {
		Display.showTitle();
		ProcMenu.run();
	}
}
