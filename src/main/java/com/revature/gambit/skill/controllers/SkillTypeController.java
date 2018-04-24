package com.revature.gambit.skill.controllers;

import com.revature.gambit.skill.beans.SkillType;

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

import com.revature.gambit.skill.services.ISkillTypeService;
import com.revature.gambit.skill.services.SkillTypeService;

/**
 * Controller that will handle requests for the skill type service.
 */
@RestController
public class SkillTypeController {

	/**
	 * Service that contains all the business logic (methods) to be executed for
	 * this controller based on the request type.
	 */
	@Autowired
	private SkillTypeService skillTypeService;

	/**
	 * Interface that contains all CRUD methods for skill type.
	 */
	@Autowired
	private ISkillTypeService iskillTypeService;

	/**
	 * Handles incoming POST request that adds a new skill type to the DB.
	 * 
	 * @param skillType
	 *            Incoming data fields will be mapped into this SkillType object.
	 * @return HTTP status code 201 (CREATED)
	 */
	@PostMapping("/skilltype")
	public ResponseEntity<Void> create(@Valid @RequestBody SkillType skillType) {
		this.skillTypeService.create(skillType);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Handles incoming GET request that grabs all the skill types.
	 * 
	 * @return Iterable object containing all the skill types retrieved along with
	 *         HTTP status code 200 (OK)
	 */
	@GetMapping("/skilltype")
	public ResponseEntity<Iterable<SkillType>> findAll() {
		ResponseEntity<Iterable<SkillType>> re = new ResponseEntity<Iterable<SkillType>>(this.skillTypeService.findByAll(), HttpStatus.OK);
		System.out.println(re);
		return re;
	}

	/**
	 * Handles incoming GET request that grabs a specific skill type.
	 * 
	 * @param name
	 *            Name of the skill type that needs to be retrieved.
	 * @return Skill type along with HTTP status code 200 (OK) if found, HTTP status
	 *         code 404 (NOT FOUND) otherwise.
	 */
	@GetMapping("/skilltype/{name}")
	public ResponseEntity<SkillType> findSkill(@PathVariable String name) {
		try {
			SkillType skillType = this.skillTypeService.findBySkillTypeName(java.net.URLDecoder.decode(name, "UTF-8"));
			if (skillType == null) {
				return new ResponseEntity<SkillType>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<SkillType>(
						this.skillTypeService.findBySkillTypeName(java.net.URLDecoder.decode(name, "UTF-8")),
						HttpStatus.OK);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SkillType>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles incoming PUT request that will update an existing skill type with a
	 * new one.
	 * 
	 * @param skillType
	 *            Existing skill type will be updated with this one.
	 * @param name
	 *            Name of the skill type to update.
	 * @return HTTP status code 202 (ACCEPTED) if success, HTTP status code 404 (NOT
	 *         FOUND) otherwise.
	 */
	@PutMapping(value = "/skilltype/{name}")
	public ResponseEntity<Boolean> update(@Valid @RequestBody SkillType skillType, @PathVariable String name) {
		try {
			boolean successful = this.skillTypeService.update(skillType, java.net.URLDecoder.decode(name, "UTF-8"));
			if (successful == true) {
				return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles incoming DELETE request that will remove a skill type from the
	 * system.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 * @return HTTP status code 202 (ACCEPTED).
	 */
   @DeleteMapping("/skilltype/{name}")
   public ResponseEntity<Void> deleteSkillTypeByName(@PathVariable String name) {
   		skillTypeService.deleteBySkillTypeName(name);
   		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
   }



}
