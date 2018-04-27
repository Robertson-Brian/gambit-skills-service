package com.revature.gambit.skill.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.services.SkillService;

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
	 * Handles incoming POST request that adds a new skill to the DB.
	 *
	 * @param skill
	 *            Incoming data fields will be mapped into this object.
	 * @return HTTP status code 201 (CREATED)
	 */
	@PostMapping("/skill")
	public ResponseEntity<Skill> create(@Valid @RequestBody Skill skill) {
		return new ResponseEntity<>(this.skillService.create(skill),HttpStatus.CREATED);
	}

	/**
	 * Handles incoming GET request that grabs all the skills.
	 *
	 * @return List object containing all the skills retrieved along with HTTP
	 *         status code 200 (OK)
	 * @return HTTP status code 204 (NO_CONTENT) if no skills exist.
	 */
	@GetMapping("/skill")
	public ResponseEntity<List<Skill>> findAll() {
		List<Skill> skills = (List<Skill>) this.skillService.findAll();

		if (skills.isEmpty()) {
			return new ResponseEntity<List<Skill>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Skill>>(skills, HttpStatus.OK);
		}
	}

	@GetMapping("/skill/name/{name}")
	public ResponseEntity<Skill> findByName(@PathVariable String name) {
		Skill skill = this.skillService.findBySkillName(name);

		if (skill == null) {
			return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Skill>(this.skillService.findBySkillName(name), HttpStatus.OK);
		}
	}

	@GetMapping("/skill/{id}")
	public ResponseEntity<Skill> findById(@PathVariable int id) {
		Skill skill = this.skillService.findBySkillID(id);

		if (skill == null) {
			return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Skill>(this.skillService.findBySkillID(id), HttpStatus.OK);
		}
	}


}
