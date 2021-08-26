package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class controller_kafkaConsumer {
	controller_kafkaConsumer(){
		
	}
	
	returnObject<String> checkRequestParameter_Validator(HttpServletRequest func_request, String func_parameter_to_check) {
		returnObject<String> returnObjectInstance = new returnObject();
		if(func_request.getParameter(func_parameter_to_check) != null) {
			returnObjectInstance.bool = true;
			returnObjectInstance.instance_returnable = func_request.getParameter(func_parameter_to_check);
			returnObjectInstance.info = func_parameter_to_check;
		}else {
			returnObjectInstance.bool = false;
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
	
	HttpServletResponse controller(HttpServletRequest func_request, HttpServletResponse func_response) {

		List<returnObject<String>> temp_list = null; 	
		
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
		}
		return func_response;
 		
	}
}
