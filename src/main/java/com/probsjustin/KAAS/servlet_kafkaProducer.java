package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class kafkaProducer
 */
@WebServlet("/kafkaProducer")
public class servlet_kafkaProducer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	logger_internal instance_logger_internal = new logger_internal();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet_kafkaProducer() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		identification_request_holder temp_identification_request_holder = new identification_request_holder(request.getRequestURI(), new Date() , request.getRemoteAddr());
		instance_logger_internal.debug(temp_identification_request_holder.getRequest_ID_String() + " Servlet Recieved Incoming Request");
		controller_kafkaProducer instance_controller_kafkaProducer = new controller_kafkaProducer(); 
		try {
			response = instance_controller_kafkaProducer.controller(request, response, temp_identification_request_holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		instance_logger_internal.debug(temp_identification_request_holder.getRequest_ID_String() + " Servlet finished processing the request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
