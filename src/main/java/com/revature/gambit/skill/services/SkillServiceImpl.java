package com.revature.gambit.skill.services;

import java.util.List;

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
	@Override
	public Skill create(Skill skill) {
		return skillRepository.save(skill);
	}

	/**
	 * Retrieves all the skills, active and non-active.
	 *
	 * @return Iterable object containing all the skills found.
	 */
	@Override
	public Iterable<Skill> findAll() {
		return skillRepository.findAll();
	}

	/**
	 * Retrieves all the active skills.
	 *
	 * @return List object containing all the skills found.
	 */
	@Override
	public List<Skill> findAllActive() {
		return this.skillRepository.findAllByIsActive(true);
	}

	/**
	 * Retrieves a skill based on its skill name.
	 *
	 * @param name
	 *            Name of the skill to retrieve.
	 * @return Skill that was found.
	 */
	@Override
	public Skill findBySkillName(String name) {
		return this.skillRepository.findBySkillName(name);
	}
	
	@Override
	public Skill findBySkillID(int id) {
		return this.skillRepository.findBySkillID(id);
	}

	/**
	 * Updates skill in DB and flushes change immediately 
	 *
	 * @param skill
	 *            Skill to be updated.
	 * @return Skill that was updated.
	 */
	@Transactional
	@Override
	public Skill saveSkill(Skill skill) {
		return skillRepository.saveAndFlush(skill);
	}

	/**
	 * Deletes a skill based on its name.
	 *
	 * @param name
	 *            Name of the skill to delete.
	 */
	@Transactional
	@Override
	public void deleteBySkillName(String name) {
		this.skillRepository.deleteBySkillName(name);
	}

	/**
	 * Deletes a skill based on its id.
	 * 
	 * @param id
	 *            id of the skill to delete.
	 */
	@Transactional
	@Override
	public void deleteBySkillID(int id) {
		this.skillRepository.deleteBySkillID(id);
	}

}