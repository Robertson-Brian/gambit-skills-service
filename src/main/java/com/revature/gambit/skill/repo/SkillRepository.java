package com.revature.gambit.skill.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.gambit.skill.beans.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

	Skill findBySkillName(String name);

	Skill findBySkillID(int id);

	boolean deleteBySkillName(String name);
	
	@Modifying
	@Query(value="update skill e set e.is_active=false where e.skill_name= :skill_name", nativeQuery=true)
	Skill deleteSoftly(@Param("skill_name")String name);

	List<Skill> findAll();

	List<Skill> findAllByIsActive(boolean bool);

}
