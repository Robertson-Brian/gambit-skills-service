package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.Skill;

public interface SkillService {

	Skill findById(int id);

	Iterable<Skill> findAllActive();

	Iterable<Skill> findAllSkill();

	Skill findByName(String name);
	
	Skill saveSkill(Skill skill);
	
	boolean deleteBySkillName(int id);

	boolean deleteBySkillID(int id);

}
