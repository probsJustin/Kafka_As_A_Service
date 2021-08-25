package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class controller_kafkaProducer {

	controller_kafkaProducer(){
		
	}
	
	Map <String,String> checkRequestParameter_Validator(HttpServletRequest func_request, String func_parameter_to_check) {
		Map<String,String> returnMap = null;
		if(func_request.getParameter(func_parameter_to_check) != null) {
			returnMap.put("bool", "true");
			returnMap.put("parameter_value", func_request.getParameter(func_parameter_to_check));
			returnMap.put("parameter_key", func_parameter_to_check);
		}else {
			returnMap.put("bool", "false");
		}
		return returnMap;
		
	}
	
	Boolean check_requestParam_Map(List<Map<String, String>> temp_list) {
		Boolean returnBool = true; 
		
		for (Map<String, String> tempFunc : temp_list){
			if(tempFunc.get("bool") == "false") {
				returnBool = false; 
			}
		}
		return returnBool;	
	}
	
	HttpServletResponse controller(HttpServletRequest func_request, HttpServletResponse func_response) {

		List<Map<String,String>> temp_list = null; 	
		
		temp_list.add(this.checkRequestParameter_Validator(func_request, "topic"));
		temp_list.add(this.checkRequestParameter_Validator(func_request, "address")); 
		temp_list.add(this.checkRequestParameter_Validator(func_request, "message"));

		if(check_requestParam_Map(temp_list)) {
			try {
				func_response.getWriter().append("the things are there that need to be there");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return func_response;
	}
	
}
