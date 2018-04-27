package com.revature.gambit.skill.controllers;

import com.revature.gambit.skill.beans.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gambit.skill.services.SkillTypeService;

import java.util.List;

import javax.validation.Valid;

/**
 * Controller that will handle requests for the skill type service.
 */
@RestController
public class SkillTypeController {

	/**
	 * Interface that contains all CRUD methods for skill type.
	 */
	@Autowired
	private SkillTypeService skillTypeService;

	/**
	 * Handles incoming POST request that adds a new skill type to the DB.
	 *
	 * @param skillType
	 *            Incoming data fields will be mapped into this SkillType object.
	 * @return HTTP status code 201 (CREATED) if the skillType is successfully created.
	 */
	@PostMapping("/skillType")
	public ResponseEntity<SkillType> create(@Valid @RequestBody SkillType skillType) {
		return new ResponseEntity<>(this.skillTypeService.create(skillType), HttpStatus.CREATED);
	}

	/**
	 * Handles incoming GET request that finds all skillTypes
	 *
	 * @return HTTP status code 201 (CREATED) if any skillTypes exist
	 * @Return HTTP status code 204 (NO_CONTENT) if no skillTypes exist
	 */
	@GetMapping("/skillType")
	public ResponseEntity<List<SkillType>> findAll() {
		List<SkillType> skillTypes = (List<SkillType>) this.skillTypeService.findAll();

		if (skillTypes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<SkillType>>(skillTypes, HttpStatus.OK); 
		}
	}

	/**
	 * Handles incoming GET request that finds a skillType based on name.
	 *
	 * @param name
	 *            The name of the skillType.
	 * @return HTTP status code 201 (CREATED) if the skillType exists
	 * @Return HTTP status code 404 (NOT_FOUND) if the skillType does not exist
	 */
	@GetMapping("/skillType/name/{name}")
	public ResponseEntity<SkillType> findBySkillTypeName(@Valid @PathVariable String name ) {
		SkillType skillType = this.skillTypeService.findBySkillTypeName(name);

		if (skillType == null) {
			return new ResponseEntity<SkillType>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<SkillType>(skillType, HttpStatus.OK);
		} 
	}

	/**
	 * Handles incoming Get request that finds a skillType based on ID.
	 *
	 * @param id
	 *            The id of the skillType.
	 * @return HTTP status code 201 (CREATED) if the skillType exists
	 * @Return HTTP status code 404 (NOT_FOUND) if the skillType does not exist
	 */
	@GetMapping("/skillType/{id}")
	public ResponseEntity<SkillType> findBySkillTypeID(@Valid @PathVariable int id) {
		SkillType skillType = this.skillTypeService.findBySkillTypeID(id);
		
		if (skillType == null) {
			return new ResponseEntity<SkillType>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<SkillType>(skillType, HttpStatus.OK);
		}
	}

}
