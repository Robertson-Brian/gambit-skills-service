package com.revature.gambit.skill.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.services.ISkillService;
import com.revature.gambit.skill.services.ISkillTypeService;
import com.revature.gambit.skill.util.LoggingUtil;

public class Receiver {

	@Autowired
	UUIDService UUIDService;

	@Autowired
	ISkillService skillService;
	@Autowired
	ISkillTypeService skillTypeService;

	/**
	 * 
	 * @param payload
	 */
	@KafkaListener(topics = "${spring.kafka.topic.skillType.uuid}")
	public void receiveUUID(String payload) {
		UUIDService.addUUIDToList(payload);
	}

	/**
	 * Also uses unique id for each Microservice instance to check if it was the one
	 * that sent it. Is used for inserts.
	 * 
	 * @param payload
	 *            json object to update another instance database
	 */
	@KafkaListener(topics = "${spring.kafka.topic.skillType.create}")
	public void receiveInsertSkill(String payload) {
		ObjectMapper om = new ObjectMapper();
		LoggingUtil.logInfo(payload);

		String[] a = payload.split(" ", 2);
		if (!a[0].equals(UUIDService.getServiceInstanceIdentifier().toString())) {
			LoggingUtil.logInfo(a[1]);
			try {
				Skill skill = om.readValue(a[1], Skill.class);
				skillService.create(skill);
			} catch (Exception e) {
				LoggingUtil.logWarn(e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Also uses unique id for each Microservice instance to check if it was the one
	 * that sent it. Is used for deletes.
	 * 
	 * @param payload
	 *            json object to update another instance database
	 */
	@KafkaListener(topics = "${spring.kafka.topic.skillType.delete}")
	public void receiveDeleteSkill(String payload) {
		ObjectMapper om = new ObjectMapper();
		LoggingUtil.logInfo(payload);

		String[] a = payload.split(" ", 2);

		if (!a[0].equals(UUIDService.getServiceInstanceIdentifier().toString())) {
			LoggingUtil.logInfo(a[1]);
			try {
				String name = om.readValue(a[1], String.class);
				skillService.deleteSkillViaName(name);
			} catch (Exception e) {
				LoggingUtil.logWarn(e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Also uses unique id for each Microservice instance to check if it was the one
	 * that sent it. Is used for inserts.
	 * 
	 * @param payload
	 *            json object to update another instance database
	 */
	@KafkaListener(topics = "${spring.kafka.topic.skillType.register}")
	public void receiveInsertSkillType(String payload) {
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
	@KafkaListener(topics = "${spring.kafka.topic.skillType.update}")
	public void receiveUpdateSkillType(String payload) {
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
	 * Also uses unique id for each Microservice instance to check if it was the one
	 * that sent it. Is used for deletes.
	 * 
	 * @param payload
	 *            json object to update another instance database
	 */
	@KafkaListener(topics = "${spring.kafka.topic.skillType.delete}")
	public void receiveDeleteSkillType(String payload) {
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