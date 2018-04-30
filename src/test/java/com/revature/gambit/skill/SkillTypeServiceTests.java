package com.revature.gambit.skill;

import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.repo.SkillTypeRepository;
import com.revature.gambit.skill.services.SkillTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTypeServiceTests {

	@Autowired
	private SkillTypeService skillTypeService;

	@Autowired
	private SkillTypeRepository skillTypeRepository;


	@Test
	public void testCreate() {
		int sizeOfList = ((List<SkillType>) skillTypeRepository.findAll()).size();
		SkillType tstSkillType = new SkillType("Testing", "Testing Desc", true, true);
		SkillType returnedSkillType = skillTypeService.create(tstSkillType);
		assertTrue(((List<SkillType>)skillTypeRepository.findAll()).size() > sizeOfList);
		assertEquals(returnedSkillType.getSkillTypeName(), tstSkillType.getSkillTypeName());
		skillTypeRepository.delete(skillTypeRepository.findOne(returnedSkillType.getSkillTypeId()));
	}

	@Test
	public void getAllSkillTypes() {
		Iterable<SkillType> skillTypes = skillTypeService.findAll();
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

	@Test
	public void getSkillTypeByName() {
		SkillType stk = skillTypeService.findBySkillTypeName("PEGA");
		assertEquals(stk.getSkillTypeDesc(), "PEGA Description");
	}

	@Test
	public void getSkillTypeByNameNotFound() {
		SkillType stk = skillTypeService.findBySkillTypeName("PEGAN");
		assertNull(stk);
	}

	@Test
	public void testDeleteBySkillTypeName() {
		Iterable<SkillType> before = this.skillTypeService.findAll();
		this.skillTypeService.deleteBySkillTypeName("JTA");
		Iterable<SkillType> after = this.skillTypeService.findAll();
		assertNotEquals(before, after);
	}
	
	@Test
	public void testDeleteBySkillTypeID() {
		Iterable<SkillType> before = this.skillTypeService.findAll();
		this.skillTypeService.deleteBySkillTypeID(1);
		Iterable<SkillType> after = this.skillTypeService.findAll();
		assertNotEquals(before, after);
	}

	public void testUpdate() {
		SkillType tstSkillType = new SkillType("Testing", "Testing Desc", true, true);
		SkillType returnedSkillType = skillTypeService.create(tstSkillType);
		
		returnedSkillType.setIsCore(false);
		skillTypeService.update(returnedSkillType);
		
		tstSkillType = skillTypeRepository.findOne(returnedSkillType.getSkillTypeId());
		assertFalse(tstSkillType.isCore());
		skillTypeRepository.delete(skillTypeRepository.findOne(returnedSkillType.getSkillTypeId()));
	}
}
