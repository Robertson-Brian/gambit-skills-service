package com.revature.gambit.skill.controllers;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	private SkillServiceImpl skillServiceImpl;


	/**
	 * Handles incoming POST request that adds a new skill to the DB.
	 *
	 * @param skill
	 *            Incoming data fields will be mapped into this object.
	 * @return HTTP status code 201 (CREATED)
	 */
	@PostMapping("/skill")
	public ResponseEntity<Skill> create(@Valid @RequestBody Skill skill) {
		return new ResponseEntity<>(skillServiceImpl.create(skill),HttpStatus.CREATED);
	}

	/**
	 * Handles incoming GET request that grabs all the skills.
	 *
	 * @return Iterable object containing all the skills retrieved along with HTTP
	 *         status code 200 (OK)
	 */
	@GetMapping("/skill")
	public ResponseEntity<Iterable<Skill>> findAll() {
		return new ResponseEntity<>(skillServiceImpl.findAll(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Skill updatedSkill) {
		skillServiceImpl.saveSkill(updatedSkill);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	/**
	 * Hard delete here sole for convenience. Use update to do soft deletes. 
	 * @param name
	 * @return
	 */
    @DeleteMapping("/skill/{name}")
    public ResponseEntity<Void> deleteSkillofName(@PathVariable String name) {
    	skillServiceImpl.deleteBySkillName(name);
    		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

	@GetMapping("/skill/{name}")
	public ResponseEntity<Skill> findByName(@PathVariable String name) {
		try {
			return new ResponseEntity<Skill>(skillServiceImpl.findByName(java.net.URLDecoder.decode(name, "UTF-8")),
					HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
	}


}
