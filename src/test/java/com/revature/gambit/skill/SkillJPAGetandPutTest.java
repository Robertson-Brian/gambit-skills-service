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
public class SkillJPAGetandPutTest {

    @Autowired
    private SkillService skillService;
    
    @Test
    public void testFindAllSkills() {
        Iterable<Skill> skill = skillService.findAllSkill();
        assertEquals(41, ((List<Skill>) skill).size());
    }
    
    @Test
    public void testFindBySkillName() {
    	Skill skill = skillService.findByName("Java");
    	assertEquals(skill.getSkillName(), "Java");
    }
    
    @Test
    public void testFindById() {
    	Skill skill = skillService.findById(1);
    	assertEquals(skill.getSkillName(), "Visual Basic");
    }
    
    @Test
    public void testFindAllByIsActive() {
    	Iterable<Skill> skills = skillService.findAllActive();
    	assertEquals(((List<Skill>) skills).size(), 40);
    }
    
    @Test
    public void testSaveSkill() {
    	Skill skill1 = skillService.findById(1);
    	Skill skill2 = skillService.saveSkill(new Skill(1, "Javas", true));
    	assertNotEquals(skill2.getSkillName(), skill1.getSkillName());
    	Skill skill3 = skillService.saveSkill(new Skill(1, skill1.getSkillName(), true));
    	assertEquals(skill1.getSkillName(), skill3.getSkillName());
    }
}
