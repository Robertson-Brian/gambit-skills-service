package com.revature.gambit.skill.messaging;

import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

	@KafkaListener(topics = "${spring.kafka.topic.skill}")
	public void recieve(String payload) {

		System.out.println("recieved payload =" + payload);

	}

}
