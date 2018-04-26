package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.Skill;

public interface SkillService {

	Skill findById(int id);
	
	Iterable<Skill> findAllSkill();

	Skill findByName(String name);
		
	boolean deleteBySkillName(String name);

	boolean deleteBySkillID(int id);

}
