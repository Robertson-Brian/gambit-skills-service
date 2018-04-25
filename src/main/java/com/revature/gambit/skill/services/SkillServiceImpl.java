package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;

/**
 * Implementation of the Skill service API methods.
 */
@Service
@Configuration
public class SkillServiceImpl implements SkillService {

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
		return null;
	}

	/**
	 * Retrieves a skill based on its skill name.
	 *
	 * @param name
	 *            Name of the skill to retrieve.
	 * @return Skill that was found.
	 */
	public Skill findByName(String name) {
		return null;
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
		return null;
	}

	/**
	 * Deletes a skill based on its name.
	 *
	 * @param name
	 *            Name of the skill to delete.
	 * @exception UnsupportedOperationException Since the method has yet to be implemented
	 */
	@Override
	@Transactional
	public boolean deleteBySkillName(String name) {
		if (findByName(name) instanceof Skill) {
			skillRepository.delete(findByName(name));
			return true;
		}
		return false;
	}

}