package com.probsjustin.KAAS;

import java.util.concurrent.Callable;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class callable_kafkaProducer implements Callable<String> {
	String kafkaHost; 
	String kafkaMessage; 
	String kafkaTopic; 
	
	callable_kafkaProducer(String func_kafkaHost, String func_kafkaMessage, String func_kafkaTopic){
		this.kafkaHost = func_kafkaHost; 
		this.kafkaMessage = func_kafkaMessage; 
		this.kafkaTopic = func_kafkaTopic; 
	}
	

	
	@Override
	public String call() throws Exception {
		 Properties properties = new Properties();
		 properties.put("bootstrap.servers", String.valueOf(this.kafkaHost) + ":9092");
		 properties.put("key.serializer", StringSerializer.class.getName());
		 properties.put("value.serializer", StringSerializer.class.getName());
		 
		 KafkaProducer kafkaProducer = new KafkaProducer(properties);
		 ProducerRecord<String, String> callable_record = new ProducerRecord(this.kafkaTopic, this.kafkaMessage);
		 
		 callable_record.headers().add("FEED_NAME", this.kafkaTopic.getBytes(StandardCharsets.UTF_8));
		
		 kafkaProducer.send(callable_record);
		 kafkaProducer.flush();
		 kafkaProducer.close();
		return kafkaHost;
	}

}
