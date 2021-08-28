package com.probsjustin.KAAS;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class logger_internal {
	String internalDebugLevel = "debug";
	Map<String,Integer> internalDebugMap = new HashMap<String,Integer>(); 
	
	
	logger_internal(){
		this.internalDebugMap.put("info", 	1);
		this.internalDebugMap.put("warn", 	2);
		this.internalDebugMap.put("error",	3);
		this.internalDebugMap.put("debug",	4);
		this.internalDebugMap.put("servlet",	0);
	}
	
	void debug(String func_debugMessage) {
		this.writeLog("debug", func_debugMessage);
	}
	void error(String func_debugMessage) {
		this.writeLog("error", func_debugMessage);

	}
	
	void writeLog(String func_debugFlag, String func_debugMessage) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		if(this.internalDebugMap.get(internalDebugLevel) <= this.internalDebugMap.get(func_debugFlag)){ 
			switch(this.internalDebugMap.get(func_debugFlag)) {
			case 1: {
				String tempDebugString = "[INFO ] " + timestamp + " | " + func_debugMessage.toString();
				System.out.println(tempDebugString);  
				break;		
			}
			case 2: {
				String tempDebugString = "[WARN ] " + timestamp + " | " + func_debugMessage.toString();
				System.out.println(tempDebugString);  
				break;		
			}
			case 3: { 
				String tempDebugString = "[ERROR] " + timestamp + " | " + func_debugMessage.toString();
				System.out.println(tempDebugString);  
				break;		
			}
			case 4: {
				String tempDebugString = "[DEBUG] " + timestamp + " | " + func_debugMessage.toString();
				System.out.println(tempDebugString);  
				break;		
				}
			}
		}
	}
}
