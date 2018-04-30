package com.revature.gambit.skill.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.revature.gambit.skill.messaging.Sender;

/**
 * Configuration that sets up a Kafka sender to be able to send to a Kafka
 * server.
 * 
 * @author Chris Snyder
 */
@Configuration
public class KafkaSenderConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	/**
	 * Creates the mapping that is going to be sent to Kafka.
	 * 
	 * @return A HashMap
	 */
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return props;
	}

	/**
	 * Creates a producerFactory for messages.
	 * 
	 * @return
	 */
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	/**
	 * Creates a new KafkaTemplate from the producerFactory.
	 * 
	 * @return a template of message going to be sent
	 */
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/**
	 * Creates the sender functions to be used (should have been created in the
	 * service)
	 * 
	 * @return an instance of sender
	 */
	@Bean
	public Sender sender() {
		return new Sender();
	}
}