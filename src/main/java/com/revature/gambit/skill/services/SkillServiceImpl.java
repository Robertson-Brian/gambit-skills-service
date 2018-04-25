package com.revature.gambit.skill.services;


import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillServiceImpl implements SkillService{

    @Autowired
    private SkillRepository skillRepository;

    public Skill create(Skill skill) { return this.skillRepository.save(skill); }

    @Override
    public Skill findById(int id) { 
    	return this.skillRepository.findBySkillID(id); 
    }
    
    @Override
	public Skill findByName(String name) {
		return skillRepository.findBySkillName(name);
	}
	
    @Override
	public Iterable<Skill> findAllSkill(){
		return skillRepository.findAll();
	}
	
    @Override
	public Iterable<Skill> findAllActive(){
		return skillRepository.findAllByIsActive(true);
	}

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
	@Override
	@Transactional
	public boolean deleteBySkillName(int id) {
		if (findById(id) instanceof Skill) {
			skillRepository.delete(findById(id));
			return true;
		}
		return false;
	}
}
