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

    /**
     * Looks up a skill type based on the skill type name.
     *
     * @param name
     *            Name of the skill type to lookup.
     * @return Skill that was found.
     */
    SkillType findBySkillTypeName(String name);

    /**
     * Looks up a skill type based on the skill type name.
     *
     * @param id
     *            Id of the skill type to lookup.
     * @return Skill that was found.
     */
    SkillType findBySkillTypeId(int id);

}
