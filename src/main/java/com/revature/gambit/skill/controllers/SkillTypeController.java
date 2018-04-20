package com.revature.gambit.skill.controllers;

import com.revature.gambit.skill.beans.SkillType;

import com.revature.gambit.skill.services.SkillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
public class SkillTypeController {

	@Autowired
	private SkillTypeService skillTypeService;

	@PostMapping("/skilltype")
	public ResponseEntity<Void> create(@Valid @RequestBody SkillType skillType) {
		this.skillTypeService.create(skillType);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/skilltype")
	public ResponseEntity<Iterable<SkillType>> findAll() {
		return new ResponseEntity<Iterable<SkillType>>(this.skillTypeService.findByAll(), HttpStatus.OK);
	}

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

}
