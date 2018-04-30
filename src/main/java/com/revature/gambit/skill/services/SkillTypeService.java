package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.repo.SkillTypeRepository;
import com.revature.gambit.skill.util.LoggingUtil;

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
		LoggingUtil.logInfo("Created skill type with name: " + skillType.getSkillTypeName());
		return this.skillTypeRepository.save(skillType);
	}

	/**
	 * Retrieves a skill type based on its skill type name.
	 * 
	 * @param name
	 *            Name of the skill type to retrieve.
	 * @return Skill Type that was found.
	 */
	public SkillType findBySkillTypeName(String name) {
		LoggingUtil.logInfo("Retrieved skill type with name: " + name);
		return this.skillTypeRepository.findBySkillTypeName(name);
	}

	/**
	 * Retrieves all the skill types.
	 * 
	 * @return Iterable object containing all the skill types.
	 */
	public Iterable<SkillType> findByAll() {
		LoggingUtil.logInfo("Retrieved all skill types");
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
	@Transactional
	public boolean update(SkillType updatedSkillType, String name) {
		SkillType skillType = this.skillTypeRepository.findBySkillTypeName(name);
		if (skillType == null) {
			LoggingUtil.logError("Couldn't find skill type with name: " + name);
			return false;
		} else {
			skillType.setSkillTypeDesc(updatedSkillType.getSkillTypeDesc());
			skillType.setIsCore(updatedSkillType.isIs_core());
			skillType.setIsActive(updatedSkillType.isIsActive());
			this.skillTypeRepository.saveAndFlush(skillType);
			LoggingUtil.logInfo("Updated skill type with name: " + name);
			return true;
		}
	}

	/**
	 * Deletes a skill type based on its name.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 */
	@Transactional
	public boolean deleteBySkillTypeName(String name) {
		if (findBySkillTypeName(name) instanceof SkillType) {
			this.skillTypeRepository.deleteBySkillTypeName(name);
			LoggingUtil.logInfo("Deleted skill type with name: " + name);
			return true;
		}
		LoggingUtil.logError("Couldn't delete skill type with name: " + name);
		return false;
	}

}