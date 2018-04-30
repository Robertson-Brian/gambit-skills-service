package com.revature.gambit.skill.services;

import java.util.List;

import com.revature.gambit.skill.beans.Skill;

/**
 * API defining all the methods the skill service will implement.
 */
public interface SkillService {

	/**
	 * Adds a new skill to the database.
	 * 
	 * @param skill
	 *            Skill to be added.
	 * @return Skill that has been added.
	 */
	Skill create(Skill skill);

	/**
	 * Retrieves all the skills, active and non-active.
	 * 
	 * @return Iterable object containing all the skills found.
	 */
	Iterable<Skill> findAll();

	/**
	 * Retrieves all the active skills.
	 * 
	 * @return List object containing all the skills fouund.
	 */
	List<Skill> findAllActive();

	/**
	 * Retrieves a skill based on its skill name.
	 * 
	 * @param name
	 *            Name of the skill to retrieve.
	 * @return Skill that was found.
	 */
	Skill findBySkillName(String name);

	Skill findBySkillID(int id);

	/**
	 * Adds a new skill to the DB, but unlike create(), this method will
	 * commit/flush changes to DB immediately.
	 * 
	 * @param skill
	 *            Skill to be added.
	 * @return Skill that was added.
	 */
	Skill saveSkill(Skill skill);

	/**
	 * Deletes a skill based on its name.
	 * 
	 * @param name
	 *            Name of the skill to delete.
	 */
	void deleteBySkillName(String name);

	void deleteBySkillID(int id);

}
