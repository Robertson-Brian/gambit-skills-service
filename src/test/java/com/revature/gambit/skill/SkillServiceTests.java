package com.revature.gambit.skill;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillServiceTests {
	
	@Autowired
	private SkillService skillService;

	@Test
	public void testFindAllSkills() {
		Iterable<Skill> skill = skillService.findAll();
		assertEquals(41, ((List<Skill>) skill).size());
	}

	@Test
	public void testFindBySkillName() {
		Skill skill = skillService.findByName("Java");
		assertEquals(skill.getSkillName(), "Java");
	}

	@Test
	public void testFindAllByIsActive() {
		Iterable<Skill> skills = skillService.findAllActive();
		assertEquals(((List<Skill>) skills).size(), 40);
	} 

	@Test
	public void testSaveSkill() {
		Skill skill1 = skillService.findByName("PEGA");
		Skill skill2 = skillService.saveSkill(new Skill(1, "Javas", true));
		assertNotEquals(skill2.getSkillName(), skill1.getSkillName());
		Skill skill3 = skillService.saveSkill(new Skill(1, skill1.getSkillName(), true));
		assertEquals(skill1.getSkillName(), skill3.getSkillName());
	}
	
	@Test
	public void testDeleteSkillFunction() {
		Iterable<Skill> skills = this.skillService.findAll();
		this.skillService.deleteSkillViaName("Java");
		Iterable<Skill> skillss = this.skillService.findAll();
		assertNotEquals(skills, skillss);
	}

	@Test
	public void testSkillCreate() {
		int numberofSkills = ((List<Skill>) skillService.findAll()).size();
		Skill skill = new Skill(1900, "test", true);
		this.skillService.create(skill);
		int newnumberofSkills = ((List<Skill>) skillService.findAll()).size();
		assertEquals(numberofSkills, newnumberofSkills - 1);
	}

	@Test
	public void getSkillByName() {
		Skill sk = new Skill(1, "Java", true);
		assertEquals(sk.getSkillName(), "Java");
	}
	
	@Test
	public void testSkillHashCode() {
		Skill sk = new Skill(1, "Java", true);
		assertEquals(sk.hashCode(), 3514319);
	}
	
/*	@Test
	public void testMain() {
		String[] test = new String[0];
		Application.main(test);
		assertTrue(true);
	}
*/
}
