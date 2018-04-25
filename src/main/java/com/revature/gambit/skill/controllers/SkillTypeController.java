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

import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.services.SkillTypeServiceImpl;

/**
 * Controller that will handle requests for the skill type service.
 */
@RestController
public class SkillTypeController {

    @Autowired
    private SkillTypeServiceImpl skillTypeServiceImpl;

	@GetMapping("/skilltype")
	public ResponseEntity<Iterable<SkillType>> findAll() {
		ResponseEntity<Iterable<SkillType>> re = new ResponseEntity<Iterable<SkillType>>(skillTypeServiceImpl.findByAll(), HttpStatus.OK);
		System.out.println(re);
		return re;
	}

	@GetMapping("/skilltype/{name}")
	public ResponseEntity<SkillType> findSkill(@PathVariable String name) {
		try {
			SkillType skillType = skillTypeServiceImpl.findBySkillTypeName(java.net.URLDecoder.decode(name, "UTF-8"));
			if (skillType == null) {
				return new ResponseEntity<SkillType>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<SkillType>(
						skillTypeServiceImpl.findBySkillTypeName(java.net.URLDecoder.decode(name, "UTF-8")),
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
           boolean successful = skillTypeServiceImpl.update(skillType,java.net.URLDecoder.decode(name, "UTF-8"));
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

   @DeleteMapping("/skilltype/{name}")
   public ResponseEntity<Void> deleteSkillTypeByName(@PathVariable String name) {
	   skillTypeServiceImpl.deleteBySkillTypeName(name);
   		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
   }

    /**
     * Handles incoming POST request that adds a new skill type to the DB.
     *
     * @param skillType
     *            Incoming data fields will be mapped into this SkillType object.
     * @return HTTP status code 201 (CREATED)
     */
    @PostMapping("/skillType")
    public ResponseEntity<SkillType> create(@Valid @RequestBody SkillType skillType) {
        return new ResponseEntity<>(skillTypeServiceImpl.create(skillType),HttpStatus.CREATED);
    }

}
