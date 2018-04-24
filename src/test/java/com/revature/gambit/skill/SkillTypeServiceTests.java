package com.revature.gambit.skill;

import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.repo.SkillTypeRepository;
import com.revature.gambit.skill.services.SkillTypeService;
import org.junit.*;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTypeServiceTests {

	@Autowired
	private SkillTypeService skillTypeService;
	
	@Autowired
	private SkillTypeRepository skillTypeRepository;

	@Test
	public void testCreate() {
		int sizeOfList = ((List<?>)skillTypeService.findByAll()).size();
		SkillType tstSkillType = new SkillType("Testing", "Testing Desc", true, true);
		skillTypeService.create(tstSkillType);
		assertTrue(((List<?>)skillTypeService.findByAll()).size() > sizeOfList);
		skillTypeRepository.delete(tstSkillType);
	}

	@Test
	public void getAllSkillTypes() {
		Iterable<SkillType> skillTypes = skillTypeService.findByAll();
		assertEquals(9, ((List<SkillType>) skillTypes).size());
	}

	@Test
	public void getSkillTypeByName() {
		SkillType stk = skillTypeService.findBySkillTypeName("PEGA");
		assertEquals(stk.getSkillTypeDesc(), "PEGA Description");
	}

	@Test
	public void getSkillTypeNotFound() {
		SkillType stk = skillTypeService.findBySkillTypeName("PEGAN");
		assertNull(stk);
	}

	@Test
	public void updateSkillType() {
		SkillType tstSkillType = new SkillType("Testing", "Testing Desc", true, true);
		skillTypeService.create(tstSkillType);
		tstSkillType.setIsCore(false);
		skillTypeService.update(tstSkillType, "Testing");
		tstSkillType = skillTypeService.findBySkillTypeName("Testing");
		assertFalse(tstSkillType.isIs_core());
		skillTypeRepository.delete(tstSkillType);

	}
	
	@Test
	public void failUpdateSkillType() {
		SkillType tstSkillType = new SkillType("Testing", "Testing Desc", true, true);
		skillTypeService.create(tstSkillType);
		tstSkillType.setIsCore(false);
		skillTypeService.update(tstSkillType, "Test");
		tstSkillType = skillTypeService.findBySkillTypeName("Testing");
		assertTrue(tstSkillType.isIs_core());
		skillTypeRepository.delete(tstSkillType);
	}

	@Test
	public void testDeleteSkillTypeFunction() {
		Iterable<SkillType> skillTypes = this.skillTypeService.findByAll();
		this.skillTypeService.deleteBySkillTypeName("PEGA");
		Iterable<SkillType> skillTypess = this.skillTypeService.findByAll();
		assertNotEquals(skillTypes, skillTypess);
	}
	
}
