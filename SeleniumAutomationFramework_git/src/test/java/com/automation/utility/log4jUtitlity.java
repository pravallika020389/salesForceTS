package com.automation.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4jUtitlity {

	private Logger log=null;
	private static log4jUtitlity ob =null;
	
	private log4jUtitlity() {
		
	}
	public static log4jUtitlity getInstance() {
		if(ob==null) {
			ob=new log4jUtitlity();
		}
		return ob;
		
	}
	public Logger getLogger() {
		log=LogManager.getLogger(log4jUtitlity.class);
		return log;
	}
}
