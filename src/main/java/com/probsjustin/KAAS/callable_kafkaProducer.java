package com.probsjustin.KAAS;

import java.util.concurrent.Callable;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class callable_kafkaProducer implements Callable<String> {
	String kafkaHost; 
	String kafkaMessage; 
	String kafkaTopic; 
	final Logger logger = LogManager.getLogger(callable_kafkaProducer.class);

	
	callable_kafkaProducer(String func_kafkaHost, String func_kafkaMessage, String func_kafkaTopic){
		logger.trace("Creating Instance of callable_kafkaProducer");
		this.kafkaHost = func_kafkaHost; 
		this.kafkaMessage = func_kafkaMessage; 
		this.kafkaTopic = func_kafkaTopic; 
	}
	
	@Override
	public String call() throws Exception {
		logger.trace("Attempting to run an instance of callable_kafkaProducer");

		 Properties properties = new Properties();
		 properties.put("bootstrap.servers", String.valueOf(this.kafkaHost));
		 properties.put("key.serializer", StringSerializer.class.getName());
		 properties.put("value.serializer", StringSerializer.class.getName());
		 
		 KafkaProducer kafkaProducer = new KafkaProducer(properties);
		 ProducerRecord<String, String> callable_record = new ProducerRecord(this.kafkaTopic, this.kafkaMessage);
		 
		 callable_record.headers().add("FEED_NAME", this.kafkaTopic.getBytes(StandardCharsets.UTF_8));
		
		 kafkaProducer.send(callable_record);
		 kafkaProducer.flush();
		 kafkaProducer.close();
		 logger.trace("Fisned and Flushed an instance of callable_kafkaProducer");

		return kafkaHost;
	}

}
