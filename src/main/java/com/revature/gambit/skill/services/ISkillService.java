package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.Skill;

public interface ISkillService {

	public Skill create(Skill skill);

	public Iterable<Skill> findAll();

	public Iterable<Skill> findAllActive();

	public Skill findByName(String name);

	public Skill saveSkill(Skill skill);
	
	public Skill deleteSoftly(String name);

	public boolean deleteSkillViaName(String name);

}
