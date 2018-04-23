package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.repo.SkillTypeRepository;

/**
 * Implementation of the Skill Type service API methods.
 */
@Service
public class SkillTypeService implements ISkillTypeService {

	/**
	 * Spring Data JPA Repository for skill type methods.
	 */
	@Autowired
	private SkillTypeRepository skillTypeRepository;

	/**
	 * Adds a new skill type to the database.
	 * 
	 * @param skillType
	 *            Skill Type to be added.
	 * @return Skill Type that has been added.
	 */
	@Transactional
	public SkillType create(SkillType skillType) {
		return null;
	}

	/**
	 * Retrieves a skill type based on its skill type name.
	 * 
	 * @param name
	 *            Name of the skill type to retrieve.
	 * @return Skill Type that was found.
	 */
	public SkillType findBySkillTypeName(String name) {
		return null;
	}

	/**
	 * Retrieves all the skill types.
	 * 
	 * @return Iterable object containing all the skill types.
	 */
	public Iterable<SkillType> findByAll() {
		return null;
	}

	/**
	 * Updates the value of a skill type.
	 * 
	 * @param updatedSkillType
	 *            Updated value that will replace the old one.
	 * @param name
	 *            Name of the skill type to be replaced.
	 * @return True if success, false if the update can't be done.
	 */
	@Transactional
	public boolean update(SkillType updatedSkillType, String name) {
		return false;
	}

	/**
	 * Deletes a skill type based on its name.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 */
	@Transactional
	public void deleteBySkillTypeName(String name) {
	}

}