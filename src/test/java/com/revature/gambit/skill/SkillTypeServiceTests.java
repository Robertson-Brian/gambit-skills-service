package com.revature.gambit.skill;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.repo.SkillTypeRepository;
import com.revature.gambit.skill.services.SkillTypeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTypeServiceTests {

	@Autowired
	private SkillTypeServiceImpl skillTypeService;
	
	@Autowired
	private SkillTypeRepository skillTypeRepository;


	@Test
	public void testCreate() {
		int sizeOfList = ((List)skillTypeRepository.findAll()).size();
		SkillType tstSkillType = new SkillType("Testing", "Testing Desc", true, true);
		SkillType returnedSkillType = skillTypeService.create(tstSkillType);
		assertTrue(((List)skillTypeRepository.findAll()).size() > sizeOfList);
		assertEquals(returnedSkillType.getSkillTypeName(), tstSkillType.getSkillTypeName());
		skillTypeRepository.delete(tstSkillType);
	}

	@Test
	public void getAllSkillTypes() {
		Iterable<SkillType> skillTypes = skillTypeService.findByAll();
		assertEquals(9, ((List<SkillType>) skillTypes).size());
	}

	@Test
	public void getSkillTypeById() {
		SkillType stk = skillTypeService.findBySkillTypeId(3);
		assertEquals(stk.getSkillTypeDesc(), "PEGA Description");
	}

	@Test
	public void getSkillTypeNotFound() {
		SkillType stk = skillTypeService.findBySkillTypeId(1000);
		assertNull(stk);
	}

	
}
