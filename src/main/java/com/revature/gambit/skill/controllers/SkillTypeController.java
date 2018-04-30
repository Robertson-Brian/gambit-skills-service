package com.revature.gambit.skill.controllers;

import com.revature.gambit.skill.beans.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.gambit.skill.services.SkillTypeService;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * Controller that will handle requests for the skill type service.
 */
@RestController
public class SkillTypeController {

	/**
	 * Interface that contains all CRUD methods for skill type.
	 */
	@SuppressWarnings("unused")
	@Autowired
	private SkillTypeService skillTypeService;

	/**
	 * Handles incoming POST request that adds a new skill type to the DB.
	 *
	 * @param skillType
	 *            Incoming data fields will be mapped into this SkillType object.
	 * @return HTTP status code 201 (CREATED)
	 */
	@PostMapping("/skillType")
	public ResponseEntity<SkillType> create(@Valid @RequestBody SkillType skillType) {
		return new ResponseEntity<>(this.skillTypeService.create(skillType),HttpStatus.CREATED);
	}

	@DeleteMapping("/skilltype/name/{name}")
	public ResponseEntity<Void> deleteSkillTypeByName(@PathVariable String name) {
		SkillType skillType = skillTypeService.findBySkillTypeName(name);

		if (skillType != null) {
			skillType.setIsActive(false);
			this.skillTypeService.update(skillType);
		}
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}


	@DeleteMapping("/skilltype/{id}")
	public ResponseEntity<Void> deleteSkillTypeByName(@PathVariable int id) {
		SkillType skillType = this.skillTypeService.findBySkillTypeId(id);

		if (skillType != null) {
			skillType.setIsActive(false);
			this.skillTypeService.update(skillType);
		}

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	/**
	 * Handles incoming GET request that grabs all the skill types.
	 *
	 * @return Iterable object containing all the skill types retrieved along with
	 *         HTTP status code 200 (OK)
	 */
	@GetMapping("/skillType")
	public ResponseEntity<Iterable<SkillType>> findAll() {
		return new ResponseEntity<>(this.skillTypeService.findAll(), HttpStatus.OK);
	}

	/**
	 * Handles incoming GET request that grabs a specific skill type.
	 *
	 * @param id
	 *            Id of the skill type that needs to be retrieved.
	 * @return Skill type along with HTTP status code 200 (OK) if found, HTTP status
	 *         code 404 (NOT FOUND) otherwise.
	 */
	@GetMapping("/skillType/{id}")
	public ResponseEntity<SkillType> findSkill(@PathVariable int id) {

		SkillType skillType = this.skillTypeService.findBySkillTypeId(id);
		if (skillType == null) {
			return new ResponseEntity<SkillType>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<SkillType>(
					this.skillTypeService.findBySkillTypeId(id),
					HttpStatus.OK);
		}
	}

	/**
	 * Handles incoming GET request that grabs a specific skill type.
	 *
	 * @param name
	 *            Name of the skill type that needs to be retrieved.
	 * @return Skill type along with HTTP status code 200 (OK) if found, HTTP status
	 *         code 404 (NOT FOUND) otherwise.
	 */
	@GetMapping("/skillType/name/{name}")
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
	@PutMapping(value = "/skillType/name/{name}")
	public ResponseEntity<SkillType> update(@Valid @RequestBody SkillType skillType, @PathVariable String name) {
		try {
			if (java.net.URLDecoder.decode(name, "UTF-8").equals(skillType.getSkillTypeName())) {
				return new ResponseEntity<>(this.skillTypeService.update(skillType),HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles incoming PUT request that will update an existing skill type with a
	 * new one.
	 *
	 * @param skillType
	 *            Existing skill type will be updated with this one.
	 * @param id
	 *            Id of the skill type to update.
	 * @return HTTP status code 202 (ACCEPTED) if success, HTTP status code 404 (NOT
	 *         FOUND) otherwise.
	 */
	@PutMapping(value = "/skillType/{id}")
	public ResponseEntity<SkillType> update(@Valid @RequestBody SkillType skillType, @PathVariable int id) {

		if ( id == skillType.getSkillTypeId()) {
			return new ResponseEntity<>(this.skillTypeService.update(skillType),HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
