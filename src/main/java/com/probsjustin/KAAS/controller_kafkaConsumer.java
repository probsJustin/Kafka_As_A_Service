package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class controller_kafkaConsumer {
	logger_internal instance_logger_internal = new logger_internal(); 
	List<String> expectedRequestParamList = new ArrayList(); 
	
	controller_kafkaConsumer(){
		instance_logger_internal.debug("controller_kafkaConsumer instance created");
		this.expectedRequestParamList.add("topic");
		this.expectedRequestParamList.add("address");
	}
	
	returnObject<String> checkRequestParameter_Validator(HttpServletRequest func_request, String func_parameter_to_check) {
		returnObject<String> returnObjectInstance = new returnObject();
		if(func_request.getParameter(func_parameter_to_check) != null) {
			returnObjectInstance.setBool(true);
			returnObjectInstance.setObject(func_request.getParameter(func_parameter_to_check).toString());
			returnObjectInstance.setInfo(func_parameter_to_check);
		}else {
			returnObjectInstance.setBool(false);
			instance_logger_internal.error("request parameter check failed for [" + func_parameter_to_check + "]");
		}
		return returnObjectInstance;
		
	}
	
	Boolean check_requestParam_Map(Map <String,returnObject<String>> temp_list) {
		Boolean returnBool = true; 
		for (String temp_itr : this.expectedRequestParamList){
			if(!temp_list.get(temp_itr).bool){
				returnBool = false;
			}
			instance_logger_internal.debug(temp_list.get(temp_itr).info);
		}
		return returnBool;	
	}
	
	returnObject check_missingParameter(Map <String,returnObject<String>> temp_list) {
		returnObject instance_returnObject = new returnObject();
		String tempString = ""; 
		for (String temp_itr : this.expectedRequestParamList){
			if(!temp_list.get(temp_itr).bool){
				tempString += temp_itr + ", ";
			}
		}
		instance_returnObject.setObject(tempString.toString().substring(0, tempString.length()-2));
		return instance_returnObject; 
	}
	
	HttpServletResponse controller(HttpServletRequest func_request, HttpServletResponse func_response, identification_request_holder func_identification_request_holder_instance) throws IOException {

		Map <String,returnObject<String>> temp_map_holder_request_parameters_of_returnObject = new HashMap<String,returnObject<String>>(); 	
		instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String()); 
		instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String() + " Checking All request parameters via temp_list");

		temp_map_holder_request_parameters_of_returnObject.put("topic", this.checkRequestParameter_Validator(func_request, "topic"));
		temp_map_holder_request_parameters_of_returnObject.put("address", this.checkRequestParameter_Validator(func_request, "address")); 

		if(check_requestParam_Map(temp_map_holder_request_parameters_of_returnObject)) {
			try {
				func_response.getWriter().append("the things are there that need to be there");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String() + " Request parameters are missing from the check_requestPAram_map");
			func_response.getWriter().append("The request is malformed and missing some parameters to fufill your request.");
			func_response.getWriter().append("\n Parameter missing: " + check_missingParameter(temp_map_holder_request_parameters_of_returnObject).instance_returnable);
		}
		return func_response;
	}
}
