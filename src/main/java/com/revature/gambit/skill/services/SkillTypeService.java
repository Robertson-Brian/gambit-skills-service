package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.SkillType;
<<<<<<< HEAD
import com.revature.gambit.skill.repo.SkillTypeRepository;
import com.revature.gambit.skill.util.LoggingUtil;
=======
>>>>>>> dev

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
	 * Retrieves a skill type based on its skill type name.
	 *
	 * @param id
	 *            Name of the skill type to retrieve.
	 * @return Skill Type that was found.
	 */
	public SkillType findBySkillTypeId(int id);

	/**
	 * Retrieves all the skill types.
	 * 
	 * @return Iterable object containing all the skill types.
	 */
	public Iterable<SkillType> findAll();

	/**
	 * Updates the value of a skill type.
	 * 
	 * @param updatedSkillType
	 *            Updated value that will replace the old one.
	 * @return SkillType that was updated.
	 */
	public SkillType update(SkillType updatedSkillType);

	/**
	 * Deletes a skill type based on its name.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 */
	public void deleteBySkillTypeName(String name);
	
	/**
	 * Deletes a skill type based on its id.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 */
	public void deleteBySkillTypeID(int id);

}
