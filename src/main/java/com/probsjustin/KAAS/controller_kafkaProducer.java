package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class controller_kafkaProducer {
	logger_internal instance_logger_internal = new logger_internal(); 
	List<String> expectedRequestParamList = new ArrayList(); 
	
	controller_kafkaProducer(){
		instance_logger_internal.debug("controller_kafkaProducer instance created");
		this.expectedRequestParamList.add("topic");
		this.expectedRequestParamList.add("address");
		this.expectedRequestParamList.add("message");
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
	
	HttpServletResponse controller(HttpServletRequest func_request, HttpServletResponse func_response, identification_request_holder func_identification_request_holder_instance) throws IOException, InterruptedException {

		Map <String,returnObject<String>> temp_map_holder_request_parameters_of_returnObject = new HashMap<String,returnObject<String>>(); 	
		instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String()); 
		instance_logger_internal.debug(func_identification_request_holder_instance.getRequest_ID_String() + " Checking All request parameters via temp_list");

		temp_map_holder_request_parameters_of_returnObject.put("topic", this.checkRequestParameter_Validator(func_request, "topic"));
		temp_map_holder_request_parameters_of_returnObject.put("address", this.checkRequestParameter_Validator(func_request, "address")); 
		temp_map_holder_request_parameters_of_returnObject.put("message", this.checkRequestParameter_Validator(func_request, "message"));

		if(check_requestParam_Map(temp_map_holder_request_parameters_of_returnObject)) {
			try {
				func_response.getWriter().append("the things are there that need to be there");
				callable_kafkaProducer instance_callable_kafkaProducer = new callable_kafkaProducer( 
						temp_map_holder_request_parameters_of_returnObject.get("address").instance_returnable.toString(), 
						temp_map_holder_request_parameters_of_returnObject.get("message").instance_returnable.toString(),
						temp_map_holder_request_parameters_of_returnObject.get("topic").instance_returnable.toString()
						); 
				ExecutorService executor_thrd_callable_kafkaProducer = Executors.newSingleThreadExecutor();
				
				Future<String> futureResult_executor_thrd_callable_kafkaProducer = executor_thrd_callable_kafkaProducer.submit(instance_callable_kafkaProducer);
				instance_logger_internal.debug("FUTURE:" + futureResult_executor_thrd_callable_kafkaProducer);
				while(!executor_thrd_callable_kafkaProducer.isShutdown() && !futureResult_executor_thrd_callable_kafkaProducer.isDone()) {
					instance_logger_internal.debug("Waiting for executor service to be terminated.");
					instance_logger_internal.debug("EXECUTOR STATUS:" + !executor_thrd_callable_kafkaProducer.isShutdown());
					instance_logger_internal.debug("FUTURE IS SHUT DOWN:" + !futureResult_executor_thrd_callable_kafkaProducer.isDone());
					instance_logger_internal.debug("FUTURE:" + futureResult_executor_thrd_callable_kafkaProducer);

		            Thread.sleep(4000);
				}
				instance_logger_internal.debug("Executor appears to be shut down.");
				instance_logger_internal.debug("RESULT FUTURE:" + futureResult_executor_thrd_callable_kafkaProducer);


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
