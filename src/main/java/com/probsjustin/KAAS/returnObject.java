package com.probsjustin.KAAS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class returnObject<T> {
	final Logger logger = LogManager.getLogger(returnObject.class);
	public Boolean bool = false;
	public Object instance_returnable; 
	public String message = ""; 
	public String info = ""; 
	
	Boolean setBool(Boolean func_bool) {
		Boolean func_bool_return = false; 
		this.bool = func_bool; 
		if(this.bool == func_bool) {
			logger.trace("The returnObject of [" + this.info + "] has been set to -> " + this.bool);
			func_bool_return = true; 
		}else {
			func_bool_return = false; 
		}
		return func_bool_return;
	}
	
	Boolean setInfo(String func_info) {
		Boolean func_bool_return = false;
		this.info = func_info; 
		if(this.info == func_info) {
			
		}
	}
}