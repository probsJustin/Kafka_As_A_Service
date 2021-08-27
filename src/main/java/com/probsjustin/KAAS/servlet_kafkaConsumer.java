package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class servlet_kafkaConsumer
 */
@WebServlet("/servlet_kafkaConsumer")
public class servlet_kafkaConsumer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = LogManager.getLogger(servlet_kafkaConsumer.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet_kafkaConsumer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		identification_request_holder temp_identification_request_holder = new identification_request_holder(request.getRequestURI(), new Date() , request.getRemoteAddr()); 
		logger.debug(temp_identification_request_holder.getRequest_ID_String() + "Servlet Recieved Incoming Request");
		logger.debug(temp_identification_request_holder.getRequest_ID_String()); 
		controller_kafkaConsumer instance_controller_kafkaConsumer = new controller_kafkaConsumer(); 
		response = instance_controller_kafkaConsumer.controller(request, response, temp_identification_request_holder);
		logger.debug(temp_identification_request_holder.getRequest_ID_String() + "Servlet finished processing the request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
