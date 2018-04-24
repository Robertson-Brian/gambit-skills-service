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
	 * Looks up a skill based on the skill name.
	 * 
	 * @param name
	 *            Name of the skill to lookup.
	 * @return Skill that was found.
	 */
	Skill findBySkillName(String name);

	/**
	 * Looks up a skill based on the skill ID.
	 * 
	 * @param id
	 *            ID of the skill to lookup.
	 * @return Skill that was found.
	 */
	Skill findBySkillID(int id);

	boolean deleteBySkillName(String name);
	
	@Modifying
	@Query(value="update skill e set e.is_active=false where e.skill_name= :skill_name", nativeQuery=true)
	Skill deleteSoftly(@Param("skill_name")String name);

	/**
	 * Deletes a skill based on the skill name.
	 * 
	 * @param name
	 *            Name of the skill to delete.
	 */
	void deleteBySkillName(String name);

	/**
	 * Retrieves all skills, including the inactive ones.
	 * 
	 * @return List of skills found.
	 */
	List<Skill> findAll();

	/**
	 * Retrieves all skills, based on their status.
	 * 
	 * @param bool
	 *            True to retrieve all active skills, False to retrieve all inactive
	 *            skills.
	 * @return List of skills found.
	 */
	List<Skill> findAllByIsActive(boolean bool);

}
