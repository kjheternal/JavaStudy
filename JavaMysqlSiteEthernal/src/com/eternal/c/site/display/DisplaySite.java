package com.eternal.c.site.display;

import com.eternal.c.util.Common;

public class DisplaySite {
	static private String SITE_NAME = "Eternal";
	static private String VERSION 	= " v0.0.6";
	static private String FEAT = " by J.H Kim";
	
	static public void showTitle() {
		Common.line();
		Common.dot();
		Common.space(22);
		Common.w(SITE_NAME);
		Common.w(VERSION);
		Common.w(FEAT);
		Common.space(16);
		Common.dot();
		Common.wn();
		Common.line();
	}
}