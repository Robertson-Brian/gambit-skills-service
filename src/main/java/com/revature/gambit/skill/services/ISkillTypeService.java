package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.SkillType;

public interface ISkillTypeService {
	
	public SkillType create(SkillType skillType);

	public SkillType findBySkillTypeName(String name);

	public Iterable<SkillType> findByAll();

	public boolean update(SkillType updatedSkillType, String name);

	public boolean deleteBySkillTypeName(String name);
	
}
