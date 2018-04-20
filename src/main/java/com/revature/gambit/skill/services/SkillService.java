package com.revature.gambit.skill.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;

@Service
@Configuration
public class SkillService implements ISkillService {

	@Autowired
	private SkillRepository skillRepository;

	public Skill create(Skill skill) {
		return this.skillRepository.save(skill);
	}

	public Iterable<Skill> findAll() {
		return this.skillRepository.findAll();
	}

	public Skill findById(int id) {
		return this.skillRepository.findBySkillID(id);
	}

	@Override
	public void deleteSkillViaId(int id) {

		skillRepository.delete(findById(id));

	}

	@Override
	public void deleteSkillViaName(int id) {
		// TODO Auto-generated method stub

		skillRepository.delete(findById(id)); //change to take a name instead of an int

	}


}
