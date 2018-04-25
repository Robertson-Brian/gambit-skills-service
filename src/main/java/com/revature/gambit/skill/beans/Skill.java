package com.revature.gambit.skill.beans;

import java.util.List;


import javax.persistence.*;

/**
 * Represents a specific skill such as Java, C#, Bash, Office, etc.
 */
@Entity
@Table(name = "SKILL")
public class Skill {

	/**
	 * ID that uniquely identifies a skill.
	 */
	@Id
	@Column(name = "SKILL_ID")
	@SequenceGenerator(name = "SKILL_ID_SEQ", sequenceName = "SKILL_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SKILL_ID_SEQ")
	private int skillID;

	/**
	 * Name of the skill.
	 */
	@Column(name = "SKILL_NAME")
	private String skillName;

	/**
	 * Flag that will be used for soft deletes.
	 */
	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	/**
	 * List of SkillTypes that will be referenced by this skill. Useful to establish
	 * a many-to-many relationship.
	 */
	@ManyToMany(mappedBy = "skills", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<SkillType> skillTypes;

	/**
	 * No-args constructor.
	 */
	public Skill() {
	}

	/**
	 * Constructor that initializes class fields.
	 * 
	 * @param skill_id
	 *            ID that uniquely identifies a skill.
	 * @param skill_name
	 *            Name of the skill.
	 * @param active
	 *            Flag that will be used for soft deletes.
	 */
	public Skill(int skillId, String skillName, boolean active) {
		this.skillID = skillId;
		this.skillName = skillName;
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
