package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class controller_kafkaConsumer {
	logger_internal instance_logger_internal = new logger_internal(); 

	controller_kafkaConsumer(){
		instance_logger_internal.debug("controller_kafkaConsumer instance created");
	}
	
	returnObject<String> checkRequestParameter_Validator(HttpServletRequest func_request, String func_parameter_to_check) {
		returnObject<String> returnObjectInstance = new returnObject();
		if(func_request.getParameter(func_parameter_to_check) != null) {
			returnObjectInstance.setBool(true);
			returnObjectInstance.setObject(func_request.getParameter(func_parameter_to_check));
			returnObjectInstance.setInfo(func_parameter_to_check);
		}else {
			returnObjectInstance.setBool(false);
			instance_logger_internal.error("request parameter check failed for [" + func_parameter_to_check + "]");
		}
		return returnObjectInstance;
		
	}
	
	Boolean check_requestParam_Map(List<returnObject<String>> temp_list) {
		Boolean returnBool = true; 
		
		for (returnObject<String> tempFunc : temp_list){
			if(tempFunc.bool == false) {
				returnBool = false; 
			}
		}
		return returnBool;	
	}
	
	HttpServletResponse controller(HttpServletRequest func_request, HttpServletResponse func_response, identification_request_holder func_identification_request_holder_instance) {

		List<returnObject<String>> temp_list = new ArrayList(); 	
		instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String()); 
		instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String() + " Checking All request parameters via temp_list");

		temp_list.add(this.checkRequestParameter_Validator(func_request, "topic"));
		temp_list.add(this.checkRequestParameter_Validator(func_request, "address")); 
		temp_list.add(this.checkRequestParameter_Validator(func_request, "batchSize"));

		if(check_requestParam_Map(temp_list)) {
			try {
				func_response.getWriter().append("the things are there that need to be there");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String() + " Request parameters are missing from the check_requestPAram_map");
		}
		return func_response;
 		
	}
}
