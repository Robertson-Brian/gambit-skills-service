package com.revature.gambit.skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.repo.SkillTypeRepository;

@Service
public class SkillTypeService implements ISkillTypeService {

	@Autowired
	private SkillTypeRepository skillTypeRepository;

	@Transactional
	public SkillType create(SkillType skillType) {
		return this.skillTypeRepository.save(skillType);
	}

	public SkillType findBySkillTypeName(String name) {
		return this.skillTypeRepository.findBySkillTypeName(name);
	}

	public Iterable<SkillType> findByAll() {
		return this.skillTypeRepository.findAll();
	}

	@Transactional
	public boolean update(SkillType updatedSkillType, String name) {
		SkillType skillType = this.skillTypeRepository.findBySkillTypeName(name);
		if (skillType == null) {
			return false;
		} else {
			skillType.setSkillTypeDesc(updatedSkillType.getSkillTypeDesc());
			skillType.setIsCore(updatedSkillType.isIs_core());
			skillType.setIsActive(updatedSkillType.isIsActive());
			this.skillTypeRepository.saveAndFlush(skillType);
			return true;
		}
	}

	@Transactional
	public boolean deleteBySkillTypeName(String name) {
		if (findBySkillTypeName(name) instanceof SkillType) {
			this.skillTypeRepository.deleteBySkillTypeName(name);
			return true;
		}
		return false;
	}

}