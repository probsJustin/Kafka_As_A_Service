package com.probsjustin.KAAS;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class controller_kafkaProducer {
	String kafkaHost; 
	String kafakTopic; 
	String kafkaMessage; 
	controller_kafkaProducer(){
		
	}
	
	Map <String,String> checkRequestParameter_Validator(HttpServletRequest func_request, String func_parameter_to_check) {
		Map<String,String> returnMap = null;
		if(func_request.getParameter(func_parameter_to_check) != null) {
			returnMap.put("bool", "true");
			returnMap.put("parameter_value", func_request.getParameter(func_parameter_to_check));
		}else {
			returnMap.put("bool", "false");
		}
		return returnMap;
		
	}
}
