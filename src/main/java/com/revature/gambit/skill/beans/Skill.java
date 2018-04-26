package com.revature.gambit.skill.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SKILL")
public class Skill {

	@Id
	@Column(name="SKILL_ID")
	@SequenceGenerator(name="SKILL_ID_SEQ",sequenceName="SKILL_ID_SEQ")
	@GeneratedValue(generator = "SKILL_ID_SEQ")
	private int skillId;

	@Column(name = "SKILL_NAME")
	private String skillName;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@ManyToMany(mappedBy = "skills")
	private List<SkillType> skillTypes;
	
	public Skill() { }
	
	public Skill(int skill_id, String skill_name, boolean active) {
		this.skillId = skill_id;
		this.skillName = skill_name;
		this.isActive = active;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillID) {
		this.skillId = skillID;
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
		result = prime * result + skillId;
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
		if (skillId != other.skillId)
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
		return "Skill [skillID=" + skillId + ", skillName=" + skillName + ", isActive=" + isActive + ", skillTypes="
				+ skillTypes + "]";
	}
	
	
	
}
