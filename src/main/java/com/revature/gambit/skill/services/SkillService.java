package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill create(Skill skill) { return this.skillRepository.save(skill); }

    public Skill findById(int id) { 
    	return this.skillRepository.findBySkillID(id); 
    }
    
	public Skill findByName(String name) {
		return skillRepository.findBySkillName(name);
	}
	
	public Iterable<Skill> findAllSkill(){
		return skillRepository.findAll();
	}
	
	public Iterable<Skill> findAllActive(){
		return skillRepository.findAllByIsActive(true);
	}

    @Transactional
	public Skill saveSkill(Skill skill) {
		return skillRepository.saveAndFlush(skill);
	}
}
