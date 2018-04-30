package com.revature.gambit.skill.messaging;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Create send functions to send based on insert, update, or delete also add a
 * UUID to the front of the message so the receiver if it was the one that sent
 * it.
 * 
 * @author Chris Snyder
 */
public class Sender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	UUIDService UUIDService;

	/**
	 * Sends a UUID to Kafka to make sure it is unique.
	 * 
	 * @param payload
	 *            The uuid to send.
	 */
	public void sendUUID(String payload) {
		kafkaTemplate.send("skill.uuid.t", payload);
	}

	/**
	 * Sends a message to insert in another instance of Skill.
	 * 
	 * @param topic
	 *            - Which topic it is sending to
	 * @param payload
	 *            - Should be a json object no spaces in it
	 */
	public void sendInsertSkill(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("skill.register.t", payload);
	}

	/**
	 * Sends a message to update in another instance of Skill.
	 * 
	 * @param topic
	 *            - Which topic it is sending to
	 * @param payload
	 *            - Should be a json object no spaces in it
	 */
	public void sendUpdateSkill(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("skill.update.t", payload);
	}

	/**
	 * Sends a message to delete in another instance of Skill.
	 * 
	 * @param topic
	 *            - Which topic it is sending to
	 * @param payload
	 *            - Should be a json object no spaces in it
	 */
	public void sendDeleteSkill(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("skill.delete.t", payload);
	}

	/**
	 * Sends a message to insert in another instance of SkillType.
	 * 
	 * @param topic
	 *            - Which topic it is sending to
	 * @param payload
	 *            - Should be a json object no spaces in it
	 */
	public void sendInsertSkillType(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("skillType.register.t", payload);
	}

	/**
	 * Sends a message to update in another instance of SkillType.
	 * 
	 * @param topic
	 *            - Which topic it is sending to
	 * @param payload
	 *            - Should be a json object no spaces in it
	 */
	public void sendUpdateSkillType(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("skillType.update.t", payload);
	}

	/**
	 * Sends a message to delete in another instance of SkillType.
	 * 
	 * @param topic
	 *            - Which topic it is sending to
	 * @param payload
	 *            - Should be a json object no spaces in it
	 */
	public void sendDeleteSkillType(String payload) {
		UUIDService.checkuuid();
		payload = UUIDService.getServiceInstanceIdentifier().toString() + " " + payload;

		kafkaTemplate.send("skillType.delete.t", payload);
	}
}