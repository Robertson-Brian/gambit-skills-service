package com.revature.gambit.skill.messaging;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Create send functions to send based on insert, update, or delete also add a
 * UUID to the front of the message so the receiver if it was the one that sent it.
 * 
 * @author Chris Snyder
 */
public class Sender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	UUIDService UUIDService;

	/**
	 * Sends a message to insert in another instance.
	 * 
	 * @param topic - Which topic it is sending to
	 * @param payload - Should be a json object no spaces in it
	 */
	public void sendInsert(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("batch.register.t", payload);
	}

	public void sendUUID(String payload) {
		kafkaTemplate.send("batch.uuid.t", payload);
	}

	/**
	 * Sends a message to update in another instance.
	 * 
	 * @param topic - Which topic it is sending to
	 * @param payload - Should be a json object no spaces in it
	 */
	public void sendUpdate(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("batch.update.t", payload);
	}

	/**
	 * Sends a message to delete in another instance.
	 * 
	 * @param topic - Which topic it is sending to
	 * @param payload - Should be a json object no spaces in it
	 */
	public void sendDelete(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("batch.delete.t", payload);
	}
}