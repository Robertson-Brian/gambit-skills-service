package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.SkillType;

/**
 * API defining all the methods the skill type service will implement.
 */
public interface SkillTypeService {

	/**
	 * Retrieves a skill type based on its skill type name.
	 * 
	 * @param name
	 *            Name of the skill type to retrieve.
	 * @return Skill Type that was found.
	 */
	public SkillType findBySkillTypeName(String name);

	/**
	 * Retrieves all the skill types.
	 * 
	 * @return Iterable object containing all the skill types.
	 */
	public Iterable<SkillType> findByAll();


	/**
	 * Deletes a skill type based on its name.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 */
	public boolean deleteBySkillTypeId(int id);

	boolean deleteBySkillTypeName(String name);

}