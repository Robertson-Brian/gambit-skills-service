package com.revature.gambit.skill.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.gambit.skill.beans.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    Skill findBySkillID(int id);
    
    public Skill findBySkillName(String name);
    
    List<Skill> findAll();
    
    Iterable<Skill> findAllByIsActive(boolean bool);
    
}
