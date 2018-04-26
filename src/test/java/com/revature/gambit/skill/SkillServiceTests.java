package com.revature.gambit.skill;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
		Iterable<Skill> skill = skillService.findAllSkill();
		assertEquals(39, ((List<Skill>) skill).size());
	}

	@Test
	public void testFindBySkillName() {
		Skill skill = skillService.findByName("Java");
		assertEquals(skill.getSkillName(), "Java");
	}
	
	@Test
	public void testDeleteSkillNameFunction() {
		Iterable<Skill> skills = this.skillService.findAllSkill();
		this.skillService.deleteBySkillName("Java");
		Iterable<Skill> skillss = this.skillService.findAllSkill();
		assertNotEquals(skills, skillss);
	}
	
	@Test
	public void testDeleteSkillIdFunction() {
		Iterable<Skill> skills = this.skillService.findAllSkill();
		this.skillService.deleteBySkillID(51);
		Iterable<Skill> skillss = this.skillService.findAllSkill();
		assertNotEquals(skills, skillss);
	}


	@Test
	public void getSkillByName() {
		Skill sk = new Skill(1, "Java", true);
		assertEquals(sk.getSkillName(), "Java");
	}

}