package com.revature.gambit.skill.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.gambit.skill.beans.SkillType;

@Repository
public interface SkillTypeRepository extends JpaRepository<SkillType, Integer> {

    SkillType findBySkillTypeName(String name);
    
    boolean deleteBySkillTypeName(String name);
    
}
