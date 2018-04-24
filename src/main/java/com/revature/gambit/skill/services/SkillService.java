package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;

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
		return skillRepository.save(skill);
	}

	/**
	 * Retrieves all the skills, active and non-active.
	 * 
	 * @return Iterable object containing all the skills found.
	 */
	public Iterable<Skill> findAll() {
		return skillRepository.findAll();
	}

	/**
	 * Retrieves all the active skills.
	 * 
	 * @return Iterable object containing all the skills fouund.
	 */
	public Iterable<Skill> findAllActive() {
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
		return skillRepository.saveAndFlush(skill);
	}
	
	@Override
	@Transactional
	public Skill deleteSoftly(String name) {
		Skill skill = findByName(name);
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
			skillRepository.delete(findByName(name));
			return true;
		}
		return false;
	}

}