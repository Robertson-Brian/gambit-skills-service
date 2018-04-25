package com.revature.gambit.skill.controllers;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;

import javax.validation.Valid;

import com.revature.gambit.skill.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/skill")
    public Skill create(@Valid @RequestBody Skill skill) { 
    	return this.skillService.create(skill); 
    }

    @GetMapping("/skill")
    public Iterable<Skill> findAll(){
        return this.skillService.findAllSkill();
    }
    
    @GetMapping("/skill/active")
    public Iterable<Skill> findActive(){
    	return this.skillService.findAllActive();
    }

    @GetMapping("/skill/name/{name}")
    public Skill findByName(@PathVariable String name) {
    	return skillService.findByName(name);
    }
    
    @GetMapping("/skill/{id}")
    public Skill findById(@PathVariable int id) {
    	return skillService.findById(id);
    }
    
    @PutMapping("/skill")
    public void update(@RequestBody Skill updatedSkill) {
        skillService.saveSkill(updatedSkill);
    }

}
