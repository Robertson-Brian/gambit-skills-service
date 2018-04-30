package com.revature.gambit.skill.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.gambit.skill.beans.Skill;

/**
 * Spring Data repository interface for Skill service. Defines the boilerplate
 * methods that will be used.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {


	/**
	 * Retrieves all skills, including the inactive ones.
	 *
	 * @return List of skills found.
	 */
	List<Skill> findAll();

	Skill findBySkillID(int id);

	Skill findBySkillName(String name);

	List<Skill> findAllByIsActive(boolean isActive);
	
	void deleteBySkillID(int id);

	void deleteBySkillName(String name);
}
