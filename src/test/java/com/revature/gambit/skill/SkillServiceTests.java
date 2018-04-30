package com.revature.gambit.skill;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.repo.SkillRepository;
import com.revature.gambit.skill.services.SkillServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillServiceTests {
	
	@Autowired
	private SkillServiceImpl skillService;
	
	@Autowired
	private SkillRepository skillRepository;

	@Test
	public void testSkillCreate() {
		int numberofSkills = ((List<Skill>) skillService.findAll()).size();
		Skill skill = new Skill(1900, "test", true);
		Skill returnedSkill = this.skillService.create(skill);
		int newnumberofSkills = ((List<Skill>) skillService.findAll()).size();
		assertEquals(numberofSkills, newnumberofSkills - 1);
		assertEquals(returnedSkill.getSkillName(), skill.getSkillName() );
	}
	
	@Test
	public void testDeleteBySkillName() {
		Skill skill = new Skill(0, "Test", true);
		skill = this.skillRepository.saveAndFlush(skill);
		
		this.skillService.deleteBySkillName("Test");
		
		Skill actualSkill = this.skillRepository.findBySkillName("Test");
		assertNull(actualSkill);
	}
	
	@Test
	public void testDeleteBySkillID() {
		Skill skill = new Skill(0, "Test", true);
		skill = this.skillRepository.saveAndFlush(skill);
		
		this.skillService.deleteBySkillID(skill.getSkillID());
		
		Skill actualSkill = this.skillRepository.findBySkillID(skill.getSkillID());
		assertNull(actualSkill);
	}
	
    public void testFindBySkillName() {
    	Skill skill = skillService.findBySkillName("Java");
    	assertEquals(skill.getSkillName(), "Java");
    }
    
    @Test
    public void testFindById() {
    	Skill skill = skillService.findBySkillID(1);
    	assertEquals(skill.getSkillName(), "Visual Basic");
    }
    
	@Test
    public void testSaveSkill() {
    	Skill skill1 = skillService.findBySkillID(1);
    	Skill skill2 = skillService.saveSkill(new Skill(1, "Javas", true));
    	assertNotEquals(skill2.getSkillName(), skill1.getSkillName());
    	Skill skill3 = skillService.saveSkill(new Skill(1, skill1.getSkillName(), true));
    	assertEquals(skill1.getSkillName(), skill3.getSkillName());
    }
	
	@Test
<<<<<<< HEAD
	public void getSkillByName() {
		Skill sk = new Skill(1, "Java", true);
		assertEquals(sk.getSkillName(), "Java");
=======
	public void testFindAllActive() {
		List<Skill> skills = this.skillService.findAllActive();
		
		assertEquals(41, skills.size());
>>>>>>> dev
	}

}
