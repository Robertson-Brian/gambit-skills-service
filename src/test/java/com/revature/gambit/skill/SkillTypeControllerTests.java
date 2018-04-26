package com.revature.gambit.skill;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.controllers.SkillTypeController;
import com.revature.gambit.skill.services.SkillTypeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTypeControllerTests {

	private MockMvc mvc;

	@InjectMocks
	private SkillTypeController skillTypeController;

	@Mock
	private SkillTypeServiceImpl skillTypeServiceImpl;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(skillTypeController).build();
	}

    @Test
    public void getSkillTypeByNameDoesNotExist() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/skilltype/{name}", "jv")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
	
    @Test
	public void testDeleteSkillTypeFunction() throws Exception {
		when((skillTypeServiceImpl).deleteBySkillTypeName("JTA")).thenReturn(true);
	    mvc.perform(MockMvcRequestBuilders.delete("/skilltype/name/{name}", "JTA")
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isAccepted());
	}

	@Test
	public void getSkillType() throws Exception{

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		Iterable<SkillType> skills = Arrays.asList(skill1,skill2);

		when(skillTypeServiceImpl.findByAll()).thenReturn((List<SkillType>) skills);

		mvc.perform(MockMvcRequestBuilders.get("/skillType")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void getSkillTypeById() throws Exception{

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		Iterable<SkillType> skills = Arrays.asList(skill1,skill2);

		when(skillTypeServiceImpl.findBySkillTypeId(100)).thenReturn(((List<SkillType>) skills).get(0));
		when(skillTypeServiceImpl.findBySkillTypeId(101)).thenReturn(((List<SkillType>) skills).get(1));

		mvc.perform(MockMvcRequestBuilders.get("/skillType/{id}", 100)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/skillType/{id}", 101)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void testDeleteSkillTypeIdFunction() throws Exception {		
		SkillType skillType = skillTypeServiceImpl.findBySkillTypeId(101);
		when((skillTypeServiceImpl).findBySkillTypeId(101)).thenReturn(skillType);
		when((skillTypeServiceImpl).deleteBySkillTypeId(101)).thenReturn(true);
	    mvc.perform(MockMvcRequestBuilders.delete("/skilltype/{id}", 101)
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isAccepted());
	}
	


}
