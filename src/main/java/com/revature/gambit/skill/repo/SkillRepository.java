package com.revature.gambit.skill.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.gambit.skill.beans.Skill;

/**
 * Spring Data repository interface for Skill service. Defines the boilerplate
 * methods that will be used.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    Skill findBySkillID(int id);
    
    public Skill findBySkillName(String name);
    
	boolean deleteBySkillID(Skill skill);
	
	boolean deleteBySkillName(String name);

    
}
