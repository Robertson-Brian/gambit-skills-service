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
	public void testFindBySkillName() {
		Skill skill = skillService.findByName("Java");
		assertEquals(skill.getSkillName(), "Java");
	}

	@Test
	public void getSkillByName() {
		Skill sk = new Skill(1, "Java", true);
		assertEquals(sk.getSkillName(), "Java");
	}

}