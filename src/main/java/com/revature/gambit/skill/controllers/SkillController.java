package com.revature.gambit.skill.controllers;


import com.revature.gambit.skill.beans.Skill;
import javax.validation.Valid;

import com.revature.gambit.skill.services.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/skill")
    public ResponseEntity<Iterable<Skill>> findAll(){
        return new ResponseEntity<Iterable<Skill>>(skillService.findAllSkill(), HttpStatus.OK);
    }
    
    @GetMapping("/skill/active")
    public ResponseEntity<Iterable<Skill>> findActive(){
    	return new ResponseEntity<Iterable<Skill>>(skillService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping("/skill/name/{name}")
    public ResponseEntity<Skill> findByName(@PathVariable String name) {
    	return new ResponseEntity<Skill>(skillService.findByName(name), HttpStatus.OK);
    }
    
    @GetMapping("/skill/{id}")
    public ResponseEntity<Skill> findById(@PathVariable int id) {
    	return new ResponseEntity<Skill>(skillService.findById(id), HttpStatus.OK);
    }
    
    @PutMapping("/skill/{id}")
    public ResponseEntity<Skill> update(@PathVariable int id, @RequestBody Skill updatedSkill) {
    	if(id == updatedSkill.getSkillID()) {
    		return new ResponseEntity<Skill>(skillService.saveSkill(updatedSkill), HttpStatus.ACCEPTED);
    	} else {
    		return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
    	}
    }

}
