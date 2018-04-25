package com.revature.gambit.skill.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.gambit.skill.beans.SkillType;

/**
 * Spring Data repository interface for Skill type service. Defines the
 * boilerplate methods that will be used.
 */
@Repository
public interface SkillTypeRepository extends JpaRepository<SkillType, Integer> {

    SkillType findBySkillTypeName(String name);
    
    boolean deleteBySkillTypeName(String name);
    
    boolean deleteBySkillTypeID(int id);
    

}
