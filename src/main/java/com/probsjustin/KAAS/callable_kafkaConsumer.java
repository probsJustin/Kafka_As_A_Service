package com.probsjustin.KAAS;

import java.util.concurrent.Callable;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;


public class callable_kafkaConsumer implements Callable<String> {
	String kafkaHost; 
	String kafkaMessage; 
	String kafkaTopic; 
	logger_internal instance_logger_internal = new logger_internal(); 

	callable_kafkaConsumer(String func_kafkaHost, String func_kafkaMessage, String func_kafkaTopic){
		instance_logger_internal.debug("Creating Instance of callable_kafkaConsumer");
		this.kafkaHost = func_kafkaHost; 
		this.kafkaMessage = func_kafkaMessage; 
		this.kafkaTopic = func_kafkaTopic; 
	}
	
	@Override
	public String call() throws Exception{
		instance_logger_internal.debug("Attempting to run an instance of callable_kafkaConsumer");

		Properties properties = new Properties();
		properties.put("bootstrap.servers", String.valueOf(this.kafkaHost));
		properties.put("key.serializer", StringSerializer.class.getName());
		properties.put("value.serializer", StringSerializer.class.getName());

	     // Create the consumer using props.
	     final Consumer<Long, String> kafka_consumer_instance = new KafkaConsumer<>(properties);
	
	     // Subscribe to the topic.
	     kafka_consumer_instance.subscribe(Collections.singletonList(this.kafkaTopic));
	     instance_logger_internal.debug("Fisned an instance of callable_kafkaConsumer");
	     return kafka_consumer_instance.toString();

	 }
	 
}
