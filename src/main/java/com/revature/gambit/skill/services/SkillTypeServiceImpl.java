package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.repo.SkillTypeRepository;

/**
 * Implementation of the Skill Type service API methods.
 */
@Service
public class SkillTypeServiceImpl implements SkillTypeService {

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
	@Override
	@Transactional
	public SkillType create(SkillType skillType) {
		return this.skillTypeRepository.save(skillType);
	}

	/**
	 * Retrieves a skill type based on its skill type name.
	 *
	 * @param name
	 *            Name of the skill type to retrieve.
	 * @return Skill Type that was found.
	 */
	@Override
	public SkillType findBySkillTypeName(String name) {
		return this.skillTypeRepository.findBySkillTypeName(name);
	}

	/**
	 * Retrieves a skill type based on its skill type name.
	 *
	 * @param id
	 *            Id of the skill type to retrieve.
	 * @return Skill Type that was found.
	 */
	public SkillType findBySkillTypeId(int id){ return this.skillTypeRepository.findBySkillTypeId(id);}

	/**
	 * Retrieves all the skill types.
	 *
	 * @return Iterable object containing all the skill types.
	 */
	@Override
	public Iterable<SkillType> findByAll() {
		return this.skillTypeRepository.findAll();
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
	@Override
	@Transactional
	public boolean update(SkillType updatedSkillType, String name) {
		return false;
	}

}