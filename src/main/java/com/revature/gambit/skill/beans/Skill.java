package com.revature.gambit.skill.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "SKILL")
public class Skill {

	@Id
	@Column(name = "SKILL_ID")
	@SequenceGenerator(name = "SKILL_ID_SEQ", sequenceName = "SKILL_ID_SEQ")
	@GeneratedValue(generator = "SKILL_ID_SEQ")
	private int skillID;

	@Column(name = "SKILL_NAME")
	private String skillName;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@ManyToMany(mappedBy = "skills", cascade =CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<SkillType> skillTypes;

	public Skill() {
	}

	public Skill(int skill_id, String skill_name, boolean active) {
		this.skillID = skill_id;
		this.skillName = skill_name;
		this.isActive = active;
	}

	public int getSkillID() {
		return skillID;
	}

	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}
	
	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + skillID;
		result = prime * result + ((skillName == null) ? 0 : skillName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (isActive != other.isActive)
			return false;
		if (skillID != other.skillID)
			return false;
		if (skillName == null) {
			if (other.skillName != null)
				return false;
		} else if (!skillName.equals(other.skillName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Skill [skillID=" + skillID + ", skillName=" + skillName + ", isActive=" + isActive + ", skillTypes="
				+ skillTypes + "]";
	}

}
