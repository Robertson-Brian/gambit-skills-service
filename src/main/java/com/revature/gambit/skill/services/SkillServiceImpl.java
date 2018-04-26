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

    /**
	 * Deletes a skill based on its name.
	 * 
	 * @param name
	 *            Name of the skill to delete.
	 */
	@Transactional
	public boolean deleteBySkillID(int id) {
		if (findById(id) instanceof Skill) {
			skillRepository.delete(findById(id));
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBySkillName(String name) {
		if (findByName(name) instanceof Skill) {
			skillRepository.delete(findByName(name));
			return true;
		}
		return false;	}
}
