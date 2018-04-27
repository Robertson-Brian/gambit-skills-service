package com.revature.gambit.skill;

import com.google.gson.Gson;
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.services.SkillTypeServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.gambit.skill.controllers.SkillTypeController;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTypeControllerTests {

	private MockMvc mvc;

	@InjectMocks
	private SkillTypeController skillTypeController;

	@Mock
	private SkillTypeServiceImpl skillTypeService;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(skillTypeController).build();
	}

	@Test
	public void postCreate() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		Gson gson = new Gson();
		String json = gson.toJson(skill1);

		when(skillTypeService.create(skill1)).thenReturn(skill1);

		mvc.perform(MockMvcRequestBuilders.post("/skillType/")
				.contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}

	@Test
	public void testFindSkillTypeByNameNotFound() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/skilltype/{name}", "jv")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	public void testFindAll() throws Exception{
		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		Iterable<SkillType> skills = Arrays.asList(skill1,skill2);

		when(skillTypeService.findAll()).thenReturn(skills);

		mvc.perform(MockMvcRequestBuilders.get("/skillType")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void testFindSkillTypeByID() throws Exception{
		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		when(skillTypeService.findBySkillTypeID(100)).thenReturn(skill1);
		when(skillTypeService.findBySkillTypeID(101)).thenReturn(skill2);

		mvc.perform(MockMvcRequestBuilders.get("/skillType/{id}", 100)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/skillType/{id}", 101)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void testFindSkillTypeByName() throws Exception{
		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		when(skillTypeService.findBySkillTypeName("Java")).thenReturn(skill1);
		when(skillTypeService.findBySkillTypeName("Fortran")).thenReturn(skill2);

		mvc.perform(MockMvcRequestBuilders.get("/skillType/name/{name}", "Java")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/skillType/name/{name}", "Fortran")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

}
