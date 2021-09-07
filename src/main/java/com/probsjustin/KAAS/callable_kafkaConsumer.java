package com.probsjustin.KAAS;

import java.util.concurrent.Callable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringSerializer;


public class callable_kafkaConsumer implements Callable<String> {
	String kafkaHost; 
	String kafkaMessage; 
	String kafkaTopic; 
    Map<String, Object> data = new HashMap<>();
	logger_internal instance_logger_internal = new logger_internal(); 
	Consumer<String,String> kafka_consumer_instance = null; 

	callable_kafkaConsumer(String func_kafkaHost, String func_kafkaMessage, String func_kafkaTopic){
		instance_logger_internal.debug("Creating Instance of callable_kafkaConsumer");
		this.kafkaHost = func_kafkaHost; 
		this.kafkaMessage = func_kafkaMessage; 
		this.kafkaTopic = func_kafkaTopic; 

		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("group.id", "test");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");  
		properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("max.poll.records", 1);
		

	     // Create the consumer using props.
	    this.kafka_consumer_instance = new KafkaConsumer<>(properties);
	}
	
	@Override
	public String call() throws Exception{
		instance_logger_internal.debug("Attempting to run an instance of callable_kafkaConsumer");

	
	     // Subscribe to the topic.
	     kafka_consumer_instance.subscribe(Collections.singletonList(this.kafkaTopic));
	     try {
	    	 while (true) {
	    	        ConsumerRecords<String, String> records = kafka_consumer_instance.poll(Long.MAX_VALUE);
	    	        for (ConsumerRecord<String, String> record : records) {

	    	          data.put("partition", record.partition());
	    	          data.put("offset", record.offset());
	    	          data.put("value", record.value());
	    	          instance_logger_internal.debug("DATA:" + data.toString());
	    	        }
	    	        break;
	    	      }
	     } catch (WakeupException e) {
	         // ignore for shutdown 
	     } finally {
	    	 kafka_consumer_instance.close();
	     }
	     
	     instance_logger_internal.debug("Finished an instance of callable_kafkaConsumer");
	     instance_logger_internal.debug("DATA:" + data.toString());
	     return data.toString();

	 }
	 
}
