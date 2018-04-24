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

@RestController
public class SkillController {

	@Autowired
	private SkillService skillService;

	@Autowired
	private ISkillService iskillService;

	@PostMapping("/skill")
	public ResponseEntity<Void> create(@Valid @RequestBody Skill skill) {
		this.skillService.create(skill);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/skill")
	public ResponseEntity<Iterable<Skill>> findAll() {
		return new ResponseEntity<Iterable<Skill>>(this.skillService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/skill/active")
	public Iterable<Skill> findActive() {
		return this.skillService.findAllActive();
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Skill updatedSkill) {
		this.skillService.saveSkill(updatedSkill);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	/**
	 * Hard delete here sole for convenience. Use update to do soft deletes. 
	 * @param name
	 * @return
	 */
    @DeleteMapping("/skill/{name}")
    public ResponseEntity<Void> deleteSkillofName(@PathVariable String name) {
    		skillService.deleteSkillViaName(name);
    		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

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
