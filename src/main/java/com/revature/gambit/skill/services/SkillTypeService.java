package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.SkillType;

/**
 * API defining all the methods the skill type service will implement.
 */
public interface SkillTypeService {

	/**
	 * Adds a new skill type to the database.
	 * 
	 * @param skillType
	 *            Skill Type to be added.
	 * @return Skill Type that has been added.
	 */
	public SkillType create(SkillType skillType);

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
	 * Updates the value of a skill type.
	 * 
	 * @param updatedSkillType
	 *            Updated value that will replace the old one.
	 * @param name
	 *            Name of the skill type to be replaced.
	 * @return True if success, false if the update can't be done.
	 */
	public boolean update(SkillType updatedSkillType, String name);

	/**
	 * Deletes a skill type based on its name.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 */
	public boolean deleteBySkillTypeId(int id);

}