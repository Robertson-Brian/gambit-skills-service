package com.revature.gambit.skill.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.services.SkillServiceImpl;


/**
 * Controller that will handle requests for the skill service.
 */
@RestController
public class SkillController {


	/**
	 * Service that contains all the business logic (methods) to be executed for
	 * this controller based on the request type.
	 */
	@Autowired
	private SkillServiceImpl skillService;

    @GetMapping("/skill/name/{name}")
    public ResponseEntity<Skill> findByName(@PathVariable String name) {
    	return new ResponseEntity<Skill>(skillService.findByName(name), HttpStatus.OK);
    }
    
    @GetMapping("/skill/{id}")
    public ResponseEntity<Skill> findById(@PathVariable int id) {
    	return new ResponseEntity<Skill>(skillService.findById(id), HttpStatus.OK);
    }
    	
	/**
	 * Hard delete by name here for convenience. You might need to comment this out avoid ambiguous mapping exceptions. 
	 * @param name
	 * @return
	 */
    @DeleteMapping("/skill/name/{name}")
    public ResponseEntity<Void> deleteBySkillName(@PathVariable String name) {
    		skillService.deleteBySkillName(name);
    		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
    
    /**
	 * Hard delete by id, the default deleting mechanism. 
	 * @param name
	 * @return
	 */
    @DeleteMapping("/skill/{id}")
    public ResponseEntity<Void> deleteBySkillId(@PathVariable int id) {
    		skillService.deleteBySkillID(id);
    		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}
