package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;
import com.revature.gambit.skill.util.LoggingUtil;

/**
 * Implementation of the Skill service API methods.
 */
@Service
@Configuration
public class SkillService implements ISkillService {

	/**
	 * Spring Data JPA Repository for skill methods.
	 */
	@Autowired
	private SkillRepository skillRepository;

	/**
	 * Adds a new skill to the database.
	 * 
	 * @param skill
	 *            Skill to be added.
	 * @return Skill that has been added.
	 */
	@Transactional
	public Skill create(Skill skill) {
		LoggingUtil.logInfo("Created skill with name: " + skill.getSkillName());
		return skillRepository.save(skill);
	}

	/**
	 * Retrieves all the skills, active and non-active.
	 * 
	 * @return Iterable object containing all the skills found.
	 */
	public Iterable<Skill> findAll() {
		LoggingUtil.logInfo("Retrieved all skills");
		return skillRepository.findAll();
	}

	/**
	 * Retrieves all the active skills.
	 * 
	 * @return Iterable object containing all the skills found.
	 */
	public Iterable<Skill> findAllActive() {
		LoggingUtil.logInfo("Retrieved all active skills");
		return skillRepository.findAllByIsActive(true);
	}

	/**
	 * Retrieves a skill based on its skill name.
	 * 
	 * @param name
	 *            Name of the skill to retrieve.
	 * @return Skill that was found.
	 */
	public Skill findByName(String name) {
		LoggingUtil.logInfo("Retrieved skill with name: " + name);
		return skillRepository.findBySkillName(name);
	}

	/**
	 * Adds a new skill to the DB, but unlike create(), this method will
	 * commit/flush changes to DB immediately.
	 * 
	 * @param skill
	 *            Skill to be added.
	 * @return Skill that was added.
	 */
	@Transactional
	public Skill saveSkill(Skill skill) {
		LoggingUtil.logInfo("Created a new skill with name: " + skill.getSkillName());
		return skillRepository.saveAndFlush(skill);
	}
	
	@Override
	@Transactional
	public Skill deleteSoftly(String name) {
		Skill skill = findByName(name);
		LoggingUtil.logInfo("Soft deleted skill with name: " + skill.getSkillName());
		return skillRepository.saveAndFlush(skill);
	}

	/**
	 * Deletes a skill based on its name.
	 * 
	 * @param name
	 *            Name of the skill to delete.
	 */
	@Override
	@Transactional
	public boolean deleteSkillViaName(String name) {
		if (findByName(name) instanceof Skill) {
			LoggingUtil.logInfo("Deleted skill with name: " + name);
			skillRepository.delete(findByName(name));
			return true;
		}
		LoggingUtil.logError("Couldn't delete skill with name: " + name);
		return false;
	}

}