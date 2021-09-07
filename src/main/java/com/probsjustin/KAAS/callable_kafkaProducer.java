package com.probsjustin.KAAS;

import java.util.concurrent.Callable;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


public class callable_kafkaProducer implements Callable<String> {
	String kafkaHost; 
	String kafkaMessage; 
	String kafkaTopic; 
	Properties properties = new Properties();
	Producer<String, String> instance_kafkaProducer = null;
	ProducerRecord<String, String> callable_record = null;
	logger_internal instance_logger_internal = new logger_internal(); 

	
	callable_kafkaProducer(String func_kafkaHost, String func_kafkaMessage, String func_kafkaTopic){
		instance_logger_internal.debug("Creating Instance of callable_kafkaProducer");
		this.kafkaHost = func_kafkaHost; 
		this.kafkaMessage = func_kafkaMessage; 
		this.kafkaTopic = func_kafkaTopic; 
		instance_logger_internal.debug("Added properties to the prop variable");
		instantiateKafkaProducer(); 
	}
	
	void instantiateKafkaProducer() {
		instance_logger_internal.debug("Created instances of producer record and kafka producer.");
		this.instance_kafkaProducer = new KafkaProducer<String, String>(properties, new StringSerializer(), new StringSerializer());
		this.callable_record = new ProducerRecord<String,String>(this.kafkaTopic, this.kafkaMessage, this.kafkaMessage);
	}
	
	Properties createPropertiesFile(String func_host) {
		Properties returnProperties = new Properties(); 
		properties.put("bootstrap.servers", func_host);
		properties.put("acks", "all");
		properties.put("retries", 0);
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 1);
		properties.put("buffer.memory", 33554432);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return returnProperties;
	}
	
	void kafkaProducer_send_flush_close() {
		instance_kafkaProducer.send(callable_record);
		instance_kafkaProducer.flush();
		instance_kafkaProducer.close(); 
		instance_logger_internal.debug("Finished and Flushed an instance of callable_kafkaProducer");

	}
	
	@Override
	public String call() throws Exception {
		instance_logger_internal.debug("Attempting to run an instance of callable_kafkaProducer");
		instance_logger_internal.debug("HOST: " + this.kafkaHost);
		instance_logger_internal.debug("MESSAGE: " + this.kafkaMessage);
		instance_logger_internal.debug("TOPIC: " + this.kafkaTopic);

		try {
			kafkaProducer_send_flush_close(); 
		} catch(Exception e) {
			instance_logger_internal.debug(e.toString());
			return "false";
		}
		return kafkaHost;
	}

}
