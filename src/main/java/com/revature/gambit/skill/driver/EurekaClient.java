package com.revature.gambit.skill.driver;

import org.springframework.cloud.openfeign.FeignClient;

import feign.RequestLine;

/**
 * TODO Service we are connecting to.
 * 
 * @author Delano
 *
 */
@FeignClient("")
public interface EurekaClient {

	@RequestLine("GET")
	public String getTestMessage();

}
