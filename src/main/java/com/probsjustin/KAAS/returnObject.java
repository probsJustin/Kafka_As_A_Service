package com.probsjustin.KAAS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class returnObject<T> {
	logger_internal instance_logger_internal = new logger_internal(); 
	public Boolean bool = false;
	public Object instance_returnable = new Object(); 
	public String message = ""; 
	public String info = ""; 
	
	Boolean setBool(Boolean func_bool) {
		Boolean func_bool_return = false; 
		this.bool = func_bool; 
		if(this.bool == func_bool) {
			instance_logger_internal.debug("This instance of returnObject of [" + this.info + "] has set its bool variable to -> " + this.bool);
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
			instance_logger_internal.debug("This instance of returnObject has been set to -> " + this.info);
			func_bool_return = true;
		}else {
			func_bool_return = false; 
		}
		return func_bool_return;
	}
	
	Boolean setMessage(String func_message) {
		Boolean func_bool_return = false;
		this.message = func_message; 
		if(this.message == func_message) {
			instance_logger_internal.debug("This instance of returnObject ["+ this.info +"] has set its message variable to -> " + this.message);
			func_bool_return = true;
		}else {
			func_bool_return = false; 
		}
	return func_bool_return;
	}
	
	Boolean setObject(Object func_object) {
		Boolean func_bool_return = false;
		this.instance_returnable = func_object; 
		if(this.instance_returnable == func_object) {
			instance_logger_internal.debug("This instance of returnObject ["+ this.info +"] has set its instance_returnable variable to -> " + this.instance_returnable.toString());
			func_bool_return = true;
		}else {
			func_bool_return = false; 
		}
	return func_bool_return;
	}
}