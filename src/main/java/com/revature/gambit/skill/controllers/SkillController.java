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
import com.revature.gambit.skill.services.ISkillService;
import com.revature.gambit.skill.services.SkillService;

import java.net.*;

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
	private SkillService skillService;

	/**
	 * Interface that contains all CRUD methods for skill.
	 */
	@Autowired
	private ISkillService iskillService;

	/**
	 * Handles incoming POST request that adds a new skill to the DB.
	 * 
	 * @param skill
	 *            Incoming data fields will be mapped into this object.
	 * @return HTTP status code 201 (CREATED)
	 */
	@PostMapping("/skill")
	public ResponseEntity<Void> create(@Valid @RequestBody Skill skill) {
		this.skillService.create(skill);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Handles incoming GET request that grabs all the skills.
	 * 
	 * @return Iterable object containing all the skills retrieved along with HTTP
	 *         status code 200 (OK)
	 */
	@GetMapping("/skill")
	public ResponseEntity<Iterable<Skill>> findAll() {
		return new ResponseEntity<Iterable<Skill>>(this.skillService.findAll(), HttpStatus.OK);
	}

	/**
	 * Handles incoming GET request that grabs all the skills that are currently
	 * active.
	 * 
	 * @return Iterable interface containing all the skills retrieved.
	 */
	@GetMapping("/skill/active")
	public Iterable<Skill> findActive() {
		return this.skillService.findAllActive();
	}

	/**
	 * Handles incoming PUT request that will update an existing Skill with a new
	 * one.
	 * 
	 * @param updatedSkill
	 *            Existing skill will be updated with this one.
	 * @return HTTP status code 202 (ACCEPTED).
	 */
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Skill updatedSkill) {
		this.skillService.saveSkill(updatedSkill);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	/**
	 * Handles incoming DELETE request that will remove a skill from
	 * the system.
	 * 
	 * @param name
	 *            Name of the skill to be deleted.
	 * @return HTTP status code 202 (ACCEPTED).
	 */
	@DeleteMapping("/skill/{name}")
	public ResponseEntity<Void> deleteSkillofName(@PathVariable String name) {
		iskillService.deleteSkillViaName(name);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	/**
	 * Handles incoming GET request that grabs a specific skill.
	 * 
	 * @param name
	 *            Name of the skill that needs to be retrieved.
	 * @return Skill along with HTTP status code 200 (OK) if found, HTTP status code
	 *         404 (NOT FOUND) otherwise.
	 */
	@GetMapping("/skill/{name}")
	public ResponseEntity<Skill> findByName(@PathVariable String name) {
		try {
			return new ResponseEntity<Skill>(this.skillService.findByName(java.net.URLDecoder.decode(name, "UTF-8")),
					HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
	}

}
