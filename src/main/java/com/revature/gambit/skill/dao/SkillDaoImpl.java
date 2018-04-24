package com.revature.gambit.skill.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import com.revature.gambit.skill.beans.Skill;

public class SkillDaoImpl implements SkillDao {
	
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("unused")
	private JpaEntityInformation<Integer, Skill> skillInfo;

	@Override
	public boolean deleteSkillByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSkillById(int Id) {
		// TODO Auto-generated method stub
		return false;
	}

}
