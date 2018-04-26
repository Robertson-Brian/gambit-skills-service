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

@RestController
public class SkillController {

    @Autowired
    private SkillServiceImpl skillService;

    @PostMapping("/skill")
    public Skill create(@Valid @RequestBody Skill skill) { 
    	return this.skillService.create(skill); 
    }

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
    	if(id == updatedSkill.getSkillId()) {
    		return new ResponseEntity<Skill>(skillService.saveSkill(updatedSkill), HttpStatus.ACCEPTED);
    	} else {
    		return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
    	}
    }

}
