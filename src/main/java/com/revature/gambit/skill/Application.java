package com.revature.gambit.skill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Driver class responsible for running the application.
 */
@SpringBootApplication
public class Application {

	/**
	 * Runs the Skill Service.
	 * 
	 * @param args
	 *            Runtime arguments (not applicable in this project).
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
