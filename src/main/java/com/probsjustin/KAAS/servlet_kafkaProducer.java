package com.probsjustin.KAAS;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Servlet implementation class kafkaProducer
 */
@WebServlet("/kafkaProducer")
public class servlet_kafkaProducer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = LogManager.getLogger(servlet_kafkaProducer.class);
       
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
		logger.trace("Servlet Recieved Incoming Request");
		identification_request_holder temp_identification_request_holder = new identification_request_holder(request.getRequestURI(), new Date() , request.getRemoteAddr());
		logger.trace(temp_identification_request_holder.getRequest_ID_String()); 
		controller_kafkaProducer instance_controller_kafkaProducer = new controller_kafkaProducer(); 
		response = instance_controller_kafkaProducer.controller(request, response, temp_identification_request_holder);
		logger.trace("Servlet finished processing the request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
