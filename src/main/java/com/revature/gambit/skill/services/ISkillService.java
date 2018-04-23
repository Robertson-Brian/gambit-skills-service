package com.revature.gambit.skill.services;

import com.revature.gambit.skill.beans.Skill;

/**
 * API defining all the methods the skill service will implement.
 */
public interface ISkillService {

	/**
	 * Adds a new skill to the database.
	 * 
	 * @param skill
	 *            Skill to be added.
	 * @return Skill that has been added.
	 */
	public Skill create(Skill skill);

	/**
	 * Retrieves all the skills, active and non-active.
	 * 
	 * @return Iterable object containing all the skills found.
	 */
	public Iterable<Skill> findAll();

	/**
	 * Retrieves all the active skills.
	 * 
	 * @return Iterable object containing all the skills fouund.
	 */
	public Iterable<Skill> findAllActive();

	/**
	 * Retrieves a skill based on its skill name.
	 * 
	 * @param name
	 *            Name of the skill to retrieve.
	 * @return Skill that was found.
	 */
	public Skill findByName(String name);

	/**
	 * Adds a new skill to the DB, but unlike create(), this method will
	 * commit/flush changes to DB immediately.
	 * 
	 * @param skill
	 *            Skill to be added.
	 * @return Skill that was added.
	 */
	public Skill saveSkill(Skill skill);

	/**
	 * Deletes a skill based on its name.
	 * 
	 * @param name
	 *            Name of the skill to delete.
	 */
	public void deleteSkillViaName(String name);

}
