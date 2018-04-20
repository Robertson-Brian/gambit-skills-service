package com.revature.gambit.skill.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.Feign;

@RestController
public class EurekaConfig {

public static int count = 0;
	
	
	@GetMapping("/")
	@HystrixCommand(fallbackMethod="myFallback")
	public String doGet(){
		
		if (count++>5){
			
			throw new RuntimeException();
			
		}
		
		return "ok";
		
	}
	
	private String myFallback(){
		
		return "whoooops";
		
	}
	
	/**
	 * Currently do not know our ports. Change port and directory appropriately
	 * @return
	 */
	
	@GetMapping("/testmessage")
	@HystrixCommand(fallbackMethod="myFallback")
	public String hello(){
		
		return Feign.builder().target(EurekaConfig.class, "http://localhost:10000/test").hello();
		
	}
}
