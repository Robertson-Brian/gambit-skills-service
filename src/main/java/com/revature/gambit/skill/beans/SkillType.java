package com.revature.gambit.skill.beans;

import javax.persistence.*;
import java.util.List;

/**
 * Represents an entire SkillType (a.k.a. curriculum) such as Pega, .NET, DBA,
 * SysAdmin, etc.
 */
@Entity
@Table(name = "SKILLTYPE")
public class SkillType {

	/**
	 * ID that uniquely identifies the skill type.
	 */
	@Id
	@Column(name = "SKILLTYPE_ID")
	@SequenceGenerator(name = "SKILLTYPE_ID_SEQ", sequenceName = "SKILLTYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SKILLTYPE_ID_SEQ")
	private Integer skillTypeId;

	/**
	 * Name of the skill type.
	 */
	@Column(name = "SKILLTYPE_NAME")
	private String skillTypeName;

	/**
	 * A description of the skill type.
	 */
	@Column(name = "SKILLTYPE_DESC")
	private String skillTypeDesc;

	/**
	 * Flag that will be used for soft deletes.
	 */
	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	/**
	 * Tells us whether this skill type is a core part of the program or not.
	 */
	@Column(name = "IS_CORE")
	private boolean isCore;

	/**
	 * List of Skills that will be referenced by this SkillType. Useful to establish
	 * a many-to-many relationship.
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "SKILL_SKILLTYPE", joinColumns = { @JoinColumn(name = "SKILLTYPE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "SKILL_ID") })
	private List<Skill> skills;

	/**
	 * No-args constructor.
	 */
	public SkillType() {
	}

	/**
	 * Constructor that initializes class fields excluding ID, which will be
	 * auto-generated in the DB before insertion.
	 * 
	 * @param skillTypeName
	 *            Name of the skill type.
	 * @param skillTypeDesc
	 *            A description of the skill type.
	 * @param isActive
	 *            Flag that will be used for soft deletes.
	 * @param isCore
	 *            Used to check whether this is identified as a core skill or not.
	 */
	public SkillType(String skillTypeName, String skillTypeDesc, boolean isActive, boolean isCore) {
		this.skillTypeName = skillTypeName;
		this.skillTypeDesc = skillTypeDesc;
		this.isActive = isActive;
		this.isCore = isCore;
	}

	/**
	 * Constructor that initializes class fields, including ID.
	 * 
	 * @param skillTypeID
	 *            ID that uniquely identifies the skill type.
	 * @param skillTypeName
	 *            Name of the skill type.
	 * @param skillTypeDesc
	 *            A description of the skill type.
	 * @param isActive
	 *            Flag that will be used for soft deletes.
	 * @param isCore
	 *            Used to check whether this is identified as a core skill or not.
	 */
	public SkillType(Integer skillTypeID, String skillTypeName, String skillTypeDesc, boolean isActive,
			boolean isCore) {
		this.skillTypeId = skillTypeID;
		this.skillTypeName = skillTypeName;
		this.skillTypeDesc = skillTypeDesc;
		this.isActive = isActive;
		this.isCore = isCore;
	}

	public Integer getSkillTypeId() {
		return skillTypeId;
	}

	public void setSkillTypeId(Integer skillId) {
		this.skillTypeId = skillId;
	}

	public String getSkillTypeName() {
		return skillTypeName;
	}

	public void setSkillTypeName(String skillTypeName) {
		this.skillTypeName = skillTypeName;
	}

	public String getSkillTypeDesc() {
		return skillTypeDesc;
	}

	public void setSkillTypeDesc(String skillTypeDesc) {
		this.skillTypeDesc = skillTypeDesc;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isCore() {
		return isCore;
	}

	public void setIsCore(boolean isCore) {
		this.isCore = isCore;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SkillType skillType = (SkillType) o;

		if (isActive != skillType.isActive)
			return false;
		if (isCore != skillType.isCore)
			return false;
		if (skillTypeId != null ? !skillTypeId.equals(skillType.skillTypeId) : skillType.skillTypeId != null)
			return false;
		if (skillTypeName != null ? !skillTypeName.equals(skillType.skillTypeName) : skillType.skillTypeName != null)
			return false;
		return skillTypeDesc != null ? skillTypeDesc.equals(skillType.skillTypeDesc) : skillType.skillTypeDesc == null;
	}

	@Override
	public int hashCode() {
		int result = skillTypeId != null ? skillTypeId.hashCode() : 0;
		result = 31 * result + (skillTypeName != null ? skillTypeName.hashCode() : 0);
		result = 31 * result + (skillTypeDesc != null ? skillTypeDesc.hashCode() : 0);
		result = 31 * result + (isActive ? 1 : 0);
		result = 31 * result + (isCore ? 1 : 0);
		return result;
	}
}
