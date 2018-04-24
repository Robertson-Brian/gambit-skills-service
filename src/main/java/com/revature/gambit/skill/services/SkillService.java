package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Iterable<Skill> findAll() {
		return skillRepository.findAll();
	}

	public Iterable<Skill> findAllActive() {
		return skillRepository.findAllByIsActive(true);
	}

	public Skill findByName(String name) {
		return skillRepository.findBySkillName(name);
	}

	@Transactional
	public Skill saveSkill(Skill skill) {
		return skillRepository.saveAndFlush(skill);
	}
	
	@Override
	@Transactional
	public Skill deleteSoftly(String name) {
		Skill skill = findByName(name);
		return skillRepository.saveAndFlush(skill);
	}

	@Override
	@Transactional
	public boolean deleteSkillViaName(String name) {
		if (findByName(name) instanceof Skill) {
			skillRepository.delete(findByName(name));
			return true;
		}
		return false;
	}

}