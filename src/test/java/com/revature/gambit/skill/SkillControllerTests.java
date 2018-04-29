package com.revature.gambit.skill;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.controllers.SkillController;
import com.revature.gambit.skill.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillControllerTests {

	@Autowired
	private ObjectMapper mapper;
	
	private MockMvc mvc;

	@InjectMocks
	private SkillController skillController;

	@Mock
	private SkillService skillService;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(skillController).build();
	}

	@Test
	public void postSkill() throws Exception {
		Skill skill = new Skill(101, "Fortran", true);
		Gson gson = new Gson();
		String json = gson.toJson(skill);
		when(skillService.create(skill)).thenReturn(skill);

		mvc.perform(MockMvcRequestBuilders.post("/skill/")
				.contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}

	@Test
	public void testFindSkillByName() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Skill skill2 = new Skill(100, "Javas2", false);

		when(skillService.findBySkillName("Javas")).thenReturn(skill1);
		when(skillService.findBySkillName("Javas2")).thenReturn(skill2);

		mvc.perform(MockMvcRequestBuilders.get("/skill/name/{name}", "Javas")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/skill/name/{name}", "Javas2")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testFindSkillByID() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Skill skill2 = new Skill(100, "Javas2", false);
		
		when(skillService.findBySkillID(99)).thenReturn(skill1);
		when(skillService.findBySkillID(100)).thenReturn(skill2);
		
		mvc.perform(MockMvcRequestBuilders.get("/skill/{id}", 99)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		mvc.perform(MockMvcRequestBuilders.get("/skill/{id}", 100)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void testFindSkillByIDNotFound() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/skill/{id}", 100000)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}
	
	@Test
	public void testFindSkillByNameNotFound() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/skill/name/{name}", "Jeva")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}
	
	@Test
	public void putSkillByIdValid() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Gson gson = new Gson();
        String json = gson.toJson(skill1);
        
        when(skillService.saveSkill(skill1)).thenReturn(skill1);
        
        mvc.perform(MockMvcRequestBuilders.put("/skill/{id}", 99)
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
	}
	
	@Test
	public void putSkillByIdInvalid() throws Exception {
		Skill skill1 = new Skill(999, "Javas", true);
		
		Gson gson =  new Gson();
		String json = gson.toJson(skill1);

		mvc.perform(MockMvcRequestBuilders.put("/skill/{id}", 101)
				.contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testFindAllActive() throws Exception {
		Skill activeSkill = new Skill(1, "Active", true);
		List<Skill> expectedSkills = Arrays.asList(activeSkill);
		
		when(this.skillService.findAllActive()).thenReturn(expectedSkills);
		
		mvc.perform(MockMvcRequestBuilders.get("/skill/active"))
		.andExpect(status().isOk())
		.andExpect(content().json(mapper.writeValueAsString(expectedSkills)));
	}
	
	@Test
	public void testFindAllActiveNoContent() throws Exception {
		List<Skill> expectedSkills = new ArrayList<>();
		
		when(this.skillService.findAllActive()).thenReturn(expectedSkills);
		
		mvc.perform(MockMvcRequestBuilders.get("/skill/active"))
		.andExpect(status().isNoContent());
	}

}
