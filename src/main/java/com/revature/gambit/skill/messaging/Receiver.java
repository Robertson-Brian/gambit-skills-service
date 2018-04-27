package com.revature.gambit.skill.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.services.ISkillTypeService;
import com.revature.gambit.skill.util.LoggingUtil;

public class Receiver {

	@Autowired
	UUIDService UUIDService;

	@Autowired
	ISkillTypeService skillTypeService;

	/**
	 * Also uses unique id for each Microservice instance to check if it was the one
	 * that sent it. Is used for inserts.
	 * 
	 * @param payload
	 *            json object to update another instance database
	 */
	@KafkaListener(topics = "${spring.kafka.topic.batch.register}")
	public void recieveInsert(String payload) {
		ObjectMapper om = new ObjectMapper();
		LoggingUtil.logInfo(payload);

		String[] a = payload.split(" ", 2);
		if (!a[0].equals(UUIDService.getServiceInstanceIdentifier().toString())) {
			LoggingUtil.logInfo(a[1]);
			try {
				SkillType skillType = om.readValue(a[1], SkillType.class);
				skillTypeService.create(skillType);
			} catch (Exception e) {
				LoggingUtil.logWarn(e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Uses unique id for each Microservice instance to check if it was the one that
	 * sent it. Is used for updates.
	 * 
	 * @param payload
	 *            json object to update another instance database
	 */
	@KafkaListener(topics = "${spring.kafka.topic.batch.update}")
	public void recieveUpdate(String payload) {
		ObjectMapper om = new ObjectMapper();
		LoggingUtil.logInfo(payload);

		String[] a = payload.split(" ", 2);

		if (!a[0].equals(UUIDService.getServiceInstanceIdentifier().toString())) {
			LoggingUtil.logInfo(a[1]);
			try {
				SkillType skillType = om.readValue(a[1], SkillType.class);
				skillTypeService.update(skillType, skillType.getSkillTypeName());
			} catch (Exception e) {
				LoggingUtil.logWarn(e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param payload
	 */
	@KafkaListener(topics = "${spring.kafka.topic.batch.uuid}")
	public void recieveUUID(String payload) {
		UUIDService.addUUIDToList(payload);
	}

	/**
	 * Also uses unique id for each Microservice instance to check if it was the one
	 * that sent it. Is used for deletes.
	 * 
	 * @param payload
	 *            json object to update another instance database
	 */
	@KafkaListener(topics = "${spring.kafka.topic.batch.delete}")
	public void recieveDelete(String payload) {
		ObjectMapper om = new ObjectMapper();
		LoggingUtil.logInfo(payload);

		String[] a = payload.split(" ", 2);

		if (!a[0].equals(UUIDService.getServiceInstanceIdentifier().toString())) {
			LoggingUtil.logInfo(a[1]);
			try {
				String name = om.readValue(a[1], String.class);
				skillTypeService.deleteBySkillTypeName(name);
			} catch (Exception e) {
				LoggingUtil.logWarn(e.toString());
				e.printStackTrace();
			}
		}
	}

}