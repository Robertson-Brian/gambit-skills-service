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
	 * Deletes a skill type based on its name.
	 * 
	 * @param name
	 *            Name of the skill type to delete.
	 */
	@Override
	@Transactional
	public boolean deleteBySkillTypeName(String name) {
		if (findBySkillTypeName(name) instanceof SkillType) {
			this.skillTypeRepository.delete(findBySkillTypeName(name));
			return true;
		}
		return false;
	}

	/**
	 * Deletes a skill type based on its id.
	 * 
	 * @param id
	 *            id of the skill type to delete.
	 * @exception UnsupportedOperationException Since the method has yet to be implemented
	 */
	@Override
	@Transactional
	public boolean deleteBySkillTypeId(int id) {
		if (findBySkillTypeId(id) instanceof SkillType) {
			this.skillTypeRepository.delete(findBySkillTypeId(id));
			return true;
		}
		return false;
	}

}