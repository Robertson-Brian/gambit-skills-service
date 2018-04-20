package com.revature.gambit.skill.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;

@Service
@Configuration
public class SkillService implements ISkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Transactional
	public Skill create(Skill skill) {
		return skillRepository.save(skill);
	}
	
	public Skill findById(int id) {
		return this.skillRepository.findBySkillID(id);
	}

	public Iterable<Skill> findAll() {
		return skillRepository.findAll();
	}
	
	public Iterable<Skill> findAllActive(){
		return skillRepository.findAllByIsActive(true);
	}
	
	public Skill findByName(String name) {
		return skillRepository.findBySkillName(name);
	}

	@Transactional
	public Skill saveSkill(Skill skill) {
		return skillRepository.save(skill);
	}

	@Transactional
	public void deleteSkillViaName(String name) {
		skillRepository.delete(findByName(name));
	}

}